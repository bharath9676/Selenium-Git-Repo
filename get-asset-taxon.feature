@asset-classification-controller
Feature: getTaxons
	Background:
		 * configure ssl = true
		
	Scenario Outline: Test case: Get the taxonIds details by using - <assetId>
		Given url "https://paetapi.sit.apps.cs.sgp.dbs.com"
		Given path '/34/assets/taxons'
        And param assetId = "<assetId>"
        When method GET
        Then print "<assertId>"
        And print response
        And status 200
        And match response[0].assetId == "<assetId>"
        And match response[0].taxonIds[0] == "<taxonId>"
        
        Examples:
        |taxonId	|assetId	|
        |5004		|32182		|
        |5004		|343830		|
        |5004		|8659669	|
        |5004		|10687110	|
        |5004		|253785		|
        |5004		|31924		|
        |5004		|343340		|
        |5004		|7083457	|
        |5004		|76904		|
        |5004		|64778		|
        |8007		|24646949	|
        |8007		|3653304	|
        |8007		|31954055	|