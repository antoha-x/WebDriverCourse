package training.selenium.homework.homework_9_1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class CheckSortCountry {

    private static final String COUNTRIES_PAGE = "http://localhost/litecart/admin/?app=countries&doc=countries";
    private static final String COUNTRY_NAME = "//tr[@class='row'][%d]/td[%d]";
    private static final int COUNTRY_COL_INDEX = 5;


    private WebDriver driver;



    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
    }


    @Test
    public void checkAlphabetCountryTest() {
        login();
        openPage(COUNTRIES_PAGE);

        List<String> countryName = new ArrayList<>();

        for (int i = 0; i < countElements(driver, By.cssSelector(".row")); i++) {
            countryName.add(driver.findElement(By.xpath(String.format(COUNTRY_NAME,i+1,COUNTRY_COL_INDEX))).getAttribute("innerText"));
        }

        List<String> countryNameSort = new ArrayList<>();
        countryNameSort.addAll(countryName);
        Collections.sort(countryNameSort);

        for (int i = 0; i < countryName.size(); i++) {
            assertEquals(countryNameSort.get(i), countryName.get(i));
        }
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    private void openPage(String url) {
        driver.manage().window().fullscreen();
        driver.get(url);
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
}
