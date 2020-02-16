package ru.java_course.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase{

  @Test
  public void testUserDeletion() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    app.getGroupHelper().selectGroup();
    app.getUserHelper().deleteSelectedUsers();
    app.logout();
  }

}
