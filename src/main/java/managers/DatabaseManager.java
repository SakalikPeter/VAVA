package managers;

import java.sql.*;

public abstract class DatabaseManager {
    private Connection connection;

    public DatabaseManager() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vava", "postgres", "mamut9191");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    protected ResultSet selectQuery(String query) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    protected void updateQuery(String query) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    protected abstract Object processRow(ResultSet rs) throws SQLException;
}
