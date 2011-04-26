package com.example.foto;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class fotograf extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView image = (ImageView) findViewById(R.id.test2);
        Bitmap bMap = BitmapFactory.decodeFile("/sdcard/DCIM/Camera/Yeni.jpg");
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 100,100, true);
        image.setImageBitmap(bMapScaled);
    }
}                                                            