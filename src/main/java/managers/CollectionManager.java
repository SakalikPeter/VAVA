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
        String query = "insert into vava.collection(user_id_fk, name, size) VALUES(?,?,?)";
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setInt(1,collection.getUserId());
        statement.setString(2,collection.getName());
        statement.setInt(3, collection.getSize());

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

    public void delete(Collection collection) throws SQLException {
        String query = "delete from vava.collection where collection_id = " + collection.getId();
        updateQuery(query);
    }

    @Override
    protected Object processRow(ResultSet rs) throws SQLException {
        return (new Collection(rs.getInt(1), rs.getInt(2),
                rs.getString(3), rs.getDate(4), rs.getInt(5)));
    }

}
