package com.hty.batterymonitor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

public class MyView extends View {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
	SimpleDateFormat sdf1 = new SimpleDateFormat("MM月dd日");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
	DBHelper dbh;
	float[] lpts, cpts, vpts;

	public MyView(Context context) {
		super(context);
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		DisplayMetrics DM = getResources().getDisplayMetrics();
		if (DM.heightPixels == 800)
			canvas.scale(0.8f, 0.8f);
		if (DM.heightPixels == 480)
			canvas.scale(0.5f, 0.5f);
		if (DM.heightPixels == 1920)
			canvas.scale(1.3f, 1.3f);
		Paint paint = new Paint();
		// paint.setStyle(Paint.Style.STROKE);
		paint.setStyle(Paint.Style.FILL);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(2);
		Paint paintc = new Paint();
		paintc.setColor(Color.GREEN);
		paintc.setAntiAlias(true);
		Paint paintv = new Paint();
		paintv.setColor(Color.RED);
		paintv.setAntiAlias(true);
		int xo = 50;
		int yo = 550;
		dbh = new DBHelper(MainApplication.getAContext());
		Cursor cursor = dbh.query();
		int cc = cursor.getCount();
		if (cc > 0) {
			int[] level = new int[cc];
			String[] time = new String[cc];
			Date[] date = new Date[cc];
			float[] x = new float[cc];
			int[] current = new int[cc];
			int[] voltage = new int[cc];
			// int[] cpu = new int[cc];
			lpts = new float[cc * 4];
			cpts = new float[cc * 4];
			vpts = new float[cc * 4];
			cursor.moveToFirst();
			time[0] = cursor.getString(cursor.getColumnIndex("time"));
			level[0] = cursor.getInt(cursor.getColumnIndex("level"));
			current[0] = cursor.getInt(cursor.getColumnIndex("current"));
			voltage[0] = cursor.getInt(cursor.getColumnIndex("voltage"));
			// cpu[0] = cursor.getInt(cursor.getColumnIndex("cpu"));
			x[0] = xo;
			try {
				date[0] = sdf.parse(time[0]);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					date[0] = sdf2.parse(time[0]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Log.e("time->date", time[0] + "->" + sdf.format(date[0]));
			int idmax = cursor.getInt(cursor.getColumnIndex("_id"));
			MainApplication.setId(cursor.getInt(cursor.getColumnIndex("_id")) - 20000);
			int i = 1;
			while (cursor.moveToNext()) {
				time[i] = cursor.getString(cursor.getColumnIndex("time"));
				level[i] = cursor.getInt(cursor.getColumnIndex("level"));
				current[i] = cursor.getInt(cursor.getColumnIndex("current"));
				voltage[i] = cursor.getInt(cursor.getColumnIndex("voltage"));
				// cpu[i] = cursor.getInt(cursor.getColumnIndex("cpu"));
				try {
					date[i] = sdf.parse(time[i]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					try {
						date[i] = sdf2.parse(time[i]);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				x[i] = Long.valueOf(date[0].getTime() - date[i].getTime()).floatValue() / 120000 + xo;
				// canvas.drawLine(x[i - 1], yo - level[i - 1] * 5, x[i], yo
				// - level[i] * 5, paint);
				// canvas.drawLine(x[i - 1], yo - current[i - 1] / 2, x[i], yo
				// - current[i] / 2, paintc);
				lpts[(i - 1) * 4] = x[i - 1];
				lpts[(i - 1) * 4 + 1] = yo - level[i - 1] * 5;
				lpts[(i - 1) * 4 + 2] = x[i];
				lpts[(i - 1) * 4 + 3] = yo - level[i] * 5;
				cpts[(i - 1) * 4] = x[i - 1];
				cpts[(i - 1) * 4 + 1] = yo - current[i - 1] / 6;
				cpts[(i - 1) * 4 + 2] = x[i];
				cpts[(i - 1) * 4 + 3] = yo - current[i] / 6;
				vpts[(i - 1) * 4] = x[i - 1];
				vpts[(i - 1) * 4 + 1] = yo - voltage[i - 1] / 20;
				vpts[(i - 1) * 4 + 2] = x[i];
				vpts[(i - 1) * 4 + 3] = yo - voltage[i] / 20;
				i = i + 1;
			}
			canvas.drawLines(vpts, paintv);
			canvas.drawLines(cpts, paintc);
			canvas.drawLines(lpts, paint);
			canvas.drawText(i + "", 70, 30, paint);
			// 画刻度
			int j = 10;
			// float[] x0 = new float[j];
			String time0 = time[0].substring(0, 12) + "00:00:00";
			// Log.e("time0", time0);
			Date date0 = null;
			try {
				date0 = sdf.parse(time0);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int k;
			float xd0 = 0;
			for (k = 0; k < j; k++) {
				xd0 = Long.valueOf(date[0].getTime() - (date0.getTime() - k * 86400000)).floatValue()
						/ 120000 + xo;
				// canvas.drawLine(x0[k], yo - 10, x0[k], yo + 10, paint);
				canvas.drawText(k + ":" + sdf1.format(new Date(date0.getTime() - k * 86400000)), xd0 - 60,
						yo + 30, paint);
				for (int h = 0; h < 24; h++) {
					float hx = Long.valueOf(
							date[0].getTime() - (date0.getTime() - k * 86400000) - h * 3600000).floatValue()
							/ 120000 + xo;
					if (hx < xo)
						break;
					canvas.drawLine(hx, yo - 10, hx, yo, paint);
					canvas.drawText(h + "", hx - 3, yo + 15, paint);
				}
			}
			canvas.drawText(xd0 + "", xd0 - 60, yo + 60, paint);
		}
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(7000, 800);
	}
}
