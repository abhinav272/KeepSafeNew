package com.abhinav.keepsafe.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by abhinav.sharma on 20/10/17.
 */

public class Formatter {

    public static CharSequence getFormattedBankCardNumber(CharSequence sequence) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        DecimalFormat fmt = new DecimalFormat("0000,0000,0000,0000", symbols);
        return fmt.format(Double.valueOf(sequence.toString()));
    }

    public static CharSequence removeStyledBankCardNumber(CharSequence sequence) {
        return sequence.toString().replaceAll(" ", "");
    }
}
