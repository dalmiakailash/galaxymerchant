package com.galaxy.merchant.parser;

import com.galaxy.merchant.exception.UnparsableException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kailash on 6/29/2017.
 */
public class HowMuchQueryStrategyTest {

    @Test
    public void shouldReturnSuccessfully() throws UnparsableException {
        ParsingResult parsingResult = ParsingResult.getInstance();
        parsingResult.addAssignmentResult("pish", "X");
        parsingResult.addAssignmentResult("glob", "I");
        parsingResult.addAssignmentResult("prok", "V");
        parsingResult.addAssignmentResult("tegj", "L");

        parsingResult = new HowMuchQueryStrategy().parse("how much is pish tegj glob glob ?");
        assertEquals(ParsingResult.ResultType.HOW_MUCH, parsingResult.getResult().getResultType());
        assertEquals("pish tegj glob glob is 42", parsingResult.getResult().getResult());
    }

}