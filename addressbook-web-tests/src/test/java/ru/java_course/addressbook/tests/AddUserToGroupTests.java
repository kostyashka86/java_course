package ru.java_course.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddUserToGroupTests extends TestBase {

    private GroupData groupData;
    private UserData userData;
    private int userId;
    private int groupId;

    @DataProvider()
    public static Iterator<Object[]> validUsersFromJson() throws IOException {
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

    @DataProvider
    public static Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public static Object[][] datafromJson() throws IOException {
        return new Object[][]{{validUsersFromJson().next()[0], validGroupsFromJson().next()[0]}};
    }

    public void ensurePreconditions(UserData user, GroupData group) {
        app.goTo().homePage();
        app.user().selectUserAll();
        Users users = app.db().users();
        if (app.db().users().size() == 0) {
            app.goTo().homePage();
            app.user().create(user);
            userData = app.db().users().iterator().next();
            userId = userData.getId();
        } else {
            userId = users.iterator().next().getId();
            userData = app.db().userById(userId);
        }
        Groups groups = app.db().groups();
        if (groups.size() > 0) {
            for (GroupData item : groups) {
                if (!userData.getGroups().contains(item)) {
                    groupData = group;
                    groupId = groupData.getId();
                    break;
                }
            }
        }
        if (groupData == null) {
            app.goTo().groupPage();
            groupData = group;
            app.group().create(groupData);
            groupId = app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt();
            groupData.withId(groupId);
        }
    }

    @Test(dataProvider = "datafromJson")
    public void testAddUserToGroup(UserData user, GroupData group) {
        ensurePreconditions(user, group);
        app.goTo().homePage();
        app.user().selectUserGroupById(groupId);
        Users before = app.db().groupUsers(groupId);
        app.goTo().homePage();
        app.user().selectUserAll();
        app.user().selectUserById(userId);
        app.user().addToGroup(groupId, userId);
        assertThat(app.user().count(), equalTo(before.size() + 1));
        Users after = app.db().groupUsers(groupId);
        assertThat(after, equalTo(
                before.withAdded(user.withId(after.stream().mapToInt(UserData::getId).max().getAsInt()))));
        verifyUserListInUI();
    }
}
