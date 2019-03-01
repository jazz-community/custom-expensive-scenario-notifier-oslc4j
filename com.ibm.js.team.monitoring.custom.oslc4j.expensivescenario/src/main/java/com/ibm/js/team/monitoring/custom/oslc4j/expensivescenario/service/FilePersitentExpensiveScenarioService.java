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
package com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario.service;

import java.io.FileWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.lyo.client.oslc.jazz.JazzFormAuthClient;

/**
 * Service to execute a start and stop for an expensive scenario. 
 * The information to stop the expensive scenario is persisted as a file.
 *
 */
public class FilePersitentExpensiveScenarioService {

	private static final String SCENARIO_RESPONSE_FILE_EXTENSION = ".json";
	private static final String SCENARIO_RESPONSE_FILE_PATH_ROOT_FOLDER = "./";

	private IExpensiveScenarioService fExpensiveScenarioService=null;

	/**
	 * Start and stop expensive scenario counter are performed persisting the
	 * scenario counter in a file or pass it as string. See option
	 * persistStartAsFile.
	 * 
	 * @param teamRepository     Team Repository
	 * @param publicURI          Public URI of the target CLM server
	 * @param scenarioName       the name of the scenario
	 * @throws URISyntaxException
	 */
	public FilePersitentExpensiveScenarioService(IExpensiveScenarioService expensiveScenarioService) throws URISyntaxException {
		if(expensiveScenarioService==null)
			throw new NullPointerException("Expensive Scenario Service can not be null");
		fExpensiveScenarioService = expensiveScenarioService;
	}


	/**
	 * Start the custom expensive scenario.
	 * Store the request response data in a file to persist it.
	 *  
	 * @throws Exception
	 */
	public void start(final JazzFormAuthClient client) throws Exception {
		String responseString = fExpensiveScenarioService.start(client);
		FileWriter fileWriter = new FileWriter(getScenarioResponseFileName());
		fileWriter.write(responseString);
		fileWriter.close();
	}

	/**
	 * Stop the custom expensive scenario.
	 * Read the request response data from a file that persists it.
	 * 
	 * @throws Exception
	 */
	public void stop(final JazzFormAuthClient client)
			throws Exception {
		Path path = Paths.get(getScenarioResponseFileName());
		String startRequestResponse = new String(Files.readAllBytes(path));
		Files.delete(path);
		fExpensiveScenarioService.stop(client, startRequestResponse);
	}

	/**
	 * @return the name of the file to contain the request respones information
	 * @throws Exception 
	 */
	private String getScenarioResponseFileName() throws Exception {
		return SCENARIO_RESPONSE_FILE_PATH_ROOT_FOLDER + fExpensiveScenarioService.getScenarioName() + SCENARIO_RESPONSE_FILE_EXTENSION;
	}
}
