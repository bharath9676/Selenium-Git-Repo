package com.dbs.paet;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.intuit.karate.cucumber.CucumberRunner;
import com.intuit.karate.cucumber.KarateStats;

import cucumber.api.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
@CucumberOptions(features = "src/test/resources/com/dbs/paet/sprint1",tags = {"PAET222"})
public class FeaturesTestKarate {

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("karate.env", "sit");
        
    }
    
    @Test
    public void testParallel() {
        String karateOutputPath = "target/surefire-reports";
        try {
    		FileUtils.deleteDirectory(new File("./target/surefire-reports"));
    		FileUtils.deleteDirectory(new File("./report/cucumber-html-reports"));
        }catch (Exception e) {
        	System.err.println("unable to delete directory. err -"+ e.getMessage());
		}
        KarateStats stats = CucumberRunner.parallel(getClass(), 1, karateOutputPath);
        generateReport(karateOutputPath);
        boolean skipFailures = Boolean.valueOf(System.getProperty("skipFailures", "false"));
        int failCount = stats.getFailCount();
        System.out.println("skipFailures JVM property value - " + skipFailures);
        if (skipFailures && failCount != 0) {
            System.out.println("Skipping build failure - " + failCount);
        } else {
            assertEquals("There are scenario failures", 0, failCount);
        }
    }

    private static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils
                .listFiles(new File(karateOutputPath), new String[]{"json"}, true);
        @SuppressWarnings({"unchecked", "rawtypes"})
        List<String> jsonPaths = new ArrayList(jsonFiles.size());
        for (File file : jsonFiles) {
            jsonPaths.add(file.getAbsolutePath());
        }
        
        //Creating Reports folder under resource directory & putting all reorts into it
        File file = new File("report");
        file.mkdirs();
        Configuration config = new Configuration(file, "Micro Service Automation");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }

}