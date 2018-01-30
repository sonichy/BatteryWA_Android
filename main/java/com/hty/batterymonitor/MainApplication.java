package com.hty.batterymonitor;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {
	private static Context mContext;
	private static String msg, bmsg;
	private static Context AContext;
	private static int idc;

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
	}

	public static void setmsg(String s) {
		msg = s;
	}

	public static String getmsg() {
		return msg;
	}

	public static void setbmsg(String s) {
		bmsg = s;
	}

	public static String getbmsg() {
		return bmsg;
	}

	public static Context getContext() {
		return mContext;
	}

	public static void setAContext(Context context) {
		AContext = context;
	}

	public static Context getAContext() {
		return AContext;
	}

	public static void setId(int id) {
		// TODO Auto-generated method stub
		idc = id;
	}

	public static int getId() {
		return idc;
	}

	final static int batteryStateIcon[] = { R.drawable.stat_sys_battery_0,
			R.drawable.stat_sys_battery_1, R.drawable.stat_sys_battery_2,
			R.drawable.stat_sys_battery_3, R.drawable.stat_sys_battery_4,
			R.drawable.stat_sys_battery_5, R.drawable.stat_sys_battery_6,
			R.drawable.stat_sys_battery_7, R.drawable.stat_sys_battery_8,
			R.drawable.stat_sys_battery_9, R.drawable.stat_sys_battery_10,
			R.drawable.stat_sys_battery_11, R.drawable.stat_sys_battery_12,
			R.drawable.stat_sys_battery_13, R.drawable.stat_sys_battery_14,
			R.drawable.stat_sys_battery_15, R.drawable.stat_sys_battery_16,
			R.drawable.stat_sys_battery_17, R.drawable.stat_sys_battery_18,
			R.drawable.stat_sys_battery_19, R.drawable.stat_sys_battery_20,
			R.drawable.stat_sys_battery_21, R.drawable.stat_sys_battery_22,
			R.drawable.stat_sys_battery_23, R.drawable.stat_sys_battery_24,
			R.drawable.stat_sys_battery_25, R.drawable.stat_sys_battery_26,
			R.drawable.stat_sys_battery_27, R.drawable.stat_sys_battery_28,
			R.drawable.stat_sys_battery_29, R.drawable.stat_sys_battery_30,
			R.drawable.stat_sys_battery_31, R.drawable.stat_sys_battery_32,
			R.drawable.stat_sys_battery_33, R.drawable.stat_sys_battery_34,
			R.drawable.stat_sys_battery_35, R.drawable.stat_sys_battery_36,
			R.drawable.stat_sys_battery_37, R.drawable.stat_sys_battery_38,
			R.drawable.stat_sys_battery_39, R.drawable.stat_sys_battery_40,
			R.drawable.stat_sys_battery_41, R.drawable.stat_sys_battery_42,
			R.drawable.stat_sys_battery_43, R.drawable.stat_sys_battery_44,
			R.drawable.stat_sys_battery_45, R.drawable.stat_sys_battery_46,
			R.drawable.stat_sys_battery_47, R.drawable.stat_sys_battery_48,
			R.drawable.stat_sys_battery_49, R.drawable.stat_sys_battery_50,
			R.drawable.stat_sys_battery_51, R.drawable.stat_sys_battery_52,
			R.drawable.stat_sys_battery_53, R.drawable.stat_sys_battery_54,
			R.drawable.stat_sys_battery_55, R.drawable.stat_sys_battery_56,
			R.drawable.stat_sys_battery_57, R.drawable.stat_sys_battery_58,
			R.drawable.stat_sys_battery_59, R.drawable.stat_sys_battery_60,
			R.drawable.stat_sys_battery_61, R.drawable.stat_sys_battery_62,
			R.drawable.stat_sys_battery_63, R.drawable.stat_sys_battery_64,
			R.drawable.stat_sys_battery_65, R.drawable.stat_sys_battery_66,
			R.drawable.stat_sys_battery_67, R.drawable.stat_sys_battery_68,
			R.drawable.stat_sys_battery_69, R.drawable.stat_sys_battery_70,
			R.drawable.stat_sys_battery_71, R.drawable.stat_sys_battery_72,
			R.drawable.stat_sys_battery_73, R.drawable.stat_sys_battery_74,
			R.drawable.stat_sys_battery_75, R.drawable.stat_sys_battery_76,
			R.drawable.stat_sys_battery_77, R.drawable.stat_sys_battery_78,
			R.drawable.stat_sys_battery_79, R.drawable.stat_sys_battery_80,
			R.drawable.stat_sys_battery_81, R.drawable.stat_sys_battery_82,
			R.drawable.stat_sys_battery_83, R.drawable.stat_sys_battery_84,
			R.drawable.stat_sys_battery_85, R.drawable.stat_sys_battery_86,
			R.drawable.stat_sys_battery_87, R.drawable.stat_sys_battery_88,
			R.drawable.stat_sys_battery_89, R.drawable.stat_sys_battery_90,
			R.drawable.stat_sys_battery_91, R.drawable.stat_sys_battery_92,
			R.drawable.stat_sys_battery_93, R.drawable.stat_sys_battery_94,
			R.drawable.stat_sys_battery_95, R.drawable.stat_sys_battery_96,
			R.drawable.stat_sys_battery_97, R.drawable.stat_sys_battery_98,
			R.drawable.stat_sys_battery_99, R.drawable.stat_sys_battery_100 };
}
