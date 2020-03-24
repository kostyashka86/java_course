package ru.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.GroupData;
import ru.java_course.addressbook.model.Groups;
import ru.java_course.addressbook.model.UserData;
import ru.java_course.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DeleteUserFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().users().size() == 0) {
            app.goTo().homePage();
            app.user().create(new UserData().withFirstname("Kostya").withLastname("Zhuravlev"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testDeleteUserFromGroup() {

        UserData userAfter = null;
        UserData userBefore = null;
        GroupData selectedGroup = null;
        UserData selectedUser;

        Groups groups = app.db().groups();
        Users allUsers = app.db().users();
        app.goTo().homePage();
        selectedUser = allUsers.iterator().next(); //случайный контакт для случай (selectedUser.getGroups().size() == 0)

        for (UserData oneOfUserToDelete : allUsers) {
            Groups groupsOfUserToDelete = oneOfUserToDelete.getGroups();
            if (groupsOfUserToDelete.size() > 0) {
                selectedUser = oneOfUserToDelete;
                selectedGroup = selectedUser.getGroups().iterator().next(); //можно дальше не искать
                break;
            }
        }

        if (selectedUser.getGroups().size() == 0) {
            selectedGroup = groups.iterator().next();
            app.user().addInGroupFinal(selectedUser, selectedGroup);
        }

        Users allUsersBefore = app.db().users(); // обновили
        for (UserData oneOfUserBefore : allUsersBefore) {
            if (oneOfUserBefore.getId() == selectedUser.getId()) {
                userBefore = oneOfUserBefore;
                break;
            }
        }

        app.goTo().homePage();
        app.user().deleteFromGroupFinal(selectedUser, selectedGroup);

        Users allUsersAfter = app.db().users(); //еще раз обновили
        for (UserData OneOfUserAfter : allUsersAfter) {
            if (OneOfUserAfter.getId() == selectedUser.getId()) {
                userAfter = OneOfUserAfter;
                break;
            }
        }
        //проверки
        assertEquals(userBefore.getGroups().size(), userAfter.getGroups().size() + 1);
        assertThat(userBefore.getGroups(), equalTo(userAfter.getGroups().withAdded(selectedGroup)));
    }
}
