package ru.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

import java.util.List;

public class UserModificationTests extends TestBase {

    @Test
    public void testUserModification() throws InterruptedException {

        app.getNavigationHelper().gotoHomePage();
        if (! app.getUserHelper().isThereAUser()) {
            app.getUserHelper().createUser(new UserData("Sergei", "Ivanovich", "Kozlov", "ivashka",
                    null, null, null, null, null, "[none]"));
        }
        List<UserData> before = app.getUserHelper().getUserList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getUserHelper().initUserModification();
        app.getUserHelper().fillUserForm(new UserData("Sergei", "Ivanovich", "Kozlov", "ivashka",
                "title", "Qiwi", "Moscow", "+77777777777", "ivanov@mail.ru", "[none]"), false);
        app.getUserHelper().submitUserModification();
        app.getUserHelper().returnToHomePage();
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size());
    }

}
