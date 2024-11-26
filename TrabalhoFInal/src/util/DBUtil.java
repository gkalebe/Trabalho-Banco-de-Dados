package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil{
    private static final String URL = "jdbc:mysql://localhost:3306/bancomalvader";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void desconectar(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
