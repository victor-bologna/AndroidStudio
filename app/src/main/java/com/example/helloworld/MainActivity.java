package com.example.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.helloworld.CadastrarActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pega a intent de outra activity
        Intent it = getIntent();

        //Recuperei a string da outra activity
        String data = it.getStringExtra("data");

        if(data != "") {
            recoverData(data);
        }
    }

    public void recoverData(String data) {
        TextView dataField = (TextView) findViewById(R.id.textView13);
        dataField.setText(data);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, CadastrarActivity.class);
        startActivity(intent);
    }
}
