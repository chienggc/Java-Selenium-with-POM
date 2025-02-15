package com.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/failed_scenarios.txt",  // Reference the rerun file
        glue = {"com.stepdefinitions"},
        plugin = {
                "pretty",
                "html:target/rerun-cucumber-report.html",
                "json:target/rerun-cucumber-report.json"
        }
)
public class RerunFailedTests {
}
