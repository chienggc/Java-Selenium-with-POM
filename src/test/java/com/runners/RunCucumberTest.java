package com.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/api",  // Path to feature files
        glue = "com.stepdefinitions",                  // Package for step definitions
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/rerun-cucumber-report.json",

        }
)
public class RunCucumberTest {
}
