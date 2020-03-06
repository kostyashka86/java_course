package ru.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

import java.util.Set;

public class UserModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.user().create(new UserData().
                    withName("Konstantin").withLastname("Zhuravlev").withGroup("[none]"));
        }
    }

    @Test
    public void testUserModification() {

        Set<UserData> before = app.user().all();
        UserData modifiedUser = before.iterator().next();
        UserData user = new UserData()
                .withId(modifiedUser.getId()).withName("Gleb").withLastname("Zhuravlev").withGroup("[none]");
        app.user().modify(user);
        Set<UserData> after = app.user().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedUser);
        before.add(user);
        Assert.assertEquals(before, after);
    }

}
