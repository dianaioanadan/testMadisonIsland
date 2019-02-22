package org.fasttrackit.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilterBy {

    @FindBy(xpath = "//dl[@id='narrow-by-list']//a[@href='https://fasttrackit.org/selenium-test/vip.html?price=-100']")
    private WebElement price;

    public WebElement getPrice() {
        return price;
    }

    @FindBy(xpath = "//dl[@id='narrow-by-list'] // dd[@class='even'] // a[@href='https://fasttrackit.org/selenium-test/sale.html?price=100-200']")
    private WebElement priceMoreThan100;

    public WebElement getPriceMoreThan100() {
        return priceMoreThan100;
    }
}
