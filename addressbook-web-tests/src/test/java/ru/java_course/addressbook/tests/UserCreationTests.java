package ru.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;
import ru.java_course.addressbook.model.Users;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase{

  @Test
  public void testUserCreation() {

    Users before = app.user().all();
    UserData user = new UserData()
            .withName("Tatyana").withLastname("Krutikova").withGroup("[none]");
    app.user().create(user);
    Set<UserData> after = app.user().all();
    assertThat(app.user().count(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadUserCreation() {

    Users before = app.user().all();
    UserData user = new UserData()
            .withName("Tatyana'").withLastname("Krutikova").withGroup("[none]");
    app.user().create(user);
    assertThat(app.user().count(), equalTo(before.size()));
    Set<UserData> after = app.user().all();
    assertThat(after, equalTo(before));
  }
}
