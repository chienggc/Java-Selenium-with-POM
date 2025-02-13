package com.stepdefinitions.momform;
import com.stepdefinitions.TestHooks;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import com.utils.BrowserSetup;
import com.utils.Config;
import com.commoncomponents.CommonComponent;

public class StageOne {


//    @FindBy(xpath = "//*[text()='FIN']/following::input[2]")
//    private WebElement finInputField;
//    script can be enhanced with POM way

    int DefaultWaitingSecond = 10;
    CommonComponent cc;
    WebDriver driver;

    public StageOne() {
    }

    public WebDriver return_driver() {
        return this.driver;
    }

    @Given("User navigate to working holiday web page")
    public CommonComponent user_navigate_to_working_holiday_web_page() {
        System.out.println("Before getting driver: " + TestHooks.getDriver());
        driver = TestHooks.getDriver();
        if (driver == null) {
            System.out.println("Driver is null, reinitializing...");
            driver = BrowserSetup.getDriver();
        }
        cc = new CommonComponent(driver);
        String url = Config.getUrl();
        driver.get(url);
        return cc;
    }

    @When("User proceed to Stage 1 Application Page")
    public void proceed_first_app_page() {
        cc.click_button_or_hyperlink("Skip");
        cc.click_button_or_hyperlink("Work Holiday Pass"); // Example with dynamic text
        cc.click_button_or_hyperlink("Start application");   // Example with different text
        check_error_exist("The service is currently unavailable, please try again later.");
    }

    @And("User selects {string} for Fin Number")
    public void remember_fin(String condition) {
        String selection = "";
        if (condition.equals("remember")) {
            selection = "FIN";
        } else {
            selection = "I cannot remember";
        }
        cc.selectRadioButtonByLabelText("What is your Foreign Identification Number (FIN)?", selection);
        cc.click_button_or_hyperlink("Continue");
    }

    // page is having unexpected error use this function to handle
    public void check_error_exist(String errortxt) {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DefaultWaitingSecond)); // Updated
            String errorMessageLocator = "//*[text()='"+errortxt+"']";
            boolean errorMessagePresent = !driver.findElements(By.xpath(errorMessageLocator)).isEmpty();
            if (errorMessagePresent) {
                System.out.println("Error message is displayed. Attempting to click again...");
                Thread.sleep(1000);  // Adjust wait time as necessary
                cc.click_button_or_hyperlink("Start application");
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    @Then("Verified user landed on Stage 1 Application Page Successfully")
    public void verify_landed_on_page_successfully() {
        cc.checkTextVisible("What is your nationality/citizenship?");
    }

    @When("User selects Nationality: {string}")
    public void select_nationality(String nationality) {
        cc.enterTextForDynamicLabel("What is your nationality/citizenship?", nationality, "textfield");
        cc.waitUntilFooterAndClick(nationality);
        cc.click_button_or_hyperlink("Continue");
    }

    @And("User selects Country: {string}")
    public void select_country(String country) {
        cc.randomizeOrSelectDropdownOption("What is the country/region of the university you studied in?", country);
        cc.click_button_or_hyperlink("Continue");
    }

    @And("User selects Residency Status: {string}")
    public void select_residency_status(String stayOrWorkOrStudy) {
        cc.selectRadioButtonByLabelText("Have you ever worked, studied or stayed long-term (not as a tourist) in Singapore?", stayOrWorkOrStudy);
        cc.click_button_or_hyperlink("Continue");
    }

    @And("User inputs for Fin Number: {string}")
    public void input_fin_num(String finNumber) {
        cc.enterTextForDynamicLabel("What is your Foreign Identification Number (FIN)?", finNumber, "textfield");
        cc.click_button_or_hyperlink("Continue");
    }

    @And("User remembers and inputs for Fin Number: {string}")
    public void remember_and_input_fin_num(String finNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DefaultWaitingSecond)); // Updated
        WebElement inputField = driver.findElement(By.xpath("//*[text()='FIN']/following::input[2]"));
        wait.until(ExpectedConditions.elementToBeClickable(inputField));
        inputField.clear();
        inputField.sendKeys(finNumber);
        cc.click_button_or_hyperlink("Continue");
    }

    @Then("Verified user is {string} for WHP")
    public void user_eligible_whp(String cond) {
        if (cond.equals("eligible")) {
            cc.checkTextVisible("This assessment does not guarantee an approval as other checks are needed.");
        } else {
            cc.checkTextVisible("Based on the information provided, you are not eligible for a Work Holiday Pass.");
        }
    }

    @And("able continue to Stage 2 page")
    public void continueToPageTwo() {
        cc.click_button_or_hyperlink("Continue");
        cc.checkTextVisible("Candidate particulars");
    }

    @When("User inputs all mandatory field for Stage 1 with Nationality {string}, country {string}, StayOrWorkOrStudy {string}")
    public void user_inputs_all_mandatory_field_for_stage_with_nationality_country_stay_or_work_or_study(String nationality, String country, String stayOrWorkOrStudy) {
        cc.enterTextForDynamicLabel("What is your nationality/citizenship?", nationality, "textfield");
        cc.waitUntilFooterAndClick(nationality);
        cc.click_button_or_hyperlink("Continue");
        cc.randomizeOrSelectDropdownOption("What is the country/region of the university you studied in?", country);
        cc.click_button_or_hyperlink("Continue");
        cc.selectRadioButtonByLabelText("Have you ever worked, studied or stayed long-term (not as a tourist) in Singapore?", stayOrWorkOrStudy);
    }




}
