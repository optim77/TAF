package pl.tomaszbuga.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.framework.BaseTest;
import pl.tomaszbuga.pom.SeleniumTrainingPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class FirstTest extends BaseTest {

    SeleniumTrainingPage seleniumTrainingPage;

    @BeforeMethod
    public void beforeSetup() {
        seleniumTrainingPage = new SeleniumTrainingPage(getDriver());
        seleniumTrainingPage.openPage();
    }

    @Test()
    public void enterTextToTextInputTest() {
        // Assign
        String expectedText = "Jakiś inny tekst, który wpisaliśmy";
        // Act
        String textFromTextInput = enterTextAndGetInputValue(expectedText);
        // Assert
        Assert.assertEquals(textFromTextInput, expectedText);
    }

    @Test()
    public void clearTextInputTest() {
        String expectedText = "Jakiś inny tekst, który wpisaliśmy";

        String textFromTextInput = enterTextAndGetInputValue(expectedText);
        Assert.assertEquals(textFromTextInput, expectedText);

        String inputValueAfterClear = getTextAfterClear();
        Assert.assertEquals(inputValueAfterClear, "");
    }

    @Test()
    public void enterTextToTextareaInputTest() {
        // Assign
        String expectedText = "Jakiś inny tekst, który wpisaliśmy";
        // Act
        String textFromTextInput = fillTextareaAndGetInputValue(expectedText);
        // Assert
        Assert.assertEquals(textFromTextInput, expectedText);
    }

    @Test()
    public void enterTextToPasswordTest(){
        String type = "password";
        String password = "Very String Admin1 Password";
        String typeFromPasswordInput = getTypeOfInput();
        // check type of password input
        String textFromPasswordInput = enterPasswordAndGetInputValue(password);
        Assert.assertEquals(type, typeFromPasswordInput);
        // check returned length of string
        Assert.assertEquals(textFromPasswordInput.length(), password.length());
    }

    @Test()
    public void setDataInDataPicker() throws ParseException {
        int year = 2023, month = 4, day = 22;
        LocalDate expectedDate = LocalDate.of(year, month, day);
        String returnedDate = seleniumTrainingPage.setData(year, month, day);
        Assert.assertEquals(seleniumTrainingPage.changeDateFormat(returnedDate), expectedDate.toString());
    }

    private String getTextAfterClear() {
        return seleniumTrainingPage
                .clearTextInput()
                .getTextFromTextInput();
    }

    private String enterTextAndGetInputValue(String expectedText) {
        return seleniumTrainingPage
                .enterTextToTextInput(expectedText)
                .getTextFromTextInput();
    }

    private String fillTextareaAndGetInputValue(String expectedText) {
        return seleniumTrainingPage
                .enterTextToTextareaInput(expectedText)
                .getTextFromTextareaInput();
    }

    private String enterPasswordAndGetInputValue(String expectedText){
        return seleniumTrainingPage
                .enterTextToPasswordInput(expectedText)
                .getTextFromPasswordInput();
    }

    private String getTypeOfInput(){
        return seleniumTrainingPage
                .checkPassword();
    }
}