package view;

import controller.ClienteController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe para a interface gráfica de saque
public class SaqueView extends JFrame {
    private ClienteController clienteController; // Controlador para manipular operações relacionadas ao cliente

    // Construtor que inicializa a tela de saque
    public SaqueView(int clienteId) {
        clienteController = new ClienteController(); // Instancia o controlador

        setTitle("Saque"); // Define o título da janela
        setSize(300, 200); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define a ação ao fechar a janela (não encerra a aplicação)

        // Configura o layout da janela
        setLayout(new FlowLayout()); // Usando FlowLayout para organizar os componentes

        // Rótulo para informar o campo de entrada
        JLabel valorLabel = new JLabel("Valor para sacar:");

        // Campo de texto para entrada do valor do saque
        JTextField valorField = new JTextField(10); // Tamanho inicial do campo (10 caracteres)

        // Botão para realizar o saque
        JButton sacarButton = new JButton("Sacar");

        // Adiciona uma ação ao botão de saque
        sacarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Converte o valor digitado no campo de texto para um número (double)
                    double valor = Double.parseDouble(valorField.getText());
                    
                    // Defina o tipo da conta (exemplo: "corrente" ou "poupanca")
                    String tipoConta = "corrente"; // Ou obtenha esse valor de outra fonte, se necessário

                    // Chama o método de saque do controlador, agora com três parâmetros
                    if (clienteController.sacar(clienteId, valor, tipoConta)) {
                        JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!"); // Mensagem de sucesso
                    } else {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente."); // Mensagem de erro
                    }
                } catch (NumberFormatException ex) {
                    // Caso o valor não seja um número válido
                    JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Criar o botão "Voltar"
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela de saque
                new MenuClienteView(); // Abre a tela do menu do cliente
            }
        });

        // Adiciona os componentes à tela
        add(valorLabel); // Rótulo
        add(valorField); // Campo de texto
        add(sacarButton); // Botão de saque
        add(voltarButton); // Botão de voltar

        // Configura a posição da janela no centro da tela
        setLocationRelativeTo(null);

        setVisible(true); // Torna a janela visível
    }
}
