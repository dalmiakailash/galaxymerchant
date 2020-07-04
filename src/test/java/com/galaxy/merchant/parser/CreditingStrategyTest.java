package com.galaxy.merchant.parser;

import com.galaxy.merchant.exception.UnparsableException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kailash on 6/29/2017.
 */
public class CreditingStrategyTest {
    ParsingResult parsingResult = ParsingResult.getInstance();

    @Test
    public void parsingShouldResultOk() throws Exception {
        parsingResult.addAssignmentResult("glob", "I");
        parsingResult = new CreditingStrategy().parse("glob glob Silver is 34 Credits");
        assertEquals(ParsingResult.ResultType.OK, parsingResult.getResult().getResultType());
    }

    @Test(expected = UnparsableException.class)
    public void parsingShouldResultUnparsable() throws Exception {
        parsingResult.addAssignmentResult("glob", "I");
        parsingResult = new CreditingStrategy().parse("glob glob glob is 34 Credits");
    }

    @Test(expected = UnparsableException.class)
    public void parsingShouldResultUnparsableWhenLineIsNotCorrect() throws Exception {
        parsingResult.addAssignmentResult("glob", "I");
        parsingResult = new CreditingStrategy().parse("Silver is 34 Credits");
    }

}