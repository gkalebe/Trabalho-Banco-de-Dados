package sql_banco__malvader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    // URL, usuário e senha para conectar no banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/bancomalvader";
    private static final String USER = "usuario";
    private static final String PASSWORD = "senha123";

    // Método para conectar ao banco
    public static Connection conectar() throws SQLException {
        try {
            // Carrega o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Retorna a conexão com o banco
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC não encontrado.", e);
        }
    }

    // Método para desconectar do banco
    public static void desconectar(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    // Exemplo de como utilizar a conexão com try-with-resources
    public static void main(String[] args) {
        // Usando try-with-resources para garantir que a conexão seja fechada
        try (Connection conn = conectar()) {
            // Verifica se a conexão foi estabelecida com sucesso
            if (conn != null) {
                System.out.println("Conexão bem-sucedida ao banco de dados!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
