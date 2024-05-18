package com.example.messageapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    EditText SignUpemail,SignUppassword;
    Button signUpBtn;
    TextView signInText;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SignUpemail=findViewById(R.id.emailsignup);
        SignUppassword=findViewById(R.id.passwordsignup);
        signUpBtn=findViewById(R.id.signupbutton);
        signInText=findViewById(R.id.signintext);
        auth=FirebaseAuth.getInstance();


        signUpBtn.setOnClickListener(v -> {
            String user=SignUpemail.getText().toString().trim();
            String pass=SignUppassword.getText().toString();

            if(TextUtils.isEmpty(user)){
                SignUpemail.setError("Email dont exist!");
            }
            if(TextUtils.isEmpty(pass)){
                SignUppassword.setError("Password dont exist!");
            }
            else{
                auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "SignUp Succesful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(SignUpActivity.this, "SignUp Failed!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


        });

        signInText.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            finish();
        });

}}