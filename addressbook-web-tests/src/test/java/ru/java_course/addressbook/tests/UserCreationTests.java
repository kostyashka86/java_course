package ru.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

import java.util.List;

public class UserCreationTests extends TestBase{

  @Test
  public void testUserCreation() throws Exception {
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().initUserCreation();
    app.getUserHelper().createUser(new UserData("Kostya", "Ivanovich", "Kozlov", "ivashka",
            null, null, null, null, null, "[none]"));
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() + 1);
    app.logout();
  }

}
