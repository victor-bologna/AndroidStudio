package com.example.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText Usuario;
    private EditText Senha;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Usuario = (EditText) findViewById(R.id.usuario);
        Senha = (EditText) findViewById(R.id.senha);
        Login = (Button) findViewById(R.id.login);

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                validar(Usuario.getText().toString(),Senha.getText().toString());
            }
        });
    }

    private void validar(String user, String password) {
        if(user.equals("Admin") && password.equals("123")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Usuario.setError("Usuário ou senha errado(s)!");
        }
    }
}
