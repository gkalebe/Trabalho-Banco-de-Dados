package dao;



import sql_banco__malvader.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

    // Método para autenticar o cliente pelo usuário e senha
    public boolean autenticar(String usuario, String senha) {
        String sql = "SELECT id_cliente FROM cliente WHERE cpf = ? AND senha = ?";
        try (Connection connection = DBUtil.conectar();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario);  // Define o nome de usuário na consulta
            ps.setString(2, senha);   // Define a senha na consulta
            ResultSet rs = ps.executeQuery();

            // Se houver um cliente com o usuário e senha fornecidos, retorna true
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Caso haja erro, retorna false
        }
    }

    // Método para consultar o saldo de um cliente
    public double consultarSaldo(int clienteId) {
        double saldo = 0.0;
        String sql = "SELECT saldo FROM conta WHERE id_cliente = ?";

        try (Connection connection = DBUtil.conectar();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, clienteId);  // Define o ID do cliente na consulta
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                saldo = rs.getDouble("saldo");  // Obtém o saldo do cliente
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saldo;
    }

    // Método para depositar um valor na conta do cliente
    public void depositar(int clienteId, double valor, int numeroConta) {
        String sql = "UPDATE conta SET saldo = saldo + ? WHERE numero_conta = ?";

        try (Connection connection = DBUtil.conectar();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            if (valor <= 0) {
                System.out.println("Valor de depósito inválido.");
                return;
            }

            ps.setDouble(1, valor);
            ps.setInt(2, numeroConta);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Depósito realizado com sucesso!");
            } else {
                System.out.println("Cliente ou tipo de conta não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao realizar depósito: " + e.getMessage());
        }
    }

    // Método para sacar um valor da conta do cliente
    public boolean sacar(int clienteId, double valor, String tipoConta) {
        String sql = "UPDATE conta SET saldo = saldo - ? WHERE id_cliente = ? AND tipo_conta = ? AND saldo >= ?";

        try (Connection connection = DBUtil.conectar();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, valor);
            ps.setInt(2, clienteId);
            ps.setString(3, tipoConta);  // Especifica o tipo da conta
            ps.setDouble(4, valor);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;  // True se o saque foi realizado, false caso contrário
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para consultar o extrato do cliente
    public String consultarExtrato(int clienteId) {
        double saldo = consultarSaldo(clienteId);
        return "Extrato do Cliente " + clienteId + ": Saldo Atual = R$ " + saldo;
    }

    // Método para consultar o limite de crédito do cliente
    public double consultarLimite(int clienteId) {
        return 1000.0; // Exemplo estático para o limite de crédito
    }

    // Método para consultar o saldo da conta corrente de um cliente
    public double consultarSaldoContaCorrente(int clienteId, String tipoConta) {
        double saldo = 0.0;
        String sql = "SELECT saldo FROM conta WHERE id_cliente = ? AND tipo_conta = ?";

        try (Connection connection = DBUtil.conectar();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            ps.setString(2, tipoConta);  // Filtra por tipo de conta (corrente ou poupança)
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                saldo = rs.getDouble("saldo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saldo;
    }

    // Método para consultar o saldo da conta poupança de um cliente
    public double consultarSaldoContaPoupanca(int clienteId, String tipoConta) {
        return consultarSaldoContaCorrente(clienteId, tipoConta);  // Reutiliza o método genérico
    }
}