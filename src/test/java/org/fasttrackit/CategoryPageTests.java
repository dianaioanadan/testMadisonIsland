package org.fasttrackit;

import org.fasttrackit.pageobjects.*;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CategoryPageTests {
    public double convertPrice(String priceString) {
        String[] newPriceString = priceString.split(" ");
        String intPriceString = newPriceString[0];
        String stringPriceString = newPriceString[1];
        intPriceString = intPriceString.replace(",", ".");
        double priceValue = Double.parseDouble(intPriceString);
        return priceValue;
    }

    @Test
    public void openCategoryPage() {
        System.setProperty("webdriver.chrome.driver", AppConfig.getChromeDriverPath());
        WebDriver driver = new ChromeDriver();
        driver.get(AppConfig.getSiteUrl());

        NavBar navBar = PageFactory.initElements(driver,NavBar.class);
        navBar.getVipPage().click();

        String URL = driver.getCurrentUrl();

        assertThat("User is not on the right page", URL, is("https://fasttrackit.org/selenium-test/vip.html"));

        driver.quit();
    }

    @Test
    public void sortByPrice() {
        System.setProperty("webdriver.chrome.driver", AppConfig.getChromeDriverPath());
        WebDriver driver = new ChromeDriver();
        driver.get(AppConfig.getSiteUrl());

        NavBar navBar = PageFactory.initElements(driver,NavBar.class);
        navBar.getVipPage().click();

        ProductGrid productGrid = PageFactory.initElements(driver,ProductGrid.class);
        productGrid.getSortByPrice().click();

        String firstProductPrice = productGrid.getFistElementPrice().getText();
        double firstPrice = convertPrice(firstProductPrice);

        String secondProductPrice = productGrid.getSecondElementPrice().getText();
        double secondPrice = convertPrice(secondProductPrice);

        String thirdProductPrice = productGrid.getThirdElementPrice().getText();
        double thirdPrice = convertPrice(thirdProductPrice);

        String fourthProductPrice = productGrid.getFourthProductPrice().getText();
        double fourthPrice = convertPrice(fourthProductPrice);

        String fifthProductPrice = productGrid.getFifthProductPrice().getText();
        double fifthPrice = convertPrice(fifthProductPrice);

        Boolean expectedResult = true;

        if (firstPrice < secondPrice) {
            expectedResult = true;
            if (secondPrice < thirdPrice) {
                expectedResult = true;
                if (thirdPrice < fourthPrice) {
                    expectedResult = true;
                    if (fourthPrice < fifthPrice) {
                        expectedResult = true;
                    } else {
                        expectedResult = false;
                    }
                } else {
                    expectedResult = false;
                }
            } else {
                expectedResult = false;
            }
        } else {
            expectedResult = false;
        }

        assertThat("Sort by price is not working.",expectedResult, is(true));

        driver.quit();
    }

    @Test
    public void openProductPageByClickOnImage(){
        System.setProperty("webdriver.chrome.driver", AppConfig.getChromeDriverPath());
        WebDriver driver = new ChromeDriver();
        driver.get(AppConfig.getSiteUrl());

        NavBar navBar = PageFactory.initElements(driver,NavBar.class);
        navBar.getVipPage().click();

        ProductGrid productGrid = PageFactory.initElements(driver,ProductGrid.class);
        productGrid.getProductImage().click();

        String URL = driver.getCurrentUrl();

        assertThat("Product page is not open.",URL, is("https://fasttrackit.org/selenium-test/vip/a-tale-of-two-cities.html"));

        driver.quit();
    }

    @Test
    public void openProductPageByClickOnText(){
        System.setProperty("webdriver.chrome.driver", AppConfig.getChromeDriverPath());
        WebDriver driver = new ChromeDriver();
        driver.get(AppConfig.getSiteUrl());

        NavBar navBar = PageFactory.initElements(driver,NavBar.class);
        navBar.getVipPage().click();

        ProductGrid productGrid = PageFactory.initElements(driver,ProductGrid.class);
        productGrid.getProductName().click();

        String URL = driver.getCurrentUrl();

        assertThat("Product page is not open.",URL, is("https://fasttrackit.org/selenium-test/vip/a-tale-of-two-cities.html"));

        driver.quit();
    }

    @Test
    public void recentlyViewedProducts() {
        System.setProperty("webdriver.chrome.driver", AppConfig.getChromeDriverPath());
        WebDriver driver = new ChromeDriver();
        driver.get(AppConfig.getSiteUrl());

        NavBar navBar = PageFactory.initElements(driver,NavBar.class);
        navBar.getVipPage().click();

        ProductGrid productGrid = PageFactory.initElements(driver,ProductGrid.class);
        productGrid.getProductName().click();
        driver.navigate().back();

        RightMenu rightMenu = PageFactory.initElements(driver,RightMenu.class);

        assertThat("Recently viewed products is not displayed", rightMenu.getRecentlyViewedElement().isDisplayed());
        driver.quit();
    }

    @Test
    public void addToWishlistWithoutLogin(){
        System.setProperty("webdriver.chrome.driver", AppConfig.getChromeDriverPath());
        WebDriver driver = new ChromeDriver();
        driver.get(AppConfig.getSiteUrl());

        NavBar navBar = PageFactory.initElements(driver,NavBar.class);
        navBar.getVipPage().click();

        String productName = "Geometric Candle Holders";

        ProductGrid productGrid = PageFactory.initElements(driver,ProductGrid.class);
        productGrid.getAddToWishListtButton(productName,driver).click();

        String URL = driver.getCurrentUrl();

        assertThat("Product page is not open.",URL, is("https://fasttrackit.org/selenium-test/customer/account/login/"));

        driver.quit();
    }

    @Test
    public void addToCompare(){
        System.setProperty("webdriver.chrome.driver", AppConfig.getChromeDriverPath());
        WebDriver driver = new ChromeDriver();
        driver.get(AppConfig.getSiteUrl());

        NavBar navBar = PageFactory.initElements(driver,NavBar.class);
        navBar.getVipPage().click();

        String productName = "Geometric Candle Holders";

        RightMenu rightMenu = PageFactory.initElements(driver,RightMenu.class);
        rightMenu.productNameAddedToCompare(productName,driver).click();

        String msg = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();

        assertThat("Product wasn't added to compare",msg,is("The product "+productName+" has been added to comparison list."));
        driver.quit();
    }

    @Test
    public void addToCart(){
        System.setProperty("webdriver.chrome.driver", AppConfig.getChromeDriverPath());
        WebDriver driver = new ChromeDriver();
        driver.get(AppConfig.getSiteUrl());

        NavBar navBar = PageFactory.initElements(driver,NavBar.class);
        navBar.getVipPage().click();

        String productName = "Geometric Candle Holders";

        ProductGrid productGrid = PageFactory.initElements(driver,ProductGrid.class);
        productGrid.getAddToCartButton(productName,driver).click();

        String msg = driver.findElement(By.className("success-msg")).getText();

        assertThat("Success message is not displayed",msg, CoreMatchers.is(productName+" was added to your shopping cart."));

        Checkout checkout = PageFactory.initElements(driver,Checkout.class);
        assertThat("Product is not on cart page",checkout.productNameAddedToCart(productName,driver).isDisplayed());

        driver.quit();
    }

    @Test
    public void filterByOneCategory() {
        System.setProperty("webdriver.chrome.driver", AppConfig.getChromeDriverPath());
        WebDriver driver = new ChromeDriver();
        driver.get(AppConfig.getSiteUrl());

        NavBar navBar = PageFactory.initElements(driver,NavBar.class);
        navBar.getVipPage().click();

        FilterBy filterBy = PageFactory.initElements(driver,FilterBy.class);
        filterBy.getPrice().click();

        ProductGrid productGrid = PageFactory.initElements(driver,ProductGrid.class);
        String firstProductPrice=productGrid.getFistElementPrice().getText();
        double firstPrice = convertPrice(firstProductPrice);

        String secondProductPrice = productGrid.getSecondElementPrice().getText();
        double secondPrice = convertPrice(secondProductPrice);

        String thirdProductPrice = productGrid.getThirdElementPrice().getText();
        double thirdPrice = convertPrice(thirdProductPrice);

        Boolean expectedResult = true;

        if (firstPrice > 100) {
            expectedResult = false;
        } else {
            if (secondPrice > 100) {
                expectedResult = false;
            } else if (thirdPrice > 100) expectedResult = false;
        }


        assertThat("Filter by price is not working.",expectedResult, is(true));

        driver.quit();
    }

    @Test
    public void searchFunctionalityFromCategoryPage(){
        System.setProperty("webdriver.chrome.driver", AppConfig.getChromeDriverPath());
        WebDriver driver = new ChromeDriver();
        driver.get(AppConfig.getSiteUrl());

        NavBar navBar = PageFactory.initElements(driver,NavBar.class);
        navBar.getVipPage().click();

        String keyword = "vase";

        Header header = PageFactory.initElements(driver,Header.class);
        header.search(keyword);

        List<WebElement> productNameContainers = driver.findElements(By.cssSelector(".product-name >a"));

        for (WebElement containers: productNameContainers){
            String productName = containers.getText();

            assertThat("Some of the products names, do not contain the search heyword", productName,containsString(keyword.toUpperCase()));
        }
        driver.quit();
    }
}
