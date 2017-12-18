package com.ethoca.test.browser.providers;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class ChromeProvider implements WebDriverProvider<WebDriver> {

    private final String path;

    @Inject
    public ChromeProvider(@Named("driver_directory") String path) {
        this.path = path;
    }

    public WebDriver get() {

        String chromeDriverPath = path + File.separator + "chromedriver";

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        return new ChromeDriver();
    }
}
