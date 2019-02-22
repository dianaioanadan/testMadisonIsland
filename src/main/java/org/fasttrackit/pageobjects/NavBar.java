package org.fasttrackit.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavBar {

    @FindBy(className = "nav-6")
    private WebElement vipPage;

    public WebElement getVipPage() {
        return vipPage;
    }

    @FindBy(className = "nav-5")
    private WebElement salePage;

    public WebElement getSalePage() {
        return salePage;
    }
}
