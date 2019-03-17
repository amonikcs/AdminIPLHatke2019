package com.amonicks.adminiplhatke2019;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddTeamPlayer extends AppCompatActivity {

    private static int PICK_IMAAGE_REQUEST = 1;
    EditText playerName,teamName;
    ImageView teamLogo;
    private Uri imageUri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team_player);

        playerName = findViewById(R.id.playerName);
        teamName = findViewById(R.id.teamName);
        teamLogo = findViewById(R.id.teamLogo);

        storageReference = FirebaseStorage.getInstance().getReference("TeamPlayerDetails");
        databaseReference = FirebaseDatabase.getInstance().getReference("TeamPlayerDetails");
    }

    public void uploadImage(View view){
        ReUsableMethod.openFileChooser(AddTeamPlayer.this);
    }

    public void save_btn(View view){
        saveTeamNameAndLogo();
    }

    private void saveTeamNameAndLogo(){
        if (imageUri != null){
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()+"."+ReUsableMethod.getFileExtension(imageUri,AddTeamPlayer.this));
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String playerNameText = playerName.getText().toString().trim();
                            String teamNameText = teamName.getText().toString().trim();
                            if (teamNameText.isEmpty()){
                                Toast.makeText(AddTeamPlayer.this, "Please Enter Player Name", Toast.LENGTH_SHORT).show();
                            } else if (teamNameText.isEmpty()){
                                Toast.makeText(AddTeamPlayer.this, "Please Enter Team Name", Toast.LENGTH_SHORT).show();
                            } else {
                                AddPlayerNameBeans addPlayerNameBeans = new AddPlayerNameBeans(playerNameText,teamNameText,taskSnapshot.getDownloadUrl().toString(),"N");
                                String id = databaseReference.push().getKey();
                                databaseReference.child(id).setValue(addPlayerNameBeans);
                                Toast.makeText(AddTeamPlayer.this, "Data Saved", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddTeamPlayer.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//
//                        }
//                    });
        } else {
            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            Picasso.with(this).load(imageUri).into(teamLogo);
        }
    }
}
