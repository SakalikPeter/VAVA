package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager extends DatabaseManager{

    public UserManager() {
        super();
    }


    public void insert(String queryString) throws SQLException {
        String query = "insert into vava.user(user_name, password) VALUES(?,?)";
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1,"matko");
        statement.setString(2,"heslo");

        statement.execute();

    }
}
