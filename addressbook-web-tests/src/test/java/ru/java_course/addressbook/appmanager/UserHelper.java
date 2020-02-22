package ru.java_course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.java_course.addressbook.model.UserData;

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

    public void initUserModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }
}
