package com.example.dbspawn;

/**
 * Created by Peto-1 on 1/12/2015.
 */


public class sintildes {

    public static String chulon(String s) {


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
        s = s.replaceAll("[\\p{C}]","e");


        return s;
        // output : E,E,E,E,U,U,I,I,A,A,O,e,e,e,e,u,u,i,i,a,a,o
    }
}
