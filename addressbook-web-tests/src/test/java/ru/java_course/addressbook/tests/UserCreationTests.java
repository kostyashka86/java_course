package ru.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

public class UserCreationTests extends TestBase{

  @Test
  public void testUserCreation() throws Exception {
    app.initUserCreation();
    app.fillUserForm(new UserData("Sergei", "Ivanovich", "Kozlov", "ivashka", "title", "Qiwi", "Moscow", "+77777777777", "ivanov@mail.ru", "1", "January", "1986"));
    app.submitUserCreation();
    app.returnToHomePage();
    app.logout();
  }

}
