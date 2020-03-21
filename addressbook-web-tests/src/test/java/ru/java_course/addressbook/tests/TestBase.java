package ru.java_course.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.java_course.addressbook.appmanager.ApplicationManager;
import ru.java_course.addressbook.model.GroupData;
import ru.java_course.addressbook.model.Groups;
import ru.java_course.addressbook.model.UserData;
import ru.java_course.addressbook.model.Users;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream().
                    map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyUserListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Users dbUsers = app.db().users();
            Users uiUsers = app.user().all();
            assertThat(uiUsers.stream().
                            map((g) -> new UserData().withId(g.getId()).withFirstname(g.getFirstname())
                                    .withLastname(g.getLastname())).collect(Collectors.toSet())
                    , equalTo(dbUsers.stream().
                            map((g) -> new UserData().withId(g.getId()).withFirstname(g.getFirstname())
                                    .withLastname(g.getLastname())).collect(Collectors.toSet())));
        }
    }
}
