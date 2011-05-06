package com.example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ApplicationService {
	private WebService service;
	private Context context;
	public ApplicationService(Context context){
		this.context=context;
		service=new WebService(this.context);
		
	}
	public boolean emailControl(String email){
		String sonuc = service.emailControl(email);
		if(sonuc.equals("\"dogru\"")){
			return true;
		}else{
			return false;
		}
		
	}
	public boolean addUser(Kullanici user){
		String sonuc = service.addUser(user);
		if(sonuc.equals("Created")){
			return true; 
		}
		return false;
	}
	public boolean login(String email,String parola){
		String sonuc = service.getUser(email, parola);
		if(sonuc.equals("-1")){
			return false;
		}
		return true;
	}
}
