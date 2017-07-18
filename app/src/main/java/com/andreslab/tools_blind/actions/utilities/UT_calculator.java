package com.andreslab.tools_blind.actions.utilities;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by macbookpro on 7/18/17.
 */

public class UT_calculator {

    ArrayList<String> palabras = new ArrayList<String>();
    Character[] p;
    int contador = 0;

    public UT_calculator(String command){
        command += " ";
        for (Character c : command.toCharArray()){
            if(!c.equals(32)){
                p[contador] = c;
                contador ++;
            }else {
                contador = 0;
                palabras.add(p.toString());
            }
        }

        Log.d("UT_CALCULATOR", palabras.get(0).toString());
    }


}
