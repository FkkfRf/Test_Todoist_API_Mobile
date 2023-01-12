package config;

import org.aeonbits.owner.Config;

@Config.Sources({

        "classpath:config/login.properties"
})
public interface LoginConfig extends Config {
    String loginEmail();
    String loginPassword();
}

