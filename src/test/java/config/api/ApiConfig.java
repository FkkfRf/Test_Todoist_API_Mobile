package config.api;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/api.properties"
})
public interface ApiConfig extends Config {
    String token();

    @Key("baseUri")
    @DefaultValue("https://api.todoist.com/rest/v2")
    String baseUri();


}