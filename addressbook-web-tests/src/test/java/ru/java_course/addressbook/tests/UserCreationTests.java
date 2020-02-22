package ru.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

public class UserCreationTests extends TestBase{

  @Test
  public void testUserCreation() throws Exception {
    app.getUserHelper().initUserCreation();
    app.getUserHelper().createUser(new UserData("Sergei", "Ivanovich", "Kozlov", "ivashka",
            null, null, null, null, null, "[none]"));
    app.logout();
  }

}
