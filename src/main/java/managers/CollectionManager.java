package managers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionManager extends DatabaseManager{

    public CollectionManager() {
        super();
    }

    public void insert() throws SQLException {
        Date date = new Date(1586532037);
        String query = "insert into vava.collection(user_id_fk, name, creation_date, size) VALUES(?,?,?,?)";
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setInt(1,1);
        statement.setString(2,"nalepky");
        statement.setDate(3, date);
        statement.setInt(4, 1);

        statement.execute();
    }

    @Override
    protected Object processRow(ResultSet rs) throws SQLException {
        return null;
    }
}
