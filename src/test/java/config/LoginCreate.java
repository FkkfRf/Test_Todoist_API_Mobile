package config;

import org.aeonbits.owner.ConfigFactory;

public class LoginCreate {
    public static LoginConfig config = ConfigFactory.create(LoginConfig.class, System.getProperties());
}
