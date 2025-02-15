package com.stepdefinitions.testform;
import com.hooks.TestHooks;
import com.utils.BrowserSetup;
import com.utils.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import com.pages.testform.TestFormPage;
import java.util.List;
import java.util.Map;
import io.cucumber.datatable.DataTable;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class TestForm {

    private TestFormPage testFormPage;

    WebDriver driver;

    public TestForm() {
    }

    @When("User enters the following details:")
    public void userEntersDetails(DataTable dataTable) {
        // Convert the DataTable into a List of Maps, where each map represents a row
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        // Loop through each row of data and fill the form accordingly
        for (Map<String, String> row : data) {
            String fieldName = row.get("Field");
            String value = row.get("Value");

            switch (fieldName) {
                case "First Name":
                    testFormPage.enterFirstName(value);
                    break;
                case "Last Name":
                    testFormPage.enterLastName(value);
                    break;
                case "Email":
                    testFormPage.enterEmail(value);
                    break;
                case "Gender":
                    testFormPage.selectGender(value);
                    break;
                case "Mobile number":
                    testFormPage.enterMobileNumber(value);
                    break;
                case "Date of Birth":
                    testFormPage.selectDateOfBirth(value);
                    break;
                case "Hobbies":
                    testFormPage.selectHobbies(value);
                    break;
                case "File":
                    testFormPage.upload_files(value);
                    break;
                case "Location":
                    testFormPage.selectLocation(value);
                    break;
                case "Address":
                    testFormPage.enterAddress(value);
                    break;
                default:
                    System.out.println("Unknown field: " + fieldName);
            }
        }
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
        testFormPage.userUpload(file);
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

    @Then("verified {string} is {string}")
    public void verifyItemChecked(String label, String cond) {
        testFormPage.itemChecked(label, cond);
    }

    @Then("verified {string} text field contains: {string}")
    public void verifyTextFieldInputed(String label, String input) {
        String text = testFormPage.getInputedText(label, "textfield");
        assert text.contains(input) : "Assertion failed: User input not tally with field Value";

    }

    @Then("verified {string} text area contains: {string}")
    public void verifyTextAreaInputed(String label, String input) {
        String text = testFormPage.getInputedText(label, "textarea");
        assert text.contains(input) : "Assertion failed: User input not tally with field Value";

    }

    @When("User clicks on calendar button to expand calendar")
    public void clickOnCalendar(){
        testFormPage.clickToExpandCalendar("Date of Birth");
    }

    @Then("Verified today's date is selected from calendar")
    public void verifyTodayDate(){
        testFormPage.clickToExpandCalendar("Date of Birth");
        String today = testFormPage.getTodaysDate();
        String selectedDate = testFormPage.getCalSelectedValue();
        boolean result = testFormPage.compareDate(selectedDate, today, "EEE MMM dd yyyy", "dd/MM/yyyy");
        assert result : "Circled date is not the same as today's date";
        System.out.println("Testttttttttttttttttttttt");
        System.out.println(result);
    }

    @When("User select today's date")
    public void selectTodayDate(){
        String today = testFormPage.getTodaysDate();
        testFormPage.selectDateOfBirth(today);
    }

    @Then("Verified today's date is circled from calendar")
    public void verifyTodayDateCircled(){
        String today = testFormPage.getTodaysDate();
        String circledDay = testFormPage.getXpathAttributeValue("//button[contains(@class, 'css-9h1j97')]", "aria-label");
        boolean result = testFormPage.compareDate(circledDay, today, "EEE MMM dd yyyy", "dd/MM/yyyy");
        assert result : "Circled date is not the same as today's date";
    }

}