//classe base para funcionario e cliente

package model;

import java.time.LocalDate;

public abstract class Usuario {
    // classe Usuario
    private int id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private Endereco endereco;

    // construtor da classe Usuario
    public Usuario(int id, String nome, String cpf, LocalDate dataNascimento, String telefone, Endereco endereco) {
        this.id = id; // inicializa o id
        this.nome = nome; // inicializa o nome
        this.cpf = cpf; // inicializa o CPF
        this.dataNascimento = dataNascimento; // inicializa a data de nascimento
        this.telefone = telefone; // inicializa o telefone
        this.endereco = endereco; // inicializa o endereço
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