package ru.java_course.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Зарегистрироваться']"));
    }

    public void login(String user, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), user);
        click(By.cssSelector("input[value='Войти']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Войти']"));
    }

    public void chooseUser(String user, String passwordAdmin) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        //a[contains(@href, 'manage_user_edit_page.php?user_id=26')] //можно по ID ходить по ссылке
        click(By.xpath("//a[contains(text(),'" + user + "')]"));
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
