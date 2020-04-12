package managers;

import models.Collection;
import models.Item;

import java.sql.*;
import java.util.ArrayList;

public class ItemManager extends DatabaseManager {

    public ItemManager() {
        super();
    }

    public void insert() throws SQLException {
        Date date = new Date(1586532037);
        String query = "insert into vava.element(collection_id_fk, author, genre, brand) VALUES(?,?,?,?)";
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setInt(1,1);
        statement.setString(2,"janko");
        statement.setString(3, "k-pop");
        statement.setString(4, null);

        statement.execute();
    }

    public ArrayList<Item> select(Collection collection) throws SQLException {
        String query = "select * from vava.element where collection_id_fk = " + collection.getId();
        ResultSet resultSet = selectQuery(query);

        ArrayList<Item> result = new ArrayList<Item>();
        while (resultSet.next()) {
            result.add((Item) processRow(resultSet));
        }
        return result;
    }

    @Override
    protected Object processRow(ResultSet rs) throws SQLException {
        return (new Item(rs.getInt(1), rs.getInt(2),
                rs.getDate(3), rs.getInt(4), rs.getString(5),
                rs.getString(6), rs.getString(7), rs.getString(8),
                rs.getString(9), rs.getString(10), rs.getInt(11),
                rs.getInt(12), rs.getString(13)));
    }
}
