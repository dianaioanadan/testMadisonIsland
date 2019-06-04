package org.fasttrackit.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductGrid {

    @FindBy(xpath = "//div[@class='sort-by']//select[@title='Sort By']//option[@value='https://fasttrackit.org/selenium-test/vip.html?dir=asc&order=price']")
    private WebElement sortByPriceFilter;

    @FindBy(id = "product-collection-image-448")
    private WebElement productImage;

    @FindBy(xpath = "//li[@class='item last']//h2//a[@title='A Tale of Two Cities']")
    private WebElement productName;

    @FindBy(xpath = "//ul[contains(@class,'products-grid')]//span[@class='price' and ./parent::*[not(contains(@class, 'old-price'))]]")
    private List<WebElement> actualProductPriceContainers;


    public List<WebElement> getActualProductContainers() {
        return actualProductPriceContainers;
    }

    public List<Double> getActualProductPricesAsDouble() {
        List<Double> convertedPrices = new ArrayList<>();
        for (WebElement priceContainer : actualProductPriceContainers) {
            String priceAsText = priceContainer.getText();

            Pattern pattern = Pattern.compile("([^ ]+).+");
            Matcher matcher = pattern.matcher(priceAsText);

            if (matcher.find()){
                String priceTextWithoutCurrency = matcher.group(1);
                priceTextWithoutCurrency = priceTextWithoutCurrency.replace(",",".");

                double convertedPrice = Double.parseDouble(priceTextWithoutCurrency);

                convertedPrices.add(convertedPrice);
            }

        }
        return  convertedPrices;
    }

    @FindBy(xpath = "//div[@class='sort-by']//select[@title='Sort By']//option[@value='https://fasttrackit.org/selenium-test/sale.html?dir=asc&order=price']")
    private WebElement sortByPriceSalePage;

    @FindBy(id = "product-price-384")
    private WebElement fistElementPriceSalePage;

    @FindBy(id = "product-price-403")
    private WebElement secondElementPriceSalePage;

    @FindBy(id = "product-price-423")
    private WebElement thirdElementPriceSalePage;

    @FindBy(id = "product-collection-image-423")
    private WebElement productImageSalePage;

    @FindBy(xpath = "//li[@class='item last']//h2//a[@title='Racer Back Maxi Dress']")
    private WebElement productNameSalePge;


    //div[@class='product-info' and .//a[text()= '" + productName + "']]//button[@title='Add to Cart']

    public WebElement getAddToCartButton(WebDriver driverclass) {
        return driverclass.findElement(By.xpath("//li//button[@title='Add to Cart']"));
    }

    public WebElement getAddToWishListtButton(String productName, WebDriver driver) {
        return driver.findElement(By.xpath("//div[@class='product-info' and .//a[text()= '" + productName + "']]//a[@class='link-wishlist']"));
    }

    public WebElement getSortByPriceFilter() {
        return sortByPriceFilter;
    }

    public WebElement getProductImage() {
        return productImage;
    }

    public WebElement getProductName() {
        return productName;
    }

    public WebElement getSortByPriceSalePage() {
        return sortByPriceSalePage;
    }

    public WebElement getFistElementPriceSalePage() {
        return fistElementPriceSalePage;
    }

    public WebElement getSecondElementPriceSalePage() {
        return secondElementPriceSalePage;
    }

    public WebElement getThirdElementPriceSalePage() {
        return thirdElementPriceSalePage;
    }

    public WebElement getProductImageSalePage() {
        return productImageSalePage;
    }

    public WebElement getProductNameSalePge() {
        return productNameSalePge;
    }
}
