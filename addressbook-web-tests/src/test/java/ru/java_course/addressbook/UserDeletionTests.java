package ru.java_course.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UserDeletionTests extends TestBase{

  @Test
  public void testUserDeletion() throws Exception {
    gotoHomePage();
    selectGroup();
    deleteSelectedUsers();
    logout();
  }

}
