package com.example.gomates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gomates.enums.JobSelector;
import com.example.gomates.globalvariables.GoMates;

public class JobActivity extends AppCompatActivity {

    private GoMates goMates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        goMates = ((GoMates)getApplicationContext());
    }

    public void funcMotorista(View view) {
        goMates.setJobSelected(JobSelector.Motorista);
        goIntent();
    }

    public void funcCarona(View view){
        goMates.setJobSelected(JobSelector.Carona);
        goIntent();
    }

    private void goIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
