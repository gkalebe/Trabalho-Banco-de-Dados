package view;

import javax.swing.*;
import java.awt.*;

public class FuncionarioView extends JFrame {

    public FuncionarioView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Funcionário - Opções");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Botões
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnConsultar = new JButton("Consultar");
        JButton btnAtualizar = new JButton("Atualizar Cargo");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnVoltar = new JButton("Voltar ao Menu");

        btnCadastrar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Cadastrar funcionário!"));
        btnConsultar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Consultar funcionário!"));
        btnAtualizar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Atualizar cargo!"));
        btnExcluir.addActionListener(e -> JOptionPane.showMessageDialog(this, "Excluir funcionário!"));
        btnVoltar.addActionListener(e -> {
            dispose(); // Fecha a tela atual
            new MenuFuncionarioView(); // Volta para o menu principal de funcionário
        });

        // Adiciona os botões à tela
        add(btnCadastrar);
        add(btnConsultar);
        add(btnAtualizar);
        add(btnExcluir);
        add(btnVoltar);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FuncionarioView::new);
    }
}
