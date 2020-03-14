package ru.java_course.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;
import ru.java_course.addressbook.model.Users;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsers() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new UserData().withFirstname("Kostya").withLastname("Shishmagaev")
            .withAllEmails("").withAllPhones("").withAddress("").withGroup("[none]")});
    list.add(new Object[]{new UserData().withFirstname("Tanya").withLastname("Shishmagaeva")
            .withAllEmails("").withAllPhones("").withAddress("").withGroup("[none]")});
    list.add(new Object[]{new UserData().withFirstname("Gleb").withLastname("Shishmagaev")
            .withAllEmails("").withAllPhones("").withAddress("").withGroup("[none]")});
    return list.iterator();
  }

  @Test(dataProvider = "validUsers")
  public void testUserCreation(UserData user) {
    Users before = app.user().all();
    app.user().create(user);
    Set<UserData> after = app.user().all();
    assertThat(app.user().count(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadUserCreation() {

      Users before = app.user().all();
      UserData user = new UserData()
              .withFirstname("Tatyana'").withLastname("Krutikova").withGroup("[none]");
    app.user().create(user);
    assertThat(app.user().count(), equalTo(before.size()));
    Set<UserData> after = app.user().all();
    assertThat(after, equalTo(before));
  }
}
