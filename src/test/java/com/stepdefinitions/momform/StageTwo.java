package com.stepdefinitions.momform;
import com.commoncomponents.CommonComponent;
import org.openqa.selenium.WebElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.time.Duration;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Objects;


public class StageTwo {

    private String fileName;
    private String fullName;
    private String alias;
    private String dob;
    private String gender;
    private String travelDoc;
    private String travelDocNum;
    private String travelDocIssueDate;
    private String travelDocExpDate;
    private String cRob;
    private String sPob;
    private String cRoo;
    private String sPoo;
    private String address;
    private String religion;
    private String marialStat;
    private String mobileNum;
    private String email;
    private String race;

    CommonComponent cc;
    WebDriver driver;
    StageOne s1;
    StageTwo s2;
    @Override
    public String toString() {
//      for debug purpose to check variable saved in class
        return "File Name: " + fileName + "\n" +
                "Full Name: " + fullName + "\n" +
                "Alias: " + alias + "\n" +
                "Date of Birth: " + dob + "\n" +
                "Gender: " + gender + "\n" +
                "Travel Document: " + travelDoc + "\n" +
                "Travel Document Number: " + travelDocNum + "\n" +
                "Travel Document Issue Date: " + travelDocIssueDate + "\n" +
                "Travel Document Expiry Date: " + travelDocExpDate + "\n" +
                "Country of Residence: " + cRob + "\n" +
                "State of Birth: " + sPob + "\n" +
                "Country of Origin: " + cRoo + "\n" +
                "State of Origin: " + sPoo + "\n" +
                "Address: " + address + "\n" +
                "Religion: " + religion + "\n" +
                "Marital Status: " + marialStat + "\n" +
                "Mobile Number: " + mobileNum + "\n" +
                "Email: " + email + "\n" +
                "Race: " + race;
    }
    public StageTwo(String fileName, String fullName, String alias, String dob, String gender,
                    String travelDoc, String travelDocNum, String travelDocIssueDate, String travelDocExpDate,
                    String cRob, String sPob, String cRoo, String sPoo, String address, String religion,
                    String marialStat, String mobileNum, String email, String race) {
        this.fileName = fileName;
        this.fullName = fullName;
        this.alias = alias;
        this.dob = dob;
        this.gender = gender;
        this.travelDoc = travelDoc;
        this.travelDocNum = travelDocNum;
        this.travelDocIssueDate = travelDocIssueDate;
        this.travelDocExpDate = travelDocExpDate;
        this.cRob = cRob;
        this.sPob = sPob;
        this.cRoo = cRoo;
        this.sPoo = sPoo;
        this.address = address;
        this.religion = religion;
        this.marialStat = marialStat;
        this.mobileNum = mobileNum;
        this.email = email;
        this.race = race;
    }

    public StageTwo(CommonComponent stageone_cc) {
        cc = stageone_cc;

    }
    public StageTwo() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // If both objects are the same, return true
        if (obj == null || getClass() != obj.getClass()) return false; // Null check and class comparison

        StageTwo stageTwo = (StageTwo) obj; // Cast the object to StageTwo

