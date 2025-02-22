package allures;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com/");
        $(".search-input-container").click();
        $("#query-builder-test").sendKeys("telepnev/Allure_Jenkins");
        $("#query-builder-test").pressEnter();
        $(linkText("telepnev/Allure_Jenkins")).click();
        $("#issues-repo-tab-count").click();
        $("h3 span").shouldHave(text("пиу пиу !!!!"));

    }
}
