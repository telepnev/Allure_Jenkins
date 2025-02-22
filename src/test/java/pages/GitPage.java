package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class GitPage {
    private final String mainPage = "https://github.com/";
    private final SelenideElement search = $(".search-input-container");
    private final SelenideElement searchQuery = $("#query-builder-test");
    private final SelenideElement issuesTab = $("#issues-repo-tab-count");


    public GitPage openMainPage() {
        open(mainPage);
        return this;
    }

    public GitPage searchRepo(String s) {
        search.click();
        searchQuery.sendKeys(s);
        searchQuery.pressEnter();
        return this;
    }

    public GitPage chooseRepo(String s) {
        $(linkText(s)).click();
        return this;
    }

    public GitPage clickRepoTab() {
        issuesTab.click();
        return this;
    }

    public GitPage findIssueByText(String s) {
        $("h3 span").shouldHave(text(s));
        return this;
    }
}
