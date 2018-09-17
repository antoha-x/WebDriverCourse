
package training.selenium.homework.less2_3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class LitecartLoginAsAdmin {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        //System.setProperty("webdriver.chrome.driver", "/Users/titovab/Public/driver/chromedriver");
        driver = new ChromeDriver();
		//driver = new FirefoxDriver();
        //driver = new SafariDriver();

        //run old schemas webdriver
        //FirefoxOptions options = new FirefoxOptions().setLegacy(true);
        //driver = new FirefoxDriver(options);

        //run nightly version firefox
        //FirefoxOptions options = new FirefoxOptions();
        //options.setBinary(new FirefoxBinary(new File("/Applications/Firefox Nightly.app/Contents/MacOS/firefox")));
        //driver = new FirefoxDriver(options);


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//implicityWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void OpenPageAndCloseBrowserTest() {
        //driver.manage().window().fullscreen();
        driver.get("http://localhost/litecart/admin/");

        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
