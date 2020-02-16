package ru.java_course.addressbook;

import org.testng.annotations.Test;

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
