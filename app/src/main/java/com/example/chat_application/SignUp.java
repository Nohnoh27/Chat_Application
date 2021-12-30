package com.example.chat_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private EditText edtNom ;
    private EditText edtEmail ;
    private EditText edtPassword ;
    private Button btnSignUp ;
    private FirebaseAuth mAuth ;
    private DatabaseReference mDbRef ;
    private Object String;
    Object nom;
    Object email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        mAuth =FirebaseAuth.getInstance();
        edtNom = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnSignUp = findViewById(R.id.btn_sign);

        Object nom;
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                  //  addUserToDataBase(nom,email,mAuth.getCurrentUser().getUid());
                                    private void addUserToDataBase(String nom, String email, mAuth.getCurrentUser().getUid()){

                                        mDbRef = FirebaseDatabase.getInstance().getReference();
                                        mDbRef.child("user").child(mAuth.getUid()).setValue(new User(nom,email,mAuth.getUid()));
                                    }
                                    Intent i = new Intent(getApplicationContext(), SignUp.class);
                                    finish();
                                    startActivity(i);

                                } else {
                                    Toast.makeText(SignUp.this,
                                            "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

     /*   private void SignUp(String email, String password){

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                addUserToDataBase(nom,email,mAuth.getCurrentUser().getUid());
                                startActivity(new Intent(getApplicationContext(), SignUp.class));
                            } else {
                                Toast.makeText(SignUp.this,
                                        "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }); */


         /*   private void addUserToDataBase(String nom, String email, mAuth.getUid()){

                mDbRef = FirebaseDatabase.getInstance().getReference();
                mDbRef.child("user").child(mAuth.getUid()).setValue(new User(nom,email,mAuth.getUid()));
            } */
        }
    }
