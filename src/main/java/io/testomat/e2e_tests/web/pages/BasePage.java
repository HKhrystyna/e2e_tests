package io.testomat.e2e_tests.web.pages;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    protected void clickByLinkText(String text) {
        $(byLinkText(text)).click();
    }
}
