//package com.listeners;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.Scenario;
//import io.cucumber.java.BeforeStep;
//import io.cucumber.java.AfterStep;
//
//
//public class ExtentReportsListener {
//
//    private static ExtentReports extent; // Make the ExtentReports instance static
//    private static ExtentTest test; // Make ExtentTest static to log across steps and scenarios
//
//    @Before // Before each scenario
//    public void setup(Scenario scenario) {
//        if (extent == null) {
//            // Initialize ExtentReports only once
//            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/cucumber-reports/extent-reports.html");
//            extent = new ExtentReports();
//            extent.attachReporter(sparkReporter);
//        }
//
//        // Create a new test for each scenario with its name
//        test = extent.createTest(scenario.getName());
//    }
//
//    @BeforeStep // Before each step - Log the start of the step
//    public void beforeStep() {
//        // You can capture the current step name using a Cucumber expression to log it
//        String currentStepName = Thread.currentThread().getStackTrace()[2].getMethodName();  // Get current step name
////        test.info("Starting step: " + currentStepName);
//    }
//
//    @AfterStep // After each step - Log the result of the step
//    public void afterStep(Scenario scenario) {
//        // You can capture the step status (pass/fail) after it finishes
//        String currentStepName = Thread.currentThread().getStackTrace()[2].getMethodName();  // Get current step name
//
//        if (scenario.getStatus() == io.cucumber.java.Status.FAILED) {
//            test.fail("Step failed: " + currentStepName);
//        } else {
//            test.pass("Step passed: " + currentStepName);
//        }
//    }
//
//    @After // After each scenario
//    public void afterScenario(Scenario scenario) {
//        // Log the scenario result (pass/fail)
//        if (scenario.isFailed()) {
//            test.fail("Scenario failed due to: " + scenario.getStatus());
//        } else {
//            test.pass("Scenario passed");
//        }
//
//        // You can log scenario details for failed tests
//        if (scenario.getStatus() == io.cucumber.java.Status.FAILED) {
//            test.fail("Scenario failed due to: " + scenario.getName());
//        }
//    }
//
//    @After // After all scenarios - Generate the report
//    public void generateReport() {
//        // Flush the extent report after all scenarios are finished
//        extent.flush();
//    }
//}
