package com.martins.valet.Utils.Helpers;

/**
 * Created by policante on 7/10/16.
 */
public class MaskHelper {

    public static String plateMask(String plate){
        if (plate == null){
            return null;
        }

        String aux = plate.trim();

        if (aux.length() == 7){
            return aux.substring(0,3).toUpperCase() + "-" + aux.substring(3);
        }

        return aux;

    }

}
