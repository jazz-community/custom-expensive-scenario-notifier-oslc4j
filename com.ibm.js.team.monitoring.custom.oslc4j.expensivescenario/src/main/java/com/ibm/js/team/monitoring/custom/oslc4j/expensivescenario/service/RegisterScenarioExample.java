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
package com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario.service;

import java.net.URISyntaxException;

import org.eclipse.lyo.client.oslc.jazz.JazzFormAuthClient;

import com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario.ExpensiveScenarioNotifierConstants;
import com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario.commands.ExpensiveScenarioNotifierCmd;

public class RegisterScenarioExample {

	/**
	 * Examples of how to use the services
	 * 
	 * @param client
	 * @param webContextUrl
	 * @param scenarioName
	 * @throws Exception
	 */
	public static void serviceWorkflow(JazzFormAuthClient client, String webContextUrl, String scenarioName)
			throws Exception {

		// Simple scenario persisted in String
		IExpensiveScenarioService service = new ExpensiveScenarioService(client, webContextUrl, scenarioName);
		String scenarioInstance = service.start();
		//
		// TODO: Your Code goes here
		//
		service.stop(scenarioInstance);

		// Example with file persistence
		IPersistedExpensiveScenarioService persistedService = new FilePersitentExpensiveScenarioService(
				new ExpensiveScenarioService(client, webContextUrl, scenarioName));
		persistedService.start();
		//
		// TODO: Your Code goes here
		//
		persistedService.stop();

	}

	/**
	 * Register scenario
	 * 
	 * @see https://jazz.net/wiki/bin/view/Deployment/CreateCustomScenarios
	 * 
	 * @param client
	 * @param webContextUrl
	 * @param mode
	 * @param scenarioName
	 * @return
	 * @throws URISyntaxException
	 * @throws Exception
	 */
	public static boolean registerScenario(JazzFormAuthClient client, String webContextUrl, String mode,
			String scenarioName) throws URISyntaxException, Exception {
		IExpensiveScenarioService expensiveScenarioService = new ExpensiveScenarioService(client, webContextUrl,
				scenarioName);
		IPersistedExpensiveScenarioService filePersistedExpensiveScenario = new FilePersitentExpensiveScenarioService(
				expensiveScenarioService);

		if (ExpensiveScenarioNotifierConstants.PARAMETER_MODE_START.equals(mode)) {
			filePersistedExpensiveScenario.start();
			return true;
		}
		if (ExpensiveScenarioNotifierConstants.PARAMETER_MODE_STOP.equals(mode)) {
			filePersistedExpensiveScenario.stop();
			return true;
		}
		if (ExpensiveScenarioNotifierConstants.PARAMETER_MODE_DEBUG.equals(mode)) {
			String scenarioInstance = expensiveScenarioService.start();
			expensiveScenarioService.stop(scenarioInstance);
			return true;
		}
		ExpensiveScenarioNotifierCmd.logger.info("Command mode not supported '{}'", mode);
		return false;
	}

}
