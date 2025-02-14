package com.pages.testform;

import org.openqa.selenium.WebDriver;

import com.commoncomponents.testform.CommonElement;

public class TestFormPage extends CommonElement {

    private WebDriver driver;

    // Constructor to initialize WebDriver
    public TestFormPage(WebDriver driver) {
        super(driver);
    }

    // ======================= Form Fields =======================

    public void enterFirstName(String firstName) {
        enterTextForDynamicLabel("First Name", firstName, "textfield");
    }

    public void enterLastName(String lastName) {
        enterTextForDynamicLabel("Last Name", lastName, "textfield");
    }

    public void enterEmail(String email) {
        enterTextForDynamicLabel("Email", email, "textfield");
    }

    public void enterMobileNumber(String mobileNumber) {
        enterTextForDynamicLabel("Mobile number", mobileNumber, "textfield");
    }

    public void enterAddress(String address) {
        enterTextForDynamicLabel("Address", address, "textarea");
    }

    public void selectLocation(String location) {
        enterTextForDynamicLabel("Location", location, "textfield");
        clickOnDropDown(location);
    }

    public void selectDateOfBirth(String dob) {
        selectDate("Date of Birth", dob);
    }

    // ======================= File Upload =======================

    public void userUpload(String file) {
        upload_files(file);
    }

    // ======================= Radio Buttons =======================

    public void selectGender(String gender) {
        selectRadioButtonByLabelText("Gender", gender);
    }

    // ======================= Checkboxes =======================

    public void selectHobbies(String hobbies) {
        selectCheckBox("Hobbies", hobbies);
    }

    public void itemChecked(String label, String cond) {
        isItemSelected(label, cond);
    }

    // ======================= Button/Actions =======================

    public void clickSubmitButton() {
        click_button_or_hyperlink("Submit now", "button");
    }

    // ======================= Validation/Verification =======================

    public void verifySuccessMessage() {
        checkTextVisible("Thank you for filling out the form.", "h2");
    }

    public void verifyFieldErrorMessage(String fieldName, String cond, String errorMsg) {
        validateFieldIsRequired(fieldName, cond, errorMsg);
    }
    public String getInputedText(String label, String field){
        return get_field_text(label , field);
    }
}
