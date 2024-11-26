package dao;

import sql_banco__malvader.DBUtil;
import java.sql.*;
import java.time.LocalDate;
import model.Funcionario;

public class FuncionarioDAO {

    // Método para autenticar o funcionário pelo código e senha
    public boolean autenticar(String codigoFuncionario, String senha) {
        String sql = "SELECT f.id_funcionario " +
                "FROM funcionario f " +
                "INNER JOIN usuario u ON f.id_usuario = u.id_usuario " +
                "WHERE f.codigo_funcionario = ? AND u.senha = ?";

        try (Connection connection = DBUtil.conectar();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, codigoFuncionario); // Define o código do funcionário
            ps.setString(2, senha); // Define a senha do funcionário

            ResultSet rs = ps.executeQuery();
            return rs.next(); // Retorna true se o funcionário for autenticado
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de erro
        }
    }

    // Método para cadastrar um novo funcionário
    public int cadastrarFuncionario(String nome, String cpf, String dataNascimento, String telefone,
                                    String senha, String cargo) {
        String usuarioSql = "INSERT INTO usuario (nome, cpf, data_nascimento, telefone, tipo_usuario, senha) " +
                "VALUES (?, ?, ?, ?, 'FUNCIONARIO', ?)";

        String funcionarioSql = "INSERT INTO funcionario (codigo_funcionario, cargo, id_usuario) " +
                "VALUES (?, ?, ?)";

        try (Connection connection = DBUtil.conectar()) {
            connection.setAutoCommit(false); // Inicia transação

            try (PreparedStatement usuarioPs = connection.prepareStatement(usuarioSql, Statement.RETURN_GENERATED_KEYS)) {
                usuarioPs.setString(1, nome);
                usuarioPs.setString(2, cpf);
                usuarioPs.setDate(3, Date.valueOf(dataNascimento)); // Converte String para Date
                usuarioPs.setString(4, telefone);
                usuarioPs.setString(5, senha);

                usuarioPs.executeUpdate();

                ResultSet rs = usuarioPs.getGeneratedKeys();
                if (rs.next()) {
                    int usuarioId = rs.getInt(1); // Obtém o ID do usuário recém-inserido

                    try (PreparedStatement funcionarioPs = connection.prepareStatement(funcionarioSql, Statement.RETURN_GENERATED_KEYS)) {
                        funcionarioPs.setString(1, ""); // Deixa o código em branco por enquanto
                        funcionarioPs.setString(2, cargo);
                        funcionarioPs.setInt(3, usuarioId);

                        funcionarioPs.executeUpdate();

                        // Obtém o ID do funcionário recém-inserido
                        rs = funcionarioPs.getGeneratedKeys();
                        if (rs.next()) {
                            int funcionarioId = rs.getInt(1); // Retorna o ID do funcionário recém-criado
                            connection.commit(); // Confirma a transação
                            return funcionarioId; // Retorna o ID do funcionário
                        }
                    }
                }
            } catch (SQLException e) {
                connection.rollback(); // Reverte a transação em caso de erro
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorna 0 se o cadastro falhar
    }

    // Método para consultar informações do funcionário pelo ID
    public String consultarFuncionario(int idFuncionario) {
        String sql = "SELECT u.nome, u.cpf, u.data_nascimento, u.telefone, f.codigo_funcionario, f.cargo " +
                "FROM funcionario f " +
                "INNER JOIN usuario u ON f.id_usuario = u.id_usuario " +
                "WHERE f.id_funcionario = ?";

        try (Connection connection = DBUtil.conectar();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idFuncionario);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                Date dataNascimento = rs.getDate("data_nascimento");
                String telefone = rs.getString("telefone");
                String codigoFuncionario = rs.getString("codigo_funcionario");
                String cargo = rs.getString("cargo");

                return String.format("Nome: %s\nCPF: %s\nData de Nascimento: %s\nTelefone: %s\nCódigo Funcionário: %s\nCargo: %s",
                        nome, cpf, dataNascimento, telefone, codigoFuncionario, cargo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Funcionário não encontrado.";
    }

    // Método para consultar informações do funcionário pelo código
    public Funcionario consultarFuncionarioPorCodigo(String codigoFuncionario) {
        String sql = "SELECT u.nome, u.cpf, u.data_nascimento, u.telefone, f.codigo_funcionario, f.cargo " +
                "FROM funcionario f " +
                "INNER JOIN usuario u ON f.id_usuario = u.id_usuario " +
                "WHERE f.codigo_funcionario = ?";

        try (Connection connection = DBUtil.conectar();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, codigoFuncionario); // Define o código do funcionário

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Recupera os dados do ResultSet
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                Date dataNascimentoSql = rs.getDate("data_nascimento");
                String telefone = rs.getString("telefone");
                String codigoFuncionarioDb = rs.getString("codigo_funcionario");
                String cargo = rs.getString("cargo");

                // Converte a data de nascimento de Date para LocalDate
                LocalDate dataNascimento = dataNascimentoSql.toLocalDate();

                // Cria o objeto Funcionario e atribui os dados
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(nome);
                funcionario.setCpf(cpf);
                funcionario.setDataNascimento(dataNascimento);
                funcionario.setTelefone(telefone);
                funcionario.setCodigoFuncionario(codigoFuncionarioDb);
                funcionario.setCargo(cargo);

                return funcionario; // Retorna o objeto Funcionario
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null caso não encontre o funcionário
    }

    // Método para atualizar o cargo de um funcionário
    public boolean atualizarCargo(int idFuncionario, String novoCargo) {
        String sql = "UPDATE funcionario SET cargo = ? WHERE id_funcionario = ?";

        try (Connection connection = DBUtil.conectar();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, novoCargo);
            ps.setInt(2, idFuncionario);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // Retorna true se o cargo foi atualizado
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para excluir um funcionário
    public boolean excluirFuncionario(int idFuncionario) {
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";

        try (Connection connection = DBUtil.conectar();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idFuncionario);

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0; // Retorna true se o funcionário foi excluído
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para atualizar o código do funcionário no banco
    public boolean atualizarCodigoFuncionario(int idFuncionario, String codigoFuncionario) {
        String sql = "UPDATE funcionario SET codigo_funcionario = ? WHERE id_funcionario = ?";

        try (Connection connection = DBUtil.conectar();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, codigoFuncionario);
            ps.setInt(2, idFuncionario);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // Retorna true se o código foi atualizado
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}