package config;

import org.aeonbits.owner.Config;

@Config.Sources({

        "classpath:config/api.properties"
})
public interface ProjectApiConfig extends Config {
    String token();

    @Key("baseUri")
    @DefaultValue("https://api.todoist.com/rest/v1")
    String baseUri();

    String projectNumber();
}

