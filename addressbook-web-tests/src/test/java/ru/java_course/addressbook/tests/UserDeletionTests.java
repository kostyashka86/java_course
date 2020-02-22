package ru.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

public class UserDeletionTests extends TestBase{

  @Test
  public void testUserDeletion() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Sergei", "Ivanovich", "Kozlov", "ivashka",
              null, null, null, null, null, "[none]"));
    }
    app.getGroupHelper().selectGroup();
    app.getUserHelper().deleteSelectedUsers();
    app.logout();
  }

}
