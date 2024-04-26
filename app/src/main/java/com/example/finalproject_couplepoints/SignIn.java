package com.example.finalproject_couplepoints;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.User;


public class SignIn extends AppCompatActivity implements View.OnClickListener, ValueEventListener, ChildEventListener {

    EditText edEmail, edPassword;
    Button btnLogIn, btnSignIn;
    DatabaseReference userDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        initialize();
    }

    public void initialize(){

        userDatabase = FirebaseDatabase.getInstance().getReference("User");


        edEmail = findViewById(R.id.edtEmail);
        edPassword = findViewById(R.id.edtPassword);
        btnLogIn = findViewById(R.id.btnLogin);
        btnSignIn = findViewById(R.id.btnSignin);

        btnLogIn.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.btnLogin)
            logIn(v);
        if (id == R.id.btnSignin)
            signIn(v);


    }

    private void signIn(View v) {

        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    private void logIn(View v) {

        String email = edEmail.getText().toString();
        userDatabase.orderByChild("email").equalTo(email).addChildEventListener(this);


    }



    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        if(snapshot.exists()){
            int userId = Integer.parseInt(snapshot.getKey());

            String password = snapshot.child("password").getValue().toString();
            String passwordInput = edPassword.getText().toString();

            if (password.equals(passwordInput)) {
                Intent intent = new Intent(SignIn.this, AfterSignIn.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            } else {
                Toast.makeText(SignIn.this, "Invalid email or password", Toast.LENGTH_LONG).show();
                edEmail.setText("");
                edPassword.setText("");
                edEmail.requestFocus();
            }
        }else{
            Toast.makeText(SignIn.this, "Invalid email or password", Toast.LENGTH_LONG).show();
            edEmail.setText("");
            edPassword.setText("");
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
    public void onDataChange(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
    }
}




