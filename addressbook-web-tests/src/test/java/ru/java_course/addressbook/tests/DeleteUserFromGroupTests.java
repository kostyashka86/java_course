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

public class DeleteUserFromGroupTests extends TestBase {

    private GroupData groupData;
    private UserData userData;
    int userId;
    int groupId;

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

    public void ensurePreconditionsDeleteFromGroup(UserData user, GroupData group) {
        app.goTo().groupPage();
        Groups groups = app.db().groups();
        if (app.db().groups().size() == 0) {
            app.group().create(group);
            groupData = app.db().groups().iterator().next();
            groupId = groupData.getId();
        } else {
            groupId = groups.iterator().next().getId();
            groupData = app.db().groupById(groupId);
        }
        app.goTo().homePage();
        Users users = app.db().users();
        app.user().selectUserGroupById(groupId);
        Users groupUsers = app.db().groupUsers(groupId);
        if (users.size() > 0) {
            for (UserData item : users) {
                if (groupUsers.contains(item)) {
                    userData = user;
                    userId = userData.getId();
                    break;
                }
            }
        }
        if (userData == null) {
            app.goTo().homePage();
            userData = user;
            app.user().create(userData.inGroup(groupData).withId(groupId));
            userId = app.db().users().stream().mapToInt((g) -> g.getId()).max().getAsInt();
            userData.withId(userId);
        }
    }

    @Test(dataProvider = "datafromJson")
    public void testDeleteUserFromGroup(UserData user, GroupData group) {
        ensurePreconditionsDeleteFromGroup(user, group);
        app.goTo().homePage();
        app.user().selectUserGroupById(groupId);
        Users before = app.db().groupUsers(groupId);
        app.user().deleteFromGroup(userId, groupId);
        Users after = app.db().groupUsers(groupId);
        assertThat(app.user().count(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(user)));
        verifyUserListInUI();
    }

}
