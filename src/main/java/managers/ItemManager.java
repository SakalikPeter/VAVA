package managers;

import java.sql.*;

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

    public void select() throws SQLException {
        String query = "select * from vava.element where collection_id_fk = 2";
        ResultSet resultSet = selectQuery(query);
    }

    @Override
    protected Object processRow(ResultSet rs) throws SQLException {
        return null;
    }
}
