package managers;

import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager extends DatabaseManager{

    public UserManager() {
        super();
    }

    public void insert() throws SQLException {
        String query = "insert into vava.user(user_name, password) VALUES(?,?)";
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1,"karol");
        statement.setString(2,"heslo");

        statement.execute();
    }

    public User select(String name, String password) throws SQLException {
        String query = "select * from vava.user where user_name = '" + name + "' AND password = '" + password + "'";

        ResultSet resultSet = selectQuery(query);

        User user = null;
        if(resultSet.next()) {
            user = (User) processRow(resultSet);
        }

        return user;
    }

    @Override
    protected Object processRow(ResultSet rs) throws SQLException {
        return (new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
    }
}
