package com.hty.batterymonitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BatteryMonitor extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startService(new Intent(this, WidgetService.class));
	}
}