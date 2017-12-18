package com.ethoca.test.suite;

import com.ethoca.test.BaseTest;
import com.ethoca.test.page.CheckoutPage;
import com.ethoca.test.page.MainPage;
import com.ethoca.test.page.AccessoriesPage;
import com.ethoca.test.page.TxnResultPage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest extends BaseTest {

    private final static String SITE = "http://store.demoqa.com/";
    private MainPage mainPage;
    private AccessoriesPage accessoriesPage;
    private CheckoutPage checkoutPage;
    private TxnResultPage txnResultPage;

    /**
     * Step 1
     *
     * @throws Exception
     */
    @Test
    public void goToSite() throws Exception {
        browser.loadPage(SITE);
        takeScreenshot();
        mainPage = new MainPage(browser.getDriver());
        Assert.assertTrue(mainPage.imgSiteLogo.isDisplayed());
        Assert.assertTrue(mainPage.buttonMyAccount.isDisplayed());
        Assert.assertTrue(mainPage.buttonShoppingCart.isDisplayed());
        Assert.assertTrue(mainPage.linkHome.isDisplayed());
        Assert.assertTrue(mainPage.linkProductCategory.isDisplayed());
    }

    /**
     * Step 2
     *
     * @throws Exception
     */
    @Test
    public void goToAccessories() throws Exception {
        Actions actions = new Actions(browser.getDriver());
        actions.moveToElement(mainPage.linkProductCategory).perform();
        actions.moveToElement(mainPage.linkAccessories).perform();
        takeScreenshot();
        actions.click().perform();
        accessoriesPage = new AccessoriesPage(browser.getDriver());
        Assert.assertTrue(accessoriesPage.textAccessoriesHeader.isDisplayed());
        Assert.assertTrue(accessoriesPage.textMagicMouseProdTitle.isDisplayed());
        Assert.assertTrue(accessoriesPage.buttonMagicMouseAddToCart.isDisplayed());
    }


    /**
     * Step 3
     *
     * @throws Exception
     */
    @Test
    public void addToCart() throws Exception {
        accessoriesPage.buttonMagicMouseAddToCart.click();
        wait.until(ExpectedConditions.elementToBeClickable(accessoriesPage.textMagicMouseAddedToCart));
        takeScreenshot();
        Assert.assertTrue(accessoriesPage.textMagicMouseAddedToCart.isDisplayed());
    }

    /**
     * Step 4
     *
     * @throws Exception
     */
    @Test
    public void checkOutStep1() throws Exception {
        mainPage.buttonShoppingCart.click();
        checkoutPage = new CheckoutPage(browser.getDriver());
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.textYourTotal));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.textMagicMouseEntry));
        Assert.assertTrue(checkoutPage.textCheckoutHeader.isDisplayed());
        Assert.assertTrue(checkoutPage.tableShoppingCart.size() == 2);
        Assert.assertTrue(checkoutPage.textMagicMouseEntry.isDisplayed());
        Assert.assertEquals(checkoutPage.textMagicMouseQty.getAttribute("value"), "1");
        Assert.assertTrue(checkoutPage.buttonContinue.isDisplayed());
        takeScreenshot();
    }


    /**
     * Step 5 & 6
     *
     * @throws Exception
     */
    @Test
    public void checkOutStep2() throws Exception {
        checkoutPage.buttonContinue.click();

        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.buttonPurchase));
        Assert.assertTrue(checkoutPage.textCalculateShippingHeader.isDisplayed());
        Assert.assertTrue(checkoutPage.textSignInHeader.isDisplayed());
        Assert.assertTrue(checkoutPage.textEnterEmail.isDisplayed());
        Assert.assertTrue(checkoutPage.buttonGoBack.isDisplayed());
        Assert.assertTrue(checkoutPage.buttonPurchase.isDisplayed());

        //Enter billing info
        checkoutPage.inputBillingEmail.sendKeys("fake@email.com");
        checkoutPage.inputBillingFirstName.sendKeys("fakeFirstName");
        checkoutPage.inputBillingLastName.sendKeys("fakeLastName");
        checkoutPage.inputBillingAddress.sendKeys("123 Fake St.");
        checkoutPage.inputBillingCity.sendKeys("Fake City");
        checkoutPage.inputBillingState.sendKeys("Fake State");
        //Select Country Afghanistan
        Select countryDropdown = new Select(checkoutPage.inputBillingCountry);
        countryDropdown.selectByValue("AF");
        checkoutPage.inputBillingPostCode.sendKeys("12345");
        checkoutPage.inputBillingPhone.sendKeys("1111111111");
        checkoutPage.inputShippingSameBilling.click();
        Assert.assertEquals(checkoutPage.inputShippingSameBilling.getAttribute("value"), "true");
        Assert.assertTrue(checkoutPage.textShippingBillingMsg.isDisplayed());
        takeScreenshot();
        checkoutPage.buttonPurchase.click();

    }

    /**
     * Step 7
     *
     * @throws Exception
     */
    @Test
    public void verifyOrder() throws Exception {
        txnResultPage = new TxnResultPage(browser.getDriver());

        //Assert txn result
        Assert.assertTrue(txnResultPage.textTxnResultsHeader.isDisplayed());
        Assert.assertTrue(txnResultPage.tableTxnResults.size() == 1);
        Assert.assertTrue(txnResultPage.textMagicMouseTxnName.isDisplayed());
        Assert.assertEquals(txnResultPage.textMagicMouseTxnPrice.getText(),"$150.00");
        Assert.assertEquals(txnResultPage.textMagicMouseTxnQty.getText(),"1");
        Assert.assertEquals(txnResultPage.textMagicMouseTxnTotalPrice.getText(),"$150.00");
        Assert.assertTrue(txnResultPage.textShippingTotalMsg.isDisplayed());
        Assert.assertEquals(txnResultPage.textShippingTotalMsg.getText(),"Total Shipping: $10.00\n" + "Total: $160.00");

    }


}
