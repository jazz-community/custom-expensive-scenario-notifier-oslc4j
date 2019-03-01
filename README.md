# custom-expensive-scenario-notifier-oslc4j
CLM, since 6.0.5 has capabilities for logging/registering expensive scenarios. These are scenarios such as compare workspace or plan loading or other scenarios that can be resource intensive and may have impact on performance. CLM provides MBeans and the repository debug capability to access statistics about expensive scenarios. this data can be collected on a regular basis and logged to better understand how the system behaves. 

This capability also allows to register custom expensive scenarios. For example it would be possible to log information about the start,end, amount and average duration of a custom automation or extension. The available API allows to register scenarios ba name, their start and stop. This information is exposed in monitoring

This project contains code to do this using OSLC4J.


Documentation under construction.

	Syntax : -command expensiveScenario -url https://<server>:port/<context>/ -user <userId> -password <password> -scenarioName <scenarioName> -mode <mode>
  
	Example: -command expensiveScenario -url https://clm.example.com:9443/rm/ -user ADMIN -password ****** -scenarioName MyCustomExpensiveScenario -mode start
	Example: -command expensiveScenario -url https://clm.example.com:9443/rm/ -user ADMIN -password ****** -scenarioName MyCustomExpensiveScenario -mode stop
