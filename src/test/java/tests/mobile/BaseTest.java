package tests.mobile;

import com.codeborne.selenide.Configuration;
import config.mobile.MobileConfig;
import drivers.LocalMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import tests.mobile.page.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class BaseTest {
    public static String loginEmail;
    public static String loginPassword;
    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    ProjectPage projectPage = new ProjectPage();
    ReschedulePage reschedulePage = new ReschedulePage();
    AlarmPage alarmPage = new AlarmPage();
    TimeZonePage timeZonePage = new TimeZonePage();
    ChangeTimePage changeTimePage = new ChangeTimePage();

    @BeforeAll
    public static void setup() {

        MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class);
        loginEmail = mobileConfig.Email();
        loginPassword = mobileConfig.Passord();

        Configuration.browser = LocalMobileDriver.class.getName();
        Configuration.browserSize = null;
    }

    @BeforeEach
    void startDriver() {
        addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        closeWebDriver();
    }
}

