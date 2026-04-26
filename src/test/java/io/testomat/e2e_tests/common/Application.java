package io.testomat.e2e_tests.common;

import io.testomat.e2e_tests.web.pages.ProjectPage;
import io.testomat.e2e_tests.web.pages.ProjectsPage;
import io.testomat.e2e_tests.web.pages.ReadmePage;
import io.testomat.e2e_tests.web.pages.SignInPage;

public class Application {

    public final ProjectsPage projectsPage = new ProjectsPage();
    public final SignInPage signInPage = new SignInPage();
    public final ProjectPage projectPage = new ProjectPage();
    public final ReadmePage readmePage = new ReadmePage();
}
