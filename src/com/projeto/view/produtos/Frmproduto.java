package com.projeto.view.produtos;

import dao.FornecedoresDao;
import dao.ProdutosDao;
import com.projeto.model.Fornecedores;
import com.projeto.model.Produtos;
import com.projeto.model.Utilitarios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class Frmproduto extends JFrame{
    private JTextField txtCodigo;
    private JTextField txtDescricao;
    private JTextField txtPreco;
    private JButton btnPesquisarCli;
    private JButton btnSalvarCli;
    private JButton btnEditarCli;
    private JButton btnExcluirCli;
    public JTabbedPane tabbedpaneclientes;
    private JPanel gridpessoal;
    private JPanel mainPanel;
    private JButton btnNovoCli;
    private JButton btnbuscar;
    private JTable tblprodutos;
    private JComboBox combForn;
    private JFormattedTextField txtStock;
    private JTextField txtNomeCli;
    private JPanel panelcabecalho;
    private JLabel lblcadastro;
    private JLabel lblcodigo;
    private JLabel lblnome;
    private JLabel lblpreco;
    private JLabel lblstock;
    private JLabel lblforn;
    private JPanel gridpro;
    private JLabel lblnomeprod;

    public Frmproduto() {

        setTitle("Controle de Vendas - Produtos");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        btnSalvarCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Produtos obj = new Produtos();
                obj.setDescricao(txtDescricao.getText());
                obj.setPreco(Double.parseDouble(txtPreco.getText()));
                obj.setQtd_estoque(Integer.parseInt(txtStock.getText()));

                Fornecedores f = new Fornecedores();
                f = (Fornecedores) combForn.getSelectedItem();
                obj.setFornecedor(f);

                ProdutosDao dao = new ProdutosDao();
                dao.cadastrarProduto(obj);

                listar();

                new Utilitarios().limpaTela(gridpessoal);
            }
        });

        createTable();
        listar();
        tblprodutos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedpaneclientes.setSelectedIndex(0);

                txtCodigo.setText(tblprodutos.getValueAt(tblprodutos.getSelectedRow(), 0).toString());
                txtDescricao.setText(tblprodutos.getValueAt(tblprodutos.getSelectedRow(), 1).toString());
                txtPreco.setText(tblprodutos.getValueAt(tblprodutos.getSelectedRow(), 2).toString());
                txtStock.setText(tblprodutos.getValueAt(tblprodutos.getSelectedRow(), 3).toString());

                Fornecedores f = new Fornecedores();
                FornecedoresDao dao = new FornecedoresDao();
                f = dao.consultarPorNome(tblprodutos.getValueAt(tblprodutos.getSelectedRow(), 4).toString());

                combForn.removeAllItems();
                combForn.getModel().setSelectedItem(f);
                carregaComboProdutos();
            }
        });


        btnEditarCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produtos obj = new Produtos();
                obj.setId(Integer.parseInt(txtCodigo.getText()));
                obj.setDescricao(txtDescricao.getText());
                obj.setPreco(Double.parseDouble(txtPreco.getText()));
                obj.setQtd_estoque(Integer.parseInt(txtStock.getText()));

                Fornecedores f = new Fornecedores();
                f = (Fornecedores) combForn.getSelectedItem();
                obj.setFornecedor(f);

                obj.setId(Integer.parseInt(txtCodigo.getText()));

                ProdutosDao dao = new ProdutosDao();
                dao.alterarProduto(obj);

                new Utilitarios().limpaTela(gridpessoal);

                listar();
            }
        });


        btnExcluirCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produtos obj = new Produtos();
                obj.setId(Integer.parseInt(txtCodigo.getText()));

                ProdutosDao dao = new ProdutosDao();
                dao.excluirProduto(obj);

                listar();

                new Utilitarios().limpaTela(gridpessoal);
            }
        });


        btnPesquisarCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });


        txtNomeCli.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                pesquisar();
            }
        });


        btnNovoCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Utilitarios().limpaTela(gridpessoal);
                carregaComboProdutos();
            }
        });


        btnbuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String descricao = txtDescricao.getText();
                Produtos obj = new Produtos();
                ProdutosDao dao = new ProdutosDao();

                obj = dao.consultarPorNome(descricao);
                combForn.removeAllItems();

                if (obj.getDescricao() != null) {
                    txtCodigo.setText(String.valueOf(obj.getId()));
                    txtDescricao.setText(obj.getDescricao());
                    txtPreco.setText(String.valueOf(obj.getPreco()));
                    txtStock.setText(String.valueOf(obj.getQtd_estoque()));

                    Fornecedores f = new Fornecedores();
                    FornecedoresDao fdao = new FornecedoresDao();
                    f = fdao.consultarPorNome(obj.getFornecedor().getNome());
                    combForn.getModel().setSelectedItem(f);

                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado");
                }
            }
        });

        carregaComboProdutos();
    }

    public void carregaComboProdutos() {
        FornecedoresDao daof = new FornecedoresDao();
        List<Fornecedores> fornecedoresList = daof.listarFornecedores();
        combForn.removeAll();
        for (Fornecedores f : fornecedoresList) {
            combForn.addItem(f);
        }
    }

    public void listar() {
        ProdutosDao dao = new ProdutosDao();
        List<Produtos> lista = dao.listarProdutos();
        DefaultTableModel dados = (DefaultTableModel) tblprodutos.getModel();
        dados.setNumRows(0);

        for (Produtos c : lista) {
            dados.addRow(new Object[]{
                    c.getId(),
                    c.getDescricao(),
                    c.getPreco(),
                    c.getQtd_estoque(),
                    c.getFornecedor().getNome(),
            });
        }
    }

    public void createTable() {
        tblprodutos.setModel(new DefaultTableModel(
                null,
                new String[]{"Codigo", "Descrição", "Preço", "Qt. Stock", "Fornecedor"}
        ));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Controle de Vendas - Produtos");
            Frmproduto form = new Frmproduto();
            frame.setContentPane(form.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public void pesquisar() {
        String descricao = "%" + txtNomeCli.getText() + "%";

        ProdutosDao dao = new ProdutosDao();
        List<Produtos> lista = dao.buscarProdutoPorNome(descricao);

        DefaultTableModel dados = (DefaultTableModel) tblprodutos.getModel();
        dados.setNumRows(0);

        for (Produtos c : lista) {
            dados.addRow(new Object[]{
                    c.getId(),
                    c.getDescricao(),
                    c.getPreco(),
                    c.getQtd_estoque(),
                    c.getFornecedor().getNome()
            });
        }
    }


    private void createUIComponents() {

    }
}
