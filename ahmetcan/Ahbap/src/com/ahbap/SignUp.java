package com.ahbap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.ahbap.database.DbHandler;
import com.ahbap.webservice.UserHandler;

public class SignUp extends Activity {
	private EditText emailText;
	private EditText nameText;
	private EditText lastnameText;
	private Button signUp;
	private DbHandler dbHandler;
	private User user;
	private UserHandler userHandler;
	private ProgressDialog dialog;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.signup);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				R.drawable.blue_sofa);

		userHandler = new UserHandler(SignUp.this);

		emailText = (EditText) findViewById(R.id.email);
		nameText = (EditText) findViewById(R.id.name);
		lastnameText = (EditText) findViewById(R.id.lastName);

		signUp = (Button) findViewById(R.id.ok_button);
		signUp.setOnClickListener(signupListener);

		dbHandler = new DbHandler(SignUp.this);
	}

	private OnClickListener signupListener = new OnClickListener() {

		public void onClick(View v) {

			String email = emailText.getText().toString();
			String name = nameText.getText().toString();
			String lastname = lastnameText.getText().toString();

			if (checkEmptyTexts(email, name, lastname)) {
				ShowDialog.showMessage(SignUp.this,
						getString(R.string.signupEmpty));
				return;
			}
			new saveUser().execute(email, name, lastname);

		}
	};

	private void showProgressDialog(String Message) {
		dialog = ProgressDialog.show(this, "Ahbap", Message, true);
	}

	private void closeProgressDialog() {
		dialog.dismiss();
	}

	private void startBrowser() {
		Intent intent = new Intent(SignUp.this, DisplayEmail.class);
		intent.putExtra("mail", "http://www." + user.getEmail().split("@")[1]);
		startActivity(intent);
		finish();
	}

	private void setDB() {
		dbHandler.deleteAll();
		dbHandler.insertKey(user.getKey());
	}

	private void setUser(String email, String name, String lastname) {
		user = new User();
		user.setName(name);
		user.setLastname(lastname);
		user.setEmail(email);
		user.setKey(Generate.key(30));
		user.setPassword(Generate.key(6));
	}

	private boolean checkEmptyTexts(String email, String name, String lastname) {
		if (email.equals("") || name.equals("") || lastname.equals("")) {
			return true;
		}
		return false;
	}

	private class saveUser extends AsyncTask<String, Void, Void> {
		private boolean isCreated;

		protected void onPreExecute() {
			showProgressDialog(getString(R.string.creatingUser));
		}

		@Override
		protected Void doInBackground(String... params) {
			setUser(params[0], params[1], params[2]);
			String s = userHandler.post(user);
			if (s.equals("Created")) {
				isCreated = true;
				setDB();
				startBrowser();
			}
			return null;
		}

		protected void onPostExecute(Void result) {
			closeProgressDialog();
			if (!isCreated) {
				ShowDialog.showMessage(SignUp.this,
						getString(R.string.badEmail));
			}
		}
	}

}