        // Compare all fields
        return Objects.equals(fileName, stageTwo.fileName) &&
                Objects.equals(fullName, stageTwo.fullName) &&
                Objects.equals(alias, stageTwo.alias) &&
                Objects.equals(dob, stageTwo.dob) &&
                Objects.equals(gender, stageTwo.gender) &&
                Objects.equals(travelDoc, stageTwo.travelDoc) &&
                Objects.equals(travelDocNum, stageTwo.travelDocNum) &&
                Objects.equals(travelDocIssueDate, stageTwo.travelDocIssueDate) &&
                Objects.equals(travelDocExpDate, stageTwo.travelDocExpDate) &&
                Objects.equals(cRob, stageTwo.cRob) &&
                Objects.equals(sPob, stageTwo.sPob) &&
                Objects.equals(cRoo, stageTwo.cRoo) &&
                Objects.equals(sPoo, stageTwo.sPoo) &&
                Objects.equals(address, stageTwo.address) &&
                Objects.equals(religion, stageTwo.religion) &&
                Objects.equals(marialStat, stageTwo.marialStat) &&
                Objects.equals(mobileNum, stageTwo.mobileNum) &&
                Objects.equals(email, stageTwo.email) &&
                Objects.equals(race, stageTwo.race);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, fullName, alias, dob, gender, travelDoc, travelDocNum,
                travelDocIssueDate, travelDocExpDate, cRob, sPob, cRoo, sPoo, address,
                religion, marialStat, mobileNum, email, race);
    }

    @Then("Verified candidate particular is filled successfully")
    public void verify_candicate_particular_is_completed(){
            this.driver = s1.return_driver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(s1.DefaultWaitingSecond));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h3[contains(text(), 'Candidate particulars')]/following::span[1][contains(@class, 'StatusIcon--status-complete')]")
            ));
            // Optionally, perform an action with the element after it becomes visible
            System.out.println("Particular is filled successfully: " );
            System.out.println(s2);
    }

    @And("User input DOB: {string}")
    public void input_date(String date){
        s2.setDob(date);
        cc.enterTextForDynamicLabel("Date of birth", date, "textfield");
    }
    @And("User upload files: {string}")
    public void upload_files(String filenames) {
        s2.setFileName(filenames);
        // Split the filenames by commas
        driver = s1.return_driver();
        String[] fileNamesArray = filenames.split(",");


        for (String filename : fileNamesArray) {
            Path filePath = Paths.get("src", "test", "TestData", filename).toAbsolutePath();
            WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
            fileInput.sendKeys(filePath.toString());

        }
    }

    @When("User navigate to stage one to Edit Fin Number")
    public void navigate_to_stageone(){
        cc.clickByXpath("//span[text()='Application (Stage 1)']");
        cc.clickByXpath("//*[text()='What is your Foreign Identification Number (FIN)?']/following::button[1]");
    }

    @And("Verified edited fin:{string} showing successfully and back to Stage 2")
    public void verify_fin_edited(String fin){
        cc.checkTextVisible(fin);
        cc.click_button_or_hyperlink("Continue");
    }


    @Then("Verified {string} is not allowed for upload")
    public void multiple_file_is_not_allowed(String condition) {
        if (condition.equals("multiple files")){
            cc.checkTextVisible("You must not upload more than 1 file.");
        }else{
            cc.checkTextVisible("You must upload only JPEG, JPG, PNG or PDF files.");
        }

    }
    @And("User input Travel document issue date: {string}")
    public void input_tdoc_issue_date(String date){
        s2.setTravelDocIssueDate(date);
        cc.enterTextForDynamicLabel("Travel document issue date", date, "textfield");
    }
    @And("User input Travel document expired date: {string}")
    public void input_tdoc_expired_date(String date){
        s2.setTravelDocExpDate(date);
        cc.enterTextForDynamicLabel("Travel document expiry date", date, "textfield");
    }
    @Then("Verified only current or future date is allowed")
    public void invalid_date_not_allowed() {
        driver = s1.return_driver();
        WebElement element = driver.findElement(By.xpath("//span[contains(text(), 'Travel document expiry date')]/following::input[1]"));
        element.sendKeys(Keys.RETURN);
        cc.checkTextVisible("You must enter a current or future date.");
    }

    @Then("Verified only 18 to 25 age is allowed")
    public void invalid_age_is_not_allowed() {
        driver = s1.return_driver();
        WebElement element = driver.findElement(By.xpath("//span[contains(text(), 'Date of birth')]/following::input[1]"));
        element.sendKeys(Keys.RETURN);
        cc.checkTextVisible("You must be between 18 to 25 years old at the time of application. Otherwise, your application is likely to be rejected.");
    }

    @When("User enter name: {string}")
    public void enter_full_name(String name) {
        s2.setFullName(name);
        cc.enterTextForDynamicLabel("Full name (in the",name, "textfield");
    }

    @When("User enter sex: {string}")
    public void enter_sex(String sex) {
        s2.setGender(sex);
        cc.selectRadioButtonByLabelText("Sex",sex);
    }

    @When("User selects Malaysian identity card colour: {string}")
    public void enter_ic_color(String color) {
        cc.selectRadioButtonByLabelText("Malaysian identity card colour",color);
    }
    @And("User enter New Malaysian identity card number: {string}")
    public void enter_ic_number(String ic_num) {
        cc.enterTextForDynamicLabel("New Malaysian identity card number",ic_num, "textfield");
    }
    @And("User enter alias: {string}")
    public void enter_alias(String name) {
        s2.setAlias(name);
        cc.enterTextForDynamicLabel("Alias (only if printed on travel document)",name, "textfield");
    }
    @And("User enter mobile number: {string}")
    public void enter_num(String num) {
        s2.setMobileNum(num);
        cc.enterTextForDynamicLabel("Mobile number",num, "textfield");
    }
    @And("User enter email: {string}")
    public void enter_mail(String email) {
        s2.setEmail(email);
        cc.enterTextForDynamicLabel("Email",email, "textfield");
    }

    @And("User enter race: {string}")
    public void enter_race(String race) {
        s2.setRace(race);
        cc.enterTextForDynamicLabel("Race",race, "textfield");
        cc.waitUntilFooterAndClick(race);
        cc.waitUntilFooterAndClick(race);
        cc.click_button_or_hyperlink("Continue");
    }

    @And("User selects Nationality State: {string}")
    public void enter_state(String state) {
        cc.enterTextForDynamicLabel("State/Province",state, "textfield");
        cc.waitUntilFooterAndClick(state);
    }

    @And("User selects Country Or Region of birth: {string}")
    public void enter_country_of_birth(String Country) {
        s2.setCRob(Country);
        cc.enterTextForDynamicLabel("Country/Region of birth",Country, "textfield");
        cc.waitUntilFooterAndClick(Country);
    }

    @And("User selects State or Province of birth: {string}")
    public void enter_state_of_birth(String state) {
        s2.setSPob(state);
        cc.enterTextForDynamicLabel("State/Province of birth",state, "textfield");
        cc.waitUntilFooterAndClick(state);
    }

    @And("User selects Country Or Region of origin: {string}")
    public void enter_country_of_origin(String state) {
        s2.setCRoo(state);
        cc.enterTextForDynamicLabel("Country/Region of origin",state, "textfield");
        cc.waitUntilFooterAndClick(state);
    }

    @And("User selects State or Province of origin: {string}")
    public void enter_state_of_origin(String state) {
        s2.setSPoo(state);
        cc.enterTextForDynamicLabel("State/Province of origin",state, "textfield");
        cc.waitUntilFooterAndClick(state);
    }

    @And("User edits for Fin Number: {string}")
    public void edit_fin_num(String finNumber){
        cc.enterTextForDynamicLabel("What is your Foreign Identification Number (FIN)?",finNumber, "textfield");
        cc.click_button_or_hyperlink("Continue");
    }

    @And("User enter address: {string}")
    public void enter_address(String address) {
        s2.setAddress(address);
        cc.enterTextForDynamicLabel("Address in country/region of origin",address, "textarea");
    }

    @And("User selects travel doc: {string}")
    public void choose_travel_doc(String tdoc) {
        s2.setTravelDoc(tdoc);
        cc.randomizeOrSelectDropdownOption("Travel document type", tdoc);
    }

    @And("User enter Travel document number: {string}")
    public void enter_travel_doc_num(String tdocnum) {
        s2.setTravelDocNum(tdocnum);
        cc.enterTextForDynamicLabel("Travel document number", tdocnum, "textfield");
    }

    @And("User enter religion: {string}")
    public void enter_religion(String religion) {
        s2.setReligion(religion);
        cc.randomizeOrSelectDropdownOption("Religion", religion);
    }
    @And("User enter marital status: {string}")
    public void enter_m_status(String mStatus) {
        s2.setMarialStat(mStatus);
        cc.randomizeOrSelectDropdownOption("Marital status", mStatus);
    }



    @Then("verified Malaysian IC and IC's color able to show and input")
    public void verify_malaysian_extra_field(){
        String ic_num = cc.get_field_text("New Malaysian identity card number", "textfield");
        String color = cc.verify_selection_is_selected_with_value("Malaysian identity card colour");
        if (ic_num.equals("961024135501") && color.equals("Blue")){
            System.out.println("IC and selected color is tally with input value." );
        }else{
            System.out.println("IC and selected color is NOT tally with input value!!!" );
        }

    }


    @Given("User fill in all the details with Nationality: {string} and continue to Stage 2 Application")
    public void testSuiteUpForStageTwo(String Nationality) {
        s1 = new StageOne();
        cc = s1.user_navigate_to_working_holiday_web_page();
        s1.proceed_first_app_page();
        s1.verify_landed_on_page_successfully();
        s1.select_nationality(Nationality);
        s1.select_country("United States");
        s1.select_residency_status("Yes, I am currently working/studying/staying in Singapore");
        s1.input_fin_num("G3000013M");
        s1.user_eligible_whp("eligible");
        s1.continueToPageTwo();
        s2 = new StageTwo(cc);
    }
    @And("Verified details previously entered in Stage 2 able to show up correctly")
    public void retrieve_page_data(){
        String file = cc.get_xpath_text("//p[@class='MomUploadFiles__FileName']");
        String fName = cc.get_field_text("Full name (in the", "textfield");
        String aName = cc.get_field_text("Alias (only if printed on travel document)", "textfield");
        String cRob = cc.get_field_text("Country/Region of birth", "textfield");
        String sPob = cc.get_field_text("State/Province of birth", "textfield");
        String cRoo = cc.get_field_text("Country/Region of origin", "textfield");
        String sPoo = cc.get_field_text("State/Province of origin", "textfield");
        String gender = cc.verify_selection_is_selected_with_value("Sex");
        String mNum = cc.get_field_text("Mobile number", "textfield");
        String email = cc.get_field_text("Email", "textfield");
        String race = cc.get_field_text("Race", "textfield");
        String address = cc.get_field_text("Address in country/region of origin", "textarea");
        String dob = cc.get_field_text("Date of birth", "textfield");
        String tDocNum = cc.get_field_text("Travel document number", "textfield");
        String travelDocIssueDate = cc.get_field_text("Travel document issue date", "textfield");
        String travelDocExpDate = cc.get_field_text("Travel document expiry date", "textfield");
        String tvDoc = cc.get_selected_text_from_dropdown("Travel document type");
        String religion = cc.get_selected_text_from_dropdown("Religion");
        String mStatus = cc.get_selected_text_from_dropdown("Marital status");
        StageTwo s2_obj = new StageTwo(file,fName,  aName,  dob, gender, tvDoc,  tDocNum,
                travelDocIssueDate,travelDocExpDate,cRob,  sPob,  cRoo,
                sPoo, address,religion,mStatus,  mNum,  email,  race);
        if (s2_obj.equals(s2)) {
            System.out.println("Both objects have the same data.");
        } else {
            Assert.fail("The objects have different data.");
        }
//        System.out.println("Debug s2_obj");
//        System.out.println(s2_obj);
//        System.out.println("Debug s2");
//        System.out.println(s2);
    }


    // Setter for fileName
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    // Setter for fullName
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Setter for alias
    public void setAlias(String alias) {
        this.alias = alias;
    }

    // Setter for dob (Date of Birth)
    public void setDob(String dob) {
        this.dob = dob;
    }

    // Setter for gender
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Setter for travelDoc (Travel Document)
    public void setTravelDoc(String travelDoc) {
        this.travelDoc = travelDoc;
    }

    // Setter for travelDocNum (Travel Document Number)
    public void setTravelDocNum(String travelDocNum) {
        this.travelDocNum = travelDocNum;
    }

    // Setter for travelDocIssueDate
    public void setTravelDocIssueDate(String travelDocIssueDate) {
        this.travelDocIssueDate = travelDocIssueDate;
    }

    // Setter for travelDocExpDate (Travel Document Expiry Date)
    public void setTravelDocExpDate(String travelDocExpDate) {
        this.travelDocExpDate = travelDocExpDate;
    }

    // Setter for cRob (Country of Residence)
    public void setCRob(String cRob) {
        this.cRob = cRob;
    }

    // Setter for sPob (State of Birth)
    public void setSPob(String sPob) {
        this.sPob = sPob;
    }

    // Setter for cRoo (Country of Origin)
    public void setCRoo(String cRoo) {
        this.cRoo = cRoo;
    }

    // Setter for sPoo (State of Origin)
    public void setSPoo(String sPoo) {
        this.sPoo = sPoo;
    }

    // Setter for address
    public void setAddress(String address) {
        this.address = address;
    }

    // Setter for religion
    public void setReligion(String religion) {
        this.religion = religion;
    }

    // Setter for marialStat (Marital Status)
    public void setMarialStat(String marialStat) {
        this.marialStat = marialStat;
    }

    // Setter for mobileNum (Mobile Number)
    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Setter for race
    public void setRace(String race) {
        this.race = race;
    }
    // Getter for fileName
    public String getFileName() {
        return fileName;
    }

    // Getter for fullName
    public String getFullName() {
        return fullName;
    }

    // Getter for alias
    public String getAlias() {
        return alias;
    }

    // Getter for dob (Date of Birth)
    public String getDob() {
        return dob;
    }

    // Getter for gender
    public String getGender() {
        return gender;
    }

    // Getter for travelDoc (Travel Document)
    public String getTravelDoc() {
        return travelDoc;
    }

    // Getter for travelDocNum (Travel Document Number)
    public String getTravelDocNum() {
        return travelDocNum;
    }

    // Getter for travelDocIssueDate
    public String getTravelDocIssueDate() {
        return travelDocIssueDate;
    }

    // Getter for travelDocExpDate (Travel Document Expiry Date)
    public String getTravelDocExpDate() {
        return travelDocExpDate;
    }

    // Getter for cRob (Country of Residence)
    public String getCRob() {
        return cRob;
    }

    // Getter for sPob (State of Birth)
    public String getSPob() {
        return sPob;
    }

    // Getter for cRoo (Country of Origin)
    public String getCRoo() {
        return cRoo;
    }

    // Getter for sPoo (State of Origin)
    public String getSPoo() {
        return sPoo;
    }

    // Getter for address
    public String getAddress() {
        return address;
    }

    // Getter for religion
    public String getReligion() {
        return religion;
    }

    // Getter for marialStat (Marital Status)
    public String getMarialStat() {
        return marialStat;
    }

    // Getter for mobileNum (Mobile Number)
    public String getMobileNum() {
        return mobileNum;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Getter for race
    public String getRace() {
        return race;
    }


}