package com.andreslab.tools_blind;

/**
 * Created by macbookpro on 7/13/17.
 */

public class COMMANDS {

    //comando de creación
    static public String NUEVA_PRESENTACION = "nueva presentación";
        //sub-comandos:

        //elementos para crear
        static public String NUEVA_VISTA = "nueva vista";   //+ variable
        static public String NUEVO_TITULO = "nuevo título"; //+ variable que identifica al titulo
        static public String NUEVO_TEXTO = "nuevo texto";   //+ variable que identifica al texto

        //seleccionar elementos a mdificar
        static public String SELECCIONAR_ELEMENTO = "nuevo título"; //+ variable que identifica al elemento
        static public String REEMPLAZAR_ELEMENTO = "reemplazar"; //+ variable que identifica al elemento

        //modificacdores
        static public String INGRESAR_TEXTO = "nuevo título";
        static public String ALINEAR = "nuevo título"; //izquierda, central, derecho
        static public String TAMANO = "nuevo título"; //titulo, subtitulo o texto
        static public String ELIMINAR = "eliminar"; //+ varible de elemento
        static public String SUBRAYAR = "subrayar";
        static public String GUARDAR = "guardar";
        static public String DESCARTAR = "descartar";
        //confirmación
            static public String SI = "si";
            static public String NO = "no";



        //utilites
        static public String IMPRIMIR = "imprimir";
        /*
        * + "elementos" //lista de
        * + "contenido de" + variable de un elemento
        * + "vistas" //las variables que identifican a cada vista
        * + "detalles" //imprime elemento de la vista actual
        * + "detalles" + variable de vista //imprime los elementos de un lista en particular
        * */
        static public String IR = "ir a"; //+ variable de la vista

    static public String NUEVA_GRABACION = "nueva nota";
    static public String NUEVA_RUTA = "nueva ruta"; //comienza mapeo de ruta
    static public String NUEVO_EVENTO = "nueva evento"; //evento del calendario
    static public String NUEVA_ALARMA = "nueva alarma";
    static public String NUEVA_UBICACION = "nueva ubicación"; //guarda las coordenadas gos de un lugar
    static public String NUEVA_FOTO = "nueva foto";



    static public String BRAILLE_DIGITAL = "diccionario";
    static public String EJERCICIOS = "reforzar";
    static public String TRANDUCCION = "traducción";


    //BASE DE DATOS
    static public String COLECCION_NOTAS = "colección de notas";
    static public String COLECCION_PRESENTACIONES = "colección de presentaciones";
    static public String COLECCION_RUTAS = "colección de rutas";
    static public String COLECCION_ALERTAS = "colección de alertas";
    static public String COLECCION_UBICACIONES = "colección de ubicaciones";

    //utilidades
    static public String UT_HISTORIAL = "historial"; //lista de ultimos comandos usados
    static public String UT_AYUDA = "ayuda"; //lista de comandos que se puede utilizar
        //AYUDA + COMANDO: opciones para un comando
    static public String UT_OPCIONES = "opciones"; //enlista comados de la accion en la que te encuentras
    static public String UT_CANCELAR = "cancelar comando"; // + nombre de comando que quiere cerrar
    static public String UI_REMOVER_COMANDO = "remover"; //+ cantidad de comandos a remover desde el ultimo




}
