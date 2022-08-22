package com.seleniumproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AutomateFlipkart {
    WebDriver driverObj;

    public void invokeBrowserAndFlipkart() {
        try {
            System.setProperty("webdriver.chrome.driver", "/home/prabhas/Desktop/MountBlueAssignment/Java/Drivers/chromedriver");
            driverObj = new ChromeDriver();
            driverObj.get("https://www.flipkart.com/");
            driverObj.manage().deleteAllCookies();
            driverObj.manage().window().maximize();
            driverObj.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            Thread.sleep(1000);
            searchProductAndAddToCart();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchProductAndAddToCart() {
        WebElement searchBox = driverObj.findElement(By.className("_3704LK"));
        searchBox.sendKeys("iPhone 13");
        driverObj.findElement(By.className("L0Z3Pu")).click();
        WebElement item = driverObj.findElement(By.xpath("//div[normalize-space()='APPLE iPhone 13 (Starlight, 128 GB)']"));
        item.click();
        String firstWindow = driverObj.getWindowHandle();
        Set<String> windows = driverObj.getWindowHandles();
        for (String window : windows) {
            if (!(window.equals(firstWindow))) {
                driverObj.switchTo().window(window);
                WebElement addToCart = driverObj.findElement(By.xpath("//button[normalize-space()='ADD TO CART']"));
                addToCart.click();
                WebElement placeOrder = driverObj.findElement(By.xpath("//span[normalize-space()='Place Order']"));
                placeOrder.click();
                WebElement enterMobile = driverObj.findElement(By.xpath("//input[@type='text']"));
                enterMobile.sendKeys("7782835748");
                driverObj.findElement(By.xpath("//span[normalize-space()='CONTINUE']")).click();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AutomateFlipkart amzObj = new AutomateFlipkart();
        amzObj.invokeBrowserAndFlipkart();
    }
}
