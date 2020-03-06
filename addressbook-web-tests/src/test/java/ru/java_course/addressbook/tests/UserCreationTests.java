package ru.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase{

  @Test
  public void testUserCreation() {

    List<UserData> before = app.user().list();
    UserData user = new UserData()
            .withName("Tatyana").withLastname("Krutikova").withGroup("[none]");
    app.user().create(user);
    List<UserData> after = app.user().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(user);
    Comparator<? super UserData> byId = Comparator.comparingInt(UserData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }

}
