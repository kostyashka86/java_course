package ru.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;
import ru.java_course.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().homePage();
    if (app.user().all().size() == 0) {
      app.user().create(new UserData().withFirstname("Tatyana").withLastname("Krutikova").withGroup("[none]"));
    }
  }

  @Test
  public void testUserDeletion() throws Exception {

    Users before = app.user().all();
    UserData deletedUser = before.iterator().next();
    app.user().delete(deletedUser);
    assertThat(app.user().count(), equalTo(before.size() - 1));
    Users after = app.user().all();
    assertThat(after, equalTo(before.without(deletedUser)));
  }

}
