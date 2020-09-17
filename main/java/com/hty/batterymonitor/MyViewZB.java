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
//		if (DM.heightPixels <= 480)
//			canvas.scale(0.5f, 0.5f);
//		else if (DM.heightPixels <= 800)
//			canvas.scale(0.8f, 0.8f);
//		else if (DM.heightPixels <= 1920)
//			canvas.scale(1.3f, 1.3f);
//		else
//			canvas.scale(1.5f, 1.5f);
		// canvas.drawText("Height:" + DM.heightPixels, 80, 20, paint);
		int xo = 50;
		int yo = 550;
		canvas.drawLine(xo, yo, DM.widthPixels, yo, paint);	//X轴
		canvas.drawLine(xo, 20, xo, 1060, paint);	//Y轴
		canvas.drawLine(xo, 20, xo-5, 30, paint);	//箭头的/
		canvas.drawLine(xo, 20, xo+5, 30, paint);	//箭头的\
		//Y轴刻度
		int kd = 100;
		for (int y = 50; y <= 1050; y += 50) {
			if(y != yo)
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
		//颜色和单位
		canvas.drawText("%", 20, 40, paint);
		paint.setColor(Color.GREEN);
		canvas.drawText("x30mA", 5, 25, paint);
		paint.setColor(Color.RED);
		canvas.drawText("x100mV", 3, 12, paint);
	}

}