package com.galaxy.merchant.parser;

import com.galaxy.merchant.exception.UnparsableException;
import com.galaxy.merchant.util.InputLineIdentifier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse line with patten of {@link InputLineIdentifier#ASSIGNMENT_PATTERN}
 * Created by Kailash on 6/29/2017.
 */
public class AssignmentStrategy implements ParsingStrategy {

    @Override
    public ParsingResult parse(final String line) throws UnparsableException {
        final ParsingResult parsingResult = ParsingResult.getInstance();
        final Matcher matcher = Pattern.compile(InputLineIdentifier.ASSIGNMENT_PATTERN).matcher(line);
        if(matcher.matches()){
            parsingResult.addAssignmentResult(matcher.group(1), matcher.group(2));
        } else {
            throw new UnparsableException();
        }
        return parsingResult;
    }
}
