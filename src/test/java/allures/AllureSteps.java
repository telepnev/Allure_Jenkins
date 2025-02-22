package allures;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AllureSteps {
    private static final String REPOSITORY = "telepnev/Allure_Jenkins";
    private static final String ISSUE = "пиу пиу !!!";


    @Test
    @DisplayName("Test with Allure steps")
    public void testLambdaIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем github.com", () -> {
            open("https://github.com/");
        });

        step("Ишем репозиторий " + REPOSITORY, () -> {
            $(".search-input-container").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").pressEnter();
        });

        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Открываем таб Issue", () -> {
            $("#issues-repo-tab-count").click();
        });

        step("Ищем по тексту Issue " + ISSUE, () -> {
            $("h3 span").shouldHave(text(ISSUE));
        });
    }


    @Test
   // @DisplayName("Test with WEB steps")
    public void testAnnotatedSteps() {
        WebStep webStep = new WebStep();

        webStep.openMainPage();
        webStep.searchRepository(REPOSITORY);
        webStep.clickByLinkRepository(REPOSITORY);
        webStep.openTabIssue();
        webStep.shouldSeeTextIssue(ISSUE);

    }
}
