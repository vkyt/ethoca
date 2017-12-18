package com.ethoca.test.browser;

import com.ethoca.test.browser.providers.ChromeProvider;
import com.ethoca.test.browser.providers.FirefoxProvider;
import com.ethoca.test.browser.providers.WebDriverProvider;
import com.ethoca.test.browser.providers.annotations.Chrome;
import com.ethoca.test.browser.providers.annotations.Firefox;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.throwingproviders.ThrowingProviderBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserModule extends AbstractModule {

    protected static final Logger LOG = LoggerFactory.getLogger(BrowserModule.class);

    private final String DRIVER_DIRECTORY = System.getProperty("driverDirectory");


    @Override
    protected void configure() {
        LOG.info("\n================ Driver Directory================\n " + DRIVER_DIRECTORY + "\n=================================================");

        bind(String.class)
                .annotatedWith(Names.named("driver_directory"))
                .toInstance(DRIVER_DIRECTORY);

        // setup the browser provider bindings
        ThrowingProviderBinder.create(binder())
                .bind(WebDriverProvider.class, org.openqa.selenium.WebDriver.class)
                .annotatedWith(Chrome.class)
                .to(ChromeProvider.class);

        ThrowingProviderBinder.create(binder())
                .bind(WebDriverProvider.class, org.openqa.selenium.WebDriver.class)
                .annotatedWith(Firefox.class)
                .to(FirefoxProvider.class);

        bind(Driver.class).to(DriverFactory.class).in(Singleton.class);
        bind(Browser.class).to(BrowserInstance.class);

    }
}
