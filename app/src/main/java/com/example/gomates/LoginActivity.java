package com.example.gomates;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private TextView checkEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        checkEmail = findViewById(R.id.checkEmail);

        Button login = findViewById(R.id.login);
        Button cadastrar = findViewById(R.id.cadastrar);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                signIn(email.getText().toString(), senha.getText().toString());

            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(createAccount(email.getText().toString(), senha.getText().toString())) {

                    checkEmail.setText("Para ativar sua conta, clique no link enviado na sua conta de email!");

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null) {

//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);

        }
    }

    public boolean createAccount(String email, String password) {
        final boolean[] ret = new boolean[1];
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    ret[0] = true;
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(LoginActivity.this, task. getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    ret[0] = false;
                }

                // ...
            }
        });
        return ret[0];
    }

    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(LoginActivity.this, "Login incorretos!", Toast.LENGTH_SHORT).show();
                }

                // ...
            }
        });
    }
}
