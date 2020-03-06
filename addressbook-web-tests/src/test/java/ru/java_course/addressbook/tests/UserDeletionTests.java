package ru.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

import java.util.Set;

public class UserDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().homePage();
    if (app.user().all().size() == 0) {
      app.user().create(new UserData().withName("Tatyana").withLastname("Krutikova").withGroup("[none]"));
    }
  }

  @Test
  public void testUserDeletion() throws Exception {

    Set<UserData> before = app.user().all();
    UserData deleteUser = before.iterator().next();
    app.user().delete(deleteUser);
    Set<UserData> after = app.user().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deleteUser);
    Assert.assertEquals(after, before);
  }

}
