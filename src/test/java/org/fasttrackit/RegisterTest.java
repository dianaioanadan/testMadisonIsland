package org.fasttrackit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterTest {
    @Test
    public void operRegisterPage() {
        System.setProperty("webdriver.chrome.driver", "src\\resorces\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fasttrackit.org/selenium-test/");

        driver.findElement(By.className("skip-account")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("firstname")).sendKeys("test1");
        driver.findElement(By.name("middlename")).sendKeys("test2");
        driver.findElement(By.id("lastname")).sendKeys("test3");
        driver.findElement(By.className("validate-email")).sendKeys("test@test.org");
        driver.findElement(By.id("password")).sendKeys("test123");
        driver.findElement(By.name("confirmation")).sendKeys("test123");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div[2]/form/div[2]/button")).click();
    }
}
