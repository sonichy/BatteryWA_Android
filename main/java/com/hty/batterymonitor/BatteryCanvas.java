package com.hty.batterymonitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class BatteryCanvas extends Activity {
	DBHelper dbh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battery_canvas);
		MainApplication.setAContext(this);
		dbh = new DBHelper(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		dbh.del(MainApplication.getId());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "刷新");
		menu.add(0, 1, 1, "列表");
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
			startActivity(new Intent(BatteryCanvas.this, BatteryRecord.class));
			break;
		case 2:
			startActivity(new Intent(BatteryCanvas.this, MainActivity.class));
			break;
		}
		return true;
	}
}