package com.ethoca.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TxnResultPage extends PageBase {

    @FindBy(xpath = "//h1[@class='entry-title' and text()='Transaction Results']")
    public WebElement textTxnResultsHeader;

    @FindBy(xpath = "//table[@class='wpsc-purchase-log-transaction-results']/tbody/tr")
    public List<WebElement> tableTxnResults;

    @FindBy(xpath = "//td[text()='Magic Mouse']")
    public WebElement textMagicMouseTxnName;

    @FindBy(xpath = "//td[text()='Magic Mouse']/../td[position()=2]")
    public WebElement textMagicMouseTxnPrice;

    @FindBy(xpath = "//td[text()='Magic Mouse']/../td[position()=3]")
    public WebElement textMagicMouseTxnQty;

    @FindBy(xpath = "//td[text()='Magic Mouse']/../td[position()=4]")
    public WebElement textMagicMouseTxnTotalPrice;

    @FindBy(xpath = "//table[@class='wpsc-purchase-log-transaction-results']/following-sibling::p")
    public WebElement textShippingTotalMsg;

    public TxnResultPage(WebDriver driver) {
        super(driver);
    }
}
