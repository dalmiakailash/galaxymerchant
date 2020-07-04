package com.galaxy.merchant;

import com.galaxy.merchant.controller.ApplicationController;
import com.galaxy.merchant.exception.UnparsableException;
import com.galaxy.merchant.parser.ParsingResult;

import java.io.*;

/**
 * Main class for application. Command line accept input file path Created by
 * Kailash on 6/28/2017.
 */
public class GalaxyMerchant {

	public static void main(String[] args) {
		String inputFilePath = args[0];
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath))) {
			final ApplicationController applicationController = new ApplicationController();
			String readLine;
			while ((readLine = bufferedReader.readLine()) != null) {
				try {
					final ParsingResult.Result result = applicationController.getOutput(readLine);
					switch (result.getResultType()) {
					case OK:
						break;
					case HOW_MANY:
					case HOW_MUCH:
						System.out.println(result.getResult());
						break;
					default:
						break;
					}
				} catch (UnparsableException e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
