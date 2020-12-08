package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Jill W
 * @date 2020/12/07
 */
public class CreateTest {
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
            statement.executeQuery("create table test.jdbc_example(day Date, name String, age UInt8) Engine=Log");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
