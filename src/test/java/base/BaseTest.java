package base;

import com.codeborne.selenide.Configuration;
import config.ProjectConfig;
import io.restassured.RestAssured;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import mobile.drivers.LocalMobileDriver;
import mobile.page.*;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.CustomApiListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;

public class BaseTest {
    public static String loginEmail;
    public static String loginPassword;
    public LoginPage loginPage = new LoginPage();
    public MainPage mainPage = new MainPage();
    public ProjectPage projectPage = new ProjectPage();
    public ReschedulePage reschedulePage = new ReschedulePage();
    public TimeZonePage timeZonePage = new TimeZonePage();
    public ChangeTimePage changeTimePage = new ChangeTimePage();
    static ProjectConfig mobileConfig = ConfigFactory.create(ProjectConfig.class);
    public static String caseEmulate = mobileConfig.testType();

    @BeforeAll
    public static void setup() {
        if (caseEmulate.equals("emulation")) {
            Configuration.browser = LocalMobileDriver.class.getName();
            loginEmail = mobileConfig.Email();
            loginPassword = mobileConfig.Passord();

            Configuration.browser = LocalMobileDriver.class.getName();
            Configuration.browserSize = null;
        } else {
            step("Устанавливаем базовый URI для REST API", () -> {
                RestAssured.filters(withCustomTemplates());
            });
        }
    }

    @BeforeEach
    void startDriver() {
        if (caseEmulate.equals("emulation")) {
            addListener("AllureSelenide", new AllureSelenide());
            open();
        }
    }

    @AfterEach
    public void tearDown() {
        if (caseEmulate.equals("emulation")) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();

            closeWebDriver();
        }
    }
}
