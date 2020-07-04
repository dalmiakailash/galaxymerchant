package com.galaxy.merchant.parser;

import com.galaxy.merchant.exception.UnparsableException;
import com.galaxy.merchant.util.InputLineIdentifier;
import com.galaxy.merchant.util.RomanToDecimalNumberConverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse line with patten of {@link InputLineIdentifier#CREDITING_PATTERN}
 * Created by Kailash on 6/29/2017.
 */
public class CreditingStrategy implements ParsingStrategy {
    @Override
    public ParsingResult parse(final String line) throws UnparsableException {
        final ParsingResult parsingResult = ParsingResult.getInstance();
        final Matcher matcher = Pattern.compile(InputLineIdentifier.CREDITING_PATTERN).matcher(line);
        if(matcher.matches()){
            final String[] firstGroup = matcher.group(1).split("\\s");
            if(firstGroup.length >= 2){
                final String metalName = firstGroup[firstGroup.length - 1];
                if(parsingResult.getSymbolForGalactic(metalName) == null){
                    final StringBuilder stringJoiner = new StringBuilder("");
                    for(int i=0; i < firstGroup.length - 1; i++){
                        stringJoiner.append(parsingResult.getSymbolForGalactic(firstGroup[i]));
                    }
                    try {
                        final int units = RomanToDecimalNumberConverter.romanToDecimal(stringJoiner.toString());
                        parsingResult.addCreditingResult(metalName, units, Double.parseDouble(matcher.group(2)));
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
