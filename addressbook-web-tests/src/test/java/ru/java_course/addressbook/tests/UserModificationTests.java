package ru.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

public class UserModificationTests extends TestBase {

    @Test
    public void testUserModification() throws InterruptedException {

        app.getNavigationHelper().gotoHomePage();
        if (! app.getUserHelper().isThereAUser()) {
            app.getUserHelper().createUser(new UserData("Sergei", "Ivanovich", "Kozlov", "ivashka",
                    null, null, null, null, null, "[none]"));
        }
        int before = app.getUserHelper().getUserCount();
        app.getGroupHelper().selectGroup(before - 1);
        app.getUserHelper().initUserModification();
        app.getUserHelper().fillUserForm(new UserData("Sergei", "Ivanovich", "Kozlov", "ivashka",
                "title", "Qiwi", "Moscow", "+77777777777", "ivanov@mail.ru", "[none]"), false);
        app.getUserHelper().submitUserModification();
        app.getUserHelper().returnToHomePage();
        int after = app.getUserHelper().getUserCount();
        Assert.assertEquals(after, before);
    }

}
