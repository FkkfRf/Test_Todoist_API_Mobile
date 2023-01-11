package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/${env}.properties"
})
public interface BaseTestConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("https://todoist.com")
    String getBaseUrl();

    @Key("baseUri")
    @DefaultValue("https://todoist.com")
    String getBaseUri();

    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("browserVersion")
    @DefaultValue("100.0")
    String getBrowserVersion();

    @Key("browserSize")
    String getBrowserSize();

    @Key("remote")
    String getRemoteURL();
}

