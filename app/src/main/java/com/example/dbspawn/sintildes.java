package com.example.dbspawn;

/**
 * Created by Peto-1 on 1/12/2015.
 */


public class sintildes {

    public static String liquidpaper(String s) {


        s = s.replaceAll("[èéêë]","e");
        s = s.replaceAll("[ûù£]","u");
        s = s.replaceAll("[ïî¡]","i");
        s = s.replaceAll("[àâ]","a");
        s = s.replaceAll("[Ô¢]","o");

        s = s.replaceAll("[ÈÉÊË]","E");
        s = s.replaceAll("[ÛÙ]","U");
        s = s.replaceAll("[ÏÎ]","I");
        s = s.replaceAll("[ÀÂ]","A");
        s = s.replaceAll("[¤]","ñ");
        s = s.replaceAll("[ ]","a");
//        este es el mas yuca de los statements quita characteres invisibles o puntos de codigo sin usar
//        hemos visto que estos corresponden a las "e" en el examinando
        s = s.replaceAll("[\\p{C}]","e");


        return s;

    }
}
