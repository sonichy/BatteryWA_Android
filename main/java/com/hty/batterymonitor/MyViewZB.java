package com.hty.batterymonitor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class MyViewZB extends View {

	public MyViewZB(Context context) {
		super(context);
	}

	public MyViewZB(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		DisplayMetrics DM = getResources().getDisplayMetrics();
		if (DM.heightPixels == 800)
			canvas.scale(0.8f, 0.8f);
		if (DM.heightPixels == 480)
			canvas.scale(0.5f, 0.5f);
		if (DM.heightPixels == 1920)
			canvas.scale(1.3f, 1.3f);
		// canvas.drawText("Height:" + DM.heightPixels, 80, 20, paint);
		int xo = 50;
		int yo = 550;
		canvas.drawLine(xo, yo, 720, yo, paint);
		canvas.drawLine(xo, 20, xo, 1060, paint);
		canvas.drawLine(xo, 20, 45, 30, paint);
		canvas.drawLine(xo, 20, 55, 30, paint);
		int kd = 100;
		for (int y = 50; y <= 1050; y += 50) {
			canvas.drawLine(xo, y, xo + 10, y, paint);
			int tx = 20;
			if (y == 50 || kd < 0)
				tx = 15;
			if (y == yo)
				tx = 25;
			if (kd == -100)
				tx = 10;
			canvas.drawText(kd + "", tx, y + 5, paint);
			kd = kd - 10;
		}
		canvas.drawText("%", 20, 40, paint);
		paint.setColor(Color.GREEN);
		canvas.drawText("x30mA", 5, 25, paint);
		paint.setColor(Color.RED);
		canvas.drawText("x100mV", 3, 12, paint);
		// paint.setColor(Color.RED);
		// canvas.drawText("CPU%", 10, 10, paint);
	}
}
