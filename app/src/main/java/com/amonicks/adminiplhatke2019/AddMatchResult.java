package com.amonicks.adminiplhatke2019;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddMatchResult extends AppCompatActivity {
    private DatabaseReference databaseReference;
    AddMatchResultsBeans addMatchResultsBeans = null;
    TextView getDate;
    ProgressBar progress_bar;
    private Spinner getTeamName1,getTeamName2;
    private TextView getTeamLogoUrl1,getTeamLogoUrl2;
    EditText team1Score,team1overs,team2Score,team2overs,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match_result);

        progress_bar = findViewById(R.id.progress_bar);

        getTeamName1 = findViewById(R.id.getTeamName1);
        getTeamName2 = findViewById(R.id.getTeamName2);

        getTeamLogoUrl1 = findViewById(R.id.getTeamLogoUrl1);
        getTeamLogoUrl2 = findViewById(R.id.getTeamLogoUrl2);

        team1Score = findViewById(R.id.team1Score);
        team2Score = findViewById(R.id.team2Score);
        team1overs = findViewById(R.id.team1overs);
        team2overs = findViewById(R.id.team2overs);
        result = findViewById(R.id.result);

        progress_bar.setVisibility(View.VISIBLE);

        databaseReference = FirebaseDatabase.getInstance().getReference("MatchResult");

        getTeamNameListAndLogoUrl();

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
                ArrayAdapter adapter = new ArrayAdapter(AddMatchResult.this,android.R.layout.simple_list_item_1,list);
                getTeamName1.setAdapter(adapter);
                getTeamName2.setAdapter(adapter);
                progress_bar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AddMatchResult.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progress_bar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void save_btn(View view){
        String getTeamNameText1 = getTeamName1.getSelectedItem().toString().trim();
        String getTeamNameText2 = getTeamName2.getSelectedItem().toString().trim();
        String getTeamLogo1 = getTeamLogoUrl1.getText().toString().trim();
        String getTeamLogo2 = getTeamLogoUrl2.getText().toString().trim();
        String team1ScoreText = team1Score.getText().toString().trim();
        String team2ScoreText = team2Score.getText().toString().trim();
        String team1oversText = team1overs.getText().toString().trim();
        String team2oversText = team2overs.getText().toString().trim();
        String resultText = result.getText().toString().trim();

        if (getTeamNameText1.equalsIgnoreCase(getTeamNameText2)){
            Toast.makeText(AddMatchResult.this, "Team Name should be different", Toast.LENGTH_SHORT).show();
        } else if (getTeamNameText1.isEmpty()){
            Toast.makeText(AddMatchResult.this, "Please Enter Team 1 Name", Toast.LENGTH_SHORT).show();
        } else if (getTeamNameText2.isEmpty()){
            Toast.makeText(AddMatchResult.this, "Please Enter Team 2 Name", Toast.LENGTH_SHORT).show();
        } else if (team1ScoreText.isEmpty()){
            Toast.makeText(AddMatchResult.this, "Please Enter Team 1 score", Toast.LENGTH_SHORT).show();
        } else if (team2ScoreText.isEmpty()){
            Toast.makeText(AddMatchResult.this, "Please Enter Team 1 score", Toast.LENGTH_SHORT).show();
        } else if (team1oversText.isEmpty()){
            Toast.makeText(AddMatchResult.this, "Please Enter Team 2 overs", Toast.LENGTH_SHORT).show();
        } else if (team2oversText.isEmpty()){
            Toast.makeText(AddMatchResult.this, "Please Enter Team 2 overs", Toast.LENGTH_SHORT).show();
        } else if (resultText.isEmpty()){
            Toast.makeText(AddMatchResult.this, "Please Enter Result", Toast.LENGTH_SHORT).show();
        }  else {
            progress_bar.setVisibility(View.VISIBLE);
            Date dt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String getDateText = sdf.format(dt);
            saveTeamNameAndLogo(getTeamNameText1,getTeamNameText2,getTeamLogo1,getTeamLogo2,team1ScoreText,team2ScoreText,team1oversText,team2oversText,getDateText,resultText);
            progress_bar.setVisibility(View.INVISIBLE);
        }
    }

    private void saveTeamNameAndLogo(String getTeamNameText1,String getTeamNameText2,String getTeamLogo1,String getTeamLogo2,String team1ScoreText,String team2ScoreText,String team1oversText,String team2oversText,String getDateText,String resultText){
        addMatchResultsBeans = new AddMatchResultsBeans(getTeamNameText1,getTeamLogo1,getTeamNameText2,getTeamLogo2,team1ScoreText,team2ScoreText,team1oversText,team2oversText,getDateText,resultText);
        String id = databaseReference.push().getKey();
        databaseReference.child(id).setValue(addMatchResultsBeans);
        Toast.makeText(AddMatchResult.this, "Data Saved", Toast.LENGTH_SHORT).show();
    }
}
