package com.galaxy.merchant.util;

import com.galaxy.merchant.exception.UnparsableException;
import com.galaxy.merchant.parser.*;

/**
 * Identifies the line provided.
 *
 * Created by Kailash on 6/28/2017.
 */
public final class InputLineIdentifier {

    public static final String UNPARSABLE_LINE = "I have no idea what are you talking";

    public static final String ASSIGNMENT_PATTERN = "^i?([A-Za-z]+) is ([I|V|X|L|C|D|M])$";
    public static final String CREDITING_PATTERN = "^(.+?) is ([0-9]+) ([c|C]redits)";
    public static final String HOW_MUCH_QUERY_PATTERN = "^i?how much is (([A-Za-z\\s])+)\\?$";
    public static final String HOW_MANY_QUERY_PATTERN = "^i?how many [c|C]redits is (([A-Za-z\\s])+)\\?$";

    private InputLineIdentifier(){}

    /**
     * Matches the incoming line pattern
     * @param line input line
     * @return ParsingStrategy type of line parsing
     * @throws UnparsableException unparsable exception if line is unidentified
     */
    public static ParsingStrategy identifyTheLineType(final String line) throws UnparsableException {
        if(line.matches(ASSIGNMENT_PATTERN)){
            return new AssignmentStrategy();
        } else if(line.matches(CREDITING_PATTERN)){
            return new CreditingStrategy();
        } else if(line.matches(HOW_MANY_QUERY_PATTERN)){
            return new HowManyQueryStrategy();
        } else if(line.matches(HOW_MUCH_QUERY_PATTERN)){
            return new HowMuchQueryStrategy();
        } else {
            throw new UnparsableException();
        }
    }

}
