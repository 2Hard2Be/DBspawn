package com.example.dbspawn;

import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Created by Peto-1 on 1/12/2015.
 */


public class sintildes {






    public static String liquidpaper(String s) {



            s = s.replaceAll("[èéêë]", "e");
            s = s.replaceAll("[ûù£]", "u");
            s = s.replaceAll("[ïî¡]", "i");
            s = s.replaceAll("[àâ]", "a");
            s = s.replaceAll("[Ô¢]", "o");

            s = s.replaceAll("[ÈÉÊË]", "E");
            s = s.replaceAll("[ÛÙ]", "U");
            s = s.replaceAll("[ÏÎ]", "I");
            s = s.replaceAll("[ÀÂ]", "A");
            s = s.replaceAll("[¤]", "ñ");
            s = s.replaceAll("[ ]", "a");

            //Replace non-diacritics as their equivalent characters
            s = s.replaceAll("\u0141", "l"); // BiaLystock
            s = Normalizer.normalize(s, Normalizer.Form.NFD);
            s = s.replaceAll("[\\p{Cc}]", "CC");
            s = s.replaceAll("[\\p{Cf}]", "FF");
            s = s.replaceAll("[\\p{Co}]", "OO");
            s = s.replaceAll("[\\p{Cs}]", "SS");
            s = s.replaceAll("[\\p{Cn}]", "us");

//

            return s;
        }
    }
