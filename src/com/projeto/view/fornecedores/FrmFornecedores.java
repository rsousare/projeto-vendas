package com.projeto.view.fornecedores;

import dao.ClientesDao;
import dao.FornecedoresDao;
import com.projeto.model.Clientes;
import com.projeto.model.Fornecedores;
import com.projeto.model.Utilitarios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class FrmFornecedores extends JFrame{
    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JFormattedTextField txtCelular;
    private JFormattedTextField txtTelefone;
    private JFormattedTextField txtCep;
    private JTextField txtMorada;
    private JTextField txtNumero;
    private JTextField txtBairro;
    private JTextField txtCidade;
    private JTextField txtComplemento;
    private JComboBox combouf;
    private JFormattedTextField txtCnpj;
    private JFormattedTextField txtCpf;
    private JTextField txtNomeCli;
    private JButton btnPesquisarCli;
    private JButton btnSalvarCli;
    private JButton btnEditarCli;
    private JButton btnExcluirCli;
    private JButton btnSalvar;
    private JLabel lblcodigo;
    private JLabel lblnome;
    private JLabel lblemail;
    private JLabel lblcep;
    private JLabel lblbairro;
    private JLabel lblrg;
    private JLabel lblmovel;
    private JLabel lblmorada;
    private JLabel lblcidade;
    private JLabel lblcpf;
    private JLabel lblnumero;
    private JLabel lblcomplemento;
    private JTabbedPane tabbedpaneFornecedores;
    private JLabel lblnomecli;
    private JTable tblcli;
    private JLabel lblcadastro;
    private JPanel panelcabecalho;
    private JPanel gridcli;
    private JPanel gridpessoal;
    private JLabel lbltelefone;
    private JLabel lbluf;
    private JPanel mainPanel;
    private JButton btnNovoCli;
    private JButton btnbuscar;
    private JPasswordField txtsenha;
    private JLabel lblsenha;
    private JComboBox combonivel;
    private JLabel lblacesso;


    public FrmFornecedores() {

        setTitle("Controle de Vendas - Fornecedores");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        btnSalvarCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Fornecedores obj = new Fornecedores();
                obj.setNome(txtNome.getText());
                obj.setCnpj(txtCnpj.getText());
                obj.setEmail(txtEmail.getText());
                obj.setTelefone(txtTelefone.getText());
                obj.setCelular(txtCelular.getText());
                obj.setCep(txtCep.getText());
                obj.setEndereco(txtMorada.getText());
                obj.setNumero(Integer.parseInt(txtNumero.getText()));
                obj.setComplemento(txtComplemento.getText());
                obj.setBairro(txtBairro.getText());
                obj.setCidade(txtCidade.getText());
                obj.setUf(combouf.getSelectedItem().toString());

                FornecedoresDao dao = new FornecedoresDao();
                dao.cadastrarFornecedor(obj);

                listar();

                new Utilitarios().limpaTela(gridpessoal);
            }
        });

        createTable();
        listar();

        tblcli.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedpaneFornecedores.setSelectedIndex(0);
                txtCodigo.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 0).toString());
                txtNome.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 1).toString());
                txtCnpj.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 2).toString());
                txtEmail.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 3).toString());
                txtTelefone.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 4).toString());
                txtCelular.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 5).toString());
                txtCep.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 6).toString());
                txtMorada.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 7).toString());
                txtNumero.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 8).toString());
                txtComplemento.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 9).toString());
                txtBairro.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 10).toString());
                txtCidade.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 11).toString());
                combouf.setSelectedItem(tblcli.getValueAt(tblcli.getSelectedRow(), 12).toString());
            }
        });

        btnEditarCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fornecedores obj = new Fornecedores();
                obj.setNome(txtNome.getText());
                obj.setCnpj(txtCnpj.getText());
                obj.setEmail(txtEmail.getText());
                obj.setTelefone(txtTelefone.getText());
                obj.setCelular(txtCelular.getText());
                obj.setCep(txtCep.getText());
                obj.setEndereco(txtMorada.getText());
                obj.setNumero(Integer.parseInt(txtNumero.getText()));
                obj.setComplemento(txtComplemento.getText());
                obj.setBairro(txtBairro.getText());
                obj.setCidade(txtCidade.getText());
                obj.setUf(combouf.getSelectedItem().toString());

                obj.setId(Integer.parseInt(txtCodigo.getText()));

                FornecedoresDao dao = new FornecedoresDao();
                dao.alterarFornecedores(obj);

                new Utilitarios().limpaTela(gridpessoal);

                listar();
            }
        });


        btnExcluirCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fornecedores obj = new Fornecedores();
                obj.setId(Integer.parseInt(txtCodigo.getText()));

                FornecedoresDao dao = new FornecedoresDao();
                dao.excluirFornecedor(obj);

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
            }
        });


        btnbuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nome = txtNome.getText();
                Fornecedores obj = new Fornecedores();
                FornecedoresDao dao = new FornecedoresDao();

                obj = dao.consultarPorNome(nome);

                if (obj.getNome() != null) {
                    txtCodigo.setText(String.valueOf(obj.getId()));
                    txtNome.setText(obj.getNome());
                    txtCnpj.setText(obj.getRg());
                    txtEmail.setText(obj.getEmail());
                    txtTelefone.setText(obj.getTelefone());
                    txtCelular.setText(obj.getCelular());
                    txtCep.setText(obj.getCep());
                    txtMorada.setText(obj.getEndereco());
                    txtNumero.setText(String.valueOf(obj.getNumero()));
                    txtComplemento.setText(obj.getComplemento());
                    txtBairro.setText(obj.getBairro());
                    txtCidade.setText(obj.getCidade());
                    combouf.setSelectedItem(obj.getUf());
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado");
                }
            }
        });

        txtCep.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Clientes obj = new Clientes();
                    ClientesDao dao = new ClientesDao();
                    obj = dao.buscaCep(txtCep.getText());

                    txtMorada.setText(obj.getEndereco());
                    txtBairro.setText(obj.getBairro());
                    txtCidade.setText(obj.getCidade());
                    combouf.setSelectedItem(obj.getUf());
                }
            }
        });
    }

    public void listar() {
        FornecedoresDao dao = new FornecedoresDao();
        List<Fornecedores> lista = dao.listarFornecedores();
        DefaultTableModel dados = (DefaultTableModel) tblcli.getModel();
        dados.setNumRows(0);

        for (Fornecedores c : lista) {
            dados.addRow(new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getCnpj(),
                    c.getEmail(),
                    c.getTelefone(),
                    c.getCelular(),
                    c.getCep(),
                    c.getEndereco(),
                    c.getNumero(),
                    c.getComplemento(),
                    c.getBairro(),
                    c.getCidade(),
                    c.getUf()
            });
        }
    }

    public void createTable() {
        tblcli.setModel(new DefaultTableModel(
                null,
                new String[]{"Codigo", "Nome", "CNPJ", "E-mail", "Telefone", "Celular", "CEP", "Morada", "Nº", "Complemento", "Bairro", "Cidade", "Estado"}
        ));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Controle de Vendas");
            FrmFornecedores form = new FrmFornecedores();
            frame.setContentPane(form.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public void pesquisar() {
        String nome = "%" + txtNomeCli.getText() + "%";

        FornecedoresDao dao = new FornecedoresDao();
        List<Fornecedores> lista = dao.buscarFornecedorPorNome(nome);
        DefaultTableModel dados = (DefaultTableModel) tblcli.getModel();
        dados.setNumRows(0);


        for (Fornecedores c : lista) {
                dados.addRow(new Object[]{
                        c.getId(),
                        c.getNome(),
                        c.getCnpj(),
                        c.getEmail(),
                        c.getTelefone(),
                        c.getCelular(),
                        c.getCep(),
                        c.getEndereco(),
                        c.getNumero(),
                        c.getComplemento(),
                        c.getBairro(),
                        c.getCidade(),
                        c.getUf()
           });
        }
    }


    private void createUIComponents() {

    }
}
