package io.testomat.e2e_tests.utils;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.openqa.selenium.WebElement;

public class StringParsers {

    @NonNull
    public static Integer parseIntegerFromString(String targetString) {
        String digitText = targetString.replaceAll("\\D+", "");
        return Integer.parseInt(digitText);
    }

    public static String normalizedText(WebElement el) {
        return el.getText().trim().toLowerCase();
    }
}
