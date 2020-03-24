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

public class AddUserToGroupTests extends TestBase {

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
    public void testAddUserToGroup() {

        Users allUsers = app.db().users();
        Groups allGroups = app.db().groups();

        UserData selectedUser = null;
        GroupData selectedGroup = null;
        UserData userAfter = null;

        for (UserData oneOfUserToAdd : allUsers) {
            Groups groupsOfUserToAdd = oneOfUserToAdd.getGroups();
            if (groupsOfUserToAdd.size() != allGroups.size()) {
                allGroups.removeAll(groupsOfUserToAdd); //находим свободную группу
                selectedGroup = allGroups.iterator().next(); //выбираем первую свободную
                selectedUser = oneOfUserToAdd; //присваеваем
                break; //чтобы не перебирать все
            }
        }
        if (selectedGroup == null) {
            UserData user = new UserData()
                    .withFirstname("new").withLastname("Kostya");
            app.user().create(user);
            Users after = app.db().users();
            user.withId(after.stream().mapToInt((g) -> (g).getId()).max().getAsInt()); //берем контакт с максимальным ID
            selectedUser = user;  //далее selected User не изменяется и является контактом Before.
            selectedGroup = allGroups.iterator().next();
        }

        app.goTo().homePage();
        app.user().allGroupsInUserPage(); //на всякий
        app.user().addInGroupFinal(selectedUser, selectedGroup);

        //проверки
        Users allUsersAfter = app.db().users(); //заново получаем из БД инфу для сравнения.
        for (UserData oneOfUserAfter : allUsersAfter) {
            if (oneOfUserAfter.getId() == selectedUser.getId()) { //ищем контакт с таким же ID
                userAfter = oneOfUserAfter;
                break;
            }
        }
        assertEquals(selectedUser.getGroups().size(), userAfter.getGroups().size() - 1);
        assertThat(selectedUser.getGroups(), equalTo(userAfter.getGroups().without(selectedGroup)));

    }
}
