package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class BaseSelenium {

    public static WebDriver driver = null;

    @Parameters({"os"})
    @BeforeMethod
    public void setUp(@Optional("mac") String os) {
        if(os.equalsIgnoreCase("mac")){
            System.setProperty("webdriver.chrome.driver", "src/main/driver/chromedriver");
        }else if(os.equalsIgnoreCase("windows")){
            System.setProperty("webdriver.chrome.driver", "src/main/driver/chromedriver.exe");
        }
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.close();
    }

    public void waitForElementToBeClickable(WebElement element, int timeOutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
