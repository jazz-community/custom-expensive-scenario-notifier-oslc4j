To Build for usage

1. Open the project com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario in Eclipse. 
2. Right click the project com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario
   - Select 'Project>Clean'
   - Right click on the file pom.xml in the project folder and select 'Run as>Maven clean' 
   - Right click on the file pom.xml in the project folder and select 'Run as>Maven install' 
3. Right click the project com.ibm.js.team.monitoring.custom.oslc4j.expensivescenario
4. Select Export
5. Select Java>Runnable Jar File 
6. In the wizard 
   - For 'Launch Configuration' select 'ExpensiveScenarioNotifier - Runnable Jar'
   - In 'Export Location' select " a path e.g. 'C:\temp\ESNOJ\esnoj.jar' 
     You can change the root for the export if needed but keep the top folder name 
     TSM and don't modify the name of the JAR file; 
   - In 'Library handling' select 'Package required libraries into generated JAR' 
   - In the last section you can choose to save the export as an ANT script.
    - Click Finish and allow to create the folder.
7. Copy the content of the projects sub-folder scripts into the export location 
   folder 'C:\temp\ESNOJ\'. The files copied are script files, tsm.bat and tsm.sh, 
   the license file LICENSE.html and the log configuration file log4j.properties
8. Check the script files and provide a dedicated JRE 1.8 if needed
9. On Unix make the script file esnoj.sh you just copied executable

The application is now usable. 

To ship it 
1. Select the folder 'C:\temp\ESNOJ\' and compress the file
2. Rename the archive file to esnoj-Vx-YYYYMMDD.zip, 
   where x is the version, YYYY is the year, MM is the month and DD is the day
3. The file is now ready for shipping. It can basically just be uncompressed 
   on a different machine in some folder and used from there.
  