package ru.java_course.mantis.tests;

import org.testng.annotations.Test;
import ru.java_course.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.AssertJUnit.assertTrue;
import static ru.java_course.mantis.tests.TestBase.app;

public class LoginTests {
    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("Administrator", "root"));
        assertTrue(session.isLoggedInAs("Administrator"));
    }
}
