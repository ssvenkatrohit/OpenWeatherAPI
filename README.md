Following file OpenWeatherAPI is a Maven project which can be used to run API tests for OpenWeather app.

How to Run
->clone or download the zip file
->Open the downloaded project in Eclipse or file editor
-> Right click on project>Run as>Maven clean.
-> Right click on Project>Maven>Update>Check force update.
-> Update the API sheet in the testData with your API Key.
-> Open POM.xml>Rightclick>RunAs>Maven test

Reports
->Post running results will be saved in reports folder.
->Logs related to the run will be saved in all.logs

TestCases Implementation/Modification
-> Open the testData excel file and in currentWeather column, include as many rows as required with differnet city names(Please include invalid names also)
-> Include the city names in fiveDayForecast column as well along with few invalid names
-> Run the tests using POM.xml
-> If the response is appropriate. test case will be passed.
-> run the invalid key scenario.



