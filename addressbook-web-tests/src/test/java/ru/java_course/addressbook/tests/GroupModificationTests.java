package ru.java_course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_course.addressbook.model.GroupData;
import ru.java_course.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification() {

        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().
                withId(modifiedGroup.getId()).withName("test1").withFooter("test2").withHeader("test3");
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

    }
}
