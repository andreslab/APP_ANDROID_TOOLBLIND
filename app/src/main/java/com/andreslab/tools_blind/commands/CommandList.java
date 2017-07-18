package com.andreslab.tools_blind.commands;

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
            "nueva practica", //braille digital
            "nuevo documento",
            "nuevo presentación",
            /*:::::::::::::::::::::*/
            "nuevo juego", //juego de memoria
            /*:::::::::::::::::::::*/
            "guardar ubicación"

    };


    final static String[][] LocalCommands = {
            {"tomar foto", "guardar foto"},
            {"-"},
            {"contacto"}, //automatico
            {"ingles a español", "español a ingles"},
            {"mensaje","dia", "hora"}, //automatico
            {"emisor","receptor","tema","mensaje"}, //automatico
            {"tema", "grabar"}, //automatico
            /*::::::::::::::::::::*/
            {"crear titulo", "secciones", "item"},
            {},
            {},
            {},
            /*::::::::::::::::::::*/
            {},
            /*::::::::::::::::::::*/
            {}

    };
}
