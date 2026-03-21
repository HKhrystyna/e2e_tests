package io.testomat.e2e_tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ProjectPageTests {

    @Test
    public void userCanFindProjectWithTests() {
        open("https://app.testomat.io/");

        //login
        $("#content-desktop #user_email").setValue("grynyshyn2002@gmail.com");
        $("#content-desktop #user_password").setValue("khrynyshyn_QA123");
        $("#content-desktop #user_remember_me").click();
        $("#content-desktop [name=\"commit\"]").click();
        $(".common-flash-success").shouldBe(visible);

        //search project
        $("#search").setValue("Manufacture Own");

        //select project
        $(byText("Manufacture Own")).click();
        sleep(10000);

        //wait for page to load
        $("h2").shouldHave(text("Manufacture Own"));
    }

    @Test
    public void userCanCreateNewProject() {
        open("https://app.testomat.io/");

        //login
        $("#content-desktop #user_email").setValue("grynyshyn2002@gmail.com");
        $("#content-desktop #user_password").setValue("khrynyshyn_QA123");
        $("#content-desktop #user_remember_me").click();
        $("#content-desktop [name=\"commit\"]").click();
        $(".common-flash-success").shouldBe(visible);

        //create project
        $("a.common-btn-primary[href='/projects/new']").click();
        $("h2").shouldHave(text("New Project"));
        $("#project_title").setValue("New Project");
        $("[name='commit']").click();


        //wait for project to create
        sleep(10000);
        $("#welcometotestomatio").shouldBe(visible);
        $(".detail-view-header-wrapper .md-icon.md-icon-close").click();
        $("h2").shouldHave(text("New Project"));
    }
}
