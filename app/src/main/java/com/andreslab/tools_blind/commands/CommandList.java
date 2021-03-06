package com.andreslab.tools_blind.commands;

import java.util.Hashtable;

/**
 * Created by macbookpro on 7/18/17.
 */

public class CommandList {

    final static String[] GlobalCommands = {
            "nueva foto",
            //"nueva operación",
            "nueva llamada",
            //"nueva traducción",
            "nueva alarma",
            "nuevo mail",
            //"nueva nota",
            "nuevo mensaje",
            /*:::::::::::::::::::::*/
            //"nuevo diagrama",
            "nueva práctica", //braille digital
            //"nuevo documento",
            //"nuevo presentación",
            /*:::::::::::::::::::::*/
            //"nuevo juego", //juego de memoria
            /*:::::::::::::::::::::*/
           // "guardar ubicación"
            "nueva nota",
            "última nota"


    };

    final static Hashtable<String, String[]> LocalCommands = new Hashtable<String, String[]>();

    public CommandList() {

        //::::::::::utilidades:::::::::::
        LocalCommands.put("nueva foto", new String[]{"tomar foto", "guardar foto", "salir"});
        //LocalCommands.put("nueva operación", new String[]{"-","salir"});
        LocalCommands.put("nueva llamada", new String[]{"contacto","salir"});
        //LocalCommands.put("nueva traducción", new String[]{"ingles a español", "español a ingles","salir"});
        LocalCommands.put("nueva alarma", new String[]{"hora","salir"});
        LocalCommands.put("nuevo mail", new String[]{"contacto","asunto","mensaje","salir"});
        //LocalCommands.put("nueva nota", new String[]{"tema", "grabar","salir"});
        LocalCommands.put("nuevo mensaje", new String[]{"contacto", "mensaje","salir"});

        //:::::::::::educativa::::::::::::
        //LocalCommands.put("nuevo diagrama", new String[]{"crear título", "crear división","sección","texto", "item","resumen","terminar edición"}); //sección son los niveles de arriba hacia abajo del mapa e item es la subdivision por cada sección
        LocalCommands.put("nueva práctica", new String[]{"iniciar","reiniciar","salir"});
        //LocalCommands.put("nuevo documento", new String[]{"crear título", "crear subtítulo","alinear","punto seguido","punto final","resumen","terminar edición"});
        //LocalCommands.put("nuevo presentación", new String[]{"crear diapositiva","crear título", "crear subtítulo","texto", "alinear","punto seguido","punto final","resumen","terminar edición"});
        //:::::::::::extras::::::::::::::
        //LocalCommands.put("nuevo juego", new String[]{"iniciar","reiniciar","salir"});
        //LocalCommands.put("guardar ubicación", new String[]{"titulo","guardar","salir"});
        LocalCommands.put("nueva nota", new String[]{"nombre","salir"});
        LocalCommands.put("última nota", new String[]{"nombre","salir"});

    }


}
