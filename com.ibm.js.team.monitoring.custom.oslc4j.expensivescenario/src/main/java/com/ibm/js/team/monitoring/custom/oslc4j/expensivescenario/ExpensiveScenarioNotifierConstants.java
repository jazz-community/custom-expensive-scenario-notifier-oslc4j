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
package com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario;

/**
 * Various constants used in the application.
 *
 */
public interface ExpensiveScenarioNotifierConstants {

	public static final String version = "1.0";

	// Commands and parameters
	public static final String PARAMETER_COMMAND = "command";
	public static final String PARAMETER_COMMAND_DESCRIPTION = "The command to execute.";
	public static final String PARAMETER_COMMAND_EXAMPLE = "exportConfigurations";


	public static final String PARAMETER_URL = "url";
	public static final String PARAMETER_URL_DESCRIPTION = "The Public URI of the application.";
	public static final String PARAMETER_URL_EXAMPLE = "https://clm.example.com:9443/rm/";
	public static final String PARAMETER_URL_PROTOTYPE = "https://<server>:port/<context>/";

	public static final String PARAMETER_USER = "user";
	public static final String PARAMETER_USER_ID_DESCRIPTION = "The user ID of a user.";
	public static final String PARAMETER_USER_ID_EXAMPLE = "ADMIN";
	public static final String PARAMETER_USER_PROTOTYPE = "<userId>";

	public static final String PARAMETER_PASSWORD = "password";
	public static final String PARAMETER_PASSWORD_DESCRIPTION = "The password of the user.";
	public static final String PARAMETER_PASSWORD_EXAMPLE = "******";
	public static final String PARAMETER_PASSWORD_PROTOTYPE = "<password>";


	// Headers
	public static final String DOORS_REQUEST_TYPE_HEADER = "DoorsRP-Request-Type";
	public static final String DOORS_REQUEST_TYPE_HEADER_VALUE = "public 2.0";
	public static final String ACCEPT_HEADER = "Accept";
	public static final String OSLC_VERSION_2_HEADER_VALUE = "2.0";
	public static final String CONTENT_TYPE_HEADER = "Content-Type";
	public static final String DNG_CM_CONFIGURATION_CONTEXT_HEADER = "Configuration-Context";
	public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";

	// Property namespaces and names for model searches
	public static final String DC_PROPERTY_NAME_TITLE = "title";

	public static final String RDF_PROPERTY_MEMBER_NAME = "member";

	public static final String CM_PROVIDER_CREATION_FACTORY = "creation";
	public static final String CM_SERVICE_PROVIDER_PROPERTY_NAME = "cmServiceProviders";
	public static final String PROCESS_NAMESPACE_PROPERTY_PROJECT_AREA = "projectArea";

	public static final String PARAMETER_SCENARIONAME = "scenarioName";
	public static final String PARAMETER_SCENARIONAME_DESCRIPTION = "A Scenario Name";
	public static final String PARAMETER_SCENARIONAME_PROTOTYPE = "<scenarioName>";
	public static final String PARAMETER_SCENARIONAME_EXAMPLE = "MyCustomExpensiveScenario";

	public static final String CMD_EXPENSIVE_SCENARIO = "expensiveScenario";
	public static final Object PARAMETER_MODE_START = "start";
	public static final Object PARAMETER_MODE_STOP = "stop";

	public static final String PARAMETER_MODE = "mode";
	public static final String PARAMETER_MODE_DESCRIPTION = "The mode of the operation: Start or Stop";
	public static final String PARAMETER_MODE_PROTOTYPE = "<mode>";
	public static final Object PARAMETER_MODE_EXAMPLE_START = PARAMETER_MODE_START;
	public static final Object PARAMETER_MODE_EXAMPLE_STOP = PARAMETER_MODE_STOP;
	

}
