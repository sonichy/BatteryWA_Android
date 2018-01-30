package com.hty.batterymonitor;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RemoteViews;

public class WidgetService extends Service {
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {

		BroadcastReceiver BR = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				int level = intent.getIntExtra("level", 0);
				int scale = intent.getIntExtra("scale", 100);
				int status = intent.getIntExtra("status", 0);
				int batteryHealth = level * 100 / scale;
				int icon = 0;

				AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
				RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.monitor_widget_layout);
				LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
				View view = inflater.inflate(R.layout.monitor_widget_layout, null);
				if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
					widgetView.setImageViewResource(R.id.chargeico,	R.drawable.charge);
				} else {
					widgetView.setImageViewResource(R.id.chargeico,	R.drawable.tm);
				}
				widgetView.setImageViewResource(R.id.monitor_widget_view, getStateIcon(batteryHealth));
				widgetManager.updateAppWidget(new ComponentName(context, WidgetProvider.class), widgetView);
			}
		};
		registerReceiver(BR, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	}

	private int getStateIcon(int batteryHealth) {
		if (batteryHealth >= 0 || batteryHealth <= 100)
			return MainApplication.batteryStateIcon[batteryHealth];
		return R.drawable.stat_sys_battery_unknown;
	}

	private int getChargingIcon(int batteryHealth) {
		if (batteryHealth >= 0 && batteryHealth < 10)
			return R.drawable.stat_sys_battery_charge_anim5;
		if (batteryHealth >= 10 && batteryHealth < 20)
			return R.drawable.stat_sys_battery_charge_anim15;
		if (batteryHealth >= 20 && batteryHealth < 30)
			return R.drawable.stat_sys_battery_charge_anim25;
		if (batteryHealth >= 30 && batteryHealth < 40)
			return R.drawable.stat_sys_battery_charge_anim35;
		if (batteryHealth >= 40 && batteryHealth < 50)
			return R.drawable.stat_sys_battery_charge_anim45;
		if (batteryHealth >= 50 && batteryHealth < 60)
			return R.drawable.stat_sys_battery_charge_anim55;
		if (batteryHealth >= 60 && batteryHealth < 70)
			return R.drawable.stat_sys_battery_charge_anim65;
		if (batteryHealth >= 70 && batteryHealth < 80)
			return R.drawable.stat_sys_battery_charge_anim75;
		if (batteryHealth >= 80 && batteryHealth < 90)
			return R.drawable.stat_sys_battery_charge_anim85;
		if (batteryHealth >= 90 && batteryHealth < 100)
			return R.drawable.stat_sys_battery_charge_anim95;
		if (batteryHealth == 100)
			return R.drawable.stat_sys_battery_charge_animfull;
		return R.drawable.stat_sys_battery_unknown;
	}

}