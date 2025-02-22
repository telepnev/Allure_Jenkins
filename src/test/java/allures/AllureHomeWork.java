package allures;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.GitPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AllureHomeWork {
    private static final String REPOSITORY = "telepnev/Allure_Jenkins";
    private static final String ISSUE = "пиу пиу !!!";

    private final String mainPage = "https://github.com/";
    private final SelenideElement search = $(".search-input-container");
    private final SelenideElement searchQuery = $("#query-builder-test");
    private final SelenideElement issuesTab = $("#issues-repo-tab-count");

    GitPage gitPage = new GitPage();
    GitSteps step = new GitSteps();

    @Test
    @Feature("Issue в репозитории")
    @Story("Поиск Issue")
    @Owner("telepnev")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Поиск Issue по тексту, SelenideListener")
    public void testSelenideListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        gitPage.openMainPage()
                .searchRepo(REPOSITORY)
                .chooseRepo(REPOSITORY)
                .clickRepoTab()
                .findIssueByText(ISSUE);
    }


    @Test
    @Feature("Issue в репозитории")
    @Story("Поиск Issue")
    @Owner("telepnev")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Поиск Issue по тексту, AnnotationsSteps")
    public void testAnnotationsSteps() {
        step.openMainPage();
        step.searchRepository(REPOSITORY);
        step.clickByLinkRepository(REPOSITORY);
        step.openTabIssue();
        step.shouldSeeTextIssue(ISSUE);
    }

    @Test
    @Feature("Issue в репозитории")
    @Story("Поиск Issue")
    @Owner("telepnev")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Поиск Issue по тексту, LambdaStep")
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем github.com", () -> {
            open(mainPage);
        });
        step("Ишем репозиторий " + REPOSITORY, () -> {
            search.click();
            searchQuery.sendKeys(REPOSITORY);
            searchQuery.pressEnter();
        });

        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Открываем таб Issue", () -> {
            issuesTab.click();
        });

        step("Ищем по тексту Issue " + ISSUE, () -> {
            $("h3 span").shouldHave(text(ISSUE));
        });
    }
}
