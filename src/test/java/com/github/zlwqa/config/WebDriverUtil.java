package com.github.zlwqa.config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverUtil {

    private static final WebDriverConfig webConfig = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    public static void configure() {
        Configuration.browser = webConfig.browser();
        Configuration.browserVersion = webConfig.versionBrowser();
        Configuration.browserSize = webConfig.browserSize();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--lang=en-en");

        if (System.getProperty("typeProperties").equals("remote")) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.remote = webConfig.remoteWebDriver();
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        Configuration.browserCapabilities = capabilities;
        Configuration.timeout=10000;
    }
}
