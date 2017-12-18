package com.ethoca.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends PageBase{

    @FindBy(id = "logo")
    public WebElement imgSiteLogo;

    @FindBy(id = "account")
    public WebElement buttonMyAccount;

    @FindBy(id = "header_cart")
    public WebElement buttonShoppingCart;

    @FindBy(className = "mobile-dropdown")
    public WebElement menuDropdown;

    @FindBy(linkText = "Home")
    public WebElement linkHome;

    @FindBy(linkText = "Product Category")
    public WebElement linkProductCategory;

    @FindBy(linkText = "Accessories")
    public WebElement linkAccessories;


    public MainPage(WebDriver driver) {
        super(driver);
    }


}
