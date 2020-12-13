def functionalTests(Map fTestData = [:]){
    echo 'First automated Test....! Here it Goes......!'
    fTestData["automationTestReport"] = "AutomationTestReport"
    fTestData["performanceTestReport"] = "PerformanceTestReport" 
    fTestData["resultVal"]=""

    try{
        dir("./") {
            sh "pwd && ls -lrt"

            try {
                sh("mvn -Pnexus test -DskipFailures=false")
                fTestData["resultVal"]="SUCCESS"
				echo "Successfully executed automation tests "	
            } catch (e) {
                fTestData["resultVal"]="FAILURE"
                error "Functional test Failed..!, Error: ${e}"

            }finally{
                utils.publishReport("",fTestData.automationTestReport,"overview-features.html","./report/cucumber-html-reports/")
            }
        }

    }catch(e){
        fTestData["resultVal"]="FAILURE"
        echo "Oops.!, There was an exception"
        error "Error: ${e}"

    }finally{
        emailTestReport(fTestData)
    }

}

def emailTestReport(Map fTestData){
    try {
          //emailMap = [emails: "bharathbk@dbs.com,philipnicholles@dbs.com,santoshbanerjee@dbs.com,srinivasareddya@dbs.com,ramakrishna1v@dbs.com,aileenwang@dbs.com,hareeshb@dbs.com,giribabu@dbs.com,tarigoppula@dbs.com,virenderverma@dbs.com;",
          //emailMap = [emails: "DAH2ORCA@DBS1Bank.onmicrosoft.com",
		  emailMap = [emails: "bharathbk@dbs.com",
                    html_msg: generateEmailBody(fTestData),
                    subject: "Automation Test Report - $env.JOB_NAME - ${fTestData.resultVal}"]
        sendEmail(emailMap)
    } catch(e) {
        fTestData["resultVal"]="FAILURE"
        echo " unexpected error ${e}"
        error "mail sending Failed"

    }
}

def generateEmailBody(Map fTestData) {
    return """
            <style>
          p { font-weight:bold;color:solid black;font-size:22px }
          table { padding:0px;margin:10px 0 0 0;border:0;border-collapse: collapse;font-family:sans serif;  }
          td,th { margin:0;padding:10px;border:1px solid #dedede; border: 1px solid black; }
          
          th{background-color: #4CAF50;color: white;font-size:16px}
          td{font-size:14px}
          tr:nth-child(even) {background-color: #f2f2f2;}
		  
          </style>
          <p><u>Automation Test report</u></p>
          <table>
              <tr>
              <th>Label</th>
              <th>Description</th>
          </tr>  
          <tbody>
          <tr>
          <td>Job Name</td>
          <td><a href='${env.JOB_URL}'>${env.JOB_NAME}</a></td>
          </tr> 
                  
          <tr>
          <td>Flow Graph</td>
          <td><a href='${env.RUN_DISPLAY_URL}'>Pipeline Steps</a></td>
          </tr>
          
         
          
          <tr>
          <td>Branch Name</td>
          <td><a href='${env.JOB_URL}'>${env.BRANCH_NAME}</a></td>
          </tr>
          
          <tr>
          <td>Build Number</td>
          <td><a href='${env.BUILD_URL}'>${env.BUILD_NUMBER}</a></td>
          </tr>
		  
		  
          <tr>
          <td>View Automation report</td>
          <td><a href="${env.BUILD_URL}${fTestData.automationTestReport}/overview-features.html">${fTestData.automationTestReport}</a></td>
          </tr>
		    
          
          <tr>
          <td>Download Automation report</td>
          <td><a href="${env.BUILD_URL}${fTestData.automationTestReport}/*zip*/${fTestData.automationTestReport}.zip">${fTestData.automationTestReport}.zip</a></td>
          </tr>
          
           <tr>
           <td>Build Duration</td>
           <td><a href='${currentBuild.durationString}'>${currentBuild.durationString}</a></td>
           </tr>
           
           <tr>
           <td>Result</td>
           <td><a href='${currentBuild.durationString}'>${fTestData.resultVal}</a></td>
           </tr>
      
          </tbody>
          </table>
		  <br><br>
		Regards,<br>
		P Bharath Kumar
          """
}

return this