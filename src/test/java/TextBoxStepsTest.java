import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class TextBoxStepsTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        baseUrl = "https://demoqa.com/text-box";

        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", false
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @Test
    @Feature("TextBoxForm")
    @Story("Заполнение и отправка контактной формы")
    @Owner("Tatiana Lisitskaya")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://demoqa.com/text-box")
    @DisplayName("Заполнение формы Text Box")

    public void fillFromLambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открытие страницы", () -> {
            open(baseUrl);
        });
        step("Заполнение формы", () -> {
            $("#userName").val("Amicus");
            $("#userEmail").val("amicus@mail.com");
            $("#currentAddress").val("MyCurrentAddress");
            $("#permanentAddress").val("HereIsMyPermanentAddress");
            $("#submit").scrollTo().click();

        });
        step("Проверка заполненной формы", () -> {
            $("#output").shouldHave(text("Amicus"),
                    text("amicus@mail.com"),
                    text("MyCurrentAddress"),
                    text("HereIsMyPermanentAddress"));
        });
    }
}