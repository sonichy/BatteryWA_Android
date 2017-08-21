package com.hy.batterywa;

import java.util.List;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {
	private int mAppWidgetId;

	@Override
	public void onEnabled(Context context) {
		context.startService(new Intent(context, WidgetService.class));
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int appWidgetIds[]) {
		for (int i = 0; i < appWidgetIds.length; i++) {
			System.out.println(i);
			Intent actionIntent = new Intent(context, MainActivity.class);
			PendingIntent pending = PendingIntent.getActivity(context, 0,
					actionIntent, 0);
			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.monitor_widget_layout);
			views.setOnClickPendingIntent(R.id.monitor_widget_view, pending);
			appWidgetManager.updateAppWidget(appWidgetIds[i], views);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		context.startService(new Intent(context, WidgetService.class));
		// 重启服务尝试，防内存不足被杀死
		if (!isServiceRunning(context, "com.hy.batterywa.BatteryService")) {
			context.startService(new Intent(context, BatteryService.class));
		}
	}

	@Override
	public void onDisabled(Context context) {
		context.stopService(new Intent(context, WidgetService.class));
	}

	public static boolean isServiceRunning(Context mContext, String className) {
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) mContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager
				.getRunningServices(Integer.MAX_VALUE);
		if (!(serviceList.size() > 0)) {
			return false;
		}
		for (int i = 0; i < serviceList.size(); i++) {
			// Log.e("WidgetProvider",
			// serviceList.get(i).service.getClassName());
			if (serviceList.get(i).service.getClassName().equals(className) == true) {
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}
}