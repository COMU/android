package com.example.loadimage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Loadimage extends Activity {
	TextView textTargetUri;
	 ImageView targetImage;
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button buttonLoadImage = (Button)findViewById(R.id.loadimage);
     
        textTargetUri = (TextView)findViewById(R.id.targeturi);
        targetImage = (ImageView)findViewById(R.id.targetimage);
        

		
        buttonLoadImage.setOnClickListener(new Button.OnClickListener(){
       
    @Override
    public void onClick(View arg0) {
     // TODO Auto-generated method stub
     Intent intent = new Intent(Intent.ACTION_PICK,
       android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
     startActivityForResult(intent, 0);
    }});
        
    }
    
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
   // TODO Auto-generated method stub
   super.onActivityResult(requestCode, resultCode, data);
   
   if (resultCode == RESULT_OK){
	   
    Uri targetUri = data.getData();
    textTargetUri.setText(targetUri.toString());
    Bitmap bitmap;
    try {
     
     bitmap=BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
     Bitmap bmap=Bitmap.createScaledBitmap(bitmap,40,50,true);
     targetImage.setImageBitmap(bmap);
    } catch (Exception e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
    }
   }
  
 // public void Load(){
      
  }  
    }
