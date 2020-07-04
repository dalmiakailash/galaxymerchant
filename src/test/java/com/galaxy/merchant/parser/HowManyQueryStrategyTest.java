package com.galaxy.merchant.parser;

import com.galaxy.merchant.exception.UnparsableException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kailash on 6/29/2017.
 */
public class HowManyQueryStrategyTest {

    @Test
    public void shouldReturnProperResult() throws UnparsableException {
        ParsingResult parsingResult = ParsingResult.getInstance();
        parsingResult.addAssignmentResult("pish", "X");
        parsingResult.addAssignmentResult("glob", "I");
        parsingResult.addAssignmentResult("prok", "V");
        parsingResult.addAssignmentResult("tegj", "L");
        parsingResult.addCreditingResult("Iron", 20, 3910);
        parsingResult = new HowManyQueryStrategy().parse("how many Credits is glob prok Iron ?");
        assertEquals("glob prok Iron is "+(4*(3910.0/20.0))+" credits", parsingResult.getResult().getResult());
    }

}