package com.ethoca.test;

import com.ethoca.test.browser.*;
import com.mycila.guice.ext.closeable.CloseableModule;
import com.mycila.guice.ext.jsr250.Jsr250Module;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITest;
import org.testng.Reporter;
import org.testng.annotations.*;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.File;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;

@Guice(modules = {CloseableModule.class, Jsr250Module.class, BrowserModule.class})
public class BaseTest implements ITest {

    @Inject
    private Provider<Browser> browserProvider;

    protected static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);
    private final static String SCREENSHOT_PATH = "screenshotDirectory";
    private final static String TIMESTAMP = "buildTimestamp";
    protected Browser browser;
    protected WebDriverWait wait;


    @Parameters({"selenium-browser"})
    @BeforeSuite
    public void setup(String browserType) {
        browser = browserProvider.get();
        browser.start(Browsers.browserForName(browserType));
        wait = new WebDriverWait(browser.getDriver(),15);
    }

    @BeforeMethod
    public void logMethod(Method method) {
        LOG.info("\n=============Running Test=================\n" + method.getName() + "\n==========================================");
    }

    public String getTestName() {
        return getClass().getSimpleName();
    }

    public void takeScreenshot() throws Exception {
        final String timeStamp = System.getProperty(TIMESTAMP);
        final String screenshotPath = System.getProperty(SCREENSHOT_PATH);
        File screenShotFile;
        File scrFile = ((TakesScreenshot) browser.getDriver()).getScreenshotAs(OutputType.FILE);
        screenShotFile = new File(screenshotPath + File.separator + "screenshot-" + timeStamp + ".png");
        FileUtils.copyFile(scrFile, screenShotFile);

        String filePath = screenShotFile.toString();
        String path = "<img src=\"file://" + filePath + "\" /> ";
        Reporter.log(path);

    }

    @AfterSuite
    public void teardown() throws Exception {
        if (browser == null) return;
        browser.stop();
    }
}
