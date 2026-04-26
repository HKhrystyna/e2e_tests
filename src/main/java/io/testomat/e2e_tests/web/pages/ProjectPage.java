package io.testomat.e2e_tests.web.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProjectPage extends BasePage {

    public void isLoaded(String targetProjectName) {
        $(".first h2").shouldHave(text(targetProjectName));
        $(".sticky-header [href$='/readme']").shouldBe(visible).shouldHave(text("Readme"));
    }

    public ProjectPage openReadme() {
        clickByLinkText("Readme");
        return this;
    }

    public ProjectPage editReadme() {
        clickByLinkText("Edit");
        return this;
    }
}
