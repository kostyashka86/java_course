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

      if (app.db().users().size() == 0) {
          app.goTo().homePage();
          app.user().create(new UserData().
                  withFirstname("Konstantin").withLastname("Zhuravlev").withGroup("[none]"));
      }
  }

  @Test
  public void testUserDeletion() throws Exception {

      Users before = app.db().users();
      UserData deletedUser = before.iterator().next();
    app.user().delete(deletedUser);
    assertThat(app.user().count(), equalTo(before.size() - 1));
      Users after = app.db().users();
    assertThat(after, equalTo(before.without(deletedUser)));
  }

}
