package com.commoncomponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class CommonComponent {

    private WebDriver driver;
    private static final int DefaultWaitingSecond = 15;
    private WebDriverWait wait;
    // Constructor
    public CommonComponent(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(DefaultWaitingSecond));
    }

    public void clickByXpath(String xpath) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        element.click();
    }
    public void selectRadioButtonByLabelText(String labelText, String radioText) {

        WebElement radioButton = driver.findElement(By.xpath("//span[contains(text(), '" + labelText + "')]" +
                "//following::p[contains(@class, 'MomInputRadioButton__LabelText') and text()='" + radioText + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(radioButton));
        radioButton.click();
    }
    public void randomizeOrSelectDropdownOption(String dropdownLabel, String item) {
        String dropdownXpath = "//span[contains(text(), '" + dropdownLabel + "')]/following::button[1]";
        WebElement dropdownElement = driver.findElement(By.xpath(dropdownXpath));
        dropdownElement.click();
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
        WebElement dropdownElement = driver.findElement(By.xpath(xpath)); // Change 'dropdownId' to your actual dropdown's ID
        return dropdownElement.getText();
    }
    public String verify_selection_is_selected_with_value(String selection){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"
                +selection+"']/following::input[@checked='']" +
                "/following::*[@class='MomInputRadioButton__LabelText'][1]")));
        return element.getText();
    }

    public WebElement checkTextVisible(String txt) {
        if(this.driver == null) {
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[text()='" + txt + "']")
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
    }

    public void waitUntilFooterAndClick(String text) {
        String xpath = "//p[contains(text(), '"+ text+"')]";
        WebElement footerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        wait.until(ExpectedConditions.elementToBeClickable(footerElement));
        footerElement.click();
    }

    public void click_button_or_hyperlink(String buttonText) {
        WebElement element = checkTextVisible(buttonText);
        element.click();
    }
}
