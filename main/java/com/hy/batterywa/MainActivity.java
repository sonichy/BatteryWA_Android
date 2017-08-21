package com.hy.batterywa;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tv1, tv2;
	Intent serviceIntent;
	boolean paused = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		if (!isServiceRunning("com.hy.batterywa.BatteryService")) {
			startService(new Intent(this, BatteryService.class));
		}
		isServiceRunning("com.hy.batterywa.BatteryService");
		handler.postDelayed(runnable, 1000);
		tv2.setText("");
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(false);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "退出");
		menu.add(0, 1, 1, "关于");
		menu.add(0, 2, 2, "更新日志");
		menu.add(0, 3, 3, "列表");
		menu.add(0, 4, 4, "折线图");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int item_id = item.getItemId();
		switch (item_id) {
		case 0:
			stopService(new Intent(MainActivity.this.getApplicationContext(), BatteryService.class));
			finish();
			break;
		case 1:
			new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher).setTitle("电量监控 V7.4")
					.setMessage("电池状态，充电计时，通知栏电量图标，桌面电量图标，历史记录，电量电流电压图表，电池充满耗尽时间估算。\n作者:黄颖\nQQ:84429027")
					.setPositiveButton("确定", null).show();
			break;
		case 2:
			new AlertDialog.Builder(this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("更新日志")
					.setMessage(
							"V7.4 (2016-12-08)\n1.快充电流超过2A最大刻度，缩小电流绘图比例，最大刻度到3A。\n\nV7.3 (2015-11-28)\n1.重新增加绘制电压折线，方便观察电压异常；\n2.修复没有记录到的电池启用电量。\nV7.2 (2015-11-25)\n1.把电池估算时间传递给通知。\nV7.1 (2015-11-16)\n1.增加充放电速度；\n2.根据充放电速度计算充满时长、时间和耗尽时长、时间；\n3.优化电池充满耗尽时间计算代码。\nV7.0 (2015-11-09)\n1.添加安卓API 21支持的新电量信息;2.FHD屏幕放大绘图。\nV6.8 (2015-10-27)\n1.取消每秒计算CPU使用率和开机时间，减少功耗。\nV6.7 (2015-10-23)\n1.修复关机引起电池供电时间计算的错误。\nV6.6 (2015-10-19)\n1.保存电池开始使用时间到本地，不再受进程被杀死影响。\nV6.5 (2015-10-15)\n1.温度根据位数处理：2位整数直接用，3位整数除以10。\nV6.4 (2015-10-13)\n1.大量画线用drawLines替代drawLine。\nV6.3 (2015-09-29)\n1.通知标题可变；\n2.如果电流不带符号，为电池供电电流添加负号。\nV6.2 (2015-09-25)\n1.修复判断服务是否运行，检测不到服务，启动多个服务，重复记录数据的BUG。\nV6.1 (2015-09-23)\n1.CPU使用率绘图效果不好取消；\n2.修复读取电流文件不存在引起的崩溃；\n3.修复数据库无数据时绘图引起的崩溃。\nV6.0 (2015-09-17)\n1.增加CPU使用率的获取、记录、绘图；\n2.数据库升级不直接删除而是智能升级；\n3.数据列表、折线图增加刷新菜单。\nV5.7 (2015-09-09)\n1.判断服务没有了才重启服务，以免启动多个服务进程。\nV5.6 (2015-09-02)\n1.使用全局变量传递信息，以免内存不足Activity被kill后，Service继续Activity发送信息引起应用崩溃；\n2.利用widget更新周期重启服务，使得被kill后仍然能复活。\nV5.5 (2015-08-27)\n1.绘图增加整点刻度，更加直观；\n2.增加更新日志。\nV5.X (2015)\n根据数据库数据绘制电量、电流对时间的曲线图。\nV4.X (2015)\n使用数据库记录电池信息，用列表列出。\n3.X (2014)\n增加桌面电量图标\nV2.X (2014)\n增加通知栏电量图标，并把电量监控做成服务形式。\nV1.X (2014)\n获取电池信息")
					.setPositiveButton("确定", null).show();
			break;
		case 3:
			startActivity(new Intent(MainActivity.this, BatteryRecord.class));
			break;
		case 4:
			Intent intent = new Intent(MainActivity.this, BatteryCanvas.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			break;
		}
		return true;
	}

	private final Handler handler = new Handler();
	private final Runnable runnable = new Runnable() {
		@Override
		public void run() {
			if (!paused) {
				this.update();
				handler.postDelayed(this, 1000);
			}
		}

		void update() {
			tv1.setText(MainApplication.getbmsg());
		}
	};

	boolean isServiceRunning(String ServiceName) {
		ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (ServiceName.equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}
}
