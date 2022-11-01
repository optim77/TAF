package pl.tomaszbuga.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.framework.BaseTest;
import pl.tomaszbuga.pom.SecondTestPage;

import java.io.IOException;

public class SecondTest extends BaseTest {

    SecondTestPage secondTestPage;

    @BeforeMethod
    public void beforeSetup() {
        secondTestPage = new SecondTestPage(getDriver());
        secondTestPage.openPage();
    }

    @Test()
    public void getAndSaveItems() throws IOException, InterruptedException {
        String SEARCH_TEXT = "baggy";
        secondTestPage.scrapVintedItems(SEARCH_TEXT);
    }
}
