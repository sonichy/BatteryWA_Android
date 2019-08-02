package com.hty.batterymonitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "battery.db";
	private final static int VERSION = 6;
	String TableName = "battery";
	private SQLiteDatabase db;
	private static DBHelper mInstance = null;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	public static synchronized DBHelper getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new DBHelper(context);
		}
		return mInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		db.execSQL("CREATE TABLE battery (_id INTEGER PRIMARY KEY ,time TEXT,level INTEGER, voltage INTEGER, current INTEGER , temperature INTEGER, cpu INTEGER)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// db.execSQL("DROP TABLE IF EXISTS battery");
		// onCreate(db);
//		switch (newVersion) {
//		case 6:
//			db.execSQL("alter table battery rename to battery_temp");
//			db.execSQL("CREATE TABLE battery (_id INTEGER PRIMARY KEY ,time TEXT,level INTEGER, voltage INTEGER, current INTEGER , temperature INTEGER, cpu INTEGER)");
//			db.execSQL("insert into battery select *,'' from battery_temp");
//			db.execSQL("drop table battery_temp");
//			break;
//		}
	}

	public void insert(ContentValues values) {
		SQLiteDatabase db = getWritableDatabase();
		db.insert(TableName, null, values);
		db.close();
	}

	public Cursor query() {
		SQLiteDatabase db = getWritableDatabase();
		// db.execSQL("delete from battery where not in (select * from battery order by _id desc limit 0,10000)");
		Cursor c = db.query(TableName, null, null, null, null, null, "_id desc");
		return c;
	}

	public void del(int id) {
		if (db == null)
			db = getWritableDatabase();
		// db.delete(TableName, "id=?", new String[] { String.valueOf(id) });
		// Log.e("id", id + "");
		db.execSQL("delete from battery where _id<" + id);
		// db.ExecuteNonQuery(CommandType.Text, "VACUUM");
	}

	@Override
	public void close() {
		if (db != null)
			db.close();
	}
}
