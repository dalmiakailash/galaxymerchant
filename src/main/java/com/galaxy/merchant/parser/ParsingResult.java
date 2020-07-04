package com.galaxy.merchant.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kailash on 6/28/2017.
 */
public class ParsingResult {

    private volatile static ParsingResult INSTANCE = null;

    private Map<String, String> galacticUnits = new HashMap<String, String>();

    private Map<String, Double> creditsItemPerUnitPricing = new HashMap<String, Double>();

    private Result result = new Result(ResultType.OK, ResultType.OK.name());

    private ParsingResult(){}

    public static ParsingResult getInstance(){
        if(INSTANCE == null){
            synchronized (ParsingResult.class){
                if(INSTANCE == null){
                    INSTANCE = new ParsingResult();
                }
            }
        }
        return INSTANCE;
    }

    public void addAssignmentResult(final String galacticUnit, final String romanNumeral){
        galacticUnits.put(galacticUnit, romanNumeral);
        result = new Result(ResultType.OK, "OK");
    }

    public void addCreditingResult(final String item, final double units, final double credits){
        creditsItemPerUnitPricing.put(item.toLowerCase(), credits/units);
        result = new Result(ResultType.OK, "OK");
    }

    public void addHowMuchCreditingResult(final Result result){
        this.result = result;
    }

    public void addHowManyResult(final Result result){
        this.result = result;
    }

    public void addUnidentifiedResult(final Result result){
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public String getSymbolForGalactic(final String galactic){
        return galacticUnits.get(galactic);
    }

    public Double perUnitPricingForMetal(final String metal){
        return creditsItemPerUnitPricing.get(metal.toLowerCase());
    }

    public enum ResultType{
        OK,
        HOW_MUCH,
        HOW_MANY,
        UNPARSABLE;
    }

    public static final class Result{
        private final ResultType resultType;
        private final String result;

        public Result(final ResultType resultType, final String result){
            this.result = result;
            this.resultType = resultType;
        }

        public ResultType getResultType() {
            return resultType;
        }

        public String getResult() {
            return result;
        }
    }
}
