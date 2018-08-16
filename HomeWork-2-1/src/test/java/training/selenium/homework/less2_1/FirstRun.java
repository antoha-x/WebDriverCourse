package training.selenium.homework.less2_1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FirstRun {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "/Users/titovab/Public/driver/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void OpenPageAndCloseBrowserTest() {
        driver.manage().window().fullscreen();
        driver.get("http://google.com/");
        wait.until(titleIs("Google"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
