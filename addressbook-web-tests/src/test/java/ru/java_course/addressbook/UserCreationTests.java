package ru.java_course.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UserCreationTests extends TestBase{

  @Test
  public void testUserCreation() throws Exception {
    initUserCreation();
    fillUserForm(new UserData("Sergei", "Ivanovich", "Kozlov", "ivashka", "title", "Qiwi", "Moscow", "+77777777777", "ivanov@mail.ru", "1", "January", "1986"));
    submitUserCreation();
    returnToHomePage();
    logout();
  }

}
