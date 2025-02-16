package com.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.BeforeClass;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",  // Path to feature files
        glue = {"com.stepdefinitions","com.hooks"},  // Packages containing step definitions and listeners
//        tags = "",  // Default tag for testing, can be overridden from command line
        plugin = {
                "pretty",  // Print Cucumber logs in the console
                "html:target/cucumber-reports.html",  // Default HTML report
                "json:target/cucumber.json",  // JSON report for use with other reporting tools
                "rerun:target/failed_scenarios.txt",
        },
        monochrome = true,  // Clean output in the console
        dryRun = false,  // Set to true to check for missing steps without running tests
        snippets = CucumberOptions.SnippetType.CAMELCASE  // Ensures step definitions are in CamelCase format
)
public class TestRunner {

//        @BeforeClass
//        public static void setUp() {
//                String cucumberTags = System.getProperty("cucumber.options");
//                System.setProperty("cucumber.options", cucumberTags);
//
//        }
}
