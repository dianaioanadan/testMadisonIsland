package org.fasttrackit;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
        System.setProperty("webdriver.chrome.driver", "src\\resorces\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fasttrackit.org/selenium-test/");

        driver.findElement(By.className("nav-6")).click();

        String URL = driver.getCurrentUrl();

        assertThat("User is not on the right page", URL, is("https://fasttrackit.org/selenium-test/vip.html"));

        driver.quit();
    }

    @Test
    public void sortByPrice() {
        System.setProperty("webdriver.chrome.driver", "src\\resorces\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fasttrackit.org/selenium-test/");

        driver.findElement(By.className("nav-6")).click();

        driver.findElement(By.xpath("//div[@class='sort-by']//select[@title='Sort By']//option[@value='https://fasttrackit.org/selenium-test/vip.html?dir=asc&order=price']")).click();

        String firstProductPrice = driver.findElement(By.id("product-price-448")).getText();
        double firstPrice = convertPrice(firstProductPrice);

        String secondProductPrice = driver.findElement(By.xpath("//li[@class='item last']//div[@class='price-box']//span[@id='product-price-391']")).getText();
        double secondPrice = convertPrice(secondProductPrice);

        String thirdProductPrice = driver.findElement(By.xpath("//li[@class='item last']//div[@class='price-box']//span[@id='product-price-437']")).getText();
        double thirdPrice = convertPrice(thirdProductPrice);

        String fourthProductPrice = driver.findElement(By.xpath("//li[@class='item last']//div[@class='price-box']//span[@id='product-price-412']")).getText();
        double fourthPrice = convertPrice(fourthProductPrice);

        String fifthProductPrice = driver.findElement(By.id("product-price-427")).getText();
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
        System.setProperty("webdriver.chrome.driver", "src\\resorces\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fasttrackit.org/selenium-test/");
        driver.findElement(By.className("nav-6")).click();

        driver.findElement(By.id("product-collection-image-448")).click();

        String URL = driver.getCurrentUrl();

        assertThat("Product page is not open.",URL, is("https://fasttrackit.org/selenium-test/vip/a-tale-of-two-cities.html"));

        driver.quit();
    }

    @Test
    public void openProductPageByClickOnText(){
        System.setProperty("webdriver.chrome.driver", "src\\resorces\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fasttrackit.org/selenium-test/");
        driver.findElement(By.className("nav-6")).click();

        driver.findElement(By.xpath("//li[@class='item last']//h2//a[@title='A Tale of Two Cities']")).click();
        String URL = driver.getCurrentUrl();

        assertThat("Product page is not open.",URL, is("https://fasttrackit.org/selenium-test/vip/a-tale-of-two-cities.html"));

        driver.quit();
    }

    @Test
    public void recentlyViewedProducts() {
        System.setProperty("webdriver.chrome.driver", "src\\resorces\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fasttrackit.org/selenium-test/");
        driver.findElement(By.className("nav-6")).click();

        driver.findElement(By.xpath("//li[@class='item last']//h2//a[@title='A Tale of Two Cities']")).click();
        driver.navigate().back();

        WebElement recentlyViewedElement = driver.findElement(By.xpath("//div[@class='block-content']//li[@class='item last odd']"));

        assertThat("Recently viewed products is not displayed", recentlyViewedElement.isDisplayed());
        driver.quit();
    }

    @Test
    public void addToWishlist(){
        System.setProperty("webdriver.chrome.driver", "src\\resorces\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fasttrackit.org/selenium-test/");
        driver.findElement(By.className("nav-6")).click();

        String productName = "Geometric Candle Holders";

        driver.findElement(By.xpath("//div[@class='product-info' and .//a[text()= '"+productName+"']]//a[@class='link-wishlist']")).click();

        String URL = driver.getCurrentUrl();

        assertThat("Product page is not open.",URL, is("https://fasttrackit.org/selenium-test/customer/account/login/"));

        driver.quit();
    }

    @Test
    public void addToCompare(){
        System.setProperty("webdriver.chrome.driver", "src\\resorces\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fasttrackit.org/selenium-test/");
        driver.findElement(By.className("nav-6")).click();

        String productName = "Geometric Candle Holders";

        driver.findElement(By.xpath("//div[@class='product-info' and .//a[text()= '"+productName+"']]//a[@class='link-compare']")).click();

        String msg = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();

        assertThat("Product wasn't added to compare",msg,is("The product "+productName+" has been added to comparison list."));
        driver.quit();
    }

    @Test
    public void addToCart(){
        System.setProperty("webdriver.chrome.driver", "src\\resorces\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fasttrackit.org/selenium-test/");
        driver.findElement(By.className("nav-6")).click();

        String productName = "Geometric Candle Holders";

        driver.findElement(By.xpath("//div[@class='product-info' and .//a[text()= '"+productName+"']]//button[@title='Add to Cart']")).click();
        String msg = driver.findElement(By.className("success-msg")).getText();

        assertThat("Success message is not displayed",msg, CoreMatchers.is(productName+" was added to your shopping cart."));

        WebElement productNameAddedToCart = driver.findElement(By.xpath("//tr[@class='first last odd'] //h2[@class = 'product-name'] // a [text()='"+productName+"']"));

        assertThat("Product is not on cart page",productNameAddedToCart.isDisplayed());

        driver.quit();
    }

    @Test
    public void filterByOneCategory() {
        System.setProperty("webdriver.chrome.driver", "src\\resorces\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fasttrackit.org/selenium-test/");
        driver.findElement(By.className("nav-6")).click();

        driver.findElement(By.xpath("//dl[@id='narrow-by-list']//span[@class='price']")).click();

        String firstProductPrice = driver.findElement(By.id("product-price-448")).getText();
        double firstPrice = convertPrice(firstProductPrice);

        String secondProductPrice = driver.findElement(By.xpath("//li[@class='item last']//div[@class='price-box']//span[@id='product-price-391']")).getText();
        double secondPrice = convertPrice(secondProductPrice);

        String thirdProductPrice = driver.findElement(By.xpath("//li[@class='item last']//div[@class='price-box']//span[@id='product-price-437']")).getText();
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
        System.setProperty("webdriver.chrome.driver", "src\\resorces\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fasttrackit.org/selenium-test/");
        driver.findElement(By.className("nav-6")).click();

        String keyword = "vase";

        driver.findElement(By.className("input-text")).sendKeys(keyword + Keys.ENTER);
        List<WebElement> productNameContainers = driver.findElements(By.cssSelector(".product-name >a"));

        for (WebElement containers: productNameContainers){
            String productName = containers.getText();

            assertThat("Some of the products names, do not contain the search heyword", productName,containsString(keyword.toUpperCase()));
        }
        driver.quit();
    }
}
