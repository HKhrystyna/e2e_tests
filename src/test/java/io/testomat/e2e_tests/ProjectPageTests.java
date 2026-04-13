package io.testomat.e2e_tests;

import io.testomat.e2e_tests.web.pages.ProjectPage;
import io.testomat.e2e_tests.web.pages.ProjectsPage;
import io.testomat.e2e_tests.web.pages.SignInPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectPageTests extends BaseTest {

    private static final ProjectsPage projectsPage = new ProjectsPage();
    private static final SignInPage signInPage = new SignInPage();
    static String email = env.get("EMAIL");
    static String password = env.get("PASSWORD");
    private final ProjectPage projectPage = new ProjectPage();
    String targetProjectName = "Manufacture Own";

    @BeforeAll
    static void openTestomatAndLogin() {
        signInPage.open();
        signInPage.loginUser(email, password);
        projectsPage.signInSuccess();
    }

    @BeforeEach
    void openProjectsPage() {
        projectsPage.open();
        projectsPage.isLoaded();
    }

    @Test
    public void userCanFindProjectWithTests() {
        projectsPage.searchForProject(targetProjectName);

        projectsPage.selectProject(targetProjectName);

        projectPage.isLoaded(targetProjectName);
    }

    @Test
    public void userCanSeeProjectDataInSearchResults() {
        projectsPage.searchForProject(targetProjectName);

        var targetProject = projectsPage.countOfProjectsShouldBeEqualTo(1).first();

        projectsPage.countOfTestsCasesShouldBeEqualTo(targetProject, 0);

        projectsPage.projectBadgeIsVisible(targetProject);

        var projectBadge = projectsPage.getProjectBadge(targetProject);
        assertEquals("Classical", projectBadge);
    }

    @Test
    public void userCanSwitchListAndCardViews() {
        projectsPage.switchFromCardToListView();

        projectsPage.projectsTableIsVisible();

        projectsPage.projectListShouldNotBeEmpty();

        projectsPage.switchFromListToCardView();
    }

    @Test
    public void userCanSwitchProjectsPages() {
        projectsPage.openSpecificProjectsPage("Free Projects");

        projectsPage.projectsPageTooltipIsVisible();

        var freeProjectsPageTooltip = projectsPage.getProjectsPageTooltip();
        assertEquals("Free Plan", freeProjectsPageTooltip);

        projectsPage.openSpecificProjectsPage("QA Club Lviv");

        projectsPage.projectsPageTooltipIsVisible();

        var enterpriseProjectsPageTooltip = projectsPage.getProjectsPageTooltip();
        assertEquals("Enterprise Plan", enterpriseProjectsPageTooltip);
    }
}
