package managers;

import models.Collection;
import models.Item;

import java.sql.*;
import java.util.ArrayList;

public class ItemManager extends DatabaseManager {

    public ItemManager() {
        super();
    }

    public void insert(Item item) throws SQLException {
        String query = "insert into vava.item(collection_id_fk, name, acquirement_date, production_year, author, " +
                "genre, brand, acquirement_location, dimensions, origin_country, price, value, note) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setInt(1,item.getCollectionId());
        statement.setString(2,item.getName());
        statement.setDate(3, item.getAcquirementDate());
        statement.setInt(4, item.getProductionYear());
        statement.setString(5,item.getAuthor());
        statement.setString(6,item.getGenre());
        statement.setString(7,item.getBrand());
        statement.setString(8,item.getAcquirementLocation());
        statement.setString(9,item.getDimensions());
        statement.setString(10,item.getOriginCountry());
        statement.setInt(11, item.getPrice());
        statement.setInt(12, item.getValue());
        statement.setString(13,item.getNote());

        statement.execute();
    }

    public ArrayList<Item> select(Collection collection) throws SQLException {
        String query = "select * from vava.item where collection_id_fk = " + collection.getId();
        ResultSet resultSet = selectQuery(query);

        ArrayList<Item> result = new ArrayList<Item>();
        while (resultSet.next()) {
            result.add((Item) processRow(resultSet));
        }
        return result;
    }

    @Override
    protected Object processRow(ResultSet rs) throws SQLException {
        return (new Item(rs.getInt(1), rs.getInt(2), rs.getString(3),
                rs.getDate(4), rs.getInt(5), rs.getString(6),
                rs.getString(7), rs.getString(8), rs.getString(9),
                rs.getString(10), rs.getString(11), rs.getInt(12),
                rs.getInt(13), rs.getString(14)));
    }
}
