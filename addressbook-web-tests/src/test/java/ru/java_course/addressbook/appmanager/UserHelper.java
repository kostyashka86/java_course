package ru.java_course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.java_course.addressbook.model.UserData;
import ru.java_course.addressbook.model.Users;

import java.util.List;

import static java.lang.Thread.sleep;

public class UserHelper extends HelperBase{

    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitUserCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillUserForm(UserData userData, boolean creation) {
        type(By.name("firstname"), userData.getFirstname());
        type(By.name("middlename"), userData.getMiddlename());
        type(By.name("lastname"), userData.getLastname());
        type(By.name("nickname"), userData.getNickname());
        type(By.name("company"), userData.getCompany());
        type(By.name("title"), userData.getTitle());
        type(By.name("address"), userData.getAddress());
        type(By.name("mobile"), userData.getMobilePhone());
        type(By.name("home"), userData.getHomePhone());
        type(By.name("work"), userData.getWorkPhone());
        type(By.name("email"), userData.getEmail());

        if (creation) {
            new Select(wd.findElement((By.name("new_group")))).selectByVisibleText(userData.getGroup());
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

    public boolean isThereAUser() {
        return isElementPresent(By.name("selected[]"));
    }

    public void selectUserById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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
            UserData user = new UserData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones);
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
        wd.navigate().back();
        return new UserData().withId(user.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }
}
