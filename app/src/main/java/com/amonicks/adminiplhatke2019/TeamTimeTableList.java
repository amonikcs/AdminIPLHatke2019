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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeamTimeTableList extends AppCompatActivity {

    RecyclerView recycler_view;
    private TeamTimeTableListAdapter teamTimeTableListAdapter;
    private DatabaseReference databaseReference;
    private List<AddTimeTableBeans> list;
    private ProgressBar progress_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_time_table_list);

        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        progress_bar = findViewById(R.id.progress_bar);

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("TimeTable");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot postdataSnapshot : dataSnapshot.getChildren()){
                    AddTimeTableBeans addTimeTableBeans = postdataSnapshot.getValue(AddTimeTableBeans.class);
                    list.add(addTimeTableBeans);
                }
                teamTimeTableListAdapter = new TeamTimeTableListAdapter(TeamTimeTableList.this,list);
                recycler_view.setAdapter(teamTimeTableListAdapter);
                progress_bar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(TeamTimeTableList.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progress_bar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
