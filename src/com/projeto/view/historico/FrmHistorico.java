package com.projeto.view.historico;

import dao.ItemVendaDao;
import dao.VendasDao;
import com.projeto.model.ItemVenda;
import com.projeto.model.Vendas;
import com.projeto.view.detalheVenda.FrmDetalheVenda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FrmHistorico extends JFrame{
    private JLabel lblCabecalho;
    private JPanel panelCabecalho;
    private JPanel panelConsulta;
    private JPanel mainPanel;
    private JTable tblHistorico;
    private JLabel lblDataI;
    private JFormattedTextField txtDataI;
    private JLabel lblDataF;
    private JFormattedTextField txtTotalV;
    private JButton btnPesquisar;
    private JFormattedTextField txtDataF;
    private JFormattedTextField txtCliente;
    private JLabel lblCliente;

    public FrmHistorico() {

        setTitle("Controle de Vendas - Histórico");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        createTable();
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate data_inicio = LocalDate.parse(txtDataI.getText(), formato);
                    LocalDate data_fim = LocalDate.parse(txtDataF.getText(), formato);

                    VendasDao dao = new VendasDao();
                    List<Vendas> lista = dao.listarVendasPorPeriodo(data_inicio, data_fim);

                    DefaultTableModel dados = (DefaultTableModel) tblHistorico.getModel();
                    dados.setNumRows(0);

                    if (lista.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum Registro encontrado para as datas especificadas!");
                    } else {

                        for (Vendas v : lista) {
                            dados.addRow(new Object[]{
                                    v.getId(),
                                    v.getData_venda(),
                                    v.getClientes().getNome(),
                                    v.getTotal_venda(),
                                    v.getObs()
                            });
                        }
                    }
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Registro Inválido!");
                }

            }
        });
        tblHistorico.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FrmDetalheVenda tela = new FrmDetalheVenda();

                tela.txtClienteV.setText(tblHistorico.getValueAt(tblHistorico.getSelectedRow(), 2).toString());
                tela.txtTotalV.setText(tblHistorico.getValueAt(tblHistorico.getSelectedRow(), 3).toString());
                tela.txtData.setText(tblHistorico.getValueAt(tblHistorico.getSelectedRow(), 1).toString());
                tela.txtObsV.setText(tblHistorico.getValueAt(tblHistorico.getSelectedRow(), 4).toString());

                int venda_id = Integer.parseInt(tblHistorico.getValueAt(tblHistorico.getSelectedRow(), 0).toString());

                ItemVenda item = new ItemVenda();
                ItemVendaDao dao_item = new ItemVendaDao();
                List<ItemVenda> listaItens = dao_item.listaItensPorVenda(venda_id);

                DefaultTableModel dados = (DefaultTableModel) tela.tblItensVendido.getModel();
                dados.setNumRows(0);

                for (ItemVenda v : listaItens) {
                    dados.addRow(new Object[]{
                            v.getProduto().getDescricao(),
                            v.getQtd(),
                            v.getProduto().getPreco(),
                            v.getSubtotal()
                    });
                }

                tela.setVisible(true);
            }
        });
    }

    public void createTable() {
        tblHistorico.setModel(new DefaultTableModel(
                null,
                new String[]{"Codigo", "Data da Venda", "Cliente", "Total da Venda", "Obs.:"}
        ));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Controle de Vendas - Histórico");
            FrmHistorico form = new FrmHistorico();
            frame.setContentPane(form.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
