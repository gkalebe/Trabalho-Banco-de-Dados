package model;

import java.time.LocalDate;

public class Funcionario extends Usuario {
    private String codigoFuncionario;
    private String cargo;
    private String senha;

    // Construtor parametrizado
    public Funcionario(int id, String nome, String cpf, LocalDate dataNascimento, String telefone, Endereco endereco, String codigoFuncionario, String cargo, String senha) {
        super(id, nome, cpf, dataNascimento, telefone, endereco);
        this.codigoFuncionario = codigoFuncionario;
        this.cargo = cargo;
        this.senha = senha;
    }

    // Construtor padrão (sem parâmetros)
    public Funcionario() {
        super(0, "", "", LocalDate.now(), "", null); // Inicializa a classe pai com valores padrão
        this.codigoFuncionario = "";
        this.cargo = "";
        this.senha = "";
    }

    public String getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(String codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Lógica para abrir conta
    public void abrirConta(Conta conta) {
        if (conta != null) {
            System.out.println("Conta aberta com sucesso: Número da conta " + conta.getNumeroConta());
        } else {
            System.out.println("Falha ao abrir a conta. Dados inválidos.");
        }
    }

    // Lógica para encerrar conta
    public void encerrarConta(Conta conta) {
        if (conta != null && conta.consultarSaldo() == 0) {
            System.out.println("Conta encerrada com sucesso: Número da conta " + conta.getNumeroConta());
        } else if (conta != null) {
            System.out.println("Não é possível encerrar a conta. Saldo remanescente: " + conta.consultarSaldo());
        } else {
            System.out.println("Falha ao encerrar a conta. Dados inválidos.");
        }
    }
}
