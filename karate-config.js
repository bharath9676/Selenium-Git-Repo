function() {
    var System = Java.type('java.lang.System');
    var env = System.getProperty("karate.env");
	var host;
	var msgId = function() {  return java.util.UUID.randomUUID().toString() };

	if(!env){
		env = 'uat';
	}
	

var config = {
	"makoKotlinURL":"https://griffin-wapi-grif.sit.apps.cs.sgp.dbs.com",
	"paet_dev" : "",
	"paet_sit" : "https://paetapi.sit.apps.cs.sgp.dbs.com",
	"paet_uat" : "https://wapi-account-sync-api-mako.uat.apps.cs.sgp.dbs.com",
    "success" : "SUCCESS",
    "failure" : "FAILURE",
	};
karate.configure('readTimeout', 150000);
return config;
}

