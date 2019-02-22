package org.fasttrackit.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductGrid {

    public WebElement getAddToCartButton(String productName, WebDriver driver){
        return   driver.findElement(By.xpath("//div[@class='product-info' and .//a[text()= '"+productName+"']]//button[@title='Add to Cart']"));
    }

    public WebElement getAddToWishListtButton(String productName, WebDriver driver){
        return   driver.findElement(By.xpath("//div[@class='product-info' and .//a[text()= '"+productName+"']]//a[@class='link-wishlist']"));
    }

    @FindBy(xpath = "//div[@class='sort-by']//select[@title='Sort By']//option[@value='https://fasttrackit.org/selenium-test/vip.html?dir=asc&order=price']")
    private WebElement sortByPrice;

    public WebElement getSortByPrice() {
        return sortByPrice;
    }

    @FindBy(id = "product-collection-image-448")
    private WebElement productImage;

    public WebElement getProductImage() {
        return productImage;
    }

    @FindBy(xpath = "//li[@class='item last']//h2//a[@title='A Tale of Two Cities']")
    private  WebElement productName;

    public WebElement getProductName() {
        return productName;
    }

    @FindBy(id = "product-price-448")
    private  WebElement fistElementPrice;

    public WebElement getFistElementPrice() {
        return fistElementPrice;
    }

    @FindBy(xpath = "//li[@class='item last']//div[@class='price-box']//span[@id='product-price-391']")
    private WebElement secondElementPrice;

    public WebElement getSecondElementPrice() {
        return secondElementPrice;
    }

    @FindBy(xpath = "//li[@class='item last']//div[@class='price-box']//span[@id='product-price-437']")
    private WebElement thirdElementPrice;

    public WebElement getThirdElementPrice() {
        return thirdElementPrice;
    }

    @FindBy(xpath = "//li[@class='item last']//div[@class='price-box']//span[@id='product-price-412']")
    private  WebElement fourthProductPrice;

    public WebElement getFourthProductPrice() {
        return fourthProductPrice;
    }

    @FindBy(id = "product-price-427")
    private WebElement fifthProductPrice;

    public WebElement getFifthProductPrice() {
        return fifthProductPrice;
    }

    @FindBy(xpath = "//div[@class='sort-by']//select[@title='Sort By']//option[@value='https://fasttrackit.org/selenium-test/sale.html?dir=asc&order=price']")
    private WebElement sortByPriceSalePage;

    public WebElement getSortByPriceSalePage() {
        return sortByPriceSalePage;
    }

    @FindBy(id = "product-price-384")
    private  WebElement fistElementPriceSalePage;

    public WebElement getFistElementPriceSalePage() {
        return fistElementPriceSalePage;
    }

    @FindBy(id = "product-price-403")
    private WebElement secondElementPriceSalePage;

    public WebElement getSecondElementPriceSalePage() {
        return secondElementPriceSalePage;
    }

    @FindBy(id = "product-price-423")
    private WebElement thirdElementPriceSalePage;

    public WebElement getThirdElementPriceSalePage() {
        return thirdElementPriceSalePage;
    }

    @FindBy(id = "product-collection-image-423")
    private WebElement productImageSalePage;

    public WebElement getProductImageSalePage() {
        return productImageSalePage;
    }

    @FindBy(xpath = "//li[@class='item last']//h2//a[@title='Racer Back Maxi Dress']")
    private  WebElement productNameSalePge;

    public WebElement getProductNameSalePge() {
        return productNameSalePge;
    }


}
