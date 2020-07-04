package com.galaxy.merchant.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kailash on 6/30/2017.
 */
public class RomanToDecimalNumberConverterTest {
    @Test
    public void shouldBeAbleToConvertValidRomans() throws Exception {
        int decimal = RomanToDecimalNumberConverter.romanToDecimal("XXXIX");
        assertEquals(39, decimal);

        decimal = RomanToDecimalNumberConverter.romanToDecimal("MMMM");
        assertEquals(4000, decimal);
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionForInvalidRomans() throws Exception {
        int decimal = RomanToDecimalNumberConverter.romanToDecimal("XXXX");
        assertEquals(40, decimal);
    }

}