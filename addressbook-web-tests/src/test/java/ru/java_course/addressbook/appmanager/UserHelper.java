package ru.java_course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.java_course.addressbook.model.GroupData;
import ru.java_course.addressbook.model.UserData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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
        type(By.name("firstname"), userData.getName());
        type(By.name("middlename"), userData.getMiddlename());
        type(By.name("lastname"), userData.getLastname());
        type(By.name("nickname"), userData.getNickname());
        type(By.name("company"), userData.getCompany());
        type(By.name("title"), userData.getTitle());
        type(By.name("address"), userData.getAddress());
        type(By.name("mobile"), userData.getMobile());
        type(By.name("email"), userData.getEmail());

        if (creation){
            new Select(wd.findElement((By.name("new_group")))). selectByVisibleText(userData.getGroup());
        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }
    public void createUser(UserData user) {
        initUserCreation();
        fillUserForm(user,true);
        submitUserCreation();
        returnToHomePage();
    }
    public boolean isThereAUser() {
        return isElementPresent(By.name("selected[]"));
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

    public int getUserCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<UserData> getUserList() {
        List<UserData> users = new ArrayList<UserData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            UserData user = new UserData(id, cells.get(0).getText(), null, cells.get(1).getText(), null,
                    null, null, cells.get(2).getText(), null, null, "[none]");
            users.add(user);
        }
        return users;
    }
}
