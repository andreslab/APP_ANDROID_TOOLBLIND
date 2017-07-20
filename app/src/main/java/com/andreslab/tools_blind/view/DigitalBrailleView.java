package com.andreslab.tools_blind.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by macbookpro on 7/18/17.
 */

public class DigitalBrailleView extends View {
    public Hashtable<String, int[]> tablero = new Hashtable<String, int[]>();


    int radio = 80;
    int circulo = -1;

    int ancho;
    int alto;

    int spacing_x;
    int spacing_y;

    int spacing_total_y;//espacio total en y del grafico
    int spacing_total_x;// espacio total en x del grafico


    int spacing_border_y;
    int spacing_border_x;



    /*float[] x = {(spacing_x * 2)+160+spacing_border_x,130};
    float[] y = {(spacing_y * 2) + 160 + spacing_border_y, 100};*/

    ArrayList<float[]> colection_position = new ArrayList<float[]>();
    ArrayList<float[]> patron_position = new ArrayList<float[]>();

    private Activity _activity;
    private Context _context;

    Boolean touch_circle = false;
/*
    float[] f1_circle1 = {spacing_x + spacing_border_x,spacing_y  ,  spacing_y + spacing_border_y};
    float[] f1_circle2 = {(spacing_x * 2) + 160 + spacing_border_x  ,  spacing_y + spacing_border_y };
    float[] f1_circle3 = {((spacing_x * 3)+ (160 * 2))+spacing_border_x  ,  spacing_y + spacing_border_y };
    float[] f1_circle4 = {((spacing_x * 4)+(160 * 3))+spacing_border_x  ,  spacing_y + spacing_border_y };

    float[] f2_circle1 = {spacing_x + spacing_border_x  ,  (spacing_y * 2) + 160 + spacing_border_y };
    float[] f2_circle2 = {(spacing_x * 2)+160+spacing_border_x  ,  (spacing_y * 2) + 160 + spacing_border_y };
    float[] f2_circle3 = {((spacing_x * 3)+ (160 * 2))+spacing_border_x  ,  (spacing_y * 2) + 160 + spacing_border_y };
    float[] f2_circle4 = {((spacing_x * 4)+(160 * 3))+spacing_border_x  ,  (spacing_y * 2) + 160 + spacing_border_y };

    float[] f3_circle1 = {spacing_x + spacing_border_x  ,  ((spacing_y * 3)+ (160 * 2)) + spacing_border_y };
    float[] f3_circle2 = {(spacing_x * 2)+160+spacing_border_x  ,  ((spacing_y * 3)+ (160 * 2)) + spacing_border_y };
    float[] f3_circle3 = {((spacing_x * 3)+ (160 * 2))+spacing_border_x  ,  ((spacing_y * 3)+ (160 * 2)) + spacing_border_y };
    float[] f3_circle4 = {((spacing_x * 4)+(160 * 3))+spacing_border_x  ,  ((spacing_y * 3)+ (160 * 2)) + spacing_border_y };

*/
    public DigitalBrailleView(Context context, Activity activity) {
        super(context);

        _context = context;
        _activity = activity;

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

        spacing_x = (ancho - (160 * 2) )/3;
        spacing_y = (alto - (160 * 3) )/4;

        spacing_total_y = (160 * 3) + (spacing_y * 2); //espacio total en y del grafico
        spacing_total_x = (160 * 2) + (spacing_x); // espacio total en x del grafico


        //spacing_border_y = (alto - spacing_total_y )/2;
        //spacing_border_x = (ancho - spacing_total_x )/2;


        float[] f1_circle1 = {spacing_x + 160/2, spacing_y};
        float[] f1_circle2 = {spacing_x + 160 + spacing_x + 160/2 , spacing_y };
        //float[] f1_circle3 = {((spacing_x * 3)+ (160 * 2))+spacing_border_x  ,  spacing_y + spacing_border_y };
        //float[] f1_circle4 = {((spacing_x * 4)+(160 * 3))+spacing_border_x  ,  spacing_y + spacing_border_y };

        float[] f2_circle1 = {spacing_x  + 160/2,  spacing_y + 160 + spacing_y + 160/2};
        float[] f2_circle2 = {spacing_x+160+spacing_x + 160/2 ,  spacing_y + 160 + spacing_y  + 160/2};
        //float[] f2_circle3 = {((spacing_x * 3)+ (160 * 2))+spacing_border_x  ,  (spacing_y * 2) + 160 + spacing_border_y };
        //float[] f2_circle4 = {((spacing_x * 4)+(160 * 3))+spacing_border_x  ,  (spacing_y * 2) + 160 + spacing_border_y };

        float[] f3_circle1 = {spacing_x  + 160/2,  ((spacing_y * 2)+ (160 * 2)) + spacing_y + 160/2};
        float[] f3_circle2 = {spacing_x +160+spacing_x  + 160/2 ,  ((spacing_y * 2)+ (160 * 2)) + spacing_y + 160/2};
        //float[] f3_circle3 = {((spacing_x * 3)+ (160 * 2))+spacing_border_x  ,  ((spacing_y * 3)+ (160 * 2)) + spacing_border_y };
        //float[] f3_circle4 = {((spacing_x * 4)+(160 * 3))+spacing_border_x  ,  ((spacing_y * 3)+ (160 * 2)) + spacing_border_y };

        colection_position.add(f1_circle1);
        colection_position.add(f1_circle2);
        //colection_position.add(f1_circle3);
        //colection_position.add(f1_circle4);
        colection_position.add(f2_circle1);
        colection_position.add(f2_circle2);
        //colection_position.add(f2_circle3);
        //colection_position.add(f2_circle4);
        colection_position.add(f3_circle1);
        colection_position.add(f3_circle2);
        //colection_position.add(f3_circle3);
        //colection_position.add(f3_circle4);




        //fila1
        canvas.drawCircle(f1_circle1[0] , f1_circle1[1],80, paint);
        canvas.drawCircle(f1_circle2[0] , f1_circle2[1],80, paint);
        //canvas.drawCircle(f1_circle3[0] , f1_circle3[1],80, paint);
        //canvas.drawCircle(f1_circle4[0] , f1_circle4[1],80, paint);

        //fila2
        canvas.drawCircle(f2_circle1[0] , f2_circle1[1],80, paint);
        canvas.drawCircle(f2_circle2[0] , f2_circle2[1],80, paint);
        //canvas.drawCircle(f2_circle3[0] , f2_circle3[1],80, paint);
        //canvas.drawCircle(f2_circle4[0] , f2_circle4[1],80, paint);

        //fila3
        canvas.drawCircle(f3_circle1[0] , f3_circle1[1],80, paint);
        canvas.drawCircle(f3_circle2[0] , f3_circle2[1],80, paint);
        //canvas.drawCircle(f3_circle3[0] , f3_circle3[1],80, paint);
        //canvas.drawCircle(f3_circle4[0] , f3_circle4[1],80, paint);
    }

