package com.example.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final String Cadastrar = "com.example.helloworld.CadastrarActivity";
    private TextView dataField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {

        Intent intent = new Intent(this, CadastrarActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                setData("data",R.id.textView13,data);
                setData("motorista",R.id.textView11,data);
                setData("placa",R.id.textView21,data);
                setData("carro",R.id.textView10,data);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    public void removerCampos(View view) {
        TextView txt13 = (TextView) findViewById(R.id.textView13);
        txt13.setText("Não definido.");
        TextView txt11 = (TextView) findViewById(R.id.textView11);
        txt11.setText("Não definido.");
        TextView txt21 = (TextView) findViewById(R.id.textView21);
        txt21.setText("Não definido.");
        TextView txt10 = (TextView) findViewById(R.id.textView10);
        txt10.setText("Não definido.");
    }

    public void setData(String name, int id, Intent data) {
        String txt = data.getStringExtra(name);
        dataField = findViewById(id);
        dataField.setText(txt);
    }
}
