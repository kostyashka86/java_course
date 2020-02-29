package ru.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

public class UserCreationTests extends TestBase{

  @Test
  public void testUserCreation() throws Exception {
    int before = app.getUserHelper().getUserCount();
    app.getUserHelper().initUserCreation();
    app.getUserHelper().createUser(new UserData("Sergei", "Ivanovich", "Kozlov", "ivashka",
            null, null, null, null, null, "[none]"));
    int after = app.getUserHelper().getUserCount();
    Assert.assertEquals(after, before + 1);
    app.logout();
  }

}
