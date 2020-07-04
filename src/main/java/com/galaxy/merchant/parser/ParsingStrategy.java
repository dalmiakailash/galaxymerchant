package com.galaxy.merchant.parser;

import com.galaxy.merchant.exception.UnparsableException;

/**
 * Parsing strategy for each given line
 * Created by Kailash on 6/28/2017.
 */
public interface ParsingStrategy {
    /**
     * Parse the given line and provide the outcome of it
     * @param line given line for parsing
     * @return Parsing Result
     */
    ParsingResult parse(final String line) throws UnparsableException;
}
