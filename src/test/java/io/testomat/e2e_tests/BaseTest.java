package io.testomat.e2e_tests;

import io.github.cdimascio.dotenv.Dotenv;
import io.testomat.e2e_tests.common.Application;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.baseUrl;

public class BaseTest {

    protected static Dotenv env = Dotenv.load();
    protected static String email = env.get("EMAIL");
    protected static String password = env.get("PASSWORD");
    protected String targetProjectName = "Manufacture Own";

    protected static Application app = new Application();

    @BeforeAll
    static void openTestomatAndLogin() {
        app.signInPage.open();
        app.signInPage.loginUser(email, password);
        app.projectsPage.signInSuccess();
    }

    static {
        baseUrl = env.get("BASE_URL");
    }
}
