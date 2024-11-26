package view;

import controller.ClienteController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaldoView extends JFrame {
    private ClienteController clienteController;

    public SaldoView(int clienteId) {
        clienteController = new ClienteController();

        setTitle("Consultar Saldo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Criar um painel para organizar os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Botões em coluna
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem ao redor

        // Criar o botão "Conta Corrente"
        JButton contaCorrenteButton = new JButton("Conta Corrente");
        contaCorrenteButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centralizar no painel
        contaCorrenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double saldoCorrente = clienteController.consultarSaldoContaCorrente(0);
                String saldoFormatado = String.format("R$ %.2f", saldoCorrente);
                JOptionPane.showMessageDialog(SaldoView.this, "Saldo Conta Corrente: " + saldoFormatado);
            }
        });

        // Criar o botão "Conta Poupança"
        JButton contaPoupancaButton = new JButton("Conta Poupança");
        contaPoupancaButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centralizar no painel
        contaPoupancaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double saldoPoupanca = clienteController.consultarSaldoContaPoupanca(0);
                String saldoFormatado = String.format("R$ %.2f", saldoPoupanca);
                JOptionPane.showMessageDialog(SaldoView.this, "Saldo Conta Poupança: " + saldoFormatado);
            }
        });

        // Criar o botão "Voltar"
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centralizar no painel
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela de saldo
                new MenuClienteView(); // Abre a tela do menu do cliente
            }
        });

        // Adicionar os botões ao painel
        buttonPanel.add(contaCorrenteButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço entre os botões
        buttonPanel.add(contaPoupancaButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço entre os botões
        buttonPanel.add(voltarButton);

        // Adicionar o painel principal à janela
        add(buttonPanel);

        // Configurar a posição da janela no centro da tela
        setLocationRelativeTo(null); // Para exibir a janela no centro da tela

        // Tornar a janela visível
        setVisible(true);
    }
}
