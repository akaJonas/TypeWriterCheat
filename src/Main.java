import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;

public class Main {

    /**
     * username and password for Typewriter.at
     */
    static String username = "username";
    static String password = "password";


    /**
     * Delay between writing the characters
     */
    static int delay = 20;

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException, AWTException {

        // setup the chromedriver
        System.setProperty("webdriver.chrome.driver", "chromeDriverPath");
        driver = new ChromeDriver();

        //maximize the window and enter to url
        driver.manage().window().maximize();
        driver.get("https://at4.typewriter.at/");


        /**
         * login on page
         */
        waitForBy(By.className("login-input"), true);
        driver.findElement(By.id("LoginForm_username")).clear();
        driver.findElement(By.id("LoginForm_username")).sendKeys(username);

        driver.findElement(By.id("LoginForm_pw")).clear();
        driver.findElement(By.id("LoginForm_pw")).sendKeys(password);


        /**
         *  navigate to task
         */
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[name=\"yt0\"]")).click();

        Thread.sleep(1000);
        driver.findElement(By.className("cockpitStartButton")).click();


        /**
         * press any key to start
         */
        Thread.sleep(1000);
        Keyboard kb = new Keyboard();
        kb.type(" ");


        /**
         * do the task
         */
        while(true) {
            try {
                Thread.sleep(delay);
                WebElement actualLetterElement = driver.findElement(By.id("actualLetter"));
                String actualLetter = actualLetterElement.getText();
                kb.type(actualLetter);
            } catch (Exception e) {}
        }

    }


    /**
     * Wait for Element to load
     * @param by
     */
    static void waitForBy(By by, boolean timeout) {
        WebDriverWait wait;
        if (timeout) {
            wait = new WebDriverWait(driver, 10);
        } else {
            wait = new WebDriverWait(driver, 3600);
        }
        WebElement element = wait.until(
        ExpectedConditions.visibilityOfElementLocated(by));
    }

}