    public boolean onTouchEvent(MotionEvent evento){



        float[] x = {(spacing_x * 2)+160+spacing_border_x,130};
        float[] y = {(spacing_y * 2) + 160 + spacing_border_y, 100};

        float getx = evento.getX();
        float gety = evento.getY();
        int accion = evento.getAction();

        Vibrator vibrator = (Vibrator) this._activity.getSystemService(this._context.VIBRATOR_SERVICE);

        if(accion == MotionEvent.ACTION_DOWN){
            /*for(int i = 0; i<2; i++){
                double cenx = getx - x[i];
                double ceny = gety - y[i];

                //funcion para saber si se toca el cieculo
                float distancia = (float)Math.sqrt(cenx * cenx + ceny * ceny);
                if(distancia <= radio){
                    //circulo fe tocado
                    circulo = i;
                    invalidate();
                }

            }*/
        }

        if(accion == MotionEvent.ACTION_UP){
            touch_circle = false;
        }

        if(accion == MotionEvent.ACTION_MOVE){
            /*if (circulo > -1) {
                x[circulo] = getx;
                y[circulo] = gety;
                invalidate();
            }*/

            patron_position = patrones(colection_position, "o");


            for(int i = 0; i<patron_position.size(); i++){
                //double cenx = getx - x[i];
                //double ceny = gety - y[i];

                double cenx = getx - patron_position.get(i)[0];
                double ceny = gety - patron_position.get(i)[1];

                //funcion para saber si se toca el cieculo
                float distancia = (float)Math.sqrt(cenx * cenx + ceny * ceny);
                if(distancia <= radio){
                    //circulo fe tocado
                    Log.d("TOUCH CIRCLE", "ciruclo fue tocado");

                    switch (i){
                        case 0:
                             if (touch_circle == false) {
                                vibrator.vibrate(50);
                                touch_circle = true;
                            }
                            break;
                        case 1:
                             if (touch_circle == false) {
                                vibrator.vibrate(25);
                                touch_circle = true;
                            }
                            break;
                        case 2:
                             if (touch_circle == false) {
                                vibrator.vibrate(25);
                                touch_circle = true;
                            }
                            break;
                        case 3:
                             if (touch_circle == false) {
                                vibrator.vibrate(25);
                                touch_circle = true;
                            }
                            break;
                        case 4:
                             if (touch_circle == false) {
                                vibrator.vibrate(25);
                                touch_circle = true;
                            }
                            break;
                        case 5:
                             if (touch_circle == false) {
                                vibrator.vibrate(25);
                                touch_circle = true;
                            }
                            break;
                        case 6:
                             if (touch_circle == false) {
                                vibrator.vibrate(25);
                                touch_circle = true;
                            }
                            break;
                        case 7:
                             if (touch_circle == false) {
                                vibrator.vibrate(25);
                                touch_circle = true;
                            }
                            break;
                        case 8:
                             if (touch_circle == false) {
                                vibrator.vibrate(25);
                                touch_circle = true;
                            }
                            break;
                        case 9:
                             if (touch_circle == false) {
                                vibrator.vibrate(25);
                                touch_circle = true;
                            }
                            break;
                        case 10:
                             if (touch_circle == false) {
                                vibrator.vibrate(25);
                                touch_circle = true;
                            }
                            break;
                        case 11:
                             if (touch_circle == false) {
                                vibrator.vibrate(25);
                                touch_circle = true;
                            }
                            break;
                        case 12:
                             if (touch_circle == false) {
                                vibrator.vibrate(25);
                                touch_circle = true;
                            }
                            break;
                        default:

                    }


                    circulo = i;
                    invalidate();
                }else{
                    touch_circle = false;
                }

            }


        }
        return true;
    }


