package com.ethoca.test.browser.providers;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class FirefoxProvider implements WebDriverProvider<WebDriver>{

    private final String path;

    @Inject
    public FirefoxProvider(@Named("driver_directory") String path) {
        this.path = path;
    }


    public WebDriver get() throws WebDriverException {

        String chromeDriverPath = path + File.separator + "geckodriver";

        System.setProperty("webdriver.gecko.driver", chromeDriverPath);
        return new FirefoxDriver();
    }
}
