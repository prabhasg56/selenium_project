package com.seleniumproject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.Set;

public class AutomateMyntra {
    WebDriver driverObj;
    @Test
    public void invokeBrowserAndMyntra() {
        try {
            System.setProperty("webdriver.chrome.driver", "/home/prabhas/Desktop/MountBlueAssignment/Java/Drivers/chromedriver");
            driverObj = new ChromeDriver();
            driverObj.get("https://www.myntra.com/");
            driverObj.manage().deleteAllCookies();
            driverObj.manage().window().maximize();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void searchProductAndAddToCart() {
        WebElement searchBox = driverObj.findElement(By.className("desktop-searchBar"));
        searchBox.sendKeys("Puma Shoes");
        driverObj.findElement(By.className("desktop-submit")).click();
        WebElement item = driverObj.findElement(By.xpath("//body/div[@id='mountRoot']/div[1]/div[1]/main[1]/div[3]/div[2]/div[1]/div[2]/section[1]/ul[1]/li[3]/a[1]"));
        item.click();
        String firstWindow = driverObj.getWindowHandle();
        Set<String> childWindows = driverObj.getWindowHandles();
        for (String window : childWindows) {
            if (!(window.equals(firstWindow))) {
                driverObj.switchTo().window(window);
                WebElement selectSize = driverObj.findElement(By.xpath("//body/div[@id='mountRoot']/div[1]/div[1]/div[1]/main[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/button[1]/p[1]"));
                selectSize.click();
                WebElement addToCart = driverObj.findElement(By.xpath("//body/div[@id='mountRoot']/div[1]/div[1]/div[1]/main[1]/div[2]/div[2]/div[3]/div[1]/div[1]"));
                addToCart.click();
                WebElement goToBag = driverObj.findElement(By.xpath("//span[contains(text(),'Bag')]"));
                goToBag.click();
                JavascriptExecutor jseObj = (JavascriptExecutor)driverObj;
                jseObj.executeScript("window.scrollBy(0,300)");
                WebElement placeOrder = driverObj.findElement(By.xpath("//button[@id='placeOrderButton']"));
                placeOrder.click();
                WebElement enterMobile = driverObj.findElement(By.xpath("//input[@type='tel']"));
                enterMobile.sendKeys("7782835748");
                driverObj.findElement(By.xpath("//div[normalize-space()='CONTINUE']")).click();
            }
        }
    }
}
