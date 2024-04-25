package com.example.finalproject_couplepoints;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Operation;
import model.OperationAdapter;
import model.User;

public class HistoryActivity extends AppCompatActivity implements ChildEventListener {

    ListView lvOperation;
    //TextView tvTitle;
    ArrayList<User> userList;
    ArrayList<Operation> operationList;
    //ArrayAdapter<User> userAdapter;

    OperationAdapter operationAdapter;
    DatabaseReference userDatabase;

    //ActivityResultLauncher actResLauncher;

    int currentUserId = 102; //    TODO: currentUserId comes from login


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initialize();
    }

    private void initialize() {
        //tvTitle = findViewById(R.id.tvTitle);

        userDatabase = FirebaseDatabase.getInstance().getReference("User");

        lvOperation = findViewById(R.id.lvOperation);

        operationList = new ArrayList<Operation>();
        userList = new ArrayList<User>();

        userDatabase.addChildEventListener(this);
        //userAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1,
        //userList);
        operationAdapter = new OperationAdapter(this, operationList);


        lvOperation.setAdapter(operationAdapter);

    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        User user = snapshot.getValue(User.class);
        userList.add(user);
        for (User u : userList) {
            if (u.getId() == currentUserId) {
                operationList.clear();
                operationList.addAll(u.getOperation());
                break; // Break out of the loop since we found the user
            }
        }
        operationAdapter.notifyDataSetChanged();

        // Logging all operations in operationList
        if (operationList != null) {
            for (Operation operation : operationList) {
                Log.d("HistoryActivity", "Operation: " + operation.toString());
                // You can customize the logging message as per your requirement
            }
        } else {
            Log.d("HistoryActivity", "No operations found for current user");
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