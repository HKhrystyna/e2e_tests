package io.testomat.e2e_tests;

import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({TextReportExtension.class})
public class ProjectPageTests extends BaseTest {

    @BeforeEach
    void openProjectsPage() {
        app.projectsPage.open();
        app.projectsPage.isLoaded();
    }

    @Test
    public void userCanFindProjectWithTests() {
        app.projectsPage.searchForProject(targetProjectName)
                .selectProject(targetProjectName);

        app.projectPage.isLoaded(targetProjectName);
    }

    @Test
    public void userCanSeeProjectDataInSearchResults() {
        app.projectsPage.searchForProject(targetProjectName)
                .countOfProjectsShouldBeEqualTo(1)
                .countOfTestsCasesShouldBeEqualTo(targetProjectName, 0)
                .shouldHaveProjectBadge(targetProjectName, "Classical");
    }

    @Test
    public void userCanSwitchListAndCardViews() {
        app.projectsPage.switchFromCardToListView()
                .projectsTableIsVisible()
                .projectListShouldNotBeEmpty()
                .switchFromListToCardView();
    }

    @Test
    public void userCanSwitchProjectsPages() {
        app.projectsPage.openSpecificProjectsPage("Free Projects")
                .shouldHaveProjectsPageTooltip("Free Plan")
                .openSpecificProjectsPage("QA Club Lviv")
                .shouldHaveProjectsPageTooltip("Enterprise Plan");
    }
}
