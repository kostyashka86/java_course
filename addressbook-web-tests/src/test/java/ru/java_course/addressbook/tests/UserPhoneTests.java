package ru.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.user().create(new UserData().
                    withFirstname("Konstantin").withLastname("Zhuravlev")
                    .withHomePhone("+7(495)1234567").withMobilePhone("909-123-45-67").withWorkPhone("1234567890")
                    .withGroup("[none]"));
        }
    }

    @Test
    public void testUserPhones() {
        app.goTo().homePage();
        UserData user = app.user().all().iterator().next();
        UserData userInfoFromEditForm = app.user().infoFromEditForm(user);
        assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoFromEditForm)));
    }

    private String mergePhones(UserData user) {
        return Stream.of(user.getHomePhone(), user.getMobilePhone(), user.getWorkPhone())
                .filter((s) -> !s.equals("")).map(UserPhoneTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
