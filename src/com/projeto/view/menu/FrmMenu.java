package com.projeto.view.menu;

import com.projeto.view.cliente.Frmcliente;
import com.projeto.view.fornecedores.FrmFornecedores;
import com.projeto.view.funcionarios.Frmfuncionarios;
import com.projeto.view.historico.FrmHistorico;
import com.projeto.view.login.FrmLogin;
import com.projeto.view.produtos.Frmproduto;
import com.projeto.view.totalVenda.FrmTotalVenda;
import com.projeto.view.vendas.Frmvendas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmMenu extends JFrame{
    private JPanel mainPanelMenu;
    private JPanel panelRodape;
    public JLabel lbllogado;
    public String usuariologado;
    public JMenuItem itemPDia;
    public JMenuItem itemHVendas;
    private JMenu menuSair;
    private JMenuItem itemSair;
    private JMenuItem itemUtili;
    private JMenuItem itemProd;
    private JMenuItem itemCli;
    private JMenuItem itemFun;
    private JMenuItem itemForn;
    private JMenuItem itemPdv;

    public FrmMenu() {
        setTitle("Controle de Vendas - Menu");
        setContentPane(mainPanelMenu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        String string = "src/com/projeto/imagens/fundo.jpg";
        JLabel label = new JLabel(new ImageIcon(string));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);

        itemSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int op;
                op = JOptionPane.showConfirmDialog(null, "Tem a certeza que quer sair?");

                if (op == 0) {
                    System.exit(0);
                }
            }
        });

        itemUtili.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmLogin telaLogin = new FrmLogin();
                telaLogin.setVisible(true);
                FrmMenu.this.dispose();
            }
        });

        itemProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frmproduto tela = new Frmproduto();
                tela.tabbedpaneclientes.setSelectedIndex(1);
                tela.setVisible(true);
            }
        });

        itemCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frmcliente tela = new Frmcliente();
                tela.setVisible(true);
            }
        });

        itemFun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frmfuncionarios tela = new Frmfuncionarios();
                tela.setVisible(true);
            }
        });

        itemForn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmFornecedores tela = new FrmFornecedores();
                tela.setVisible(true);
            }
        });

        itemPdv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frmvendas tela = new Frmvendas();
                tela.setVisible(true);
            }
        });

        itemPDia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmTotalVenda tela = new FrmTotalVenda();
                tela.setVisible(true);
            }
        });

        itemHVendas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmHistorico tela = new FrmHistorico();
                tela.setVisible(true);
            }
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Controle de Vendas - Menu");
            FrmMenu form = new FrmMenu();
            frame.setContentPane(form.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);

            String string = "src/com/projeto/imagens/fundo.jpg";
            JLabel label = new JLabel(new ImageIcon(string));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);

            //frame.setLayout(new BorderLayout());
            //frame.add(label, BorderLayout.CENTER);

            //JMenuBar menuBar = createMenuBar();
            //frame.setJMenuBar(menuBar);

            frame.setVisible(true);
        });
    }



    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        String imgCli = "src/com/projeto/imagens/clientes.png";
        ImageIcon imagCli = new ImageIcon(imgCli);
        JMenu menuCli = new JMenu("Clientes");
        itemCli = new JMenuItem("Control de Clientes");
        menuCli.add(itemCli);
        menuCli.setIcon(imagCli);

        String imgFun = "src/com/projeto/imagens/funcionarios.png";
        ImageIcon imagFun = new ImageIcon(imgFun);
        JMenu menuFun = new JMenu("Funcionários");
        itemFun = new JMenuItem("Controlo de Funcionarios");
        menuFun.add(itemFun);
        menuFun.setIcon(imagFun);

        String imgFor = "src/com/projeto/imagens/fornecedores.png";
        ImageIcon imagFor = new ImageIcon(imgFor);
        JMenu menuForn = new JMenu("Fornecedores");
        itemForn = new JMenuItem("Controlo de Fornecedores");
        menuForn.add(itemForn);
        menuForn.setIcon(imagFor);

        String imgPro = "src/com/projeto/imagens/produtos.png";
        ImageIcon imagPro = new ImageIcon(imgPro);
        JMenu menuProd = new JMenu("Produtos");
        JMenuItem itemStock = new JMenuItem("Controlo de Stocks");
        itemProd = new JMenuItem("Consulta de Produto");
        menuProd.add( itemStock);
        menuProd.add(itemProd);
        menuProd.setIcon(imagPro);

        String imgVen = "src/com/projeto/imagens/vendas.png";
        ImageIcon imagVen = new ImageIcon(imgVen);
        JMenu menuVen = new JMenu("Vendas");
        itemPdv = new JMenuItem("Abrir PDV");
        itemPDia = new JMenuItem("Posição do Dia");
        itemHVendas = new JMenuItem("Histórico de Vendas");
        menuVen.add(itemPdv);
        menuVen.add(itemPDia);
        menuVen.add(itemHVendas);
        menuVen.setIcon(imagVen);

        String imgConf = "src/com/projeto/imagens/configuracoes.png";
        ImageIcon imagConf = new ImageIcon(imgConf);
        JMenu menuConf = new JMenu("Configurações");
        itemUtili = new JMenuItem("Trocar de Utilizador");
        menuConf.add(itemUtili);
        menuConf.setIcon(imagConf);

        String imgSair = "src/com/projeto/imagens/sair.png";
        ImageIcon imagSair = new ImageIcon(imgSair);
        itemSair = new JMenuItem("Sair");
        menuSair = new JMenu("Sair");
        menuSair.add(itemSair);
        menuSair.setIcon(imagSair);

        menuBar.add(menuCli);
        menuBar.add(menuFun);
        menuBar.add(menuForn);
        menuBar.add(menuProd);
        menuBar.add(menuVen);
        menuBar.add(menuConf);
        menuBar.add(menuSair);

        return menuBar;
    }

    public JPanel getMainPanel() {
        return mainPanelMenu;
    }
}
