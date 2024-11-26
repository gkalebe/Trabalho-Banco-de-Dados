package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sql_banco__malvader.DBUtil;

public class ContaController {

    // Método para cadastrar uma nova conta
    public String cadastrarConta(String numeroConta, String agencia, String tipoConta, int idCliente,
                                 Double saldo, Double limite, Double taxaRendimento, String dataVencimento) {
        String mensagem = "";

        try (Connection conn = DBUtil.conectar()) { // Usando try-with-resources para garantir o fechamento da conexão
            conn.setAutoCommit(false); // Inicia transação

            // Cadastrar conta principal
            String sqlConta = "INSERT INTO conta (numero_conta, agencia, saldo, tipo_conta, id_cliente) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmtConta = conn.prepareStatement(sqlConta)) {
                stmtConta.setString(1, numeroConta);
                stmtConta.setString(2, agencia);
                stmtConta.setDouble(3, saldo);
                stmtConta.setString(4, tipoConta);
                stmtConta.setInt(5, idCliente);
                stmtConta.executeUpdate();
            }

            // Cadastrar conta corrente ou conta poupança dependendo do tipo
            if (tipoConta.equals("CORRENTE")) {
                // Usar limite para conta corrente
                String sqlContaCorrente = "INSERT INTO conta_corrente (limite, data_vencimento, id_conta) "
                        + "SELECT ?, ?, id_conta FROM conta WHERE numero_conta = ?";

                try (PreparedStatement stmtContaCorrente = conn.prepareStatement(sqlContaCorrente)) {
                    stmtContaCorrente.setDouble(1, limite);  // Limite para conta corrente
                    stmtContaCorrente.setString(2, dataVencimento);
                    stmtContaCorrente.setString(3, numeroConta);
                    stmtContaCorrente.executeUpdate();
                }
            } else if (tipoConta.equals("POUPANCA")) {
                // Usar taxa de rendimento para conta poupança
                String sqlContaPoupanca = "INSERT INTO conta_poupanca (taxa_rendimento, id_conta) "
                        + "SELECT ?, id_conta FROM conta WHERE numero_conta = ?";

                try (PreparedStatement stmtContaPoupanca = conn.prepareStatement(sqlContaPoupanca)) {
                    stmtContaPoupanca.setDouble(1, taxaRendimento);  // Taxa de rendimento para conta poupança
                    stmtContaPoupanca.setString(2, numeroConta);
                    stmtContaPoupanca.executeUpdate();
                }
            }

            conn.commit(); // Confirma as alterações
            mensagem = "Conta cadastrada com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            mensagem = "Erro ao cadastrar conta: " + e.getMessage();
        }

        return mensagem;
    }

    // Método para consultar um cliente pelo CPF
    public String consultarCliente(String cpfCliente) {
        String mensagem = "";
        String sql = "SELECT * FROM cliente WHERE cpf = ?";

        try (Connection conn = DBUtil.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpfCliente);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");
                String telefone = resultSet.getString("telefone");
                String dataNascimento = resultSet.getString("data_nascimento");

                mensagem = "Cliente encontrado: " + nome + " (CPF: " + cpf + ", Telefone: " + telefone + ", Data de Nascimento: " + dataNascimento + ")";
            } else {
                mensagem = "Cliente não encontrado!";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mensagem = "Erro ao consultar cliente: " + e.getMessage();
        }

        return mensagem;
    }

    public String excluirContaPorClienteOuNumero(Integer idCliente, String numeroConta) {
        String mensagem = "";
        
        // Verifica se nenhum parâmetro foi fornecido
        if (idCliente == null && (numeroConta == null || numeroConta.isEmpty())) {
            return "Você deve fornecer pelo menos um parâmetro: idCliente ou numeroConta.";
        }
    
        try (Connection conn = DBUtil.conectar()) {
            conn.setAutoCommit(false); // Inicia transação
    
            // Se o numeroConta não for fornecido, busca o número da conta pelo idCliente
            if (numeroConta == null || numeroConta.isEmpty()) {
                String sqlBuscarNumeroConta = "SELECT numero_conta FROM conta WHERE id_cliente = ?";
                try (PreparedStatement stmtBuscarNumeroConta = conn.prepareStatement(sqlBuscarNumeroConta)) {
                    stmtBuscarNumeroConta.setInt(1, idCliente);
                    ResultSet resultSet = stmtBuscarNumeroConta.executeQuery();
    
                    if (resultSet.next()) {
                        numeroConta = resultSet.getString("numero_conta");
                    } else {
                        mensagem = "Nenhuma conta encontrada para o cliente com id: " + idCliente;
                        return mensagem;
                    }
                }
            }
    
            // Excluir conta corrente se existir
            String sqlExcluirContaCorrente = "DELETE FROM conta_corrente WHERE id_conta = (SELECT id_conta FROM conta WHERE numero_conta = ?)";
            try (PreparedStatement stmtExcluirContaCorrente = conn.prepareStatement(sqlExcluirContaCorrente)) {
                stmtExcluirContaCorrente.setString(1, numeroConta);
                stmtExcluirContaCorrente.executeUpdate();
            }
    
            // Excluir conta poupança se existir
            String sqlExcluirContaPoupanca = "DELETE FROM conta_poupanca WHERE id_conta = (SELECT id_conta FROM conta WHERE numero_conta = ?)";
            try (PreparedStatement stmtExcluirContaPoupanca = conn.prepareStatement(sqlExcluirContaPoupanca)) {
                stmtExcluirContaPoupanca.setString(1, numeroConta);
                stmtExcluirContaPoupanca.executeUpdate();
            }
    
            // Excluir a conta principal
            String sqlExcluirConta = "DELETE FROM conta WHERE numero_conta = ?";
            try (PreparedStatement stmtExcluirConta = conn.prepareStatement(sqlExcluirConta)) {
                stmtExcluirConta.setString(1, numeroConta);
                stmtExcluirConta.executeUpdate();
            }
    
            conn.commit(); // Confirma as alterações
            mensagem = "Conta excluída com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            mensagem = "Erro ao excluir conta: " + e.getMessage();
        }
    
        return mensagem;
    }
    
    
}
