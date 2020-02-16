package ru.java_course.addressbook;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase{

  @Test
  public void testUserDeletion() throws Exception {
    gotoHomePage();
    selectGroup();
    deleteSelectedUsers();
    logout();
  }

}
