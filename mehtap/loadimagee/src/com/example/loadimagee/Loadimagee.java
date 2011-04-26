package com.example.loadimagee;


import java.io.FileNotFoundException;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Loadimagee extends Activity {
    private TextView textFile;
	private TextView textFolder;
	private TextView textFileName;
	private TextView textFileName_WithoutExt;
	private TextView textFileName_Ext;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button buttonLoadImage = (Button)findViewById(R.id.loadimage);
        textFile = (TextView)findViewById(R.id.textfile);
        textFolder = (TextView)findViewById(R.id.textfolder);
        textFileName = (TextView)findViewById(R.id.textfilename);
      
        textFileName_WithoutExt = (TextView)findViewById(R.id.textfilename_withoutext);
        textFileName_Ext = (TextView)findViewById(R.id.textfilename_ext);
      
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
        	   String FilePath = data.getData().getPath();
        	   String FileName = data.getData().getLastPathSegment();
        	   int lastPos = FilePath.length() - FileName.length();
        	   String Folder = FilePath.substring(0, lastPos);
        	  
        	   textFile.setText("Full Path: \n" + FilePath + "\n");
        	   textFolder.setText("Folder: \n" + Folder + "\n");
        	   textFileName.setText("File Name: \n" + FileName + "\n");
        	   Filename thisFile = new Filename(FileName);
        	   textFileName_WithoutExt.setText("Filename without Ext: " + thisFile.getFilename_Without_Ext());
        	         textFileName_Ext.setText("Ext: " + thisFile.getExt());
        	  
        	  }
        	  }
        	
          
        	 }
        	
    
