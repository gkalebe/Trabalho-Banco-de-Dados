package controller;

import dao.ClienteDAO;
import model.ContaCorrente;
import model.ContaPoupanca;

public class ClienteController {
    private ClienteDAO clienteDAO; // Instância do DAO para acessar dados do banco
    private ContaCorrente contaCorrente;
    private ContaPoupanca contaPoupanca;

    // Construtor que inicializa o ClienteDAO
    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    // Método para consultar o saldo de um cliente
    public double consultarSaldo(int clienteId) {
        // Chama o método no DAO que retorna o saldo do cliente no banco de dados
        return clienteDAO.consultarSaldo(clienteId);
    }

    // Método para realizar um depósito na conta de um cliente
    public void depositar(int clienteId, double valor, int numeroConta) {
        // Chama o método no DAO para incrementar o saldo no banco de dados
        clienteDAO.depositar(clienteId, valor, numeroConta);
    }

    // Método para realizar um saque na conta de um cliente
    public boolean sacar(int clienteId, double valor, String tipoConta) {
        // Chama o método no DAO para decrementar o saldo, se houver saldo suficiente
        return clienteDAO.sacar(clienteId, valor, tipoConta);
    }

    // Método para consultar o extrato de um cliente
    public String consultarExtrato(int clienteId) {
        // Chama o método no DAO que retorna uma string representando o extrato
        return clienteDAO.consultarExtrato(clienteId);
    }

    // Método para consultar o limite de crédito de um cliente
    public double consultarLimite(int clienteId) {
        // Chama o método no DAO que retorna o limite de crédito do cliente
        return clienteDAO.consultarLimite(clienteId);
    }

    // Método para autenticar o cliente com usuário e senha
    public boolean autenticar(String usuario, String senha) {
        // Chama o método no DAO que verifica se o usuário e senha estão corretos no banco
        return clienteDAO.autenticar(usuario, senha);
    }

    // Método para consultar o saldo da conta corrente
    public double consultarSaldoContaCorrente(int clienteId) {
        // Consulta o saldo da conta corrente diretamente no banco
        return clienteDAO.consultarSaldoContaCorrente(clienteId, "corrente");
    }

    // Método para consultar o saldo da conta poupança
    public double consultarSaldoContaPoupanca(int clienteId) {
        // Consulta o saldo da conta poupança diretamente no banco
        return clienteDAO.consultarSaldoContaPoupanca(clienteId, "poupanca");
    }
}
