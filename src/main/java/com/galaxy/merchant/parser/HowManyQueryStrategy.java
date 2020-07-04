package com.galaxy.merchant.parser;

import com.galaxy.merchant.exception.UnparsableException;
import com.galaxy.merchant.util.InputLineIdentifier;
import com.galaxy.merchant.util.RomanToDecimalNumberConverter;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse line with patten of {@link InputLineIdentifier#HOW_MANY_QUERY_PATTERN}
 * Created by Kailash on 6/29/2017.
 */
public class HowManyQueryStrategy implements ParsingStrategy {

    private final String response = "{0}is {1} credits";

    @Override
    public ParsingResult parse(String line) throws UnparsableException{
        final ParsingResult parsingResult = ParsingResult.getInstance();
        final Matcher matcher = Pattern.compile(InputLineIdentifier.HOW_MANY_QUERY_PATTERN).matcher(line);
        if(matcher.matches()){
            final String suffix = matcher.group(1);
            final String[] unitsAndMetalName = suffix.split("\\s");
            if(unitsAndMetalName.length >= 2){
                final String metalName = unitsAndMetalName[unitsAndMetalName.length - 1];
                if(parsingResult.getSymbolForGalactic(metalName) == null
                        && parsingResult.perUnitPricingForMetal(metalName) != null){
                    final StringBuilder stringJoiner = new StringBuilder("");
                    for(int i=0; i < unitsAndMetalName.length - 1; i++){
                        stringJoiner.append(parsingResult.getSymbolForGalactic(unitsAndMetalName[i]));
                    }
                    try {
                        final int units = RomanToDecimalNumberConverter.romanToDecimal(stringJoiner.toString());
                        final String resultString = MessageFormat.format(response, suffix,
                                String.valueOf(Double.valueOf(units) * parsingResult.perUnitPricingForMetal(metalName)));
                        final ParsingResult.Result result = new ParsingResult.Result(ParsingResult.ResultType.HOW_MANY,
                                resultString);
                        parsingResult.addHowManyResult(result);
                    } catch (final Exception e) {
                        throw new UnparsableException();
                    }

                } else {
                    throw new UnparsableException();
                }

            } else {
                throw new UnparsableException();
            }

        } else {
            throw new UnparsableException();
        }
        return parsingResult;
    }
}
