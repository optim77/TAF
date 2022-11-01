package pl.tomaszbuga.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.framework.PageObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SecondTestPage extends PageObject {

    String site = "https://www.vinted.pl/";

    @FindBy(css = "#onetrust-accept-btn-handler")
    private WebElement cookiesAcceptButton;

    @FindBy(css = "#search_text")
    private WebElement searchInput;

    private boolean startingFlag = false;
    private String filename;

    public SecondTestPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SecondTestPage openPage(){
        driver.get(site);
        return this;
    }

    public void acceptCookie(String searchText){
        if(!startingFlag){
            waitUntilElementIsVisible(cookiesAcceptButton);
            cookiesAcceptButton.click();
            searchInput.sendKeys(searchText);
            searchInput.sendKeys(Keys.ENTER);
            this.filename = createHTML();
            this.startingFlag = true;
        }else{
            waitUntilElementIsClickable(driver.findElement(By.className("feed-grid")));
        }
    }

    public void scrapVintedItems(String searchText) throws IOException, InterruptedException {
        acceptCookie(searchText);
        // Need to use wait in this way because others doesn't work
        Thread.sleep(1000);
        List<WebElement> items = null;
        try{
            items = driver.findElements(By.className("feed-grid__item"));
        }catch (Exception e){
            e.printStackTrace();
        }
        for(WebElement i: items){
            String profileName = i.findElement(By.className("Text_text__yaRJO")).getText();
            String profileURL = i.findElement(By.className("Cell_cell__uucuS")).getAttribute("href");
            String itemURL = i.findElement(By.className("ItemBox_overlay__Hr4av")).getAttribute("href");
            String itemData = i.findElement(By.className("ItemBox_overlay__Hr4av")).getAttribute("title");
            WebElement imageObject = i.findElement(By.className("Image_ratio__Q-d1N"));
            String imageURL = imageObject.findElement(By.className("Image_content__jMRPK")).getAttribute("src");
            String snippet = setFormForHTML(profileName, profileURL, itemData, itemURL, imageURL);
            addElementToHTMLFile(this.filename, snippet);
            //System.out.println(i.getText());
        }

        openNextPage(searchText);
    }

    private String createHTML() {
        String filename = "vinted_" + Math.random() * 100 + ".html";
        try{

            FileWriter html = new FileWriter(filename);
            html.write("""
                    <!doctype html>
                    <html>
                        <head>
                            <title>Our Funky HTML Page</title>
                            <meta name="description" content="Our first page">
                            <meta name="keywords" content="html tutorial template">
                            <link rel="stylesheet" 
                            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" 
                            integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" 
                            crossorigin="anonymous">
                        </head>
                        <body class='bg-dark'>
                            <div class='container'>
                                <div class='row'>
                                    
                                </div>
                            </div>
                        </body>
                    </html>
                    """);
            html.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return filename;
    }

    private String setFormForHTML(String profileName, String profileUrl, String itemData, String itemURL, String imageURL){
        return """
                <div class='col-3 bg-dark text-white text-center'>
                    <a class='col-12' href='%s'>%s</a>
                    <a href='%s'>
                        <img src='%s' class='col-12 rounded'>
                    </a>
                    <p class='col-12 text-center'>%s</p>
                    
                </div>
                """.formatted(profileUrl, profileName, itemURL, imageURL,itemData);
    }

    private void addElementToHTMLFile(String filename,String element) throws IOException {
        try{
            Path path = Paths.get("C:\\Users\\NASA\\Desktop\\testing\\java\\" + filename);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            // index 15 because in HTML file created in createHTML() method body section starts at 8 th line
            lines.add(15, element);
            Files.write(path, lines, StandardCharsets.UTF_8);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    // Scroll site to bottom and then open next page
    private void openNextPage(String searchText) throws IOException, InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
        WebElement nextPageButton = driver.findElement(By.className("Pagination_next__lgFUF"));
        if (nextPageButton != null){
            nextPageButton.click();
            scrapVintedItems(searchText);
        }
    }
}
