package training.selenium.homework.homework_7;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class ClickAllMenu {

    private final static String MENU_SELECTOR = "#box-apps-menu li";
    private final static String FIRST_MENU_SELECTOR = "//ul[@id='box-apps-menu']/li[%d]";
    private final static String COUNT_SECOND_MENU_SELECTOR = "//ul[@id='box-apps-menu']/li[%d]/*/li";
    private final static String SECOND_MENU_SELECTOR = "//ul[@id='box-apps-menu']/li[%d]/*/li[%d]";
    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void OpenPageAndCloseBrowserTest() {
        login();
        for (int i = 1; i < countElements(driver, By.cssSelector(MENU_SELECTOR)); i++) {
            WebElement menuItem = driver.findElement(By.xpath(String.format(FIRST_MENU_SELECTOR, i)));
            menuItem.click();
            if (!isElementPresent(driver, By.cssSelector("h1")))
                System.out.println("[ERROR]: tagName <H1> not found in " + menuItem.getAttribute("outerText"));

            if (countElements(driver, By.xpath(String.format(COUNT_SECOND_MENU_SELECTOR, i))) > 0 ) {
                for (int k = 1; k <= countElements(driver, By.xpath(String.format(COUNT_SECOND_MENU_SELECTOR, i))); k++) {
                    WebElement secondMenuItem = driver.findElement(By.xpath(String.format(SECOND_MENU_SELECTOR, i, k)));
                    secondMenuItem.click();
                }
            }
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

    private int countElements(final WebDriver driver, final By locator) {
        return driver.findElements(locator).size();
    }

    private boolean isElementPresent(final WebDriver driver, final By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}