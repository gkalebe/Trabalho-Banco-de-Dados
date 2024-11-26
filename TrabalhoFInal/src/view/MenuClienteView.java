package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe para a interface gráfica do menu do cliente
public class MenuClienteView extends JFrame {
    private int clienteId; // ID do cliente para identificar o usuário atual

    // Construtor padrão (sem parâmetros)
    public MenuClienteView() {
        this(0); // Define um ID padrão (0) se não for fornecido
    }

    // Construtor que inicializa o menu do cliente com o ID do cliente
    public MenuClienteView(int clienteId) {
        this.clienteId = clienteId; // Armazena o ID do cliente

        setTitle("Menu do Cliente"); // Define o título da janela
        setSize(400, 350); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar a janela

        setLayout(new BorderLayout()); // Define o layout principal como BorderLayout

        // Cria um rótulo de boas-vindas
        JLabel label = new JLabel("Bem-vindo(a) a aba Cliente!", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16)); // Define a fonte e o estilo do texto
        add(label, BorderLayout.NORTH); // Adiciona o rótulo na parte superior da janela

        // Painel para organizar os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 10, 10)); // Layout de grade com 6 linhas e espaçamento entre os botões

        // Botão para consultar saldo
        JButton saldoButton = new JButton("Consultar Saldo");
        saldoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SaldoView(0); // Abre a tela de consulta de saldo
            }
        });

        // Botão para realizar depósito
        JButton depositoButton = new JButton("Depósito");
        depositoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DepositoView(0); // Abre a tela de depósito diretamente
            }
        });

        // Botão para realizar saque
        JButton saqueButton = new JButton("Saque");
        saqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerificarSenhaView(MenuClienteView.this, 0, "Saque"); // Verifica a senha antes de realizar o saque
            }
        });

        // Botão para consultar extrato
        JButton extratoButton = new JButton("Consultar Extrato");
        extratoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExtratoView(0); // Abre a tela de consulta de extrato
            }
        });

        // Botão para consultar limite
        JButton limiteButton = new JButton("Consultar Limite");
        limiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerificarSenhaView(MenuClienteView.this, 0, "Consultar Limite"); // Verifica a senha antes de consultar o limite
            }
        });

        // Botão para encerrar o programa
        JButton encerrarButton = new JButton("Encerrar Programa");
        encerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Finaliza a aplicação
            }
        });

        // Adiciona os botões no painel
        buttonPanel.add(saldoButton);
        buttonPanel.add(depositoButton);
        buttonPanel.add(saqueButton);
        buttonPanel.add(extratoButton);
        buttonPanel.add(limiteButton);
        buttonPanel.add(encerrarButton);

        // Adiciona o painel de botões na parte central da janela
        add(buttonPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true); // Torna a janela visível
    }

    // Método principal para testar a classe com um cliente fictício
    public static void main(String[] args) {
        new MenuClienteView(1); // Abre o menu para um cliente fictício
    }
}
