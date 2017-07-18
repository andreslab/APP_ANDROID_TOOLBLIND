package com.andreslab.tools_blind;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
        paint.setColor(Color.GRAY);
        canvas.drawPaint(paint);
    }

}
