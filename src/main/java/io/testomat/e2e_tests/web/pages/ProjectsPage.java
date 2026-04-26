package io.testomat.e2e_tests.web.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectsPage {

    private final SelenideElement searchInput = $("#search");

    public void open() {
        Selenide.open("/");
    }

    public ProjectsPage isLoaded() {
        searchInput.shouldBe(visible);
        return this;
    }

    public ProjectsPage searchForProject(String targetProjectName) {
        searchInput.setValue(targetProjectName);
        return this;
    }

    public void selectProject(String targetProjectName) {
        $(byText(targetProjectName)).click();
    }

    public void signInSuccess() {
        $("#container .common-flash-success").shouldBe(visible);
    }

    public ProjectsPage countOfProjectsShouldBeEqualTo(int expectedCount) {
        $$("#grid ul li").filter(visible).shouldHave(size(expectedCount));
        return this;
    }

    private SelenideElement projectItem(String projectName) {
        return $$("#grid ul li").findBy(text(projectName));
    }

    public ProjectsPage countOfTestsCasesShouldBeEqualTo(String projectName, int expectedCount) {
        projectItem(projectName).shouldHave(text(expectedCount + " tests"));
        return this;
    }

    public ProjectsPage shouldHaveProjectBadge(String projectName, String expectedBadge) {
        projectItem(projectName)
                .$("a .common-badge-project-default")
                .shouldBe(visible)
                .shouldHave(text(expectedBadge));
        return this;
    }

    public ProjectsPage switchFromCardToListView() {
        $("#table-icon").click();
        $("#myTable").shouldBe(visible);
        return this;
    }

    public ProjectsPage switchFromListToCardView() {
        $("#table-icon").click();
        $("#grid").shouldBe(visible);
        return this;
    }

    public ProjectsPage projectsTableIsVisible() {
        $("#team-members").shouldBe(visible);
        return this;
    }

    public ProjectsPage projectListShouldNotBeEmpty() {
        $("#team-members .bg-gray-50").shouldBe(visible);
        return this;
    }

    public ProjectsPage openSpecificProjectsPage(String optionText) {
        $("#content-desktop #company_id").selectOption(optionText);
        return this;
    }

    private SelenideElement projectsPageTooltip() {
        return $(".common-page-header-left .tooltip-project-plan");
    }

    public ProjectsPage shouldHaveProjectsPageTooltip(String expectedText) {
        projectsPageTooltip()
                .shouldBe(visible)
                .shouldHave(text(expectedText));
        return this;
    }
}
