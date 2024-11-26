package model;

import java.time.LocalDate;

public class ContaCorrente extends Conta {
    private double limite;
    private LocalDate dataVencimento;

    public ContaCorrente(String numeroConta, double saldoInicial, String agencia, Cliente cliente, double limite, LocalDate dataVencimento) {
        super(numeroConta, saldoInicial, agencia, cliente);
        this.limite = limite;
        this.dataVencimento = dataVencimento;
    }

    public double consultarLimite() {
        return limite;
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            setSaldo(consultarSaldo() + valor);
        }
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && consultarSaldo() + limite >= valor) {
            setSaldo(consultarSaldo() - valor);
            return true;
        }
        return false;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
