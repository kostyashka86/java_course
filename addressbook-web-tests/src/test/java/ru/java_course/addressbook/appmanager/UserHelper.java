package ru.java_course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.java_course.addressbook.model.GroupData;
import ru.java_course.addressbook.model.UserData;
import ru.java_course.addressbook.model.Users;

import java.util.List;

import static java.lang.Thread.sleep;

public class UserHelper extends HelperBase {

    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void returnToHome() {
        click(By.linkText("home"));
    }

    public void submitUserCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillUserForm(UserData userData, boolean creation) {
        type(By.name("firstname"), userData.getFirstname());
        type(By.name("lastname"), userData.getLastname());
        type(By.name("address"), userData.getAddress());
        type(By.name("mobile"), userData.getMobilePhone());
        type(By.name("home"), userData.getHomePhone());
        type(By.name("work"), userData.getWorkPhone());
        type(By.name("email"), userData.getEmail());
        type(By.name("email2"), userData.getEmailTwo());
        type(By.name("email3"), userData.getEmailThree());

        if (creation) {
            if (userData.getGroups().size() > 0) {
                Assert.assertTrue(userData.getGroups().size() == 1);
                new Select(wd.findElement((By.name("new_group")))).selectByVisibleText(userData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void create(UserData user) {
        initUserCreation();
        fillUserForm(user, true);
        submitUserCreation();
        userCache = null;
        returnToHomePage();
    }

    public void modify(UserData user) {
        initUserModificationById(user.getId());
        fillUserForm(user, false);
        submitUserModification();
        userCache = null;
        returnToHomePage();
    }

    public void delete(UserData user) throws InterruptedException {
        selectUserById(user.getId());
        deleteSelectedUsers();
        userCache = null;
        sleep(4000);
    }

    public void selectUserGroupById(int id) {
        wd.findElement(By.xpath(String.format("//form[@id='right']//select[@name='group']//option[@value='%s']", id))).click();
    }

    public boolean isThereAUser() {
        return isElementPresent(By.name("selected[]"));
    }

    public void selectUserById(int id) {
        wd.findElement(By.cssSelector(String.format("input[value ='%s']", id))).click();
    }

    private void initUserModificationById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }

    public void initUserCreation() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedUsers() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void submitUserModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void initUserModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Users userCache = null;

    public Users all() {
        if (userCache != null) {
            return new Users(userCache);
        }
        userCache = new Users();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            UserData user = new UserData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllEmails(allEmails).withAllPhones(allPhones).withAddress(address);
            userCache.add(user);
        }
        return new Users(userCache);
    }

    public UserData infoFromEditForm(UserData user) {
        initUserModificationById(user.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String emailTwo = wd.findElement(By.name("email2")).getAttribute("value");
        String emailThree = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new UserData().withId(user.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail(email).withEmailTwo(emailTwo).withEmailThree(emailThree)
                .withAddress(address);
    }

    public void selectUserAll() {
        wd.findElement(By.xpath(String.format("//form[@id='right']//select[@name='group']//option[@value='']"))).click();
        userCache = null;
    }

    public void addToGroup(int groupId, int userId) {
        selectGroupById(groupId);
        initAddUserToGroup();
        userCache = null;
        returnToSelectedGropePage(groupId);
    }

    public void selectGroupById(int id) {
        wd.findElement(By.xpath(String.format("//div[@class='right']//select[@name='to_group']//option[@value='%s']", id))).click();
    }

    public void initAddUserToGroup() {
        click(By.name("add"));
    }

    public void allGroupsInUserPage() {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
    }

    public void addInGroupFinal(UserData selectedUser, GroupData selectedGroup) {
        selectUserById(selectedUser.getId());
        addUserToGroupAfterSelect(selectedGroup);
    }

    public void addUserToGroupAfterSelect(GroupData selectedGroup) {
        String id = String.valueOf(selectedGroup.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByValue(id);
        click(By.name("add"));
    }

    public void deleteFromGroupFinal(UserData user, GroupData group) {
        String id = String.valueOf(group.getId());
        new Select(wd.findElement(By.name("group"))).selectByValue(id);
        selectUserById(user.getId());
        deleteUserFromGroupAfterSelect();
    }

    public void returnToSelectedGropePage(int id) {
        click(By.xpath(String.format("//div[@class='msgbox']//i//a[@href='./?group=%s']", id)));
    }

    public void deleteUserFromGroupAfterSelect() {
        click(By.name("remove"));
    }
}
