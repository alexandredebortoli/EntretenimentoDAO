package conexao;

import java.sql.*;

public class ConexaoDB {
    static final String DB_URL = "jdbc:mysql://localhost:3306/entretenimento";
    static final String USER = "root";
    static final String PASS = "";

    public static Connection createConnectionMySQL() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
