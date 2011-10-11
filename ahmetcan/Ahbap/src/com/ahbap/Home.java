package com.ahbap;

import java.io.FileNotFoundException;

import com.ahbap.webservice.ImageHandler;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class Home extends Activity {
	private ImageButton profil_photo;
	private Bundle extras;
	private ImageHandler imageHandler;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		extras = getIntent().getExtras();
		profil_photo = (ImageButton) findViewById(R.id.profileImage);
		profil_photo.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 0);

			}
		});

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			Uri targetUri = data.getData();

			Bitmap bitmap;
			try {
				bitmap = BitmapFactory.decodeStream(getContentResolver()
						.openInputStream(targetUri));
				Bitmap bMapScaled = Bitmap.createScaledBitmap(bitmap, 100, 100,
						true);

				profil_photo.setImageBitmap(bMapScaled);

			} catch (FileNotFoundException e) {
				e.printStackTrace();

			}
			imageHandler = new ImageHandler(Home.this);
			Toast.makeText(Home.this, "" + getRealPathFromURI(targetUri),
					Toast.LENGTH_LONG).show();
			imageHandler
					.post(extras.getString("email"),
							extras.getString("password"),
							getRealPathFromURI(targetUri));
		}

	}

	public String getRealPathFromURI(Uri contentUri) {

		// can post image
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(contentUri, proj, // Which columns to
														// return
				null, // WHERE clause; which rows to return (all rows)
				null, // WHERE clause selection arguments (none)
				null); // Order-by clause (ascending by name)
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();

		return cursor.getString(column_index);
	}
}
