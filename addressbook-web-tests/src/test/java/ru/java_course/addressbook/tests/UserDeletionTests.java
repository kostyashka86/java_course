package ru.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

import static java.lang.Thread.sleep;

public class UserDeletionTests extends TestBase{

  @Test
  public void testUserDeletion() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Sergei", "Ivanovich", "Kozlov", "ivashka",
              null, null, null, null, null, "[none]"));
    }
    int before = app.getUserHelper().getUserCount();
    app.getGroupHelper().selectGroup(before - 1);
    app.getUserHelper().deleteSelectedUsers();
    sleep(4000);
    int after = app.getUserHelper().getUserCount();
    Assert.assertEquals(after, before - 1);
    app.logout();
  }

}
