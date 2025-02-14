package com.stepdefinitions.testform;
import com.stepdefinitions.TestHooks;
import com.utils.BrowserSetup;
import com.utils.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.pages.testform.TestFormPage;

public class TestForm {

    private TestFormPage testFormPage;

    WebDriver driver;

    public TestForm() {
    }

    public WebDriver return_driver() {
        return this.driver;
    }



    @Given("User navigates to test form page")
    public void navigateTestPage() {
        System.out.println("Before getting driver: " + TestHooks.getDriver());
        driver = TestHooks.getDriver();
        if (driver == null) {
            System.out.println("Driver is null, reinitializing...");
            driver = BrowserSetup.getDriver();
        }
        testFormPage = new TestFormPage(driver);
        String url = Config.getUrl();
        driver.get(url);
        testFormPage.checkTextVisible("First Name", "div");

    }
    @When("User enters first name: {string}")
    public void userEntersFirstName(String firstName) {
        testFormPage.enterFirstName(firstName);
    }

    @When("User enters last name: {string}")
    public void userEntersLastName(String lastName) {
        testFormPage.enterLastName(lastName);
    }

    @When("User enters email: {string}")
    public void userEntersEmail(String email) {
        testFormPage.enterEmail(email);
    }

    @When("User enters address: {string}")
    public void userEntersAddress(String address) {
        testFormPage.enterAddress(address);
    }

    @When("User enters mobile num: {string}")
    public void userEntersMobileNum(String num) {
        testFormPage.enterMobileNumber(num);
    }

    @When("User selects gender: {string}")
    public void userSelectsGender(String gender) {
        testFormPage.selectGender(gender);
    }

    @When("User selects hobbies : {string}")
    public void userSelectsHobbies(String hobbies) {
        testFormPage.selectHobbies(hobbies);
    }

    @When("User uploads file: {string}")
    public void userUploadFile(String file) {
        testFormPage.upload_files(file);
    }

    @When("User selects location: {string}")
    public void userSelectLocation(String loc) {
        testFormPage.selectLocation(loc);
    }

    @When("User selects date of birth: {string}")
    public void userSelectDate(String date) {
        testFormPage.selectDateOfBirth(date);
    }


    @And("User submits the form")
    public void userSubmitsTheForm() {
        testFormPage.clickSubmitButton();
    }

    @Then("verified form submitted successfully")
       public void userShouldSeeASuccessMessage() {
        testFormPage.verifySuccessMessage();
    }

    @Then("verified field: {string} {string} validation message: {string}")
    public void verifyFieldWithErrorMessage(String field,String cond, String text) {
        testFormPage.verifyFieldErrorMessage(field, cond, text);
    }


}