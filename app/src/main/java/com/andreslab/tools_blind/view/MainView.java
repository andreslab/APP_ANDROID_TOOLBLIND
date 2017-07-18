package com.andreslab.tools_blind.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by macbookpro on 7/17/17.
 */

public class MainView extends View {
    public MainView(Context context){
        super(context);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);

        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        if(getMeasuredWidth() > 1000){
            canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, 250, paint);

            Path path = new Path();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(30);
            path.moveTo(0,getMeasuredHeight() / 2);
            path.lineTo(getMeasuredWidth(), getMeasuredHeight() / 2);
            canvas.drawPath(path,paint);
        }else{
            canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, 150, paint);
        }




    }

}
