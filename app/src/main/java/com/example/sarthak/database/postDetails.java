package com.example.sarthak.database;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class postDetails extends AppCompatActivity {
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    List<Upload> Uploads;
    int position;
    public String itemName;

    ImageView imgView;
    TextView route,description,date;
    Button claim;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        Uploads = new ArrayList<>();
        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        imgView=findViewById(R.id.image_view);
        route=findViewById(R.id.route);
        description=findViewById(R.id.details);
        date=findViewById(R.id.date);
        claim=findViewById(R.id.claim);



        Bundle bundle=getIntent().getExtras();
        position=bundle.getInt("position");

        claim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(postDetails.this,otp.class);
                i3.putExtra("prodName",itemName);
                startActivity(i3);

            }
        });


        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    upload.setKey(postSnapshot.getKey());
                    Uploads.add(upload);
                }
                Upload uploadCurrent = Uploads.get(position);
                route.setText(uploadCurrent.getRoute());
                date.setText(uploadCurrent.getDate());

                description.setText(uploadCurrent.getName());
                itemName=uploadCurrent.getName();

                Picasso.get().load(uploadCurrent.getImageUrl()).into(imgView);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

