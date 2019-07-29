package com.example.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final String Cadastrar = "com.example.helloworld.CadastrarActivity";
    private TextView dataField;
    private ImageView avatar;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int RESULT_LOAD_CADASTRAR = 2;
    private int activityInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
