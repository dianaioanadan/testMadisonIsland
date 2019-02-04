package org.fasttrackit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Wishlist {
    @Test
    public void addElementOnWishlist() {
        System.setProperty("webdriver.chrome.driver", "src\\resorces\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fasttrackit.org/selenium-test/");

        driver.findElement(By.className("nav-5")).click();
        driver.findElement(By.className("link-wishlist")).click();
        driver.quit();
    }
}
