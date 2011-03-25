package com.example.mailsender;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class mailsender extends Activity {
	/** Called when the activity is first created. */
	EditText emailadress;
	EditText subject;
	EditText body;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);

		Button addImage = (Button) findViewById(R.id.send_email);
		addImage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Mail m = new Mail("ahmetcan196@gmail.com", "mesela12");
				emailadress = (EditText) findViewById(R.id.emailadress);
				body = (EditText) findViewById(R.id.body);
				subject = (EditText) findViewById(R.id.subject);
				String[] toArr = { emailadress.getText().toString() };
				m.setTo(toArr);
				m.setSubject(subject.getText().toString());
				m.setBody(body.getText().toString());

				try {

					if (m.send()) {
						Toast.makeText(mailsender.this,
								"Email was sent successfully.",
								Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(mailsender.this, "Email was not sent.",
								Toast.LENGTH_LONG).show();
					}
				} catch (Exception e) {
					Log.e("MailApp", "Could not send email", e);
				}
			}
		});
	}
}