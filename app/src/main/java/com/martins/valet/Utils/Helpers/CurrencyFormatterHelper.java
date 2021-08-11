package com.martins.valet.Utils.Helpers;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by policante on 7/16/16.
 */
public class CurrencyFormatterHelper {
    public static String formatNumberToReal(double value) {
        Locale brazil = new Locale( "pt", "BR" );
        NumberFormat nfVal = NumberFormat.getCurrencyInstance(brazil);
        return nfVal.format(value);
    }
}
