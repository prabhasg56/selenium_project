import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InvokeAndSearchBrowser {
    WebDriver driver;
    JavascriptExecutor jse;

    public void invokeBrowser() {
        try {
            System.setProperty("webdriver.chrome.driver", "/home/prabhas/Desktop/MountBlueAssignment/Java/Drivers/chromedriver");
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get("https://www.amazon.in");
            searchItems();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void searchItems() {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("boadBasshead 242");
        driver.findElement(By.id("nav-search-submit-button")).click();
        jse = (JavascriptExecutor) driver;
        //jse.executeScript("scroll(0,800)");
        driver.findElement(By.className("a-size-base a-color-base")).click();
    }

    public static void main(String[] args) {
        InvokeAndSearchBrowser obj = new InvokeAndSearchBrowser();
        obj.invokeBrowser();
    }
}
