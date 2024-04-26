package com.example.finalproject_couplepoints;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import model.Operation;
import model.OperationStatus;
import model.OperationType;
import model.User;

public class RedeemPoints extends AppCompatActivity implements View.OnClickListener, ChildEventListener {

    TextInputEditText edtEditClassDescription;
    Button btnGivePoints;
    int userId;
    DatabaseReference userDatabase;

    ArrayList<User> userList;
    User userData;
    private int lastId;

    private int operationCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_points);
        userId = getIntent().getIntExtra("userId", 0);
        initialize();
    }

    public void initialize(){

        //userDatabase = FirebaseDatabase.getInstance().getReference("User").child(String.valueOf(userId));
        userDatabase = FirebaseDatabase.getInstance().getReference("User");
        userList = new ArrayList<User>();
        userDatabase.addChildEventListener(this);
//        userDatabase.child("operationCounter").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    operationCounter = snapshot.getValue(Integer.class);
//                } else {
//                    operationCounter = 0;
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        edtEditClassDescription = findViewById(R.id.edtEditClassDescription);
        btnGivePoints = findViewById(R.id.btnGivePoints);
        btnGivePoints.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        redeemPoints();



    }

    private void redeemPoints(){
       try{
           String message = edtEditClassDescription.getText().toString().trim();

           // Create new operation
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
           String currentDate = sdf.format(new Date());
           Operation newOperation = new Operation(
                   operationCounter, // You may need to generate a unique ID for the operation
                   message,
                   currentDate,
                   100,
                   OperationType.REDEEM,
                   OperationStatus.REQUESTED
           );
           userData.addOperation(newOperation);
           String id = String.valueOf(userData.getId());
           userDatabase.child(id).setValue(userData);

           // Add new operation to the user's operation list
           //userDatabase.child("operation").push().setValue(newOperation);

           Toast.makeText(this, "Points redeemed successfully", Toast.LENGTH_SHORT).show();

       }catch (NumberFormatException e) {
           Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

       User user = snapshot.getValue(User.class);
        userList.add(user);
        for (User u : userList) {
            if (u.getId() == userId) {
                userData = u;
                operationCounter = userData.getOperation().size();
                break; // Break out of the loop since we found the user
            }
        }

    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}