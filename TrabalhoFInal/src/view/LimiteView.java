package view;

import controller.ClienteController; // Importa o controlador que gerencia as operações dos clientes
import javax.swing.*; // Importa as bibliotecas para criar interfaces gráficas
import java.awt.*; // Importa as bibliotecas para trabalhar com layouts
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe que exibe o limite de crédito do cliente
public class LimiteView extends JFrame {
    private ClienteController clienteController; // Controlador para realizar a operação de consulta

    // Construtor da janela de limite de crédito
    public LimiteView(int clienteId) {
        clienteController = new ClienteController(); // Inicializa o controlador
        
        // Obtém o limite de crédito do cliente chamando o método correspondente no controlador
        double limite = clienteController.consultarLimite(clienteId);
        
        // Configura o título e o tamanho da janela
        setTitle("Limite de Crédito");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define a ação ao fechar a janela (não encerra a aplicação)

        // Configura o layout da janela
        setLayout(new FlowLayout()); // Usando FlowLayout para uma organização simples

        // Rótulo (label) para exibir o limite de crédito
        JLabel limiteLabel = new JLabel("Seu limite: R$ " + String.format("%.2f", limite)); 
        add(limiteLabel); // Adiciona o rótulo à janela
        
        // Criar o botão "Voltar"
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela de limite de crédito
                new MenuClienteView(); // Abre a tela do menu do cliente
            }
        });

        // Adiciona o botão de voltar à janela
        add(voltarButton);
        
        // Configura a posição da janela no centro da tela
        setLocationRelativeTo(null);

        // Exibe a janela
        setVisible(true);
    }
}
