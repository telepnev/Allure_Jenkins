package allures;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class GitSteps {
    private final String mainPage = "https://github.com/";
    private final SelenideElement search = $(".search-input-container");
    private final SelenideElement searchQuery = $("#query-builder-test");
    private final SelenideElement issuesTab = $("#issues-repo-tab-count");

    @Step("Открываем github.com")
    public void openMainPage() {
        open(mainPage);
    }

    @Step("Ищем репозиторий {repo}")
    public void searchRepository(String repo) {
        search.click();
        searchQuery.sendKeys(repo);
        searchQuery.pressEnter();
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickByLinkRepository(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открываем таб Issue")
    public void openTabIssue() {
        issuesTab.click();
    }

    @Step("Ищем по тексту Issue {issue}")
    public void shouldSeeTextIssue(String issue) {
        $("h3 span").shouldHave(text(issue));
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
