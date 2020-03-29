package ru.java_course.mantis.appmanager;

import ru.java_course.mantis.model.UserData;
import ru.java_course.mantis.model.Users;

import java.sql.*;

public class DbHelper {
    private final ApplicationManager app;

    public DbHelper(ApplicationManager app) {
        this.app = app;
    }

    public Users userInfoFromDb() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?serverTimezone=UTC&user=root&password=");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT id, username, email from mantis_user_table ");
        Users users = new Users();
        while (rs.next()) {
            users.add(new UserData().withId(rs.getInt("id"))
                    .withName(rs.getString("username")).withEmail(rs.getString("email")));
        }
        rs.close();
        st.close();
        conn.close();
        return users;
    }
}
