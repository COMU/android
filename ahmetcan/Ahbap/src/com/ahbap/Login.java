package com.ahbap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.ahbap.parser.JsonParser;
import com.ahbap.webservice.UserHandler;

public class Login extends Activity {
	private EditText emailText;
	private EditText passwordText;
	private Button loginButton;
	private Button signupButton;
	private UserHandler userhandler;
	private ProgressDialog dialog;
	private JsonParser parser;

	public void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		setContentView(R.layout.login);

		userhandler = new UserHandler(Login.this);
		parser = new JsonParser();

		emailText = (EditText) findViewById(R.id.email);
		passwordText = (EditText) findViewById(R.id.password);

		signupButton = (Button) findViewById(R.id.signupButton);
		signupButton.setOnClickListener(signupButtonListener);
		loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(loginButtonListener);

	}

	private OnClickListener signupButtonListener = new OnClickListener() {

		public void onClick(View v) {
			Intent intent = new Intent(Login.this, SignUp.class);
			startActivity(intent);
		}
	};

	private OnClickListener loginButtonListener = new OnClickListener() {

		public void onClick(View v) {
			String emailAddress = emailText.getText().toString();
			String password = Mda5.getMDA5(passwordText.getText().toString());

			if (checkEmptyTexts(emailAddress, password)) {
				ShowDialog.showMessage(Login.this,
						getString(R.string.loginEmpty));
				return;
			}
			new LoginTask().execute(emailAddress, password);
		}
	};

	public boolean checkEmptyTexts(String emailAddress, String password) {
		if (password.equals("") || emailAddress.equals("")) {
			return true;
		}
		return false;
	}

	private void showProgressDialog(String Message) {
		dialog = ProgressDialog.show(Login.this, "Ahbap", Message, true);
	}

	private void closeProgressDialog() {
		dialog.dismiss();
	}

	private class LoginTask extends AsyncTask<String, Void, Void> {
		private boolean isSuccesful;

		protected void onPreExecute() {
			showProgressDialog("Wait...");
		}

		@Override
		protected Void doInBackground(String... params) {
			String userJsonString = userhandler.get(params[0], params[1]);

			if (!userJsonString.equals("-1")) {
				isSuccesful = true;
				Intent intent = new Intent(Login.this, Ahbap.class);
				intent.putExtra("email", params[0]);
				intent.putExtra("password", params[1]);
				startActivity(intent);
				finish();
			}
			return null;
		}

		protected void onPostExecute(Void result) {
			closeProgressDialog();
			if (!isSuccesful) {
				ShowDialog.showMessage(Login.this,
						"Invalid username or password !");
			}
		}
	}
}
