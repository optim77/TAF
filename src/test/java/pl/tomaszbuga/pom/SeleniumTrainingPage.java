package pl.tomaszbuga.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.framework.PageObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SeleniumTrainingPage extends PageObject {
    private String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    @FindBy(css = "#my-text-id")
    private WebElement textInput;

    @FindBy(css = "[name='my-textarea']")
    private WebElement textareaInput;

    @FindBy(css ="[name='my-password']")
    private WebElement passwordInput;

    @FindBy(css = "[name='my-date']")
    private WebElement datePickerInput;

    @FindBy(css = ".datepicker")
    private WebElement datePicker;

    @FindBy(css = ".datepicker-switch")
    private WebElement YearAndMonthDatePickerButton;

    @FindBy(xpath = "/html/body/div/div[2]/table/thead/tr[2]/th[3]")
    private WebElement pushNext;

    @FindBy(xpath = "/html/body/div/div[2]/table/thead/tr[2]/th[1]")
    private WebElement pushPrev;

    @FindBy(xpath = "/html/body/div/div[2]/table/thead/tr[2]/th[2]")
    private WebElement yearClickDatePicker;

    public SeleniumTrainingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SeleniumTrainingPage openPage() {
        driver.get(baseUrl);
        return this;
    }

    public SeleniumTrainingPage enterTextToTextInput(String text) {
        enterTextToInput(text, textInput);
        return this;
    }

    public SeleniumTrainingPage enterTextToTextareaInput(String text) {
        enterTextToInput(text, textareaInput);
        return this;
    }

    public String checkPassword(){
        return checkAttributeType(passwordInput);
    }

    public SeleniumTrainingPage enterTextToPasswordInput(String text){
        enterTextToInput(text, passwordInput);
        return this;
    }

    public String setData(int year, int month, int day){
        setYear(year);
        setMonth(month);
        setDay(day);
        return getTextFromDateInput();

    }
    private void setYear(int year){
        LOGGER.info("Set " + year + " year on datepicker");
        datePickerInput.click();
        waitUntilElementIsVisible(YearAndMonthDatePickerButton);
        YearAndMonthDatePickerButton.click();
        String defaultYear = yearClickDatePicker.getText();
        int accYear = Integer.parseInt(defaultYear);
        if (year > accYear){
            while( year != accYear){
                pushNext.click();
                accYear++;
            }
        }else{
            while(year != accYear){
                pushPrev.click();
                accYear--;
            }
        }
        LOGGER.info("Year has been entered");
    }
    private void setMonth(int month){
        LOGGER.info("Set " + month + " year on datepicker");
        List<WebElement> months = driver.findElements(By.className("month"));
        int counter = 1;
        for(WebElement m: months){
            if (month == counter){
                m.click();
                break;
            }
            counter++;
        }
        LOGGER.info("Month has been entered");
    }

    private void setDay(int day){
        LOGGER.info("Set " + day + " year on datepicker");
        List<WebElement> days = driver.findElements(By.className("day"));
        int counter = 1;
        for(WebElement d: days){
            if(day == Integer.parseInt(d.getText())){
                d.click();
                break;
            }
            counter++;
        }
        LOGGER.info("Month has been entered");
    }

    public String changeDateFormat(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date d = sdf.parse(date);
        sdf.applyPattern("yyyy-MM-dd");
        return sdf.format(d);
    }

    public String getTextFromPasswordInput(){
        return getTextFromInput(passwordInput);
    }

    public String getTextFromTextInput() {
        return getTextFromInput(textInput);
    }

    public String getTextFromTextareaInput() {
        return getTextFromInput(textareaInput);
    }

    public String getTextFromDateInput(){ return getTextFromDataPickerInput(datePickerInput);}
    public SeleniumTrainingPage clearTextInput() {
        textInput.clear();
        return this;
    }
}
