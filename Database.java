import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:employees.db";

    public static Connection connect() throws Exception {
        Class.forName("org.sqlite.JDBC"); // 🔥 THIS LINE IS CRITICAL
        return DriverManager.getConnection(URL);
    }

    public static void createTable() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS employees (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT, " +
                    "department TEXT, " +
                    "salary REAL)";
            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace(); // show real error
        }
    }
}