package com.seleniumproject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AutomateAmazon {
    WebDriver driverObj;
    JavascriptExecutor jse;

    public void invokeBrowserAndAmazon() {
        try {
            System.setProperty("webdriver.chrome.driver", "/home/prabhas/Desktop/MountBlueAssignment/Java/Drivers/chromedriver");
            driverObj = new ChromeDriver();
            driverObj.get("https://www.amazon.in");
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
        try {
            driverObj.findElement(By.id("twotabsearchtextbox")).sendKeys("java books");
            Thread.sleep(3000);
            driverObj.findElement(By.id("nav-search-submit-button")).click();
            jse = (JavascriptExecutor) driverObj;
            //jse.executeScript("scroll(0,400)");
            driverObj.findElement(By.xpath("//body/div[@id='a-page']/div[@id='search']/div[1]/div[1]/div[1]/span[3]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/h2[1]/a[1]/span[1]")).click();
            driverObj.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
            String parentHandle = driverObj.getWindowHandle();
            Set<String> childhandles = driverObj.getWindowHandles();
            for (String handle : childhandles) {
                if (!handle.equals(parentHandle)) {
                    driverObj.switchTo().window(handle);
                    driverObj.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
                    driverObj.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();
                    driverObj.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("7782835748");
                    driverObj.findElement(By.xpath("//input[@id='continue']")).click();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AutomateAmazon amzObj = new AutomateAmazon();
        amzObj.invokeBrowserAndAmazon();
    }
}
