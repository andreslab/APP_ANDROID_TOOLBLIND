package com.andreslab.tools_blind.commands;

import java.util.Hashtable;

/**
 * Created by macbookpro on 7/18/17.
 */

public class CommandList {

    final static String[] GlobalCommands = {
            "nueva foto",
            "nueva operación",
            "nueva llamada",
            "nueva traducción",
            "nueva alarma",
            "nuevo email",
            "nueva nota",
            /*:::::::::::::::::::::*/
            "nuevo diagrama",
            "nueva práctica", //braille digital
            "nuevo documento",
            "nuevo presentación",
            /*:::::::::::::::::::::*/
            "nuevo juego", //juego de memoria
            /*:::::::::::::::::::::*/
            "guardar ubicación"

    };

    final static Hashtable<String, String[]> LocalCommands = new Hashtable<String, String[]>();

    public CommandList() {

        LocalCommands.put("nueva foto", new String[]{"tomar foto", "guardar foto", "salir"});
        LocalCommands.put("nueva operación", new String[]{"-","salir"});
        LocalCommands.put("nueva llamada", new String[]{"contacto","salir"});
        LocalCommands.put("nueva traducción", new String[]{"ingles a español", "español a ingles","salir"});
        LocalCommands.put("nueva alarma", new String[]{"mensaje","dia", "hora","salir"});
        LocalCommands.put("nuevo email", new String[]{"emisor","receptor","tema","mensaje","salir"});
        LocalCommands.put("nueva nota", new String[]{"tema", "grabar","salir"});
        LocalCommands.put("nuevo diagrama", new String[]{"crear titulo", "secciones", "item","terminar edición"});
        LocalCommands.put("nueva práctica", new String[]{"salir"});
        LocalCommands.put("nuevo documento", new String[]{"terminar edición"});
        LocalCommands.put("nuevo presentación", new String[]{"terminar edición"});
        LocalCommands.put("nuevo juego", new String[]{"salir"});
        LocalCommands.put("guardar ubicación", new String[]{"salir"});
    }


}
