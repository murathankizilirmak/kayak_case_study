package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MainPage extends BasePage {

    public static void main(String[] args) throws IOException, AWTException {
    }

    @Test
    public static void OpenTheMainPage() throws AWTException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(@id,'accept')]"))));
        if (driver.getCurrentUrl().contains("https://www.kayak.ch/")) {
            try {
                driver.findElement(By.xpath("//button[contains(@id,'accept')]")).click();
            } catch (Exception ex) {
                BufferedImage capture = new Robot().createScreenCapture(screenRect);
                ImageIO.write(capture, "jpg", new File(".\\src\\test\\java\\Screenshots\\MainPage.jpg"));
                Assert.fail("Main page not opened properly");
                driver.close();
            }
        }
    }

    @Test
    public static void SearchRoundTripDestination(String Departure, String Return) throws AWTException, IOException {
        try {
            driver.findElement(By.xpath(CLICK_MULTIPLY_BUTTON)).click();
            Assert.assertNotNull("Departure text area not founded", driver.findElement(By.xpath(MAIN_PAGE_DEPARTURE_TEXT_AREA)));
            driver.findElement(By.xpath(MAIN_PAGE_DEPARTURE_TEXT_AREA)).sendKeys(Departure);
            Assert.assertNotNull("Auto complete not working", driver.findElement(By.xpath(MAIN_PAGE_DEPARTURE_AUTO_COMPLETE_FIRST_ELEMENT)));
            driver.findElement(By.xpath(MAIN_PAGE_DEPARTURE_AUTO_COMPLETE_FIRST_ELEMENT)).click();
            driver.findElement((By.xpath(MAIN_PAGE_RETURN))).click();
            driver.findElement((By.xpath(MAIN_PAGE_DEPARTURE_TEXT_AREA))).sendKeys(Return);
            driver.findElement(By.xpath(MAIN_PAGE_DEPARTURE_AUTO_COMPLETE_FIRST_ELEMENT)).click();

        } catch (Exception e) {
            Rectangle screenRect = new Rectangle();
            BufferedImage capture = new Robot().createScreenCapture(screenRect);
            ImageIO.write(capture, "jpg", new File(".\\src\\test\\java\\Screenshots\\DepartureOrReturn.jpg"));
            Assert.fail("Departure data not inserted properly");
            driver.close();
        }
    }


    @Test
    public static void SearchRoundTripRange(Integer range) throws AWTException, IOException {

        try{
            driver.findElement(By.xpath(MAIN_PAGE_DEPARTURE_CALENDER)).click();
            driver.findElement(By.xpath("//div[contains(@class,'pres-default mkUa-isPriceHigh')]["+1+"]")).click();
            driver.findElement(By.xpath("//div[contains(@class,'pres-default mkUa-isPriceHigh')]["+range+"]")).click();
        }
        catch (Exception e)
        {
            Rectangle screenRect = new Rectangle();
            BufferedImage capture = new Robot().createScreenCapture(screenRect);
            ImageIO.write(capture, "jpg", new File(".\\src\\test\\java\\Screenshots\\Calender.jpg"));
            Assert.fail("Departure data not inserted properly");
            driver.close();
        }
    }

    public static void ClickSearch()
    {
        driver.findElement(By.xpath(MAIN_SEARCH_BUTTON)).click();
        if (driver.findElements(By.xpath("//div[@class='resultInner']")).size()==0)
        {
            Assert.fail("Search result not loaded");
        }
    }

}