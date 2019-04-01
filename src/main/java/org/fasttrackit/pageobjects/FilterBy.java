package org.fasttrackit.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilterBy {

    @FindBy(xpath = "//*[@id='narrow-by-list']/dd[1]/ol/li[1]/a")
    private WebElement priceLessThan100Filter;

    @FindBy(xpath = "//*[@id='narrow-by-list']/dd[2]/ol/li[1]/a")
    private WebElement priceMoreThan100Filter;

    public WebElement getPrice() {
        return priceLessThan100Filter;
    }

    public WebElement getPriceMoreThan100Filter() {
        return priceMoreThan100Filter;
    }
}
