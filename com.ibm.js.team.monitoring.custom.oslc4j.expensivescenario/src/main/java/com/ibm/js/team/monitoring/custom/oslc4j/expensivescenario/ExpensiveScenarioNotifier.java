/*******************************************************************************
 * Copyright (c) 2012 - 2019 IBM Corporation.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 *
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *
 *    Ralph Schoon - Initial implementation
 *******************************************************************************/
package com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario;

import java.net.URISyntaxException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario.commands.CommandFactory;
import com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario.framework.ICommand;
import com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario.util.TimeStampUtil;

/**
 * Main class that is called and controls the execution.
 * 
 * @see https://jazz.net/wiki/bin/view/Main/DNGServerAPI
 *
 */
public class ExpensiveScenarioNotifier {

	public static final Logger logger = LoggerFactory.getLogger(ExpensiveScenarioNotifier.class);

	/**
	 * Main entry point for the application. Gets and performs the command.
	 * 
	 * @param args
	 * @throws ParseException
	 * @throws URISyntaxException
	 */
	public static void main(String[] args) throws URISyntaxException, ParseException {
		logger.info("{}", TimeStampUtil.getTimestamp());
		logger.info("\nExpensive Scenario Announcer Version: {}", ExpensiveScenarioNotifierConstants.version);
		ExpensiveScenarioNotifier esn = new ExpensiveScenarioNotifier();
		boolean result = esn.execute(args);
		logger.info("{}", TimeStampUtil.getTimestamp());
		if (result) {
			logger.info("Success.");
		} else {
			logger.info("Failed.");
			System.exit(1);
		}
	}

	/**
	 * Execute the command
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public boolean execute(final String[] args) throws ParseException {
		boolean result = false;
		CommandLine cmd = null;
		Options options = new Options();
		options.addOption(ExpensiveScenarioNotifierConstants.PARAMETER_COMMAND, true,
				ExpensiveScenarioNotifierConstants.PARAMETER_COMMAND_EXAMPLE);
		try {
			// Parse the command line
			CommandLineParser cliParser = new DefaultParser();
			// ignore unrecognized options, we only care for other issues,
			cmd = cliParser.parse(options, args, true);
		} catch (ParseException e) {
			if (e instanceof MissingArgumentException) {
				logger.error("Missing command \n" + "Syntax: -command commandName {[-parameter] [parameterValue]}");
				printSupportedCommands();
				return result;
			}
			if (!(e instanceof UnrecognizedOptionException)) {
				logger.info("Failed.");
				throw (e);
			}
		}

		// Get the command name provided as option
		String command = cmd.getOptionValue(ExpensiveScenarioNotifierConstants.PARAMETER_COMMAND);
		if (command == null) {
			logger.error("Missing command \nSyntax: -command commandName {[-parameter] [parameterValue]}");
			printSupportedCommands();
			logger.info("Failed.");
			return result;
		}

		// get the class to execute
		ICommand execCommand = CommandFactory.INSTANCE().getCommandMap().get(command);
		if (execCommand == null) {
			logger.error("Unsupported command name '{}' ", command);
			printSupportedCommands();
			logger.info("Failed.");
			return result;
		}

		// run the command
		result = execCommand.run(options, args);
		return result;
	}

	/**
	 * Print the syntax.
	 */
	public void printSupportedCommands() {
		logger.error("Available commands: \n");
		CommandFactory.INSTANCE().printCommandSyntax();
	}
}
