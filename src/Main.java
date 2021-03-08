import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;

public class Main {

    static String username = "username";
    static String password = "password";
    static int delay = 20;

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException, AWTException {

        System.setProperty("webdriver.chrome.driver", "chromeDriverPath");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://at4.typewriter.at/");

        waitForBy(By.className("login-input"), true);
        driver.findElement(By.id("LoginForm_username")).clear();
        driver.findElement(By.id("LoginForm_username")).sendKeys(username);

        driver.findElement(By.id("LoginForm_pw")).clear();
        driver.findElement(By.id("LoginForm_pw")).sendKeys(password);

        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[name=\"yt0\"]")).click();

        Thread.sleep(1000);
        driver.findElement(By.className("cockpitStartButton")).click();

        Thread.sleep(1000);
        Keyboard kb = new Keyboard();
        kb.type(" ");

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
