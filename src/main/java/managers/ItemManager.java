package managers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        statement.setString(4, "pepino");

        statement.execute();
    }

    @Override
    protected Object processRow(ResultSet rs) throws SQLException {
        return null;
    }
}
