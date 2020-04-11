package managers;

import java.sql.*;
import java.util.ArrayList;

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

    public void select() throws SQLException {
        String query = "select * from vava.collection where user_id_fk = 1";
        ResultSet resultSet = selectQuery(query);

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }
    }

    @Override
    protected Object processRow(ResultSet rs) throws SQLException {
        return null;
    }
}
