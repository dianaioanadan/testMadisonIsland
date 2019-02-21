package org.fasttrackit.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RightMenu {

    @FindBy(xpath = "//div[@class='block-content']//li[@class='item last odd']")
    private WebElement recentlyViewedElement;

    public WebElement getRecentlyViewedElement() {
        return recentlyViewedElement;
    }

    public WebElement productNameAddedToCompare(String productName, WebDriver driver){
        return   driver.findElement(By.xpath("//div[@class='product-info' and .//a[text()= '"+productName+"']]//a[@class='link-compare']"));
    }
}
