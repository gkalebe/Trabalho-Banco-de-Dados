package view;

import controller.ClienteController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe responsável pela interface para verificação de senha
public class VerificarSenhaView extends JDialog {
    private int clienteId; // ID do cliente que solicita a ação
    private String acao; // Ação que o cliente deseja realizar (e.g., consultar saldo, saque, etc.)
    private ClienteController clienteController;  // Controlador do cliente para interagir com as operações de banco

    // Construtor da janela de verificação de senha
    public VerificarSenhaView(JFrame parent, int clienteId, String acao) {
        super(parent, "Verificar Senha", true);  // "true" para tornar a janela modal (bloqueia a janela principal)
        this.clienteId = clienteId;  // Armazena o ID do cliente
        this.acao = acao;  // Armazena a ação a ser realizada
        this.clienteController = new ClienteController();  // Inicializa o controlador do cliente

        setSize(300, 150);  // Define o tamanho da janela
        setLocationRelativeTo(parent);  // Posiciona a janela no centro da tela pai
        setLayout(new BorderLayout());  // Define o layout da janela como BorderLayout

        // Cria um rótulo de instrução para o campo de senha
        JLabel labelInstrucoes = new JLabel("Digite a senha para " + acao, JLabel.CENTER);
        labelInstrucoes.setFont(new Font("Arial", Font.BOLD, 14));  // Define a fonte e estilo do texto
        add(labelInstrucoes, BorderLayout.NORTH);  // Adiciona o rótulo na parte superior da janela

        // Cria o campo de senha
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 14));  // Define a fonte do campo de senha
        add(campoSenha, BorderLayout.CENTER);  // Adiciona o campo de senha no centro da janela

        // Cria um painel de botões para confirmar ou cancelar a operação
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());  // os botões ficam lado a lado

        // Botão de confirmação
        JButton botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String senha = new String(campoSenha.getPassword());  // Obtém a senha digitada
                if (autenticarSenha(senha)) {  // Verifica se a senha está correta
                    realizarAcao();  // Realiza a ação solicitada pelo cliente
                    dispose();  // Fecha a janela de verificação de senha
                } else {
                    JOptionPane.showMessageDialog(VerificarSenhaView.this, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);  // Exibe uma mensagem de erro se a senha estiver incorreta
                }
            }
        });

        // Botão de cancelamento
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Fecha a janela sem realizar nenhuma ação
            }
        });

        // Adiciona os botões no painel de botões
        buttonPanel.add(botaoConfirmar);
        buttonPanel.add(botaoCancelar);
        add(buttonPanel, BorderLayout.SOUTH);  // Adiciona o painel de botões na parte inferior da janela

        setVisible(true);  // Torna a janela visível
    }

    // Método para autenticar a senha fornecida pelo cliente
    private boolean autenticarSenha(String senha) {
        // Verifica se a senha fornecida é igual à senha predefinida
        return "cli123".equals(senha);  // Senha de exemplo 
    }
        private void realizarAcao() {
            switch (acao) {
                case "Consultar Saldo":
                    new SaldoView(clienteId);  // Chama a tela de saldo
                    break;
                case "Saque":
                    new SaqueView(clienteId);  // Chama a tela de saque 
                    break;
                case "Consultar Limite":
                    new LimiteView(clienteId);  // Chama a tela de limite 
                    break;
        }
    }

}

