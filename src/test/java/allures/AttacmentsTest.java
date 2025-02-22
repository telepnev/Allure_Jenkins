package allures;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class AttacmentsTest {
    private static final String REPOSITORY = "telepnev/Allure_Jenkins";
    private static final String ISSUE = "пиу пиу !!!";


    @Test
    @DisplayName("Test with Allure steps -> Attacments")
    public void testAttacmentsLambdaIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем github.com", () -> {
            open("https://github.com/");
            attachment("Source", webdriver().driver().source());
        });

    }


    @Test
    @DisplayName("Test with WEB steps -> Attacments")
    public void testAttacmentsAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebStep webStep = new WebStep();

        webStep.openMainPage();
        webStep.takeScreenshot();

    }
}
