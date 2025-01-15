package com.projeto.view.pagamentos;

import dao.ItemVendaDao;
import dao.ProdutosDao;
import dao.VendasDao;
import com.projeto.model.*;
import com.projeto.view.vendas.Frmvendas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FrmPagamentos extends JFrame{
    private JLabel lblCabacalhoP;
    private JPanel panelCabecalho;
    private JTextField txtDinheiro;
    private JLabel lblDinheiro;
    private JTextField txtCartao;
    private JLabel lblCartao;
    private JTextField txtCheque;
    private JLabel lblCheque;
    private JTextField txtTroco;
    private JLabel lblTroco;
    public JTextField txtTotal;
    private JLabel lblTotal;
    private JButton btnFinalizar;
    private JPanel panel1;
    private JPanel panel2;
    private JTextArea txtObs;
    private JLabel lblObs;
    private Frmvendas frmvendas;
    public Clientes cliente;

    public DefaultTableModel carrinhoPagam;

    public FrmPagamentos() {

        txtCartao.setText("0");
        txtDinheiro.setText("0");
        txtCheque.setText("0");

        setTitle("Controle de Vendas - Pagamentos");
        setContentPane(panel2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double pcartao, pcheque, pdinheiro, totalpago, totalvenda, troco;

                pcartao = Double.parseDouble(txtCartao.getText());
                pcheque = Double.parseDouble(txtCheque.getText());
                pdinheiro = Double.parseDouble(txtDinheiro.getText());

                totalvenda = Double.parseDouble(txtTotal.getText());
                totalpago = pcartao + pcheque + pdinheiro;

                troco = totalpago - totalvenda;
                txtTroco.setText(String.valueOf(troco));

                Vendas objV = new Vendas();
                objV.setClientes(cliente);

                Date agora = new Date();
                SimpleDateFormat dataPt = new SimpleDateFormat("yyyy-MM-dd");
                //objV.setData_venda(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                String datamysql = dataPt.format(agora);
                objV.setData_venda(datamysql);

                objV.setTotal_venda(totalvenda);
                objV.setObs(txtObs.getText());

                VendasDao daoV = new VendasDao();
                daoV.cadastrarVenda(objV);
//                System.out.println("Id: " + objV.getClientes().getId());

                objV.setId(daoV.retornaUltimaVenda());

                for (int i = 0; i < carrinhoPagam.getRowCount(); i++) {

                    int qtd_stock, qtd_comprada, qtd_atualizada;

                    Produtos objP = new Produtos();
                    ProdutosDao daoP = new ProdutosDao();

                    ItemVenda itemVenda = new ItemVenda();
                    itemVenda.setVendas(objV);

                    objP.setId(Integer.parseInt(carrinhoPagam.getValueAt(i, 0).toString()));
                    itemVenda.setProduto(objP);
                    itemVenda.setQtd(Integer.parseInt(carrinhoPagam.getValueAt(i, 2).toString()));
                    itemVenda.setSubtotal(Double.parseDouble(carrinhoPagam.getValueAt(i, 4).toString()));

                    qtd_stock = daoP.retornaStockAtual(objP.getId());
                    qtd_comprada = Integer.parseInt(carrinhoPagam.getValueAt(i, 2).toString());
                    qtd_atualizada = qtd_stock - qtd_comprada;

                    daoP.baixarStock(objP.getId(), qtd_atualizada);

                    ItemVendaDao itemDao = new ItemVendaDao();
                    itemDao.cadastraItem(itemVenda);

                    new Utilitarios().limpaTela(panel1);
                    txtObs.setText("");
                }
                JOptionPane.showMessageDialog(null, "Venda Registrada com Sucesso");
            }
        });
    }
}
