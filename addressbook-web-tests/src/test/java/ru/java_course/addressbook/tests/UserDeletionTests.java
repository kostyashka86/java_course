package ru.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

import java.util.List;

import static java.lang.Thread.sleep;

public class UserDeletionTests extends TestBase{

  @Test
  public void testUserDeletion() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Sergei", "Ivanovich", "Kozlov", "ivashka",
              null, null, null, null, null, "[none]"));
    }
    List<UserData> before = app.getUserHelper().getUserList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getUserHelper().deleteSelectedUsers();
    sleep(4000);
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() - 1);
    app.logout();
  }

}
