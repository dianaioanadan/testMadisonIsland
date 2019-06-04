package org.fasttrackit;

import org.fasttrackit.pageobjects.*;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class VipCategoryPageTests extends TestBase {

    @Test
    public void openCategoryPage() {

        NavigationBar navigationBar = PageFactory.initElements(driver, NavigationBar.class);
        navigationBar.getVipPage().click();

        String URL = driver.getCurrentUrl();

        assertThat("User is not on the right page", URL, is(AppConfig.getSiteUrl() + "vip.html"));
    }

    @Test
    public void sortByPrice() {

        NavigationBar navigationBar = PageFactory.initElements(driver, NavigationBar.class);
        navigationBar.getVipPage().click();

        ProductGrid productGrid = PageFactory.initElements(driver, ProductGrid.class);
        productGrid.getSortByPriceFilter().click();

        List<Double> prices = productGrid.getActualProductPricesAsDouble();
        int i;

        Boolean expectedResult = true;
        for(i=0; i <prices.size()-1 ;i++){
            if (prices.get(i)> prices.get(i+1)){
                expectedResult = false;
            }
        }

        assertThat("Sort by price is not working.", expectedResult, is(true));
    }

    @Test
    public void openProductPageByClickOnImage() {

        NavigationBar navigationBar = PageFactory.initElements(driver, NavigationBar.class);
        navigationBar.getVipPage().click();

        ProductGrid productGrid = PageFactory.initElements(driver, ProductGrid.class);
        productGrid.getProductImage().click();

        ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);

        String URL = driver.getCurrentUrl();

        assertThat("Product page is not open.", URL, is(AppConfig.getSiteUrl() + "vip/a-tale-of-two-cities.html"));
        assertThat("Product page is not open.",productPage.getProductPageTitle().isDisplayed());
    }

    @Test
    public void openProductPageByClickOnText() {

        NavigationBar navigationBar = PageFactory.initElements(driver, NavigationBar.class);
        navigationBar.getVipPage().click();

        ProductGrid productGrid = PageFactory.initElements(driver, ProductGrid.class);
        productGrid.getProductName().click();

        ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);


        String URL = driver.getCurrentUrl();

        assertThat("Product page is not open.", URL, is(AppConfig.getSiteUrl() + "vip/a-tale-of-two-cities.html"));
        assertThat("Product page is not open.",productPage.getProductPageTitle().isDisplayed());
    }

    @Test
    public void recentlyViewedProducts() {

        NavigationBar navigationBar = PageFactory.initElements(driver, NavigationBar.class);
        navigationBar.getVipPage().click();

        ProductGrid productGrid = PageFactory.initElements(driver, ProductGrid.class);
        productGrid.getProductName().click();
        driver.navigate().back();

        RightMenu rightMenu = PageFactory.initElements(driver, RightMenu.class);

        assertThat("Recently viewed products component is not displayed", rightMenu.getRecentlyViewedElement().isDisplayed());
    }

    @Test
    public void addToWishlistWithoutLogin() {

        NavigationBar navigationBar = PageFactory.initElements(driver, NavigationBar.class);
        navigationBar.getVipPage().click();

        String productName = "Geometric Candle Holders";

        ProductGrid productGrid = PageFactory.initElements(driver, ProductGrid.class);
        productGrid.getAddToWishListtButton(productName, driver).click();

        String URL = driver.getCurrentUrl();

        assertThat("Product page is not open.", URL, is(AppConfig.getSiteUrl() + "customer/account/login/"));
    }

    @Test
    public void addToCompareButtonFunctionality() {

        NavigationBar navigationBar = PageFactory.initElements(driver, NavigationBar.class);
        navigationBar.getVipPage().click();

        String productName1 = "Geometric Candle Holders";

        RightMenu rightMenu = PageFactory.initElements(driver, RightMenu.class);
        rightMenu.productNameAddedToCompare(productName1, driver).click();

        String msg = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();

        assertThat("Product wasn't added to compare", msg, is("The product " + productName1 + " has been added to comparison list."));

        String productName2 = "Modern Murray Ceramic Vase";
        rightMenu.productNameAddedToCompare(productName2, driver).click();

        String msg2 = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();

        assertThat("Product wasn't added to compare", msg2, is("The product " + productName2 + " has been added to comparison list."));

        assertThat("Compare component is not displayed",rightMenu.getCompareComponent().isDisplayed());
    }

    @Test
    public void addToCart() {

        waitForPageToLoad(5000);
        NavigationBar navigationBar = PageFactory.initElements(driver, NavigationBar.class);
        navigationBar.getVipPage().click();

        String productName = "Geometric Candle Holders";

        ProductGrid productGrid = PageFactory.initElements(driver, ProductGrid.class);
        productGrid.getAddToCartButton(driver).click();

        String msg = driver.findElement(By.className("success-msg")).getText();

        assertThat("Success message is not displayed", msg, CoreMatchers.is(productName + " was added to your shopping cart."));

        Checkout checkout = PageFactory.initElements(driver, Checkout.class);
        assertThat("Product is not on cart page", checkout.productNameAddedToCartButton(productName, driver).isDisplayed());
    }

    @Test
    public void filterByPrice() {

        NavigationBar navigationBar = PageFactory.initElements(driver, NavigationBar.class);
        navigationBar.getVipPage().click();

        FilterBy filterBy = PageFactory.initElements(driver, FilterBy.class);
        filterBy.getPrice().click();

        ProductGrid productGrid = PageFactory.initElements(driver, ProductGrid.class);

        List<Double> prices = productGrid.getActualProductPricesAsDouble();


        Boolean expectedResult = true;
        int i;

        for(i=0;i<prices.size();i++){
            if ((0>prices.get(i))&(prices.get(i)>100)){
                expectedResult = false;
            }
        }


        assertThat("Filter by price is not working.", expectedResult, is(true));
    }

    @Test
    public void searchFunctionalityFromCategoryPage() throws InterruptedException {

        NavigationBar navigationBar = PageFactory.initElements(driver, NavigationBar.class);
        navigationBar.getVipPage().click();

        String keyword = "vase";

        Header header = PageFactory.initElements(driver, Header.class);
        header.search(keyword);

        Thread.sleep(2000);

        List<WebElement> productNameContainers = driver.findElements(By.cssSelector(".product-name >a"));

        for (WebElement containers : productNameContainers) {
            String productName = containers.getText();

            assertThat("Some of the products names, do not contain the search heyword", productName, containsString(keyword.toUpperCase()));
        }
    }
}
