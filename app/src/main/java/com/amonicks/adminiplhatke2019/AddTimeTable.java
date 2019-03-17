package com.amonicks.adminiplhatke2019;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddTimeTable extends AppCompatActivity {

    private DatabaseReference databaseReference;
    AddTimeTableBeans addTimeTableBeans = null;
    String teamLogoText1,teamLogoText2;
    TextView getDate;
    private DatePickerDialog.OnDateSetListener getDateSetListener;
    ProgressBar progress_bar;
    private Spinner getTeamName1,getTeamName2;
    private TextView getTeamLogoUrl1,getTeamLogoUrl2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time_table);


        progress_bar = findViewById(R.id.progress_bar);
        getDate = findViewById(R.id.getDate);

        getTeamName1 = findViewById(R.id.getTeamName1);
        getTeamName2 = findViewById(R.id.getTeamName2);

        getTeamLogoUrl1 = findViewById(R.id.getTeamLogoUrl1);
        getTeamLogoUrl2 = findViewById(R.id.getTeamLogoUrl2);

        progress_bar.setVisibility(View.VISIBLE);

        databaseReference = FirebaseDatabase.getInstance().getReference("TimeTable");

        getTeamNameListAndLogoUrl();

        getDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String dt_month,dt_day;
                if (month < 9)
                    dt_month = "0"+(month+1);
                else
                    dt_month = ""+(month+1);

                if (day < 10)
                    dt_day = "0"+day;
                else
                    dt_day = ""+day;

                getDate.setText(year+"-"+dt_month+"-"+dt_day);
            }
        };

        getTeamName1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String teamName = getTeamName1.getSelectedItem().toString().trim();
                final List<String> list = new ArrayList<>();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("TeamName");
                Query query = databaseReference.orderByChild("teamName").equalTo(teamName);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            AddTeamNameBeans addTeamNameBeans = ds.getValue(AddTeamNameBeans.class);
                            getTeamLogoUrl1.setText(addTeamNameBeans.getTeamLogo());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getTeamName2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String teamName = getTeamName2.getSelectedItem().toString().trim();
                final List<String> list = new ArrayList<>();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("TeamName");
                Query query = databaseReference.orderByChild("teamName").equalTo(teamName);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            AddTeamNameBeans addTeamNameBeans = ds.getValue(AddTeamNameBeans.class);
                            getTeamLogoUrl2.setText(addTeamNameBeans.getTeamLogo());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getTeamNameListAndLogoUrl() {

        final List<String> list = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("TeamName");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postdataSnapshot : dataSnapshot.getChildren()){
                    AddTeamNameBeans addTeamNameBeans = postdataSnapshot.getValue(AddTeamNameBeans.class);
                    list.add(addTeamNameBeans.getTeamName());
                }
                ArrayAdapter adapter = new ArrayAdapter(AddTimeTable.this,android.R.layout.simple_list_item_1,list);
                getTeamName1.setAdapter(adapter);
                getTeamName2.setAdapter(adapter);
                progress_bar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AddTimeTable.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progress_bar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void save_btn(View view){
        String getTeamNameText1 = getTeamName1.getSelectedItem().toString().trim();
        String getTeamNameText2 = getTeamName2.getSelectedItem().toString().trim();
        String getTeamLogo1 = getTeamLogoUrl1.getText().toString().trim();
        String getTeamLogo2 = getTeamLogoUrl2.getText().toString().trim();
        String getDateText = getDate.getText().toString().trim();
        if (getTeamNameText1.isEmpty()){
            Toast.makeText(AddTimeTable.this, "Please Enter Team 1 Name", Toast.LENGTH_SHORT).show();
        } else if (getTeamNameText2.isEmpty()){
            Toast.makeText(AddTimeTable.this, "Please Enter Team 2 Name", Toast.LENGTH_SHORT).show();
        } else if (getDateText.isEmpty()){
            Toast.makeText(AddTimeTable.this, "Please Select Date", Toast.LENGTH_SHORT).show();
        } else {
            progress_bar.setVisibility(View.VISIBLE);
            saveTeamNameAndLogo(getTeamNameText1,getTeamNameText2,getTeamLogo1,getTeamLogo2,getDateText);
            progress_bar.setVisibility(View.INVISIBLE);
        }
    }
    public void getDate(View view){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddTimeTable.this,android.R.style.Theme_Holo_Dialog_MinWidth,getDateSetListener,year,month,day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    private void saveTeamNameAndLogo(String getTeamNameText1,String getTeamNameText2,String getTeamLogo1,String getTeamLogo2,String getDateText){
        addTimeTableBeans = new AddTimeTableBeans(getTeamNameText1,getTeamLogo1,getTeamNameText2,getTeamLogo2,getDateText,"--");
        String id = databaseReference.push().getKey();
        databaseReference.child(id).setValue(addTimeTableBeans);
        Toast.makeText(AddTimeTable.this, "Data Saved", Toast.LENGTH_SHORT).show();
    }
}
