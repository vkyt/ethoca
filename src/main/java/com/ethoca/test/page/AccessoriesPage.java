package com.ethoca.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccessoriesPage extends PageBase {

    @FindBy(xpath = "//h1[text() = 'Accessories']")
    public WebElement textAccessoriesHeader;

    @FindBy(xpath = "//h2[@class='prodtitle']/a[text()='Magic Mouse']")
    public WebElement textMagicMouseProdTitle;

    @FindBy(xpath = "//h2[@class='prodtitle']/a[text()='Magic Mouse']/../../form/div[contains(@class,'wpsc_buy_button_container')]")
    public WebElement buttonMagicMouseAddToCart;

    @FindBy(xpath ="//h2[@class='prodtitle']/a[text()='Magic Mouse']/../../form/div[contains(@class,'wpsc_buy_button_container')]//div[contains(@class, 'alert') and contains(@class,'addtocart')]/p[text()='Item has been added to your cart!']")
    public WebElement textMagicMouseAddedToCart;

    public AccessoriesPage(WebDriver driver) {
        super(driver);
    }
}
