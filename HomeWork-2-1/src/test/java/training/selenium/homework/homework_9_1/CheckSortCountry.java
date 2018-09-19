package training.selenium.homework.homework_9_1;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class CheckSortCountry {

    private static final String COUNTRIES_PAGE = "http://localhost/litecart/admin/?app=countries&doc=countries";
    private static final String COUNTRY = "//tr[@class='row'][%d]/td[%d]";
    private static final String ROW_COUNTRY = ".row";
    private static final int COUNTRY_COL_INDEX = 5;
    private static final int COL_TIME_ZONE = 6;
    private static final int COL_INDEX_LINK = 5;


    private WebDriver driver;



    @Before
    public void start() {
        driver = new ChromeDriver();
        //driver.manage().window().fullscreen();
        login();
    }

    @Ignore
    @Test
    public void checkAlphabetCountryTest() {
        openPage(COUNTRIES_PAGE);

        List<String> countryName = new ArrayList<>();

        for (int i = 0; i < countElements(driver, By.cssSelector(ROW_COUNTRY)); i++) {
            countryName.add(getTextElement(driver, COUNTRY,i+1, COUNTRY_COL_INDEX));
        }

        List<String> countryNameSort = new ArrayList<>();
        countryNameSort.addAll(countryName);
        Collections.sort(countryNameSort);

        for (int i = 0; i < countryName.size(); i++) {
            assertEquals(countryNameSort.get(i), countryName.get(i));
        }

    }

    @Test
    public void checkCountryAlphabetTimeZoneTest() {
        openPage(COUNTRIES_PAGE);
        List<WebElement> countryWithTimeZone = new ArrayList<>();
        for (int i = 0; i < countElements(driver, By.cssSelector(ROW_COUNTRY)); i++) {
            if (Integer.parseInt(getTextElement(driver, COUNTRY, i+1, COL_TIME_ZONE)) > 0 ) {
                countryWithTimeZone.add(driver.findElement(By.xpath(String.format(COUNTRY, i + 1, COL_INDEX_LINK))));
                System.out.println(driver.findElement(By.xpath(String.format(COUNTRY, i + 1, COL_INDEX_LINK))).getAttribute("innerHTML"));
            }
        }

        for (WebElement element : countryWithTimeZone) {
            element.findElement(By.cssSelector("a")).click();
            checkSortTimeZone(driver);
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

    private String getTextElement(WebDriver driver, String xPathLocator, int firstIndex, int secondIndex) {
       return driver.findElement(By.xpath(String.format(xPathLocator, firstIndex, secondIndex))).getAttribute("innerText");
    }

    private boolean checkSortTimeZone(WebDriver driver) {
        ArrayList<String> listTimeZone = new ArrayList<>();
        List<WebElement> listTimeZoneElements = driver.findElements(By.xpath("//table[@id='table-zones']/*/tr/td[3]"));

        System.out.println(listTimeZoneElements.size());
        int i = 0;
        for (WebElement element : listTimeZoneElements) {
            if (!element.getText().isEmpty()) {
                listTimeZone.add(element.getText());
                i++;
            }
        }
        System.out.println(i);
        return true;
    }
}
