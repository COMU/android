package com.example;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DbHelper {
	private static final String DATABASE_NAME = "ahbap.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "KeyTable";
	private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME
			+ " ( " + "  \"Id\" INTEGER PRIMARY KEY," + "  \"Key\" TEXT" + ")";
	private static final String TABLE_INSERT = "INSERT INTO " + TABLE_NAME
			+ " (Key) VALUES (?)";
	private Context context;
	private SQLiteDatabase database;
	private SQLiteStatement insertSQLiteStatement;

	public DbHelper(Context context) {
		this.context = context;
		DbOpenHelper dbOpenHelper = new DbOpenHelper(this.context);
		database = dbOpenHelper.getWritableDatabase();
		insertSQLiteStatement = database.compileStatement(TABLE_INSERT);
	}

	public long insertKey(String key) {
		if (key.equals("")) {
			return -1;
		}
		insertSQLiteStatement.bindString(1, key);
		return insertSQLiteStatement.executeInsert();
	}
	public void close(){
		database.close();
		database = null;
	}
	public String getKey() {
		Cursor cursor = database.query(TABLE_NAME, new String[] { "id,Key" },
				null, null, null, null, "id desc");
		String key = "";
		if (cursor.moveToFirst()) {
			do {
				key = (cursor.getString(1));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return key;
	}
	public void deleteAll(){
		this.database.delete(TABLE_NAME, null, null);
	}
	private static class DbOpenHelper extends SQLiteOpenHelper {
		public DbOpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
	}

}
