package ru.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

import java.util.List;

import static java.lang.Thread.sleep;

public class UserDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().homePage();
    if (app.user().list().size() == 0) {
      app.user().create(new UserData("Tatyana", null, "Krutikova", null,
              null, null, null, null, null, "[none]"));
    }
  }

  @Test
  public void testUserDeletion() throws Exception {

    List<UserData> before = app.user().list();
    int index = before.size() - 1;
    app.user().delete(index);
    List<UserData> after = app.user().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(after, before);
  }

}
