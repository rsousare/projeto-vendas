package com.projeto.view.stock;

import dao.ProdutosDao;
import com.projeto.model.Produtos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FrmStock extends JFrame{
    private JPanel panelCabecalho;
    private JLabel lblCabecalho;
    private JPanel mainPanel;
    private JTextField txtDescricao;
    private JLabel lblDescricao;
    private JLabel lblStockA;
    private JTextField txtStockA;
    private JButton btnPesquisar;
    private JButton btnAdicionar;
    private JTextField txtQtd;
    private JLabel lblQtd;
    private JTable tblStock;

    int idProduto, qtd_nova;

    public FrmStock() {
        createTable();
        listar();

        setTitle("Controle de Vendas - Stock");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = "%" + txtDescricao.getText() + "%";

                ProdutosDao dao = new ProdutosDao();
                List<Produtos> lista = dao.buscarProdutoPorNome(nome);

                DefaultTableModel dados = (DefaultTableModel) tblStock.getModel();
                dados.setNumRows(0);

                for (Produtos p : lista) {
                    dados.addRow(new Object[]{
                            p.getId(),
                            p.getDescricao(),
                            p.getPreco(),
                            p.getQtd_estoque(),
                            p.getFornecedor().getNome()
                    });
                }
            }
        });
        tblStock.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                idProduto = Integer.parseInt(tblStock.getValueAt(tblStock.getSelectedRow(), 0).toString());
                txtDescricao.setText(tblStock.getValueAt(tblStock.getSelectedRow(), 1).toString());
                txtStockA.setText(tblStock.getValueAt(tblStock.getSelectedRow(), 3).toString());
            }
        });
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int qtd_stock, qtd;
                    qtd_stock = Integer.parseInt(txtStockA.getText());
                    qtd = Integer.parseInt(txtQtd.getText());
                    qtd_nova = qtd_stock + qtd;

                    ProdutosDao dao = new ProdutosDao();
                    dao.baixarStock(idProduto, qtd_nova);

                    JOptionPane.showMessageDialog(null, "Stock Atualizado");
                    listar();

                }catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Erro ao Atualizar!" + erro);
                }
            }
        });
    }

    public void createTable() {
        tblStock.setModel(new DefaultTableModel(
                null,
                new String[]{"Código", "Descrição", "Preço", "Qtd. Stock", "Fornecedor"}
        ));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Controle de Vendas - Stock");
            FrmStock form = new FrmStock();
            frame.setContentPane(form.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public void listar() {
        ProdutosDao daoP = new ProdutosDao();
        List<Produtos> lista = daoP.listarProdutos();

        DefaultTableModel dados = (DefaultTableModel) tblStock.getModel();
        dados.setNumRows(0);

        for (Produtos p : lista) {
            dados.addRow(new Object[]{
                    p.getId(),
                    p.getDescricao(),
                    p.getPreco(),
                    p.getQtd_estoque(),
                    p.getFornecedor().getNome()
            });
        }
    }
}
