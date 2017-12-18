package com.ethoca.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

public abstract class PageBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final int TIMEOUT = 15;
    private static final Logger LOG = LoggerFactory.getLogger(PageBase.class);

    public PageBase(final WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
        PageFactory.initElements(driver, this);
    }

    public void clickElement(final By locator) {
        try {
            driver.findElement(locator).click();
            return;
        } catch (StaleElementReferenceException e) {
            LOG.error("Clicking a stale element.");
        } catch (NoSuchElementException e) {
            LOG.error("No element found.");
        } catch (WebDriverException e) {
            LOG.error("Driver error.");
        }

        throw new RuntimeException("Clicking action failed with locator: " + locator.toString());
    }

}

