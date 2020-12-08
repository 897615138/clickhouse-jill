package test;

import java.sql.*;
import java.util.Properties;

/**
 * @author Jill W
 * @date 2020/12/07
 */
public class InsertTest {
    private static final String URL;
    private static final Properties PROPS = new Properties();

    static {
        URL = "jdbc:clickhouse://172.16.203.2:8123/";
        PROPS.setProperty("user", "default");
        PROPS.setProperty("password", "");
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, PROPS);
             Statement statement = connection.createStatement()) {
            Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
            try (PreparedStatement stmt = connection.prepareStatement("insert into test.jdbc_example values(?, ?, ?)")) {
                // insert 10 records
                for (int i = 0; i < 10; i++) {
                    stmt.setDate(1, new Date(System.currentTimeMillis()));
                    stmt.setString(2, "panda_" + (i + 1));
                    stmt.setInt(3, 18 - i);
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
