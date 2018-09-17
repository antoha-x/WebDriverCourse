package training.selenium.homework.homework_7;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ClickAllMenu {

    private final static String MENU_SELECTOR = "#box-apps-menu li";
    private final static String SECOND_MENU_SELECTOR = "//ul[@id='box-apps-menu']/li[%d]";
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(this.driver, 10);
    }

    @Test
    public void OpenPageAndCloseBrowserTest() {
        login();
        for (int i = 1; i < countLeftMenuCategory(driver, By.cssSelector(MENU_SELECTOR)); i++) {
            WebElement menuItem = driver.findElement(By.xpath(String.format(SECOND_MENU_SELECTOR, i)));
            menuItem.click();
            if (isElementPresent(driver, By.cssSelector("h1")) == false)
                System.out.println("[ERROR]: tagName <H1> not found in " + menuItem.getAttribute("outerText"));

        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    private void login() {
        driver.manage().window().fullscreen();
        driver.get("http://localhost/litecart/admin/");

        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    private int countLeftMenuCategory(WebDriver driver, By locator) {
        return driver.findElements(locator).size();
    }

    private boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}