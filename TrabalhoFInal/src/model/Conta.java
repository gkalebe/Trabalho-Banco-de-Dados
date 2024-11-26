package model;

public abstract class Conta {
    private String numeroConta;
    private double saldo;
    private String agencia;
    private Cliente cliente;

    public Conta(String numeroConta, double saldoInicial , String agencia, Cliente cliente) {
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial;
        this.agencia = agencia;
        this.cliente = cliente;
    }


    public double consultarSaldo() {
        return saldo;
    }

    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public boolean transferir(Conta contaDestino, double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            contaDestino.depositar(valor);
            return true;
        }
        return false;
    }

    public boolean solicitarEmprestimo(double valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        }
        return false;
    }


    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }
}
