package dao;

import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import sql_banco__malvader.DBUtil;

import java.sql.*;

public class ContaDAO {
    private Connection connection;

    // Construtor que inicializa a conexão com o banco de dados através do DBUtil
    public ContaDAO() {
        try {
            this.connection = DBUtil.conectar();  // Usando o método conectar() de DBUtil
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar o saldo de uma conta (corrente ou poupança)
    public double consultarSaldo(int clienteId, String tipoConta) {
        double saldo = 0;
        String sql = "";

        // Consulta SQL para buscar saldo dependendo do tipo de conta
        if (tipoConta.equals("corrente")) {
            sql = "SELECT cc.saldo " +
                    "FROM conta_corrente cc" +
                    "JOIN conta c ON cc.id_conta = c.id_conta" +
                    "WHERE c.id_cliente = ?";
        } else if (tipoConta.equals("poupanca")) {
            sql = "SELECT cp.saldo " +
                    "FROM conta_poupanca cp" +
                    "JOIN conta c ON cp.id_conta = c.id_conta" +
                    "WHERE c.id_cliente = ?";
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                saldo = rs.getDouble("saldo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saldo;
    }

    // Método para realizar um depósito em uma conta
    public void depositar(int clienteId, double valor, String tipoConta) {
        String sql = "";

        if (tipoConta.equals("corrente")) {
            sql = "UPDATE conta_corrente cc" +
                    "JOIN conta c ON cc.id_conta = c.id_conta" +
                    "SET cc.saldo = cc.saldo + ?" +
                    "WHERE c.id_cliente = ?";
        } else if (tipoConta.equals("poupanca")) {
            sql = "UPDATE conta_poupanca cp" +
                    "JOIN conta c ON cp.id_conta = c.id_conta" +
                    "SET cp.saldo = cp.saldo + ?" +
                    "WHERE c.id_cliente = ?";
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setInt(2, clienteId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para realizar um saque em uma conta
    public boolean sacar(int clienteId, double valor, String tipoConta) {
        double saldoAtual = consultarSaldo(clienteId, tipoConta);
        if (saldoAtual >= valor) {
            String sql = "";

            if (tipoConta.equals("corrente")) {
                sql = "UPDATE conta_corrente cc" +
                        "JOIN conta c ON cc.id_conta = c.id_conta" +
                        "SET cc.saldo = cc.saldo - ?" +
                        "WHERE c.id_cliente = ?";
            } else if (tipoConta.equals("poupanca")) {
                sql = "UPDATE conta_poupanca cp" +
                        "JOIN conta c ON cp.id_conta = c.id_conta" +
                        "SET cp.saldo = cp.saldo - ?" +
                        "WHERE c.id_cliente = ?";
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setDouble(1, valor);
                stmt.setInt(2, clienteId);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    // Método para consultar o id da conta corrente de um cliente
    public int consultarIdContaCorrente(int clienteId) {
        int idConta = 0;
        String sql = "SELECT cc.id_conta" +
                "FROM conta_corrente cc" +
                "JOIN conta c ON cc.id_conta = c.id_conta" +
                "WHERE c.id_cliente = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idConta = rs.getInt("id_conta");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idConta;
    }

    // Método para consultar o id da conta poupança de um cliente
    public int consultarIdContaPoupanca(int clienteId) {
        int idConta = 0;
        String sql = "SELECT cp.id_conta" +
                "FROM conta_poupanca cp" +
                "JOIN conta c ON cp.id_conta = c.id_conta" +
                "WHERE c.id_cliente = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idConta = rs.getInt("id_conta");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idConta;
    }
}
