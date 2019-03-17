package com.amonicks.adminiplhatke2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowMatchResult extends AppCompatActivity {

    RecyclerView recycler_view;
    private ShowMatchResultListAdapter showMatchResultListAdapter;
    private DatabaseReference databaseReference;
    private List<AddMatchResultsBeans> list;
    private ProgressBar progress_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_match_result);

        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        progress_bar = findViewById(R.id.progress_bar);

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String datetime = sdf.format(dt);
        databaseReference = FirebaseDatabase.getInstance().getReference("MatchResult");
        Query query = databaseReference.orderByChild("dateTime").endAt(datetime);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot postdataSnapshot : dataSnapshot.getChildren()){
                    AddMatchResultsBeans addMatchResultsBeans = postdataSnapshot.getValue(AddMatchResultsBeans.class);
                    list.add(addMatchResultsBeans);
                }
                showMatchResultListAdapter = new ShowMatchResultListAdapter(ShowMatchResult.this,list);
                recycler_view.setAdapter(showMatchResultListAdapter);
                progress_bar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ShowMatchResult.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progress_bar.setVisibility(View.INVISIBLE);
            }
        });

    }
}
