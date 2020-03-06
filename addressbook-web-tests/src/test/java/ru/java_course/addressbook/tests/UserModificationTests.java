package ru.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().homePage();
        if (app.user().list().size() == 0) {
            app.user().create(new UserData().
                    withName("Konstantin").withLastname("Zhuravlev").withGroup("[none]"));
        }
    }

    @Test
    public void testUserModification() {

        List<UserData> before = app.user().list();
        int index = before.size() - 1;
        UserData user = new UserData()
                .withId(before.get(index).getId()).withName("Gleb").withLastname("Zhuravlev").withGroup("[none]");
        app.user().modify(index, user);
        List<UserData> after = app.user().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(user);
        Comparator<? super UserData> byId = Comparator.comparingInt(UserData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
