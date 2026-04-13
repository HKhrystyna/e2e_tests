package io.testomat.e2e_tests.web.pages;

import com.codeborne.selenide.ElementsCollection;
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

    public void isLoaded() {
        searchInput.shouldBe(visible);
    }

    public void searchForProject(String targetProjectName) {
        searchInput.setValue(targetProjectName);
    }

    public void selectProject(String targetProjectName) {
        $(byText(targetProjectName)).click();
    }

    public void signInSuccess() {
        $("#container .common-flash-success").shouldBe(visible);
    }

    public ElementsCollection countOfProjectsShouldBeEqualTo(int expectedCount) {
        return $$("#grid ul li").filter(visible).shouldHave(size(expectedCount));
    }

    public void countOfTestsCasesShouldBeEqualTo(SelenideElement targetProject, int expectedCount) {
        targetProject.shouldHave(text(expectedCount + " tests"));
    }

    public void projectBadgeIsVisible(SelenideElement targetProject) {
        targetProject.$("a .common-badge-project-default").shouldBe(visible);
    }

    public String getProjectBadge(SelenideElement targetProject) {
        return targetProject.$("a .common-badge-project-default").getText();
    }

    public void switchFromCardToListView() {
        $("#table-icon").click();
        $("#myTable").shouldBe(visible);
    }

    public void switchFromListToCardView() {
        $("#table-icon").click();
        $("#grid").shouldBe(visible);
    }

    public void projectsTableIsVisible() {
        $("#team-members").shouldBe(visible);
    }

    public void projectListShouldNotBeEmpty() {
        $("#team-members .bg-gray-50").shouldBe(visible);
    }

    public void openSpecificProjectsPage(String optionText) {
        $("#content-desktop #company_id").click();
        $$("#content-desktop #company_id option").findBy(text(optionText)).click();
    }

    public void projectsPageTooltipIsVisible() {
        $(".common-page-header-left .tooltip-project-plan").shouldBe(visible);
    }

    public String getProjectsPageTooltip() {
        return $(".common-page-header-left .tooltip-project-plan").getText();
    }
}
