package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    @Override
    protected Object processRow(ResultSet rs) throws SQLException {
        return null;
    }
}
