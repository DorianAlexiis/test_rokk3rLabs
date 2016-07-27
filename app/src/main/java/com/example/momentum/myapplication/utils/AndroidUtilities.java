package com.example.momentum.myapplication.utils;

/**
 * Created by Dorian on 27/07/2016.
 */

public class AndroidUtilities {

    /**
     * Explode.
     *
     * @param texto
     *            the texto
     * @param delimitador
     *            the delimitador
     * @return the string[]
     */
    public static String[] explode(String texto, String delimitador) {
        String[] resultado;
        resultado = texto.split(delimitador);
        return resultado;
    }
}
