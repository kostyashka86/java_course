package ru.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserEmailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.user().create(new UserData().
                    withFirstname("Konstantin").withLastname("Zhuravlev")
                    .withEmail("as123dsdsf@ddd.ru").withEmailTwo("sf@d.com").withEmailThree("")
                    .withGroup("[none]"));
        }
    }

    @Test
    public void testUserEmails() {
        app.goTo().homePage();
        UserData user = app.user().all().iterator().next();
        UserData userInfoFromEditForm = app.user().infoFromEditForm(user);
        assertThat(user.getAllEmails(), equalTo(mergeEmails(userInfoFromEditForm)));
    }

    private String mergeEmails(UserData user) {
        return Stream.of(user.getEmail(), user.getEmailTwo(), user.getEmailThree())
                .filter((s) -> !s.equals("")).map(UserEmailTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String email) {
        return email.replaceAll("\\s", "");
    }

}
