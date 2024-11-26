package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe principal para a interface gráfica de menu cliente
public class LoginView extends JFrame {

    private final String senhaFuncionario = "func123";
    private final String usuarioFuncionario = "funcionario"; // Usuário de exemplo para funcionário
    private final String usuarioCliente = "cliente"; // Usuário de exemplo para cliente
    private final String senhaCliente = "cli123"; // Senha de exemplo para cliente

    // Construtor da tela do Login
    public LoginView() {
        setTitle("Login - Selecione o tipo de acesso");
        setSize(400, 250); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        setLocationRelativeTo(null); // Centraliza a janela

        // Layout de grade para organizar os botões e campos
        setLayout(new GridLayout(3, 1, 10, 10)); // 3 linhas e 1 coluna

        // Criação dos botões para escolher o tipo de acesso
        JButton clienteButton = new JButton("Acessar como Cliente");
        JButton funcionarioButton = new JButton("Acessar como Funcionário");
        JButton sairButton = new JButton("Sair");

        // Ação do botão "Acessar como Cliente"
        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCamposLogin("Cliente"); // Exibe campos de login para Cliente
                dispose(); // Fecha a janela principal
            }
        });

        // Ação do botão "Acessar como Funcionário"
        funcionarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCamposLogin("Funcionário"); // Exibe campos de login para Funcionário
                dispose(); // Fecha a janela principal
            }
        });

        // Ação do botão "Sair"
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Finaliza a aplicação
            }
        });

        // Adiciona os botões à janela
        add(clienteButton);
        add(funcionarioButton);
        add(sairButton);

        setVisible(true); // Torna a janela visível
    }

    // Método para mostrar os campos de login após escolher o tipo de acesso
    private void mostrarCamposLogin(String tipoAcesso) {
        // Cria uma nova janela para o login
        JFrame loginFrame = new JFrame("Login - " + tipoAcesso);
        loginFrame.setSize(400, 200);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela
        loginFrame.setLocationRelativeTo(null); // Centraliza a janela

        // Layout de grade para organizar os campos
        loginFrame.setLayout(new GridLayout(4, 2, 10, 10));

        // Campos de entrada para usuário e senha
        JLabel usuarioLabel = new JLabel("Usuário:");
        JTextField usuarioField = new JTextField();
        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaField = new JPasswordField();

        // Botão para realizar o login
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                char[] senha = senhaField.getPassword();

                // Verificação simples (exemplo, você pode melhorar conforme necessário)
                if (usuario.isEmpty() || senha.length == 0) {
                    JOptionPane.showMessageDialog(loginFrame, "Por favor, preencha todos os campos.");
                } else {
                    // Lógica para autenticação de login de cliente ou funcionário
                    if (tipoAcesso.equals("Funcionário") && usuario.equals(usuarioFuncionario) && new String(senha).equals(senhaFuncionario)) {
                        JOptionPane.showMessageDialog(loginFrame, "Login de Funcionário realizado com sucesso!");
                        loginFrame.dispose(); // Fecha a janela de login
                        new MenuFuncionarioView(); // Abre o menu de funcionário
                    } else if (tipoAcesso.equals("Cliente") && usuario.equals(usuarioCliente) && new String(senha).equals(senhaCliente)) {
                        JOptionPane.showMessageDialog(loginFrame, "Login de Cliente realizado com sucesso!");
                        loginFrame.dispose(); // Fecha a janela de login
                        new MenuClienteView(); // Abre o menu do cliente
                    } else {
                        JOptionPane.showMessageDialog(loginFrame, "Usuário ou senha incorretos.");
                    }
                }
            }
        });

        // Botão para voltar à tela principal
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose(); // Fecha a janela de login
                new LoginView(); // Volta para a tela principal de seleção de acesso
            }
        });

        // Adiciona os componentes à janela de login
        loginFrame.add(usuarioLabel);
        loginFrame.add(usuarioField);
        loginFrame.add(senhaLabel);
        loginFrame.add(senhaField);
        loginFrame.add(voltarButton); // Adiciona o botão de voltar
        loginFrame.add(loginButton); // Adiciona o botão de login

        loginFrame.setVisible(true); // Torna a janela de login visível
    }

    // Método principal para iniciar a aplicação
    public static void main(String[] args) {
        new LoginView(); // Inicia o menu cliente
    }
}
