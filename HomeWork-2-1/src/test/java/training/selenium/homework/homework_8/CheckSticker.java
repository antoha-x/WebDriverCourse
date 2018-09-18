package training.selenium.homework.homework_8;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.NoSuchElementException;

public class CheckSticker {

    private static final String IMAGE_BOX = "div.image-wrapper";
    private static final String STICKER_BOX = "div.sticker";
    private static final int STICKER = 1;


    private WebDriver driver;



    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void checkAllOnlyOneSticker() {
        openMainPage();
        for (int i = 1; i < countElements(driver, By.cssSelector(IMAGE_BOX)); i++) {
            List<WebElement> product = driver.findElements(By.cssSelector(IMAGE_BOX));
            if (countElements(product.get(i)) != STICKER) {
                System.out.println("Item product" + imageText(product.get(i)) + " is have not only one Sticker");
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    private void openMainPage() {
        driver.manage().window().fullscreen();
        driver.get("http://localhost/litecart/en/");
    }

    private int countElements(final WebDriver driver, final By locator) {
        return driver.findElements(locator).size();
    }

    private int countElements(WebElement product) {
        return product.findElements(By.cssSelector(STICKER_BOX)).size();
    }

    private String imageText(WebElement image) {
        try {
            return image.findElement(By.cssSelector("img")).getAttribute("alt");
        } catch (NoSuchElementException ex) {
            System.out.println("[ERROR]: alternate text for image not found!");
            throw ex;
        }
    }
}
