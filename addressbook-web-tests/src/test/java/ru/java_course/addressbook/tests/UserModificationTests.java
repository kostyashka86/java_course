package ru.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.GroupData;
import ru.java_course.addressbook.model.UserData;

import javax.xml.crypto.Data;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class UserModificationTests extends TestBase {

    @Test
    public void testUserModification() throws InterruptedException {

        app.getNavigationHelper().gotoHomePage();
        if (! app.getUserHelper().isThereAUser()) {
            app.getUserHelper().createUser(new UserData(null, null, "Ivanov Ivan", null,
                    null, null, null, null, null, "[none]"));
        }
        List<UserData> before = app.getUserHelper().getUserList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getUserHelper().initUserModification();
        UserData user = new UserData(before.get(before.size() - 1).getId(),null, null, "Ivanov Ivan", null,
                null, null, null, null, null, "[none]");
        app.getUserHelper().fillUserForm(user, false);
        app.getUserHelper().submitUserModification();
        app.getUserHelper().returnToHomePage();
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(user);
        Comparator<? super UserData> byId = Comparator.comparingInt(UserData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
