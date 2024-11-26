package controller;

import dao.FuncionarioDAO;
import model.Funcionario;
import java.util.Random;

public class FuncionarioController {

    private FuncionarioDAO funcionarioDAO;

    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public boolean autenticarFuncionario(String codigoFuncionario, String senha) {
        return funcionarioDAO.autenticar(codigoFuncionario, senha);
    }

    public String cadastrarFuncionario(String nome, String cpf, String dataNascimento, String telefone, String senha, String cargo) {
        if (nome == null || nome.isEmpty() || cpf == null || cpf.isEmpty() || senha == null || senha.isEmpty()) {
            return "Dados obrigatórios ausentes (nome, CPF ou senha).";
        }
        int idFuncionario = funcionarioDAO.cadastrarFuncionario(nome, cpf, dataNascimento, telefone, senha, cargo);
        if (idFuncionario > 0) {
            String codigoFuncionario = gerarCodigoFuncionario();
            boolean sucesso = funcionarioDAO.atualizarCodigoFuncionario(idFuncionario, codigoFuncionario);
            return sucesso ? "Funcionário cadastrado com sucesso. Código gerado: " + codigoFuncionario
                           : "Funcionário cadastrado, mas erro ao gerar o código.";
        }
        return "Erro ao cadastrar o funcionário.";
    }

    public String consultarFuncionario(String codigoFuncionario) {
        Funcionario funcionario = funcionarioDAO.consultarFuncionarioPorCodigo(codigoFuncionario);
        if (funcionario != null) {
            return String.format("Nome: %s\nCPF: %s\nData de Nascimento: %s\nTelefone: %s\nCódigo Funcionário: %s\nCargo: %s",
                    funcionario.getNome(), funcionario.getCpf(), funcionario.getDataNascimento(),
                    funcionario.getTelefone(), funcionario.getCodigoFuncionario(), funcionario.getCargo());
        }
        return "Funcionário não encontrado.";
    }

    public String atualizarCargoFuncionario(String codigoFuncionario, String novoCargo) {
        if (novoCargo == null || novoCargo.isEmpty()) {
            return "Cargo inválido.";
        }
        Funcionario funcionario = funcionarioDAO.consultarFuncionarioPorCodigo(codigoFuncionario);
        if (funcionario == null) {
            return "Funcionário não encontrado com o código informado.";
        }
        boolean sucesso = funcionarioDAO.atualizarCargo(funcionario.getId(), novoCargo);
        return sucesso ? "Cargo atualizado com sucesso." : "Erro ao atualizar o cargo.";
    }

    public String excluirFuncionario(int idFuncionario) {
        boolean sucesso = funcionarioDAO.excluirFuncionario(idFuncionario);
        return sucesso ? "Funcionário excluído com sucesso." : "Erro ao excluir o funcionário.";
    }

    private String gerarCodigoFuncionario() {
        Random random = new Random();
        int codigo = 1000000 + random.nextInt(9000000);
        return String.valueOf(codigo);
    }
}
