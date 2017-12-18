package com.ethoca.test.browser;

import com.ethoca.test.browser.providers.WebDriverProvider;
import com.ethoca.test.browser.providers.annotations.Chrome;
import com.ethoca.test.browser.providers.annotations.Firefox;
import org.openqa.selenium.WebDriver;

import javax.inject.Inject;

public class DriverFactory implements Driver {
    private final WebDriverProvider<WebDriver> chromeProvider;
    private final WebDriverProvider<WebDriver> firefoxProvider;

    @Inject
    DriverFactory(@Chrome WebDriverProvider<WebDriver> chromeProvider,
                  @Firefox WebDriverProvider<WebDriver> firefoxProvider) {

        this.chromeProvider = chromeProvider;
        this.firefoxProvider = firefoxProvider;
    }

    public WebDriver get(Browsers browser) {
        WebDriver driver;

        switch (browser) {
            case CHROME:
                driver = this.chromeProvider.get();
                break;
            case FIREFOX:
            default:
                driver = this.firefoxProvider.get();
                break;
        }

        return driver;
    }
}
