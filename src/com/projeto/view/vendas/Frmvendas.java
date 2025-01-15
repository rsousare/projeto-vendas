package com.projeto.view.vendas;

import dao.ClientesDao;
import dao.ProdutosDao;
import com.projeto.model.Clientes;
import com.projeto.model.Produtos;
import com.projeto.view.pagamentos.FrmPagamentos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Frmvendas extends JFrame{
    private JPanel panel1;
    private JLabel lblCpf;
    private JTextField txtCpf;
    private JLabel lblData;
    private JTextField txtData;
    private JLabel lblNome;
    private JTextField txtNome;
    private JPanel mainPanel;
    private JPanel panel2;
    private JLabel lblCabecalho;
    private JPanel panelCabecalho;
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JLabel lblProduto;
    private JTextField txtProduto;
    private JLabel lblPreco;
    private JTextField txtPreco;
    private JLabel lblQtd;
    private JTextField txtQtd;
    private JButton btnPesquisar;
    private JButton btnadItem;
    private JTable tblCarrinho;
    private JPanel panel4;
    private JLabel lblTotal;
    private JTextField txtTotal;
    private JButton btnPagamento;
    private JButton btnCancelar;
    private JButton btnPesCli;

    double total, preco, subtotal;
    int qtd;

    DefaultTableModel carrinho;
    Clientes obj = new Clientes();

    public Frmvendas() {

        setTitle("Controle de Vendas - Vendas");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        createTable();
        dataSistema();

        txtCpf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    ClientesDao dao = new ClientesDao();
                    obj = dao.consultarPorCpf(txtCpf.getText());
                    txtNome.setText(obj.getNome());
                }
            }
        });
        btnPesCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clientes obj = new Clientes();
                ClientesDao dao = new ClientesDao();
                obj = dao.consultarPorCpf(txtCpf.getText());
                txtNome.setText(obj.getNome());
            }
        });

        txtCodigo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Produtos obj = new Produtos();
                    ProdutosDao dao = new ProdutosDao();

                    obj = dao.consultarPorCodigo(Integer.parseInt(txtCodigo.getText()));

                    txtProduto.setText(obj.getDescricao());
                    txtPreco.setText(String.valueOf(obj.getPreco()));
                }
            }
        });
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produtos obj = new Produtos();
                ProdutosDao dao = new ProdutosDao();

                obj = dao.consultarPorCodigo(Integer.parseInt(txtCodigo.getText()));

                txtProduto.setText(obj.getDescricao());
                txtPreco.setText(String.valueOf(obj.getPreco()));
            }
        });

        btnadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               qtd = Integer.parseInt(txtQtd.getText());
               preco = Double.parseDouble(txtPreco.getText());

               subtotal = qtd * preco;

               total += subtotal;
               txtTotal.setText(String.valueOf(total));

               carrinho.addRow(new Object[] {
                       txtCodigo.getText(),
                       txtProduto.getText(),
                       txtQtd.getText(),
                       txtPreco.getText(),
                       subtotal
               });
            }
        });
        btnPagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmPagamentos frmPagamentos = new FrmPagamentos();
                frmPagamentos.txtTotal.setText(String.valueOf(total));

                frmPagamentos.cliente = obj;
                frmPagamentos.carrinhoPagam = carrinho;
                frmPagamentos.setVisible(true);
                dispose();
            }
        });
    }

    public void dataSistema() {
        Date agora = new Date();
        SimpleDateFormat dataPt = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = dataPt.format(agora);
        txtData.setText(dataFormatada);
    }


    public void createTable() {
        carrinho = new DefaultTableModel(
                null,
                new String[]{"Codigo", "Produto", "Qtd", "Preço", "SubTotal"}
        );
        tblCarrinho.setModel(carrinho);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Controle de Vendas");
            Frmvendas form = new Frmvendas();
            frame.setContentPane(form.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }


    private void createUIComponents() {

    }
}
