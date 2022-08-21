import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTestSelenium {
    public static void main(String[] args) {

        // Lounch Chrome browser
        // System.setProperty(Key, CromeDriver path)
        System.setProperty("webdriver.chrome.driver", "/home/prabhas/Desktop/MountBlueAssignment/Java/Drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        // Launch google web page
        driver.navigate().to("http://www.google.com");
        // Create title of webpage and print
        String title = driver.getTitle();
        System.out.println("Page title : " + title);
        //Capture current URL of webpage
        System.out.println("URL: " + driver.getCurrentUrl());
      }
}
