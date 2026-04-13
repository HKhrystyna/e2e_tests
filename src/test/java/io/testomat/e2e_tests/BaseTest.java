package io.testomat.e2e_tests;

import com.codeborne.selenide.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

public class BaseTest {
    public static Dotenv env = Dotenv.load();

    static {
        Configuration.baseUrl = env.get("BASE_URL");
    }
}
