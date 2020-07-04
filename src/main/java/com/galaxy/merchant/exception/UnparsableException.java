package com.galaxy.merchant.exception;

import com.galaxy.merchant.util.InputLineIdentifier;

/**
 * Created by Kailash on 6/29/2017.
 */
public class UnparsableException extends Exception{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8483124092459105686L;

	public UnparsableException(){
        super(InputLineIdentifier.UNPARSABLE_LINE);
    }
    
}
