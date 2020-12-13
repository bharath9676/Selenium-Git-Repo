@PAET
@Sprint1
@ClientController
Feature: Client-controller - To return list of client details (S/P account) from Avaloq based on the client ids
             Background:
               *  configure ssl = true
               *  url paet_sit
      Scenario Outline: Test case_<index>: Get the client details from Avaloq based on the client id - <clientId>
		              Given path "34/avaloq/clients"
		              And param clientId = "<clientId>"
		              And header Content-Type = "application/json"
		              When method GET
		              Then status 200
		              * def resp = response
		              And match resp[0].clientId == "<clientId>"
		              And match resp[0].fullName == "<fullName>"
		              And match resp[0].currency ==  "<currency>"
		              And match resp[0].language ==  "en"
		              
	 Examples:
	 |index		|clientId 		|fullName		|currency|
	 |001		|S-080005	    |bp-4_123581	|SGD	|
	 |002		|S-102934	    |bp-4_83771		|SGD|
	 |003		|P-086204	    |bp-4_13623		|USD|	

	                
      Scenario: Test case: Client-controller - flashClients
		              Given path "flushClients"
		              And header Content-Type = "application/json"
		              When method GET
		              Then status 200
		              * def resp = response
		              And match resp == true
		              
		              
		              
	   Scenario Outline: Test case_<index>: Get the client details from Avaloq based on the client id - <clientId>
		              Given path "34/clients"
		              And param clientId = "<clientId>"
		              And header Content-Type = "application/json"
		              When method GET
		              Then status 200
		              * def resp = response
		              And match resp[0].clientId == "<clientId>"
		              And match resp[0].fullName == "<fullName>"
		              And match resp[0].currency ==  "<currency>"
		              And match resp[0].language ==  "en"
		              
	 Examples:
	 |index		|clientId 		|fullName		|currency|
	 |001		|S-080005	    |bp-4_123581	|SGD	|
	 |002		|S-102934	    |bp-4_83771		|SGD|
	 |003		|P-086204	    |bp-4_13623		|USD|	
	