package io.testomat.e2e_tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.testomat.e2e_tests.utils.StringParsers.parseIntegerFromString;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectPageTests extends BaseTest {

    static String baseUrl = env.get("BASE_URL");
    static String email = env.get("EMAIL");
    static String password = env.get("PASSWORD");
    String targetProjectName = "Manufacture Own";

    @BeforeAll
    static void openTestomatAndLogin() {
        open(baseUrl);

        loginUser(email, password);
    }

    @BeforeEach
    void openHomePage() {
        open(baseUrl);
    }

    @Test
    public void userCanFindProjectWithTests() {
        searchForProject(targetProjectName);

        selectProject(targetProjectName);

        waitForProjectPageIsLoaded(targetProjectName);
    }

    @Test
    public void userCanFindProjectWithoutTests() {
        searchForProject(targetProjectName);

        SelenideElement targetProject = countOfProjectsShouldBeEqualTo(1).first();

        countOfTestsCasesShouldBeEqualTo(targetProject, 0);

        projectShouldHaveCorrectBadge(targetProject, "Classical");
    }

    @Test
    public void userCanSwitchListAndCardViews() {
        switchFromCardToListView();

        projectListShouldNotBeEmpty();

        switchFromListToCardView();
    }

    @Test
    public void userCanOpenFreeProjects() {
        openSpecificProjectsPage("Free Projects");

        freePlanShouldBeOnFreeProjectsPage();

        openSpecificProjectsPage("QA Club Lviv");
    }


    public static void loginUser(String email, String password) {
        $("#content-desktop #user_email").setValue(email);
        $("#content-desktop #user_password").setValue(password);
        $("#content-desktop #user_remember_me").click();
        $("#content-desktop [name=\"commit\"]").click();
        $(".common-flash-success").shouldBe(visible);
    }

    private void waitForProjectPageIsLoaded(String targetProjectName) {
        $(".first h2").shouldHave(text(targetProjectName));
        $(".sticky-header [href$='/readme']").shouldBe(visible).shouldHave(text("Readme"));
    }

    private void selectProject(String targetProjectName) {
        $(byText(targetProjectName)).click();
    }

    private void searchForProject(String targetProjectName) {
        $("#search").setValue(targetProjectName);
    }

    private void projectShouldHaveCorrectBadge(SelenideElement targetProject, String expectedBadge) {
        targetProject.$("a .common-badge-project-default").shouldHave(text(expectedBadge));
    }

    private void countOfTestsCasesShouldBeEqualTo(SelenideElement targetProject, int expectedCount) {
        String countOfTests = targetProject.$("p").getText();
        Integer actualCountOfTests = parseIntegerFromString(countOfTests);
        assertEquals(expectedCount, actualCountOfTests);
    }

    @NonNull
    private ElementsCollection countOfProjectsShouldBeEqualTo(int expectedCount) {
        return $$("#grid ul li").filter(visible).shouldHave(size(expectedCount));
    }

    private void switchFromCardToListView() {
        $("#table-icon").click();
        $("#myTable").shouldBe(visible);
    }

    private void switchFromListToCardView() {
        $("#table-icon").click();
        $("#grid").shouldBe(visible);
    }

    private void projectListShouldNotBeEmpty() {
        $("#team-members .bg-gray-50").shouldBe(visible);
    }

    private void openSpecificProjectsPage(String optionText) {
        $("#content-desktop #company_id").click();
        $$("#content-desktop #company_id option").findBy(text(optionText)).click();
    }

    private void freePlanShouldBeOnFreeProjectsPage() {
        $(".common-page-header-left .tooltip-project-plan").shouldHave(text("free plan"));
    }
}
