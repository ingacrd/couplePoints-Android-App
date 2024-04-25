package com.example.finalproject_couplepoints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBegin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_intro);
        initialize();
    }

    public void initialize(){

        btnBegin = findViewById(R.id.buttonBegin);
        btnBegin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.buttonBegin)
            goTologIn(v);
    }

    private void goTologIn(View v) {

        Intent intent = new Intent(this, SignIn.class);

        startActivity(intent);
    }
}