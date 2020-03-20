package ru.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;
import ru.java_course.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().users().size() == 0) {
            app.goTo().homePage();
            app.user().create(new UserData().
                    withFirstname("Konstantin").withLastname("Zhuravlev").withGroup("[none]"));
        }
    }

    @Test
    public void testUserModification() {

        Users before = app.db().users();
        UserData modifiedUser = before.iterator().next();
        UserData user = new UserData()
                .withId(modifiedUser.getId()).withFirstname("Gleb").withLastname("Zhuravlev").withEmail("123@321")
                .withEmailTwo("ddf@dsd").withEmailThree("1w1w@dd").withAddress("moscow").withWorkPhone("1234222")
                .withMobilePhone("7555").withHomePhone("333333").withGroup("[none]");
        app.user().modify(user);
        assertThat(app.group().count(), equalTo(before.size()));
        Users after = app.db().users();
        assertThat(after, equalTo(before.without(modifiedUser).withAdded(user.withGroup(null))));
    }

}
