package ru.java_course.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.GroupData;
import ru.java_course.addressbook.model.UserData;
import ru.java_course.addressbook.model.Users;

import static org.hamcrest.MatcherAssert.assertThat;

public class AddUserToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {

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
    public void testAddUserToGroup() {
        Users before = app.db().users();
        UserData addingUserToGroup = before.iterator().next();
        app.user().addUserToGroup(addingUserToGroup);
        Users after = app.db().users();
        assertThat(after.size(), CoreMatchers.equalTo(before.size()));
    }


}
