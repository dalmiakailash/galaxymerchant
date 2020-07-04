package com.galaxy.merchant.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Converts roman to numeral
 * Created by Kailash on 6/29/2017.
 */
public class RomanToDecimalNumberConverter {

    private static final String VALID_ROMAN = "^(?i)M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

    private static final Map<String, Integer> ROMAN_MAP = new HashMap<String, Integer>() {
        /**
		 * 
		 */
		private static final long serialVersionUID = 3676269682067454767L;

		{
            put("M", 1000);
            put("D", 500);
            put("C", 100);
            put("L", 50);
            put("X", 10);
            put("V", 5);
            put("I", 1);
        }
    };

    /**
     * Converts given roman number to readable decimal format
     * @param romanNumber
     * @throws Exception when given roman is not standard
     */
    public static int romanToDecimal(final String romanNumber) throws Exception {
        if(!romanNumber.matches(VALID_ROMAN)){
            throw new Exception("Unparsable roman string "+romanNumber);
        }
        int decimal = 0;
        int lastNumber = 0;
        String romanNumeral = romanNumber.toUpperCase();
        /* operation to be performed on upper cases even if user
           enters roman values in lower case chars */
        for (int x = romanNumeral.length() - 1; x >= 0; x--) {
            decimal = processDecimal(ROMAN_MAP.get(String.valueOf(romanNumber.charAt(x))), lastNumber, decimal);
            lastNumber = ROMAN_MAP.get(String.valueOf(romanNumber.charAt(x)));
        }
        return decimal;
    }

    private static int processDecimal(int decimal, int lastNumber, int lastDecimal) {
        if (lastNumber > decimal) {
            return lastDecimal - decimal;
        } else {
            return lastDecimal + decimal;
        }
    }

}
