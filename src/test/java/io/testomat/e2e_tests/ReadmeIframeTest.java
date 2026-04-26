package io.testomat.e2e_tests;

import com.codeborne.selenide.junit5.TextReportExtension;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({TextReportExtension.class})
public class ReadmeIframeTest extends BaseTest {

    @BeforeEach
    void openProjectsPage() {
        app.projectsPage.open();
        app.projectsPage.isLoaded();
    }

    @Test
    @DisplayName("Update readme text in iframe")
    void updateReadmeIframeTest() {
        String randomReadmeText = RandomStringUtils.randomAlphanumeric(10);

        app.projectsPage.isLoaded()
                .searchForProject(targetProjectName)
                .selectProject(targetProjectName);

        app.projectPage.openReadme().editReadme();

        app.readmePage.isLoaded()
                .clickOnEditReadme()
                .editReadmeTextInEditor(randomReadmeText)
                .clickOnUpdate()
                .updateSuccess()
                .openReadmeAfterUpdate()
                .shouldHaveReadmeText(randomReadmeText);
    }

    @Test
    @DisplayName("Cancel update of readme text in iframe")
    void cancelReadmeUpdateIframeTest() {
        String randomReadmeText = RandomStringUtils.randomAlphanumeric(10);

        app.projectsPage.isLoaded()
                .searchForProject(targetProjectName)
                .selectProject(targetProjectName);

        app.projectPage.openReadme().editReadme();

        app.readmePage.isLoaded()
                .clickOnEditReadme()
                .editReadmeTextInEditor(randomReadmeText)
                .clickOnCancel()
                .openReadmeAfterUpdate()
                .shouldNotHaveReadmeText(randomReadmeText);
    }
}
