package com.example.gomates;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = FirebaseAuth.getInstance().getCurrentUser();
        usuario = (TextView) findViewById(R.id.usuario);

        if(user != null) {

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
