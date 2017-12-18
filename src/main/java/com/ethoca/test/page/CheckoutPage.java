package com.ethoca.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutPage extends PageBase {

    @FindBy(xpath = "//header/h1[@class='entry-title' and text()='Checkout']")
    public WebElement textCheckoutHeader;

    @FindBy(xpath = "//span[@class='yourtotal']")
    public WebElement textYourTotal;

    @FindBy(xpath = "//table[@class='checkout_cart']//a[text()='Magic Mouse']")
    public WebElement textMagicMouseEntry;

    @FindBy(xpath = "//table[@class='checkout_cart']//a[text()='Magic Mouse']/../..//form/input[@type='text']")
    public WebElement textMagicMouseQty;

    @FindBy(xpath = "//table[@class='checkout_cart']/tbody/tr")
    public List<WebElement> tableShoppingCart;

    @FindBy(xpath = "//span[text()='Continue']")
    public WebElement buttonContinue;

    @FindBy(xpath = "//h2[text()='Calculate Shipping Price']")
    public WebElement textCalculateShippingHeader;

    @FindBy(xpath = "//h2[text()='Sign in']")
    public WebElement textSignInHeader;

    @FindBy(xpath = "//label[contains(text(),'Enter your email address')]")
    public WebElement textEnterEmail;

    @FindBy(xpath = "//input[@title='billingemail' and @type='text']")
    public WebElement inputBillingEmail;

    @FindBy(xpath = "//input[@title='billingfirstname' and @type='text']")
    public WebElement inputBillingFirstName;

    @FindBy(xpath = "//input[@title='billinglastname' and @type='text']")
    public WebElement inputBillingLastName;

    @FindBy(xpath = "//textarea[@title='billingaddress' and @data-wpsc-meta-key='billingaddress']")
    public WebElement inputBillingAddress;

    @FindBy(xpath = "//input[@title='billingcity' and @type='text']")
    public WebElement inputBillingCity;

    @FindBy(xpath = "//input[@title='billingstate' and @type='text']")
    public WebElement inputBillingState;

    @FindBy(xpath = "//select[@title='billingcountry']")
    public WebElement inputBillingCountry;

    @FindBy(xpath = "//input[@title='billingpostcode' and @type='text']")
    public WebElement inputBillingPostCode;

    @FindBy(xpath = "//input[@title='billingphone']")
    public WebElement inputBillingPhone;

    @FindBy(id = "shippingSameBilling")
    public WebElement inputShippingSameBilling;

    @FindBy(id = "shippingsameasbillingmessage")
    public WebElement textShippingBillingMsg;

    @FindBy(xpath = "//span[text()='Go Back']")
    public WebElement buttonGoBack;

    @FindBy(xpath = "//input[@value='Purchase' and @type='submit']")
    public WebElement buttonPurchase;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
}
