package org.fasttrackit.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationBar {

    @FindBy(xpath = "//*[@id='nav']/ol/li[6]")
    private WebElement vipPage;

    @FindBy(xpath = "//*[@id='nav']/ol/li[5]")
    private WebElement salePage;

    public WebElement getVipPage() {
        return vipPage;
    }

    public WebElement getSalePage() {
        return salePage;
    }
}
