/*******************************************************************************
 * Copyright (c) 2012 - 2013, 2018 IBM Corporation.
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
package com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.http.HttpStatus;
import org.eclipse.lyo.client.oslc.OSLCConstants;
import org.eclipse.lyo.client.oslc.jazz.JazzFormAuthClient;
import org.eclipse.lyo.client.oslc.jazz.JazzRootServicesHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario.ExpensiveScenarioNotifierConstants;
import com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario.framework.AbstractCommand;
import com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario.framework.ICommand;
import com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario.service.ExpensiveScenarioService;
import com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario.service.FilePersitentExpensiveScenarioService;
import com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario.service.IExpensiveScenarioService;

/**
 *
 */
public class ExpensiveScenarioNotifierCmd extends AbstractCommand implements ICommand {

	public static final Logger logger = LoggerFactory.getLogger(ExpensiveScenarioNotifierCmd.class);

	/**
	 * Create new command and give it the name
	 */
	public ExpensiveScenarioNotifierCmd() {
		super(ExpensiveScenarioNotifierConstants.CMD_EXPENSIVE_SCENARIO);
	}

	@Override
	public Options addCommandOptions(Options options) {
		options.addOption(ExpensiveScenarioNotifierConstants.PARAMETER_URL, true,
				ExpensiveScenarioNotifierConstants.PARAMETER_URL_DESCRIPTION);
		options.addOption(ExpensiveScenarioNotifierConstants.PARAMETER_USER, true,
				ExpensiveScenarioNotifierConstants.PARAMETER_USER_ID_DESCRIPTION);
		options.addOption(ExpensiveScenarioNotifierConstants.PARAMETER_PASSWORD, true,
				ExpensiveScenarioNotifierConstants.PARAMETER_PASSWORD_DESCRIPTION);
		options.addOption(ExpensiveScenarioNotifierConstants.PARAMETER_SCENARIONAME, true,
				ExpensiveScenarioNotifierConstants.PARAMETER_SCENARIONAME_DESCRIPTION);
		options.addOption(ExpensiveScenarioNotifierConstants.PARAMETER_MODE, true,
				ExpensiveScenarioNotifierConstants.PARAMETER_MODE_DESCRIPTION);
		return options;
	}

	@Override
	public boolean checkParameters(final CommandLine cmd) {
		boolean isValid = true;

		if (!(cmd.hasOption(ExpensiveScenarioNotifierConstants.PARAMETER_URL)
				&& cmd.hasOption(ExpensiveScenarioNotifierConstants.PARAMETER_USER)
				&& cmd.hasOption(ExpensiveScenarioNotifierConstants.PARAMETER_PASSWORD)
				&& cmd.hasOption(ExpensiveScenarioNotifierConstants.PARAMETER_SCENARIONAME)
				&& cmd.hasOption(ExpensiveScenarioNotifierConstants.PARAMETER_MODE)
				)) {
			isValid = false;
		}
		return isValid;
	}

	@Override
	public void printSyntax() {
		logger.info("{}", getCommandName());
		logger.info("\tSyntax : -{} {} -{} {} -{} {} -{} {} -{} {} -{} {}",
				ExpensiveScenarioNotifierConstants.PARAMETER_COMMAND, getCommandName(),
				ExpensiveScenarioNotifierConstants.PARAMETER_URL,
				ExpensiveScenarioNotifierConstants.PARAMETER_URL_PROTOTYPE,
				ExpensiveScenarioNotifierConstants.PARAMETER_USER,
				ExpensiveScenarioNotifierConstants.PARAMETER_USER_PROTOTYPE,
				ExpensiveScenarioNotifierConstants.PARAMETER_PASSWORD,
				ExpensiveScenarioNotifierConstants.PARAMETER_PASSWORD_PROTOTYPE,
				ExpensiveScenarioNotifierConstants.PARAMETER_SCENARIONAME,
				ExpensiveScenarioNotifierConstants.PARAMETER_SCENARIONAME_PROTOTYPE,
				ExpensiveScenarioNotifierConstants.PARAMETER_MODE,
				ExpensiveScenarioNotifierConstants.PARAMETER_MODE_PROTOTYPE);
		logger.info("\tExample: -{} {} -{} {} -{} {} -{} {} -{} {} -{} {}",
				ExpensiveScenarioNotifierConstants.PARAMETER_COMMAND, getCommandName(),
				ExpensiveScenarioNotifierConstants.PARAMETER_URL,
				ExpensiveScenarioNotifierConstants.PARAMETER_URL_EXAMPLE,
				ExpensiveScenarioNotifierConstants.PARAMETER_USER,
				ExpensiveScenarioNotifierConstants.PARAMETER_USER_ID_EXAMPLE,
				ExpensiveScenarioNotifierConstants.PARAMETER_PASSWORD,
				ExpensiveScenarioNotifierConstants.PARAMETER_PASSWORD_EXAMPLE,
				ExpensiveScenarioNotifierConstants.PARAMETER_SCENARIONAME,
				ExpensiveScenarioNotifierConstants.PARAMETER_SCENARIONAME_EXAMPLE,
				ExpensiveScenarioNotifierConstants.PARAMETER_MODE,
				ExpensiveScenarioNotifierConstants.PARAMETER_MODE_EXAMPLE);
	}

	@Override
	public boolean execute() {

		boolean result = false;

		// Get all the option values
		String webContextUrl = getCmd().getOptionValue(ExpensiveScenarioNotifierConstants.PARAMETER_URL);
		String user = getCmd().getOptionValue(ExpensiveScenarioNotifierConstants.PARAMETER_USER);
		String passwd = getCmd().getOptionValue(ExpensiveScenarioNotifierConstants.PARAMETER_PASSWORD);
		String scenarioName = getCmd().getOptionValue(ExpensiveScenarioNotifierConstants.PARAMETER_SCENARIONAME);
		String mode = getCmd().getOptionValue(ExpensiveScenarioNotifierConstants.PARAMETER_MODE);

		try {

			// Login
			JazzRootServicesHelper helper = new JazzRootServicesHelper(webContextUrl, OSLCConstants.OSLC_CM);
			logger.trace("Login");
			//	String authUrl = webContextUrl.replaceFirst("/rm", "/jts");
			String authUrl = webContextUrl;
			JazzFormAuthClient client = helper.initFormClient(user, passwd, authUrl);
			if (client.login() == HttpStatus.SC_OK) {

				IExpensiveScenarioService expensiveScenarioService = new ExpensiveScenarioService(authUrl, scenarioName);
				FilePersitentExpensiveScenarioService expensiveScenario = new FilePersitentExpensiveScenarioService(expensiveScenarioService);
				if(ExpensiveScenarioNotifierConstants.PARAMETER_MODE_START.equals(mode)) {
					expensiveScenario.start(client);
				}
				if(ExpensiveScenarioNotifierConstants.PARAMETER_MODE_STOP.equals(mode)) {
					expensiveScenario.stop(client);
				}
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return result;
	}
}
