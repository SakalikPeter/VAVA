package managers;

import models.Collection;
import models.User;

import java.sql.*;
import java.util.ArrayList;

public class CollectionManager extends DatabaseManager{

    public CollectionManager() {
        super();
    }

    public void insert(Collection collection) throws SQLException {
        Date date = new Date(1586532037);
        String query = "insert into vava.collection(user_id_fk, name, creation_date, size) VALUES(?,?,?,?)";
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setInt(1,1);
        statement.setString(2,"auticka");
        statement.setDate(3, date);
        statement.setInt(4, 1);

        statement.execute();
    }

    public ArrayList<Collection> select(int user_id) throws SQLException {
        String query = "select * from vava.collection where user_id_fk = " + user_id;
        ResultSet resultSet = selectQuery(query);

        ArrayList<Collection> result = new ArrayList<Collection>();
        while (resultSet.next()) {
            result.add((Collection) processRow(resultSet));
        }
        return result;
    }

    @Override
    protected Object processRow(ResultSet rs) throws SQLException {
        return (new Collection(rs.getInt(1), rs.getInt(2),
                rs.getString(3), rs.getDate(4), rs.getInt(5)));
    }
}
