package com.projeto.view.totalVenda;

import dao.VendasDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FrmTotalVenda extends JFrame{
    private JFormattedTextField txtDataV;
    private JLabel lblDataV;
    private JButton btnConsultar;
    private JPanel mainPanelTotalV;
    private JLabel lblTVenda;
    private JPanel panelCabecalho;
    private JTextField txtTotalV;
    private JLabel lblTotalV;

    public FrmTotalVenda() {

        setTitle("Controle de Vendas - Total de Vendas");
        setContentPane(mainPanelTotalV);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate data_venda = LocalDate.parse(txtDataV.getText(), formato);

                    double total_venda;
                    VendasDao dao = new VendasDao();
                    total_venda = dao.retornaTotalVendaPorData(data_venda);

                    txtTotalV.setText(String.valueOf(total_venda) + " €");
                }catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Informe uma data válida" + erro);
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanelTotalV;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Controle de Vendas - Total da Venda por Data");
            FrmTotalVenda form = new FrmTotalVenda();
            frame.setContentPane(form.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
