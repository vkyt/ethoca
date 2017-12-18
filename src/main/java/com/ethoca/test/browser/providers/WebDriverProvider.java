package com.ethoca.test.browser.providers;

import com.google.inject.throwingproviders.CheckedProvider;
import org.openqa.selenium.WebDriverException;

public interface WebDriverProvider<T> extends CheckedProvider<T> {
    T get() throws WebDriverException;
}
