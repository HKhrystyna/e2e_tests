package io.testomat.e2e_tests.utils;

import org.checkerframework.checker.nullness.qual.NonNull;

public class StringParsers {

    @NonNull
    public static Integer parseIntegerFromString(String targetString) {
        String digitText = targetString.replaceAll("\\D+", "");
        return Integer.parseInt(digitText);
    }

}
