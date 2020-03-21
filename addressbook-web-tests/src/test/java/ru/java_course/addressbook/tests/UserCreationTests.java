package ru.java_course.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.GroupData;
import ru.java_course.addressbook.model.Groups;
import ru.java_course.addressbook.model.UserData;
import ru.java_course.addressbook.model.Users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @DataProvider()
  public Iterator<Object[]> validUsersFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<UserData> users = gson.fromJson(json, new TypeToken<List<UserData>>() {
      }.getType());
      return users.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test(dataProvider = "validUsersFromJson")
  public void testUserCreation(UserData user) {
    Groups groups = app.db().groups();
    Users before = app.db().users();
    app.user().create(user.inGroup(groups.iterator().next()));
    Users after = app.db().users();
    assertThat(app.user().count(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(user.withId(after.stream().mapToInt(UserData::getId).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadUserCreation() {

    Users before = app.db().users();
    UserData user = new UserData()
            .withFirstname("Tatyana'").withLastname("Krutikova");
    app.user().create(user);
    assertThat(app.user().count(), equalTo(before.size()));
    Set<UserData> after = app.db().users();
    assertThat(after, equalTo(before));
    verifyUserListInUI();
  }
}
