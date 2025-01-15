package com.projeto.view.detalheVenda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrmDetalheVenda extends JFrame{
    private JLabel lblCabecalho;
    private JPanel panelCabecalho;
    private JPanel panelConsulta;
    private JPanel mainPanel;
    private JLabel lblData;
    private JLabel lblTotalV;
    private JLabel lblCliente;
    private JLabel lblObs;
    public JTextArea txtObsV;
    public JFormattedTextField txtData;
    public JTable tblItensVendido;
    public JTextField txtClienteV;
    public JTextField txtTotalV;

    public FrmDetalheVenda() {
        createTable();

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public void createTable() {
        tblItensVendido.setModel(new DefaultTableModel(
                null,
                new String[]{"Produto", "Qtd. Comprada", "Valor", "Subtotal"}
        ));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Controle de Vendas - Detalhe da Venda");
            FrmDetalheVenda form = new FrmDetalheVenda();
            frame.setContentPane(form.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
