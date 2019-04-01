package org.fasttrackit.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage {

    @FindBy(xpath = "//div[@class='product-shop']//div[@class='product-name']")
    private WebElement productPageTitle;

    public WebElement getProductPageTitle() {
        return productPageTitle;
    }
}
