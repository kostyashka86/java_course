package ru.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.GroupData;
import ru.java_course.addressbook.model.UserData;

public class AddUserToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().users().size() != 0) {
            app.goTo().homePage();
            app.user().deleteAll();
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (app.db().users().size() == 0) {
            app.goTo().homePage();
            app.user().create(new UserData().
                    withFirstname("Konstantin").withLastname("Zhuravlev"));
        }
    }

    @Test
    public void testAddUserToGroup(UserData user) {
        //Set<GroupData> before = user.getGroups();
        app.user().addUserToGroup(user);
        //Set<GroupData> after = user.getGroups();
        //assertThat(after, equalTo(before));
    }


}
