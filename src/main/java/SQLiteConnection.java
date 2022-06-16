import java.sql.*;
/**
 * Class SQLiteConnection responsible for the connection to the database SQLite
 * and executing queries in the database
 */
public abstract class SQLiteConnection {
    protected Connection conn;

    /**
     * Ensure connection to the database
     *
     * @return  connection
     */
    public Connection getConnection() {
        conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:sqlite.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Return the last ID from the chosen table
     *
     * @param tableName name of the table, which ID needs to be retrieved
     * @return last id from the table
     */
    public int getLatestIdByTableName(String tableName) {
        String sql = "SELECT seq FROM sqlite_sequence WHERE name='" + tableName + "'";
        // connection to the database and executing query
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                return rs.getInt("seq");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return 0;
    }
}