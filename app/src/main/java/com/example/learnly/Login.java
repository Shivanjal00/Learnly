package com.example.learnly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;
    String emailpattern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    TextView logsignup;
    EditText logemail,logpassword;
    Button logbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        logbutton = findViewById(R.id.logbutton);
        logemail = findViewById(R.id.logemail);
        logpassword = findViewById(R.id.logpassword);
        logsignup = findViewById(R.id.logsignup);

        logsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, registration.class);
                startActivity(intent);
                finish();
            }
        });

        logbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = logemail.getText().toString();
                String pass = logpassword.getText().toString();

                if (TextUtils.isEmpty(Email)){
                    Toast.makeText(Login.this, "Enter a Email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else if (!Email.matches(emailpattern)) {
                    logemail.setError("Enter a valid Email");
                } else if (logpassword.length()<5) {
                    logpassword.setError("Password must be longer than five characters");
                }else {
                    auth.signInWithEmailAndPassword(Email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                try {
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }catch (Exception e){
                                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });
    }
}