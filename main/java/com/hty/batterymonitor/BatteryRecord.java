package com.hty.batterymonitor;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class BatteryRecord extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battery_record);
		final DBHelper helper = new DBHelper(this);
		Cursor c = helper.query();
		String[] from = { "time", "level", "voltage", "current", "temperature" };
		int[] to = { R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5 };
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,	R.layout.battery_record_row, c, from, to);
		adapter.setViewBinder(viewBinder);
		ListView listView = (ListView) this.findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		listView.setDivider(null);// 设置分隔线为空
	}

	private final SimpleCursorAdapter.ViewBinder viewBinder = new SimpleCursorAdapter.ViewBinder() {
		@Override
		public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
			// TODO Auto-generated method stub
			if (cursor.getColumnIndex("temperature") == columnIndex) {
				TextView tv = (TextView) view;
				tv.setText("" + cursor.getInt(columnIndex) / 10.0);
				return true;
			}
			return false;
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "刷新");
		menu.add(0, 1, 1, "折线图");
		menu.add(0, 2, 2, "返回");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int item_id = item.getItemId();
		switch (item_id) {
		case 0:
			onCreate(null);
			break;
		case 1:
			startActivity(new Intent(BatteryRecord.this, BatteryCanvas.class));
			break;
		case 2:
			startActivity(new Intent(BatteryRecord.this, MainActivity.class));
			break;
		}
		return true;
	}
}