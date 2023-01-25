package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE) // объединяем в один config  параметры из разных properties
@Config.Sources({"classpath:config/${testType}.properties",
         "classpath:config/api.properties"
})

public interface ProjectConfig extends Config {
    @Key("testType")
    @DefaultValue("api")
    String testType();

    @Key("deviceName")
    String deviceName();

    @Key("platformName")
    @DefaultValue("android")
    String platformName();

    @Key("platformVersion")
    String platformVersion();

    @Key("App")
    String App();

    @Key("email")
    String Email();
    @Key("password")
    String Passord();

    @Key("baseUri")
    @DefaultValue("https://api.todoist.com/rest/v2")
    String baseUri();

    String token();
}
