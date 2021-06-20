package com.example.m1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.net.URL;

public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    public ImageLoader(ImageView imageView){
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap bitmap = null;
        try{
            URL imageUrl = new URL(url);
            bitmap = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
        }catch(Exception e){
            Log.e("Error", e.toString());
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
