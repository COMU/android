package com.example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ApplicationService {
	private Kullanici user;
	private WebService service;
	private Context context;
	public ApplicationService(Context context,Kullanici kullanici){
		service=new WebService();
		this.context=context;
		user=kullanici;
	}
	public boolean save(){
		AlertDialog.Builder alertbox= new AlertDialog.Builder(context);
		String sonuc = service.addUser(user);
		if(sonuc.equals("Created")){
			alertbox.setMessage(sonuc);
			alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			alertbox.show();
			return true; 
		}
		alertbox.setMessage(sonuc);
		alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		alertbox.show();
		return false;
	}
}
