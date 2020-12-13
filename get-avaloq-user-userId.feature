@user-avaloq-controller
Feature: getUserByUserID
	Background:
		* configure ssl = true
		
	Scenario Outline: Test case for userId_<userId>: Get the avaloq user's Fullname - <fullName>
	
		Given url "https://paetapi.sit.apps.cs.sgp.dbs.com/34/avaloq/users"
        And param userId = "<userId>"
        When method GET
        Then print response
        #And print "<fullName>"
        And match response[0].fullName == "<fullName>"
        
       
        Examples:
       |userId		|fullName			|
       |JESSLIU		|Jess YingYing LIU	|
       |cheeholim	|Chee Ho LIM		|
       |elainelam	|Elaine Yee Ling LAM|
		