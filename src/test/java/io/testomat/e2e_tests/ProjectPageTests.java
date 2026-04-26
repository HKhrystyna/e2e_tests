package io.testomat.e2e_tests;

import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({TextReportExtension.class})
public class ProjectPageTests extends BaseTest {

    @BeforeEach
    void openProjectsPage() {
        app.projectsPage.open();
        app.projectsPage.isLoaded();
    }

    @Test
    public void userCanFindProjectWithTests() {
        app.projectsPage.searchForProject(targetProjectName);

        app.projectsPage.selectProject(targetProjectName);

        app.projectPage.isLoaded(targetProjectName);
    }

    @Test
    public void userCanSeeProjectDataInSearchResults() {
        app.projectsPage.searchForProject(targetProjectName);

        var targetProject = app.projectsPage.countOfProjectsShouldBeEqualTo(1).first();

        app.projectsPage.countOfTestsCasesShouldBeEqualTo(targetProject, 0);

        app.projectsPage.projectBadgeIsVisible(targetProject);

        var projectBadge = app.projectsPage.getProjectBadge(targetProject);
        assertEquals("Classical", projectBadge);
    }

    @Test
    public void userCanSwitchListAndCardViews() {
        app.projectsPage.switchFromCardToListView();

        app.projectsPage.projectsTableIsVisible();

        app.projectsPage.projectListShouldNotBeEmpty();

        app.projectsPage.switchFromListToCardView();
    }

    @Test
    public void userCanSwitchProjectsPages() {
        app.projectsPage.openSpecificProjectsPage("Free Projects");

        app.projectsPage.projectsPageTooltipIsVisible();

        var freeProjectsPageTooltip = app.projectsPage.getProjectsPageTooltip();
        assertEquals("Free Plan", freeProjectsPageTooltip);

        app.projectsPage.openSpecificProjectsPage("QA Club Lviv");

        app.projectsPage.projectsPageTooltipIsVisible();

        var enterpriseProjectsPageTooltip = app.projectsPage.getProjectsPageTooltip();
        assertEquals("Enterprise Plan", enterpriseProjectsPageTooltip);
    }
}
