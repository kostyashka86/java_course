package ru.java_course.addressbook.tests;

import org.testng.annotations.Test;
import ru.java_course.addressbook.model.UserData;

public class UserModificationTests extends TestBase {

    @Test
    public void testUserModification() {

        app.getNavigationHelper().gotoHomePage();
        app.getGroupHelper().selectGroup();
        app.getUserHelper().initUserModification();
        app.getUserHelper().fillUserForm(new UserData("Sergei", "Ivanovich", "Kozlov", "ivashka", "title", "Qiwi", "Moscow", "+77777777777", "ivanov@mail.ru"));
        app.getUserHelper().submitUserModification();
        app.getUserHelper().returnToHomePage();
    }

}
