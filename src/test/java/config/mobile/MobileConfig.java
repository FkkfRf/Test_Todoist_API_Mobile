package config.mobile;

import org.aeonbits.owner.Config;

@Config.Sources({
         "classpath:config/mobile/mution.properties"
})

public interface MobileConfig extends Config {

    @Key("deviceName")
    String deviceName();

    @Key("platformName")
    @DefaultValue("android")
    String platformName();

    @Key("platformVersion")
    String platformVersion();

    @Key("email")
    String Email();
    @Key("password")
    String Passord();

}
