package com.commoncomponents.testform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Random;
public class CommonElement {

    private static final String monthOrYearbutton = "//*[contains(@aria-label, '%s')]";
    private static final String monthOrYearOption = "//select//option[contains(text(), '%s')]";
    private static final String folowBtnByText = "//*[text()='%s']/following::button[1]";
    private static final String dateXpath = "//*[contains(@aria-label, '%s %s %s')]";
    private static final String validationXpath = "//*[text()='%s']/following::div[contains(@class, 'error-message')]";
    private WebDriver driver;
    private static final int DefaultWaitingSecond = 10;
    private WebDriverWait wait;
    Wait<WebDriver> fluentWait ;
    private static final String[] monthsAbbr = {
            "", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };
    private static final String[] monthsFull = {
            "", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
    };
    // Constructor
    public CommonElement(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(DefaultWaitingSecond));
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
    }

    public CommonElement() {
    }
    public static String convertMonth(String monthNumber, boolean fullName) {
        int monthIndex = Integer.parseInt(monthNumber);
        return fullName ? monthsFull[monthIndex] : monthsAbbr[monthIndex];
    }



    public void clickByXpath(String xpath) {
         // Updated
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        element.click();
    }
    public void selectRadioButtonByLabelText(String labelText, String radioText) {
        WebElement radioButton = driver.findElement(By.xpath("//*[contains(text(), '" + labelText + "')]"
                + "/following::span[text()='"+radioText+"']/ancestor::label[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(radioButton));
        radioButton.click();
    }
    public void randomizeOrSelectDropdownOption(String dropdownLabel, String item) {
        // Locate all the options in the dropdown
        String dropdownXpath = "//span[contains(text(), '" + dropdownLabel + "')]/following::button[1]";
        WebElement dropdownElement = driver.findElement(By.xpath(dropdownXpath));
        dropdownElement.click();
// Updated
        if (item.equals("random")) {
            List<WebElement> dropdownOptions = driver.findElements(By.xpath("//div[contains(@class, 'MomInputDropdownOption')]/p"));
            // Check if the dropdown has options
            if (!dropdownOptions.isEmpty()) {
                // Randomly select an option from the list
                Random rand = new Random();
                int randomIndex = rand.nextInt(dropdownOptions.size());  // Pick a random index
                WebElement randomOption = dropdownOptions.get(randomIndex);

                // Wait for the option to be clickable
                wait.until(ExpectedConditions.elementToBeClickable(randomOption));
                // Click the randomly selected option
                randomOption.click();
            } else {
                System.out.println("No dropdown options available.");
            }
        } else {
            WebElement dropDownOption = driver.findElement(By.xpath("//div[contains(@class, 'MomInputDropdownOption')]/p[contains(text(), '" + item + "')]"));
            wait.until(ExpectedConditions.elementToBeClickable(dropDownOption));
            dropDownOption.click();
        }
    }
    public String get_selected_text_from_dropdown(String xpath){
        String dropdownXpath = "//span[contains(text(), '"+xpath+"')]/following::p[contains(@class, 'MomInputSelect__ToggleText')]";
        WebElement dropdownElement = driver.findElement(By.xpath(dropdownXpath)); // Change 'dropdownId' to your actual dropdown's ID
        String selectedText = dropdownElement.getText();
        System.out.println("Selected option: " + selectedText);
        return selectedText;
    }

    public String get_field_text(String label, String field){
        String labelXpath = "//*[text()='" + label + "']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(labelXpath)));
        String input_xpath = "";
        if (field.equals("textfield")){
            input_xpath = labelXpath + "/following::input[1]";
        }else{
            input_xpath = labelXpath + "/following::textarea[1]";
        }
        WebElement inputField = driver.findElement(By.xpath(input_xpath)); // Replace with the appropriate selector
        return inputField.getAttribute("value");
    }
    public String get_xpath_text(String xpath){
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(xpath)
        ));
        WebElement element = driver.findElement(By.xpath(xpath)); // Change 'dropdownId' to your actual dropdown's ID
        return element.getText();
    }
    public String verify_selection_is_selected_with_value(String selection){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"
                +selection+"']/following::input[@checked='']" +
                "/following::*[@class='MomInputRadioButton__LabelText'][1]")));
        return element.getText();
    }

    public WebElement checkTextVisible(String txt, String tag) {
        if(this.driver == null) {
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//"+tag+"[text()='" + txt + "']")
        ));
        System.out.println("Text is now visible: " + element.getText());
        return element;
    }

    public void enterTextForDynamicLabel(String labelText, String textToEnter, String field) {
        String labelXpath = "//*[text()='" + labelText + "']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(labelXpath)));
        String input_xpath;
        if(field.equals("textarea")){
            input_xpath = labelXpath + "/following::textarea[1]";
        }else{
            input_xpath = labelXpath + "/following::input[1]";
        }
        WebElement inputField = driver.findElement(By.xpath(input_xpath));
        wait.until(ExpectedConditions.elementToBeClickable(inputField));
        inputField.clear();
        inputField.sendKeys(textToEnter);
        WebElement inputLabel = driver.findElement(By.xpath(labelXpath));
        inputLabel.click();

    }

    public void selectCheckBox(String labelText, String checkBoxItems) {
        String labelXpath = "//*[text()='" + labelText + "']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(labelXpath)));
        String[] checkBoxItemsArray;
        if (checkBoxItems.contains(",")) {
            checkBoxItemsArray = checkBoxItems.split(",");
        } else {
            checkBoxItemsArray = new String[] { checkBoxItems }; // No comma, so just put it in an array
        }
        // Loop through the array and print each hobby
        for (String checkBoxItem : checkBoxItemsArray) {
            String input_xpath;
            input_xpath = labelXpath + "/following::span[text()='"+checkBoxItem.trim()+"']/ancestor::label[1]";
            WebElement inputField = driver.findElement(By.xpath(input_xpath));
            wait.until(ExpectedConditions.elementToBeClickable(inputField));
            inputField.click();
        }




    }

    public void clickOnDropDown(String text) {
        String xpath = "//*[contains(text(), '"+ text+"')]";
        WebElement footerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        wait.until(ExpectedConditions.elementToBeClickable(footerElement));
        footerElement.click();
    }

    public void click_button_or_hyperlink(String buttonText, String tag) {
        WebElement element = checkTextVisible(buttonText, tag);
        element.click();
    }

    public void upload_files(String filenames) {
        Path filePath = Paths.get("src", "test", "TestData", filenames).toAbsolutePath();
        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
        fileInput.sendKeys(filePath.toString());

    }
    public void validateFieldIsRequired(String fieldName,String cond, String errorMsg){
        String errorXpath = String.format(validationXpath, fieldName);
        if (cond.equals("have")){
            String validationText = get_xpath_text(errorXpath);
            assert validationText.contains(errorMsg) : "Assertion failed: validation message not tally";
        }else{
            boolean hasErrorMsg = isElementNotVisible(errorXpath);
            assert hasErrorMsg : "Assertion failed: The text is visible!!!";
        }

    }

    public void isItemSelected(){
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), 'Sport')]/preceding::span[1]"));
        String idValue = element.getAttribute("data-checked");
        if (idValue != null) {
            System.out.println("Item is selected");
        } else {
            System.out.println("Item is not selected");
        }
    }

    public boolean isElementNotVisible(String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            boolean isVisible = element.isDisplayed();
            if (isVisible) {
                System.out.println("Element is visible.");
            } else {
                System.out.println("Element is not visible.");
            }
            return !isVisible;
        } catch (NoSuchElementException e) {
            System.out.println("Element does not exist.");
            return true; // Element is not visible because it does not exist
        }
    }

    public void selectDate(String dateLabel, String items) {
        String[] selections;
        selections = items.split("/");
        String day = selections[0];
        String month = selections[1];
        String year = selections[2];
        String abbrMonth = convertMonth(month, false);
        String fullMonth = convertMonth(month, true);
        WebElement calendarBtn = fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(folowBtnByText, dateLabel))));
        calendarBtn.click();
        WebElement yearElement = fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(monthOrYearbutton, "Change displayed year"))));
        yearElement.click();
        WebElement yearOptionElement = fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(monthOrYearOption, year))));
        yearOptionElement.click();
        WebElement monthElement = fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(monthOrYearbutton, "Change displayed month"))));
        monthElement.click();
        WebElement monthOptionElement = fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(monthOrYearOption, fullMonth))));
        monthOptionElement.click();
        WebElement date = fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(dateXpath, abbrMonth, day, year))));
        date.click();
        // select date //*[contains(@aria-label, 'Oct 05 2025')]
    }
}
