package com.example.finalproject_couplepoints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.Operation;
import model.User;

public class AfterSignIn extends AppCompatActivity implements View.OnClickListener {

    Button btnRedeemPoints, btnHistory;

    TextView userPoints;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sigin);


        userId = getIntent().getIntExtra("userId", 0);
        initialize();
    }

    public void initialize(){

        btnRedeemPoints = findViewById(R.id.btnRedeemPoints);
        btnHistory = findViewById(R.id.btnHistory);
        btnRedeemPoints.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        DatabaseReference userDatabase = FirebaseDatabase.getInstance().getReference("User").child(String.valueOf(userId));


        userDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    User user = snapshot.getValue(User.class);
                    int totalPoints = 0;
                    if (user.getOperation() != null) {
                        for (Operation operation : user.getOperation()) {
                            totalPoints += operation.getPoints();
                        }
                    }
                    userPoints = findViewById(R.id.user1Points);

                    userPoints.setText( String.valueOf(totalPoints));
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.btnRedeemPoints){

            Intent intent = new Intent(this,RedeemPoints.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        }
        if (id == R.id.btnHistory){
            Intent intent = new Intent(this, HistoryActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        }

    }
}