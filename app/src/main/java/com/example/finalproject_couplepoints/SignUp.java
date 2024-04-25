package com.example.finalproject_couplepoints;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Operation;
import model.OperationStatus;
import model.OperationType;
import model.User;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText edFullName, edEmail, edPassword, edConfirmPassword;
    Button btnSignUp, btnGoToLogIn;
    DatabaseReference userReference;

    private int lastId;
    private int lastOperationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initialize();
    }

    public void initialize(){
        userReference = FirebaseDatabase.getInstance().getReference("User");

        edFullName = findViewById(R.id.edtFullName);
        edEmail = findViewById(R.id.edtEmail);
        edPassword = findViewById(R.id.edtPassword);
        edConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignup);
        btnGoToLogIn = findViewById(R.id.btngoToLogin);

        btnSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.btnSignup)
            signUp(v);
        if (id == R.id.btngoToLogin)
            goTologIn(v);

    }

    private void goTologIn(View v) {

        Intent intent = new Intent(this,SignIn.class);
        startActivity(intent);

    }

    private void signUp(View v) {

        String fullName = edFullName.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        String password = edPassword.getText().toString().trim();
        String confirmPassword = edConfirmPassword.getText().toString().trim();


        if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }


        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            edPassword.setText("");
            edConfirmPassword.setText("");
            edPassword.requestFocus();
            return;
        }


        userReference.orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lastId = 101;
                lastOperationId = 1;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    lastId = Integer.parseInt(dataSnapshot.getKey());
                }

                int newId = lastId + 1;
                String defaultPhoto = getResources().getDrawable(R.drawable.profile).toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = dateFormat.format(new Date());

                ArrayList<Operation> operationsList = new ArrayList<Operation>();
                operationsList.add(new Operation(lastOperationId,"",currentDate,0, OperationType.DEFAULT, OperationStatus.DEFAULT));


                User newUser = new User(newId, email, fullName, password,defaultPhoto,0,0,operationsList);
                int newOperationId = lastOperationId + 1;

                userReference.child(String.valueOf(newId)).setValue(newUser, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error == null) {
                            Toast.makeText(SignUp.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            lastId = newId;
                            Intent intent = new Intent(SignUp.this, SignIn.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUp.this, "Registration failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}