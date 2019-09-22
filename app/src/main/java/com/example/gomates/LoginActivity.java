package com.example.gomates;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 200;
    private FirebaseAuth mAuth;

    List<AuthUI.IdpConfig> providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Init Provider
        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(), // Email
                new AuthUI.IdpConfig.PhoneBuilder().build(), // Phone
                new AuthUI.IdpConfig.GoogleBuilder().build() // Google
        );

        showSignInOptions();

    }

    private void showSignInOptions() {

        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.MyTheme)
                .setIsSmartLockEnabled(true)
                .build(), MY_REQUEST_CODE
        );

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MY_REQUEST_CODE) {

            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this,""+user.getEmail(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, JobActivity.class);
                startActivity(intent);

            } else {

                Toast.makeText(this, ""+response.getError().getMessage(),Toast.LENGTH_SHORT).show();

            }
        }
    }
}
