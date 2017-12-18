package com.ethoca.test.browser;

public enum Browsers {

    FIREFOX,
    CHROME;

    public static Browsers browserForName(String browser) throws IllegalArgumentException{
        for(Browsers b: values()){
            if(b.toString().equalsIgnoreCase(browser)){
                return b;
            }
        }
        throw new IllegalArgumentException(("Invalid browser [" + browser + "]"));
    }


}
