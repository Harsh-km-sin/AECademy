package com.harshsingh.aecademy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpPage extends AppCompatActivity {
    EditText edt1, edt2, edt3, edt4;
    Button btn;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize UI elements
        edt1 = findViewById(R.id.sedt1);
        edt2 = findViewById(R.id.sedt2);
        edt3 = findViewById(R.id.sedt3);
        edt4 = findViewById(R.id.sedt4);
        btn = findViewById(R.id.signbtn);

        // Set click listener for "Back to Login" TextView
        TextView textSignUp = findViewById(R.id.yesAcc);
        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpPage.this, LoginPage.class);
                startActivity(intent);
            }
        });

        // Set click listener for the register button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserToFirestore();
            }
        });
    }

    private void addUserToFirestore() {
        String name = edt1.getText().toString();
        String contact = edt2.getText().toString();
        String username = edt3.getText().toString();
        String password = edt4.getText().toString();

        if (name.isEmpty() || contact.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new user with provided details
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("contact", contact);
        user.put("username", username);
        user.put("password", password);

        // Add a new document with a generated ID
        db.collection("Users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(SignUpPage.this, "Data added to Firestore", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpPage.this, "Error adding document", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
