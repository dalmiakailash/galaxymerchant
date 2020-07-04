package com.galaxy.merchant.controller;

import com.galaxy.merchant.exception.UnparsableException;
import com.galaxy.merchant.parser.ParsingResult;
import com.galaxy.merchant.util.InputLineIdentifier;

/**
 * Created by Kailash on 6/28/2017.
 */
public class ApplicationController {

    public ApplicationController() {
    }

    public ParsingResult.Result getOutput(final String input) throws UnparsableException {
        return InputLineIdentifier.identifyTheLineType(input).parse(input).getResult();
    }

}
