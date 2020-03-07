package ru.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;
import ru.java_course.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.user().create(new UserData().
                    withFirstname("Konstantin").withLastname("Zhuravlev").withGroup("[none]"));
        }
    }

    @Test
    public void testUserModification() {

        Users before = app.user().all();
        UserData modifiedUser = before.iterator().next();
        UserData user = new UserData()
                .withId(modifiedUser.getId()).withFirstname("Gleb").withLastname("Zhuravlev").withGroup("[none]");
        app.user().modify(user);
        assertThat(app.group().count(), equalTo(before.size()));
        Users after = app.user().all();
        assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
    }

}
