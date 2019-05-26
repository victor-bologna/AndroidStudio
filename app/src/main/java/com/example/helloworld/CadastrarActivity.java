package com.example.helloworld;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.helloworld.R.id.data;
import static com.example.helloworld.R.id.datavis;

public class CadastrarActivity extends AppCompatActivity {

    public static final String MainActivity = "com.example.helloworld.MainActivity";
    private static final String TAG = "CadastrarActivity";

    private TextView errorText;
    private TextView mDisplayDate;
    private ImageButton mSetDate;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        mDisplayDate = (TextView) findViewById(R.id.datavis);
        mSetDate = (ImageButton) findViewById(R.id.data);
        errorText = (TextView) findViewById(R.id.errorText);

        mSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CadastrarActivity.this,
                        android.R.style.Theme,
                        mOnDateSetListener,
                        day,month,year);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mDisplayDate.setText(date);
                if(errorText.getText().length() > 0) {
                    errorText.setText("");
                }
            }
        };

        Spinner dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"Milena Suemi Uehara", "Felipe Wagner Caleme", "Everton Souza", "Felipe Lugarinho"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent();
        final TextView dataField = (TextView) findViewById(R.id.datavis);

        if(dataField.length()==0) {

            errorText.setText("Sem nenhuma data preenchida.");
        } else {
            final Spinner spinnerField = (Spinner) findViewById(R.id.spinner);

            if(spinnerField.getSelectedItemId() == 0) {
                intent.putExtra("carro", "Chevrolet Corsa");
                intent.putExtra("placa", "XPO-1234");
            } else if(spinnerField.getSelectedItemId() == 1) {
                intent.putExtra("carro", "VW Fusca");
                intent.putExtra("placa", "ASV-3142");
            } else if(spinnerField.getSelectedItemId() == 2) {
                intent.putExtra("carro", "Ford Ka");
                intent.putExtra("placa", "ABS-2341");
            } else if(spinnerField.getSelectedItemId() == 3) {
                intent.putExtra("carro", "Nissan March");
                intent.putExtra("placa", "KTR-3652");
            }

            intent.putExtra("data", dataField.getText().toString());
            intent.putExtra("motorista", spinnerField.getSelectedItem().toString());

            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    public void cancel(View view) {
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED,intent);
        finish();
    }
}
