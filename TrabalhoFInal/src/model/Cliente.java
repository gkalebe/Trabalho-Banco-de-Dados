//classe que herda de usuario e implementa de acordo com cliente

package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private String senha;
    private double saldo;
    private double limite;
    private List<String> extrato; 
    private int id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private Endereco endereco;

    public Cliente(int id, String nome, String cpf, LocalDate dataNascimento, String telefone, Endereco endereco, String senha, double limite) {
        super(id, nome, cpf, dataNascimento, telefone, endereco);//chama o construtor da classe Usuario
        this.senha = senha;
        this.saldo = 0.0;//inicializa o saldo com zero
        this.limite = limite;
        this.extrato = new ArrayList<>();//inicializa a lista de extrato
        this.id = id; // inicializa o id
        this.nome = nome; // inicializa o nome
        this.cpf = cpf; // inicializa o CPF
        this.dataNascimento = dataNascimento; // inicializa a data de nascimento
        this.telefone = telefone; // inicializa o telefone
        this.endereco = endereco; // inicializa o endereço

    }

    // Métodos específicos como consultar, depositar e sacar
    // Lógica para consultar saldo
    public double consultarSaldo() {
        
        return saldo;//retorna o saldo atual
    }

    // Lógica para depósito
    public void depositar(double valor) {
        if(valor > 0){//verifica se o valor e positivo
            saldo += valor;//adiciona o valor ao saldo
            extrato.add("Déposito: +" +valor);//registra o deposito no extrato
            System.out.println("Depósito de " + valor + " realizado com sucesso.");//confirma o deposito
}
        else{
            System.out.println("Valor de depósito inválido.");//exibir se o valor for invalido
        }
        
    }

    // Lógica para saque
    public boolean sacar(double valor) {
       //verifica se o valor e positivo e se o saldo com limite permite o saque
        if (valor > 0 && valor <= (saldo + limite)) {
            saldo -= valor;//valor do saldo atualizado
            extrato.add("Saque: -" + valor);//registra o saque no extrato
            System.out.println("Saque de " + valor + " realizado com sucesso.");//confirma o saque
            return true;
        } else {
            System.out.println("Saldo insuficiente ou valor de saque inválido.");//exibir mensagem de erro
            return false;//false para indicar falha
        }
    }

     // Lógica para consultar extrato
    public String consultarExtrato() {
       
        StringBuilder sb = new StringBuilder();//StringBuilder para formatar o texto
        sb.append("Extrato:\n");
        for (String entrada : extrato) {//percorre todas as entradas do extrato
            sb.append(entrada).append("\n");// adiciona cada entrada ao StringBuilder
        }
        return sb.toString();//retorna o extrato
    }

    // Lógica para consultar limite
    public double consultarLimite() {
        
        return this.limite;//retorna o valor do limite
    }

    // getters e setters para os atributos 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
} 
