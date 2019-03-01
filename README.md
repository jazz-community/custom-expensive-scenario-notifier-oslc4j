# custom-expensive-scenario-notifier-oslc4j
CLM allows logging of custom expensive scenarios. This project contains code to do this using OSLC4J


Documentation under construction.

	Syntax : -command expensiveScenario -url https://<server>:port/<context>/ -user <userId> -password <password> -scenarioName <scenarioName> -mode <mode>
  
	Example: -command expensiveScenario -url https://clm.example.com:9443/rm/ -user ADMIN -password ****** -scenarioName MyCustomExpensiveScenario -mode start
	Example: -command expensiveScenario -url https://clm.example.com:9443/rm/ -user ADMIN -password ****** -scenarioName MyCustomExpensiveScenario -mode stop
