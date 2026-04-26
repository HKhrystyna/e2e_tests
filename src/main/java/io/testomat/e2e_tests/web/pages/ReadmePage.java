package io.testomat.e2e_tests.web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.time.Duration.ofSeconds;

public class ReadmePage extends BasePage {

    public ReadmePage clickOnEditReadme() {
        $(byText("Edit Readme")).click();
        switchTo().frame($("#modal-overlays iframe[src='/ember-monaco/frame.html']"));
        return this;
    }

    public ReadmePage editReadmeTextInEditor(String targetText) {
        $(".view-lines div span").click();
        $("textarea").setValue(targetText);
        return this;
    }

    public ReadmePage clickOnUpdate() {
        switchTo().defaultContent();
        $(byText("Update")).click();
        return this;
    }

    public ReadmePage clickOnCancel() {
        switchTo().defaultContent();
        $(byText("Cancel")).click();
        return this;
    }

    public ReadmePage isLoaded() {
        $(".setting-header").shouldHave(text("Readme"));
        return this;
    }

    public ReadmePage updateSuccess() {
        $(".ember-notify-cn")
                .should(appear, ofSeconds(5))
                .shouldHave(text("Readme has been saved"));
        return this;
    }

    public ReadmePage openReadmeAfterUpdate() {
        clickByLinkText("Readme");
        return this;
    }

    private SelenideElement readmeContent() {
        return $(".detail-view-content");
    }

    public ReadmePage shouldHaveReadmeText(String expectedText) {
        readmeContent().shouldHave(text(expectedText));
        return this;
    }

    public ReadmePage shouldNotHaveReadmeText(String expectedText) {
        readmeContent().shouldNotHave(text(expectedText));
        return this;
    }

}
