package ru.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.user().create(new UserData().
                    withFirstname("Konstantin").withLastname("Zhuravlev")
                    .withAddress("Moscow, Red Square, 22"));
        }
    }

    @Test
    public void testUserAddress() {
        app.goTo().homePage();
        UserData user = app.user().all().iterator().next();
        UserData userInfoFromEditForm = app.user().infoFromEditForm(user);
        assertThat(user.getAddress(), equalTo(userInfoFromEditForm.getAddress()));
    }
}