    ArrayList<float[]>  patrones(ArrayList<float[]> _collection_position, String letra){

        ArrayList<float[]> patronReturn = new ArrayList<float[]>();
        switch (letra){
            case "a":
                patronReturn.add(colection_position.get(0));
                return patronReturn;
            case "b":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(2));
                return patronReturn;
            case "c":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(1));
                return patronReturn;
            case "d":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(3));
                return patronReturn;
            case "e":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(3));
                return patronReturn;
            case "f":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(2));
                return patronReturn;
            case "g":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(2));
                patronReturn.add(colection_position.get(3));
                return patronReturn;
            case "h":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(2));
                patronReturn.add(colection_position.get(3));
                return patronReturn;
            case "i":
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(2));
                return patronReturn;
            case "j":
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(2));
                patronReturn.add(colection_position.get(3));
                return patronReturn;
            case "k":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(4));
                return patronReturn;
            case "l":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(2));
                patronReturn.add(colection_position.get(4));
                return patronReturn;
            case "m":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(4));
                return patronReturn;
            case "n":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(3));
                patronReturn.add(colection_position.get(4));
                return patronReturn;
            case "ñ":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(2));
                patronReturn.add(colection_position.get(3));
                patronReturn.add(colection_position.get(5));
                return patronReturn;
            case "o":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(3));
                patronReturn.add(colection_position.get(4));
                return patronReturn;
            case "p":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(2));
                patronReturn.add(colection_position.get(4));
                return patronReturn;
            case "q":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(2));
                patronReturn.add(colection_position.get(3));
                patronReturn.add(colection_position.get(4));
                return patronReturn;
            case "r":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(2));
                patronReturn.add(colection_position.get(3));
                patronReturn.add(colection_position.get(4));
                return patronReturn;
            case "s":
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(2));
                patronReturn.add(colection_position.get(4));
                return patronReturn;
            case "t":
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(2));
                patronReturn.add(colection_position.get(3));
                patronReturn.add(colection_position.get(4));
                return patronReturn;
            case "u":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(4));
                patronReturn.add(colection_position.get(5));
                return patronReturn;
            case "v":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(2));
                patronReturn.add(colection_position.get(4));
                patronReturn.add(colection_position.get(5));
                return patronReturn;
            case "w":
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(2));
                patronReturn.add(colection_position.get(3));
                patronReturn.add(colection_position.get(5));
                return patronReturn;
            case "x":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(4));
                patronReturn.add(colection_position.get(5));
                return patronReturn;
            case "y":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(3));
                patronReturn.add(colection_position.get(4));
                patronReturn.add(colection_position.get(5));
                return patronReturn;
            case "z":
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(3));
                patronReturn.add(colection_position.get(4));
                patronReturn.add(colection_position.get(5));
                return patronReturn;
            default:
                patronReturn.add(colection_position.get(0));
                patronReturn.add(colection_position.get(1));
                patronReturn.add(colection_position.get(2));
                patronReturn.add(colection_position.get(3));
                patronReturn.add(colection_position.get(4));
                patronReturn.add(colection_position.get(5));
                return patronReturn;

        }

    }
}