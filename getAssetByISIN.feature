@asset-batch-controller
Feature: getAssetByISIN
	Background:
		 * configure ssl = true
		
	Scenario Outline: Test case: Get the asset details by using - <isin>
		Given url "https://paetapi.sit.apps.cs.sgp.dbs.com"
		Given path '/34/assets'
        And param isin = "<isin>"
        When method GET
        Then print "<isin>"
        And print response
        And match response[0].name == "<name>"
        
        Examples:
        |isin				|name																	|assertId	|
        |SG1M51904654		|Ord Sh, CapitaLand Mall Trust											|32182		|
        |SG1T70931228		|Ord Sh, ESR-REIT														|343830		|
        |SG1AA5000001		|Ord Sh, Frasers Hospitality Trust										|8659669	|
        |SG1AF6000009		|Ord Sh, Keppel DC REIT													|10687110	|
        |SG2C32962814		|Ord Sh, Mapletree Industrial Trust										|253785		|
        |SG1U68934629		|Ord Sh, Keppel Corp Ltd												|31924		|
        |SG1T22929874		|Ord Sh, Keppel REIT													|343340		|
        |SG2G60000004		|Ord Sh, OUE Commercial Real Estate Investment Trust					|7083457	|
        |SG1I52882764		|Ord Sh, SATS Ltd														|76904		|
        |SG1R50925390		|Ord Sh, Sembcorp Industries Ltd										|64778		|
        |LU1574459522		|GS Emerging Markets Corporate Bond Portfolio- USD - Stable Mdis - Cash	|24646949	|
        |IE00B91X6F72		|PIMCO GIS Income Fund - Admin - USD - Mdis - Cash						|3653304	|
        |LU1514167136		|Schroder ISF - Global Credit Income MF - A - USD - Mdis - Cash			|31954055	|