package com.example.mycameraapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

     static final int  REQ_IMAGE_CAP = 1;
     ImageView myImageView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mybutton = (Button)findViewById(R.id.ID_myButton);
        myImageView = (ImageView) findViewById(R.id.ID_myImageView);

        if(!hasCamera()){
            mybutton.setEnabled(false);
        }

    }

    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);

    }

    public  void LaunchCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQ_IMAGE_CAP);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_IMAGE_CAP && resultCode == RESULT_OK) {

            Bundle extra = data.getExtras();
            Bitmap photo = (Bitmap) extra.get("data");
            myImageView.setImageBitmap(photo);
        }
    }
}