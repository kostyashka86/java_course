package ru.java_course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.java_course.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {

        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test4", null, null);
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
        app.logout();
    }

}
