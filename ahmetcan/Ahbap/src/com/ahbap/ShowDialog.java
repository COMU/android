package com.ahbap;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class ShowDialog {
	public static void showMessage(Context context, String message){
		
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setMessage(message);
		alert.setNeutralButton("Ok", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				//TODO
			}
		});
		alert.show();
	}
	
	
}
