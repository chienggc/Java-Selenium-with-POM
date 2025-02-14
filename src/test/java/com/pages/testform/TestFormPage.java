package com.pages.testform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.commoncomponents.testform.CommonElement;

public class TestFormPage extends CommonElement{

    private WebDriver driver;
    // Constructor to initialize WebDriver
    public TestFormPage(WebDriver driver) {
        super(driver);
    }


    public void enterFirstName(String firstName) {
        enterTextForDynamicLabel("First Name",firstName, "textfield");
    }

    public void enterLastName(String lastName) {
        enterTextForDynamicLabel("Last Name",lastName, "textfield");
    }

    public void enterEmail(String email) {
        enterTextForDynamicLabel("Email",email, "textfield");
    }

    public void userUpload(String file){
        upload_files(file);
    }

    public void selectGender(String gender) {
        selectRadioButtonByLabelText("Gender", gender);
    }

    public void enterMobileNumber(String mobileNumber) {
        enterTextForDynamicLabel("Mobile number",mobileNumber, "textfield");
    }

    public void selectHobbies(String hobbies) {
        selectCheckBox("Hobbies",hobbies);
    }

    public void selectLocation(String loc){
        enterTextForDynamicLabel("Location",loc, "textfield");
        clickOnDropDown(loc);
    }

    public void selectDateOfBirth(String dob){
        selectDate("Date of Birth",dob);
    }

    public void clickSubmitButton(){
        click_button_or_hyperlink("Submit now", "button");
    }

    public void verifySuccessMessage(){
        checkTextVisible("Thank you for filling out the form.", "h2");

    }
    public void verifyFieldErrorMessage(String fieldName, String cond, String errorMsg){
        validateFieldIsRequired(fieldName, cond, errorMsg);
    }

    public void enterAddress(String address) {
         enterTextForDynamicLabel("Address",address, "textarea");
    }

    public void itemChecked(String label, String cond){
        isItemSelected(label, cond);
    }

}
