package com.andreslab.tools_blind.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Hashtable;

/**
 * Created by macbookpro on 7/18/17.
 */

public class DigitalBrailleView extends View {
    public Hashtable<String, int[]> tablero = new Hashtable<String, int[]>();
    int ancho;
    int alto;


    public DigitalBrailleView(Context context) {
        super(context);
        tablero.put("a",new int[12]);
        tablero.put("b",new int[12]);
        tablero.put("c",new int[12]);
        tablero.put("d",new int[12]);
        tablero.put("e",new int[12]);
        tablero.put("f",new int[12]);
        tablero.put("g",new int[12]);
        tablero.put("h",new int[12]);
        tablero.put("i",new int[12]);
        tablero.put("j",new int[12]);
        tablero.put("k",new int[12]);
        tablero.put("l",new int[12]);
        tablero.put("m",new int[12]);
        tablero.put("n",new int[12]);
        tablero.put("ñ",new int[12]);
        tablero.put("o",new int[12]);
        tablero.put("p",new int[12]);
        tablero.put("q",new int[12]);
        tablero.put("r",new int[12]);
        tablero.put("s",new int[12]);
        tablero.put("t",new int[12]);
        tablero.put("u",new int[12]);
        tablero.put("v",new int[12]);
        tablero.put("w",new int[12]);
        tablero.put("x",new int[12]);
        tablero.put("y",new int[12]);
        tablero.put("z",new int[12]);
        tablero.put("á",new int[12]);
        tablero.put("é",new int[12]);
        tablero.put("í",new int[12]);
        tablero.put("ó",new int[12]);
        tablero.put("ú",new int[12]);


    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        ancho = getMeasuredWidth();
        alto = getMeasuredHeight();

        Paint paint = new Paint();
        canvas.drawColor(Color.BLACK);

        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        //fila1
        canvas.drawCircle(ancho / 4,alto / 4,80, paint);
        canvas.drawCircle(ancho / 3,alto / 4,80, paint);
        canvas.drawCircle(ancho / 2,alto / 4,80, paint);
        canvas.drawCircle(ancho,alto / 4,80, paint);

        //fila2
        canvas.drawCircle(ancho / 4,alto / 3,80, paint);
        canvas.drawCircle(ancho / 3,alto / 3,80, paint);
        canvas.drawCircle(ancho / 2,alto / 3,80, paint);
        canvas.drawCircle(ancho ,alto / 3,80, paint);

        //fila3
        canvas.drawCircle(ancho / 4,alto / 2,80, paint);
        canvas.drawCircle(ancho / 3 ,alto / 2,80, paint);
        canvas.drawCircle(ancho / 2,alto / 2,80, paint);
        canvas.drawCircle(ancho ,alto / 2,80, paint);

    }
}