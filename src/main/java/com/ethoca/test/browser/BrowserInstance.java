package com.ethoca.test.browser;

import com.google.inject.Inject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class BrowserInstance implements Browser{

    private WebDriver driver;
    private final DriverFactory driverFactory;
    protected static final Logger LOG = LoggerFactory.getLogger(BrowserInstance.class);
    private Browsers browserType = null;

    @Inject
    BrowserInstance(DriverFactory driverFactory){
        this.driverFactory = driverFactory;
    }

    public synchronized void start(Browsers browser) {
        if (driver == null) {
            browserType = browser;

            if (browser == null){
                browserType = Browsers.FIREFOX;
            }

            try {
                driver = driverFactory.get(browserType);
            } catch (UnreachableBrowserException e) {
                driver = driverFactory.get(browserType);
            } catch (WebDriverException e) {
                driver = driverFactory.get(browserType);
            }finally{
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }

            setupBrowser(driver);
        }
    }

    public synchronized void stop() {
        if (getDriver() == null) return;

        try {
            getDriver().quit();
            driver = null;
            LOG.info("closing the browser");
        } catch (UnreachableBrowserException e) {
            LOG.info("cannot close unreachable browser");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public BrowserInstance loadPage(String url) {
        driver.get(url);
        return this;
    }

    private static void setupBrowser(WebDriver driver) {
        LOG.info("starting the browser and resize");

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        LOG.info("waited");

        driver.manage().window().setPosition(new Point(0, 0));
        LOG.info("position set");

        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        LOG.info("Screen Size:" + screenSize.toString());

        Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        LOG.info(String.format("Width: %f   Height: %f", screenSize.getWidth(), screenSize.getHeight()));

        driver.manage().window().setSize(dim);
        LOG.info("finish starting up browser");
    }

    private class BrowserCleanup implements Runnable {
        public void run() {
            stop();
        }
    }


}
