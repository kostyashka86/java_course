package ru.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.GroupData;
import ru.java_course.addressbook.model.UserData;

import javax.xml.crypto.Data;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class UserCreationTests extends TestBase{

  @Test
  public void testUserCreation() throws Exception {
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().initUserCreation();
    UserData user = new UserData(null, null, "Ivanov Ivan", null,
            null, null, null, null, null, "[none]");
    app.getUserHelper().createUser(user);
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(user);
    Comparator<? super UserData> byId = Comparator.comparingInt(UserData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
    app.logout();
  }

}
