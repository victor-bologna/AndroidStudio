package com.example.helloworld;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CadastrarActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.helloworld.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        final EditText dataField = (EditText) findViewById(R.id.data);
        String data = dataField.getText().toString();

        intent.putExtra(EXTRA_MESSAGE, data);
        startActivity(intent);
    }
}
