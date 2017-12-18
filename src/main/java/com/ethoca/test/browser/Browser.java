package com.ethoca.test.browser;

import org.openqa.selenium.WebDriver;

public interface Browser {

    void start(Browsers browser);

    void stop();

    WebDriver getDriver();

    Browser loadPage(String url);

}
