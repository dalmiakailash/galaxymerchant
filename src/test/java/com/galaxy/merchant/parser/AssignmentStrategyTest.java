package com.galaxy.merchant.parser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kailash on 6/29/2017.
 */
public class AssignmentStrategyTest {

    @Test
    public void parse() throws Exception {
        final ParsingResult parsingResult = new AssignmentStrategy().parse("glob is I");
        assertNotNull(parsingResult);
        assertEquals(ParsingResult.ResultType.OK,parsingResult.getResult().getResultType());
    }

}