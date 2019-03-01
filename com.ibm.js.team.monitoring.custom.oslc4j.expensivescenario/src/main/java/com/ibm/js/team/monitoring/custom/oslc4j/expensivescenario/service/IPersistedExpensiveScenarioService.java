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

import org.eclipse.lyo.client.oslc.jazz.JazzFormAuthClient;

public interface IPersistedExpensiveScenarioService {

	/**
	 * Start the custom expensive scenario.
	 * Store the request response data in a file to persist it.
	 *  
	 * @throws Exception
	 */
	void start(JazzFormAuthClient client) throws Exception;

	/**
	 * Stop the custom expensive scenario.
	 * Read the request response data from a file that persists it.
	 * 
	 * @throws Exception
	 */
	void stop(JazzFormAuthClient client) throws Exception;

}