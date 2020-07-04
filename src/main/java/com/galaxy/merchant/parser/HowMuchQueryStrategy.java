package com.galaxy.merchant.parser;

import com.galaxy.merchant.exception.UnparsableException;
import com.galaxy.merchant.util.InputLineIdentifier;
import com.galaxy.merchant.util.RomanToDecimalNumberConverter;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse line with patten of {@link InputLineIdentifier#HOW_MUCH_QUERY_PATTERN}
 * Created by Kailash on 6/29/2017.
 */
public class HowMuchQueryStrategy implements ParsingStrategy {
    private final String response = "{0}is {1}";

    @Override
    public ParsingResult parse(String line) throws UnparsableException {
        final ParsingResult parsingResult = ParsingResult.getInstance();
        final Matcher matcher = Pattern.compile(InputLineIdentifier.HOW_MUCH_QUERY_PATTERN).matcher(line);
        if(matcher.matches()){
            final String suffix = matcher.group(1);
            final String[] units = suffix.split("\\s");
            final StringBuilder stringJoiner = new StringBuilder("");
            for(int i=0; i < units.length; i++){
                stringJoiner.append(parsingResult.getSymbolForGalactic(units[i]));
            }
            try {
                final int unit = RomanToDecimalNumberConverter.romanToDecimal(stringJoiner.toString());
                final String resultString = MessageFormat.format(response, suffix, unit);
                final ParsingResult.Result result = new ParsingResult.Result(ParsingResult.ResultType.HOW_MUCH,
                        resultString);
                parsingResult.addHowManyResult(result);
            } catch (final Exception e) {
                throw new UnparsableException();
            }

        } else {
            throw new UnparsableException();
        }
        return parsingResult;
    }
}
