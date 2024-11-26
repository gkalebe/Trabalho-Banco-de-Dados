package view;

import controller.ClienteController; // Importa a classe responsável por controlar as ações dos clientes
import javax.swing.*; // Importa classes para criar a interface gráfica
import java.awt.*; // Importa classes para layouts e gráficos
import java.awt.event.ActionEvent; // Importa a classe para eventos de ação
import java.awt.event.ActionListener; // Importa a interface para ouvir eventos de ação

// Classe que cria a janela para realizar depósitos
public class DepositoView extends JFrame {
    private ClienteController clienteController; // Controlador responsável por gerenciar as operações do cliente

    // Construtor da janela de depósito
    public DepositoView(int clienteId) {
        clienteController = new ClienteController(); // Inicializa o controlador

        setTitle("Depósito"); // Define o título da janela
        setSize(350, 250); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela ao clicar em "X"

        // Configuração do layout da janela
        setLayout(new GridLayout(4, 2, 10, 10)); // Usando GridLayout para organizar os componentes

        // Criação dos componentes da interface
        JLabel numeroContaLabel = new JLabel("Número da conta:"); // Rótulo para o número da conta
        JTextField numeroContaField = new JTextField(10); // Campo de texto para o número da conta
        JLabel valorLabel = new JLabel("Valor para depositar:"); // Rótulo para o valor do depósito
        JTextField valorField = new JTextField(10); // Campo de texto para o valor
        JButton depositarButton = new JButton("Depositar"); // Botão para realizar o depósito
        JButton voltarButton = new JButton("Voltar"); // Botão para voltar ao menu

        // Define a ação a ser executada ao clicar no botão de depósito
        depositarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String numeroContaStr = numeroContaField.getText();
                    int numeroConta = Integer.parseInt(numeroContaStr);// Obtém o número da conta
                    if (numeroContaStr.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira o número da conta!");
                        return;
                    }

                    double valor = Double.parseDouble(valorField.getText()); // Converte o valor para double
                    if (valor <= 0) { // Verifica se o valor é inválido
                        JOptionPane.showMessageDialog(null, "Por favor, insira um valor válido!");
                    } else {
                        // Chama o método de depósito no controlador
                        clienteController.depositar(clienteId , valor , numeroConta);
                        JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!"); // Mensagem de sucesso
                    }
                } catch (NumberFormatException ex) { // Captura erro de conversão de texto para número
                    JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido.");
                }
            }
        });

        // Define a ação para o botão de voltar
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                new MenuClienteView(); // Abre a janela do Menu Cliente
            }
        });

        // Adiciona os componentes à janela
        add(numeroContaLabel); // Rótulo para o número da conta
        add(numeroContaField); // Campo de texto para o número da conta
        add(valorLabel); // Rótulo para o valor do depósito
        add(valorField); // Campo de texto para o valor do depósito
        add(depositarButton); // Botão de depósito
        add(voltarButton); // Botão de voltar

        // Centraliza a janela na tela
        setLocationRelativeTo(null);

        // Torna a janela visível
        setVisible(true);
    }
}
