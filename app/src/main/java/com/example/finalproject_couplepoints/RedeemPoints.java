package com.example.finalproject_couplepoints;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import model.Operation;
import model.OperationStatus;
import model.OperationType;

public class RedeemPoints extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText edtEditClassDescription;
    Button btnGivePoints;
    DatabaseReference operationDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_points);
        initialize();
    }

    public void initialize(){

        operationDatabase = FirebaseDatabase.getInstance().getReference().child("operation");
        edtEditClassDescription = findViewById(R.id.edtConfirmPassword);
        btnGivePoints = findViewById(R.id.btnGivePoints);
        btnGivePoints.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String message = edtEditClassDescription.getText().toString().trim();

        if(!message.isEmpty()){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String currentDate = sdf.format(new Date());
            Operation operation = new Operation(
                    0,
                    message,
                    currentDate,
                    100,
                    OperationType.REDEEM,
                    OperationStatus.REQUESTED
            );

            //Saving object in the database
            operationDatabase.push().setValue(operation);

            Toast.makeText(this,"Request sent",Toast.LENGTH_SHORT).show();
            edtEditClassDescription.setText("");
        }else{
            Toast.makeText(this, "Please type a message", Toast.LENGTH_SHORT).show();
        }

    }
}