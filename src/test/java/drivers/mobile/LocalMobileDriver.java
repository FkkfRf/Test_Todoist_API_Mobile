package drivers.mobile;

import com.codeborne.selenide.WebDriverProvider;
import config.mobile.MobileConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class LocalMobileDriver implements WebDriverProvider {

    public static URL getAppiumServerUrl() {
        try {
            return new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public WebDriver createDriver(Capabilities capabilities) {

        File app = getAppPath();

        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(config.platformName())
                .setDeviceName(config.deviceName())
                .setPlatformVersion(config.platformVersion())
                .setApp(app.getAbsolutePath())
                .setAppWaitPackage("com.todoist")
                .setAppWaitActivity("com.todoist.activity.WelcomeActivity");
        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    private File getAppPath() {
        // todo file moved from wiki github
        String appUrl = "https://download.apkcombo.com/com.todoist/Todoist_v10700_apkcombo.com.apk?ecp=Y29tLnRvZG9pc3QvdjEwNzAwLzEwNzAwLjViMWVkMWMzZTZiMmE4YzBkZjgzOTJkMTFhNmZiMDIxNDYzZjVlNGIuYXBr&iat=1674144811&sig=5df42c152c709619eba90a3b4f84dead&size=49160321&from=cf&version=latest&lang=ru&fp=f4d06cadc50f27c4816760f7b08630df&ip=178.252.127.221";
        String appPath = "src/test/resources/apps/Todoist_v10700_apkcombo.com.apk";

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app;
    }
}