package com.galaxy.merchant.controller;

import com.galaxy.merchant.exception.UnparsableException;
import com.galaxy.merchant.parser.ParsingResult;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kailash on 6/29/2017.
 */
public class ApplicationControllerTest {

    @Test
    public void shouldReturnOkResult() throws IllegalAccessException, UnparsableException, InstantiationException {
        ParsingResult.Result parsingResult = new ApplicationController().getOutput("glob is I");
        assertEquals(ParsingResult.ResultType.OK, parsingResult.getResultType());

        parsingResult = new ApplicationController().getOutput("prok is V");
        assertEquals(ParsingResult.ResultType.OK, parsingResult.getResultType());

        parsingResult = new ApplicationController().getOutput("pish is X");
        assertEquals(ParsingResult.ResultType.OK, parsingResult.getResultType());

        parsingResult = new ApplicationController().getOutput("tegj is L");
        assertEquals(ParsingResult.ResultType.OK, parsingResult.getResultType());

        parsingResult = new ApplicationController().getOutput("glob glob Silver is 34 Credits");
        assertEquals(ParsingResult.ResultType.OK, parsingResult.getResultType());

        parsingResult = new ApplicationController().getOutput("glob prok Gold is 57800 Credits");
        assertEquals(ParsingResult.ResultType.OK, parsingResult.getResultType());

        parsingResult = new ApplicationController().getOutput("pish pish Iron is 3910 Credits");
        assertEquals(ParsingResult.ResultType.OK, parsingResult.getResultType());

        parsingResult = new ApplicationController().getOutput("how much is pish tegj glob glob ?");
        assertEquals(ParsingResult.ResultType.HOW_MUCH, parsingResult.getResultType());
        assertEquals("pish tegj glob glob is 42", parsingResult.getResult());

        parsingResult = new ApplicationController().getOutput("how many Credits is glob prok Silver ?");
        assertEquals(ParsingResult.ResultType.HOW_MANY, parsingResult.getResultType());
        assertEquals("glob prok Silver is 68.0 credits", parsingResult.getResult());

        parsingResult = new ApplicationController().getOutput("how many Credits is glob prok Gold ?");
        assertEquals(ParsingResult.ResultType.HOW_MANY, parsingResult.getResultType());
        assertEquals("glob prok Gold is 57800.0 credits", parsingResult.getResult());

        parsingResult = new ApplicationController().getOutput("how many Credits is glob prok Iron ?");
        assertEquals(ParsingResult.ResultType.HOW_MANY, parsingResult.getResultType());
        assertEquals("glob prok Iron is 782.0 credits", parsingResult.getResult());

    }

    @Test(expected = UnparsableException.class)
    public void shouldFailForWrongString() throws IllegalAccessException, UnparsableException, InstantiationException {
        new ApplicationController().getOutput("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
    }

}