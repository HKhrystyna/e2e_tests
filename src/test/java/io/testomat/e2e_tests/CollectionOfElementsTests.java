package io.testomat.e2e_tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.testomat.e2e_tests.utils.StringParsers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$$;

public class CollectionOfElementsTests extends BaseTest {

    private static final Set<String> ALLOWED_PROJECT_TYPES =
            Set.of("bdd", "classical");

    private static final Set<String> ALLOWED_FRAMEWORKS =
            Set.of("cucumber", "jest", "webdriverio-mocha");

    @BeforeEach
    void openProjectsPage() {
        app.projectsPage.open();
        app.projectsPage.isLoaded().switchFromListToCardView();
    }

    @Test
    @DisplayName("All projects should have correct tests count")
    void allProjectsShouldHaveCorrectTests() {
        var labelCountOfTests = $$("#grid ul li p").shouldHave(CollectionCondition.sizeGreaterThan(0));

        for (SelenideElement labelCountOfTest : labelCountOfTests) {
            labelCountOfTest.shouldHave(Condition.text("2 tests")
                    .or(Condition.text("0 tests"))
                    .or(Condition.text("21 tests")));
        }
    }

    @Test
    @DisplayName("All BDD projects should have BDD badge")
    void allBDDProjectsShouldHaveBDDBadge() {
        List<SelenideElement> bddProjects = $$("#grid ul li")
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .stream()
                .filter(project ->
                        project.$("h3").getText().contains("BDD"))
                .toList();

        Assertions.assertFalse(bddProjects.isEmpty(), "BDD projects should be present");

        for (SelenideElement project : bddProjects) {
            project.$(".project-badges").shouldHave(Condition.text("BDD"));
        }
    }

    @Test
    @DisplayName("All projects should have unique name")
    void allProjectsShouldHaveUniqueName() {
        List<String> projectNames = $$("#grid ul li h3")
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .texts();

        var uniqueProjectNames = new HashSet<>(projectNames);

        Assertions.assertEquals(
                projectNames.size(),
                uniqueProjectNames.size(),
                () -> "Project names should be unique. Actual names: " + projectNames
        );
    }

    @Test
    @DisplayName("All projects should have allowed project type")
    void allProjectsShouldHaveAllowedProjectType() {
        app.projectsPage.switchFromCardToListView().projectsTableIsVisible();

        var projectTypes = $$(".text-indigo-700");

        projectTypes.shouldHave(CollectionCondition.allMatch(
                "Allowed project types only",
                type -> ALLOWED_PROJECT_TYPES.contains(
                        StringParsers.normalizedText(type)
                )
        ));
    }

    @Test
    @DisplayName("Projects should have allowed framework")
    void projectsShouldHaveAllowedFramework() {
        app.projectsPage.switchFromCardToListView().projectsTableIsVisible();

        var frameworks = $$(".text-gray-500 .company-team-members-member-status-primary")
                .excludeWith(Condition.text("No Framework"));

        frameworks.shouldHave(CollectionCondition.allMatch(
                "Allowed frameworks only",
                framework -> ALLOWED_FRAMEWORKS.contains(
                        StringParsers.normalizedText(framework)
                )
        ));
    }
}
