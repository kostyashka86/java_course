package ru.java_course.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;
import ru.java_course.addressbook.model.Users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsers() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{new UserData().withFirstname(split[0]).withLastname(split[1]).withGroup(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test(dataProvider = "validUsers")
  public void testUserCreation(UserData user) {
    Users before = app.user().all();
    app.user().create(user);
    Set<UserData> after = app.user().all();
    assertThat(app.user().count(), equalTo(before.size() + 1));

    assertThat(((Users) after).without(user.withGroup(null).withAddress("").withAllPhones("").withAllEmails("")), equalTo(
            before.withAdded(user.withId(after.stream().mapToInt(UserData::getId).max().getAsInt()))));
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
