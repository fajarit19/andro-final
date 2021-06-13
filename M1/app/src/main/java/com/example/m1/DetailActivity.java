package com.example.m1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {

    TextView textview_placename, textview_address, textview_managerunit, textview_applyunit,
             textview_contactor, textview_officephone, textview_fax, textview_email, textview_register,
             textview_imageurl;
    ImageView imageurl_imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Item item = (Item)getIntent().getSerializableExtra("item");

        textview_placename = findViewById(R.id.textView_placename);
        textview_placename.setText(item.placeName);

        textview_address = findViewById(R.id.textView_addr);
        textview_address.setText(item.address);
        textview_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=" + item.address));
                startActivity(intent);
            }
        });

        textview_managerunit = findViewById(R.id.textView_managerunit);
        textview_managerunit.setText(item.managerUnit);

        textview_applyunit = findViewById(R.id.textView_applyunit);
        textview_applyunit.setText(item.applyUnit);

        textview_contactor = findViewById(R.id.textView_contactor);
        textview_contactor.setText(item.contactor);

        textview_officephone = findViewById(R.id.textView_officephone);
        textview_officephone.setText(item.officePhone);
        textview_officephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + item.officePhone));
                    startActivity(intent);
                }else{
                    if(Build.VERSION.SDK_INT>=23){
                        ActivityCompat.requestPermissions(DetailActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
                    }

                }

            }
        });

        textview_fax = findViewById(R.id.textView_fax);
        textview_fax.setText(item.fax);

        textview_email = findViewById(R.id.textView_email);
        textview_email.setText(item.email);

        textview_register = findViewById(R.id.textView_register);
        textview_register.setText(item.register);

        textview_imageurl = findViewById(R.id.textView_image);
        textview_imageurl.setText(item.imageUrl);
        imageurl_imageview = findViewById(R.id.imageView_image);
        try{

        }catch (Exception e){
            Log.e("IMAGE_ERROR", "Error:",e);
        }
    }
}