package config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;

public class ProjectProvider {
    static ProjectConfig configBase = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    public static void configBase() {
        Configuration.baseUrl = ProjectProvider.configBase.getBaseUrl();
        Configuration.browser = ProjectProvider.configBase.getBrowser();
        Configuration.browserVersion = ProjectProvider.configBase.getBrowserVersion();
        Configuration.browserSize = ProjectProvider.configBase.getBrowserSize();
        String remoteUrl = ProjectProvider.configBase.getRemoteURL();
        if (remoteUrl != null) {
            Configuration.remote = remoteUrl;
        }
    }
}
