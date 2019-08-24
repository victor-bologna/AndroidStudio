package com.example.gomates;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public static final String Cadastrar = "com.example.helloworld.CadastrarActivity";
    private TextView dataField;
    private TextView tipoUsuario; // Ainda  tem que fazer
    private TextView usuario;
    private ImageView avatar;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int RESULT_LOAD_CADASTRAR = 2;
    private int activityInfo;
    private FirebaseUser user;
    private Button signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        avatar = (ImageView) findViewById(R.id.avatar);
        user = FirebaseAuth.getInstance().getCurrentUser();
        usuario = (TextView) findViewById(R.id.usuario);
        signOut = (Button) findViewById(R.id.singout);
        final Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance().signOut(MainActivity.this).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        startActivity(intent2);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT);
                        startActivity(intent2);

                    }
                });
            }
        });

        if(user != null) {

            getPicture(user);

            if(user.getPhotoUrl() != null)
                avatar.setImageURI(user.getPhotoUrl());
            usuario.setText(user.getDisplayName());

        }

        avatar = (ImageView) findViewById(R.id.avatar);

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                activityInfo = RESULT_LOAD_IMAGE;
            }
        });
    }

    private void getPicture(FirebaseUser user) {

        if(user.getPhotoUrl() != null)
            Picasso.get().load(user.getPhotoUrl()).into(avatar);
        else
            Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(avatar);

    }


    public void sendMessage(View view) {
        Intent intent = new Intent(this, CadastrarActivity.class);
        startActivityForResult(intent, 1);
        activityInfo = RESULT_LOAD_CADASTRAR;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && activityInfo == RESULT_LOAD_CADASTRAR) {
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
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null && activityInfo == RESULT_LOAD_IMAGE) {
            Uri selectedUri = data.getData();
            avatar.setImageURI(selectedUri);
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setPhotoUri(data.getData())
                    .build();
            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d("Info", "User profile updated.");
                    }
                }
            });
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
