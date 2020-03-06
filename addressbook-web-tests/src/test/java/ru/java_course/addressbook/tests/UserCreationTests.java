package ru.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

import java.util.Set;

public class UserCreationTests extends TestBase{

  @Test
  public void testUserCreation() {

    Set<UserData> before = app.user().all();
    UserData user = new UserData()
            .withName("Tatyana").withLastname("Krutikova").withGroup("[none]");
    app.user().create(user);
    Set<UserData> after = app.user().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(user);
    Assert.assertEquals(after, before);
  }

}
