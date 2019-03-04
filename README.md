# custom-expensive-scenario-notifier-oslc4j
CLM, since 6.0.5 has capabilities for logging/registering expensive scenarios. These are scenarios such as compare workspace or plan loading or other scenarios that can be resource intensive and may have impact on performance. CLM provides MBeans and the repository debug capability to access statistics about expensive scenarios. This data can be collected on a regular basis and logged to better understand how the system behaves. 

This capability also allows to register custom expensive scenarios. It is possibe to register the start and end time of a custom expensive scenario. The scenario information is available using the monitoring capabilities. See [CLM Monitoring] (https://jazz.net/library/article/91590) section **6. Resource-Intensive Scenarios Summary bean**. 

Another, simpler approach, is to access the ICounterContentService using the URL https://<server>:<port>/<app>/service/com.ibm.team.repository.service.internal.counters.ICounterContentService and then look into the information in the sections **scenarios** **and scenariosSummary*.  

The available API allows to register custom scenarios by name using a start and a stop command.

This project contains code to do this using OSLC4J. It might be necessary to change the login process to log into applications other than ccm. E.g. for RM log into JTS.


Syntax:
```bash
-command expensiveScenario 
	-url https://<server>:port/<context>/ 
	-user <userId> 
	-password <password> 
	-scenarioName <scenarioName> 
	-mode <mode>
```

Example start Custom Scenario MyCustomExpensiveScenario:
```bash
-command expensiveScenario 
	-url https://clm.example.com:9443/ccm/ 
	-user ADMIN 
	-password ****** 
	-scenarioName MyCustomExpensiveScenario 
	-mode start
```

Example start Custom Scenario MyCustomExpensiveScenario:
```bash
-command expensiveScenario 
	-url https://clm.example.com:9443/ccm/ 
	-user ADMIN 
	-password ****** 
	-scenarioName MyCustomExpensiveScenario 
	-mode stop
```
