package com.example.dimensions;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageDimensions extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView image = (ImageView) findViewById(R.id.test2);
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 45, 45, true);
        image.setImageBitmap(bMapScaled);
        
    }

}
