package com.projeto.view.cliente;

import dao.ClientesDao;
import com.projeto.model.Clientes;
import com.projeto.model.Utilitarios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class Frmcliente extends JFrame{
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
    private JFormattedTextField txtRg;
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
    private JLabel lblnumero;
    private JLabel lblcomplemento;
    private JTabbedPane tabbedpaneclientes;
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
    private JLabel lblcpf;
    private JFormattedTextField txtCnpj;
    private JPasswordField txtsenha;
    private JLabel lblsenha;
    private JTextField textField1;
    private JComboBox combonivel;
    private JLabel lblacesso;


    public Frmcliente() {

        setTitle("Controle de Vendas - Clientes");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        btnSalvarCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Clientes obj = new Clientes();
                obj.setNome(txtNome.getText());
                obj.setRg(txtRg.getText());
                obj.setCpf(txtCpf.getText());
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

                ClientesDao dao = new ClientesDao();
                dao.cadastrarCliente(obj);

                listar();

                new Utilitarios().limpaTela(gridpessoal);
            }
        });

        createTable();
        listar();
        tblcli.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedpaneclientes.setSelectedIndex(0);
                txtCodigo.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 0).toString());
                txtNome.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 1).toString());
                txtRg.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 2).toString());
                txtCpf.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 3).toString());
                txtEmail.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 4).toString());
                txtTelefone.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 5).toString());
                txtCelular.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 6).toString());
                txtCep.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 7).toString());
                txtMorada.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 8).toString());
                txtNumero.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 9).toString());
                txtComplemento.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 10).toString());
                txtBairro.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 11).toString());
                txtCidade.setText(tblcli.getValueAt(tblcli.getSelectedRow(), 12).toString());
                combouf.setSelectedItem(tblcli.getValueAt(tblcli.getSelectedRow(), 13).toString());
            }
        });
        btnEditarCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clientes obj = new Clientes();
                obj.setNome(txtNome.getText());
                obj.setRg(txtRg.getText());
                obj.setCpf(txtCpf.getText());
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

                ClientesDao dao = new ClientesDao();
                dao.alterarCliente(obj);

                new Utilitarios().limpaTela(gridpessoal);

                listar();
            }
        });


        btnExcluirCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clientes obj = new Clientes();
                obj.setId(Integer.parseInt(txtCodigo.getText()));

                ClientesDao dao = new ClientesDao();
                dao.excluirCliente(obj);

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
                Clientes obj = new Clientes();
                ClientesDao dao = new ClientesDao();

                obj = dao.consultarPorNome(nome);

                if (obj.getNome() != null) {
                    txtCodigo.setText(String.valueOf(obj.getId()));
                    txtNome.setText(obj.getNome());
                    txtRg.setText(obj.getRg());
                    txtCpf.setText(obj.getCpf());
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
        ClientesDao dao = new ClientesDao();
        List<Clientes> lista = dao.listarClientes();
        DefaultTableModel dados = (DefaultTableModel) tblcli.getModel();
        dados.setNumRows(0);

        for (Clientes c : lista) {
            dados.addRow(new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getRg(),
                    c.getCpf(),
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
                new String[]{"Codigo", "Nome", "RG", "CPF", "E-mail", "Telefone", "Celular", "CEP", "Morada", "Nº", "Complemento", "Bairro", "Cidade", "Estado"}
        ));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Controle de Vendas");
            Frmcliente form = new Frmcliente();
            frame.setContentPane(form.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public void pesquisar() {
        String nome = "%" + txtNomeCli.getText() + "%";

        ClientesDao dao = new ClientesDao();
        List<Clientes> lista = dao.buscarClientesPorNome(nome);
        DefaultTableModel dados = (DefaultTableModel) tblcli.getModel();
        dados.setNumRows(0);

        for (Clientes c : lista) {
            dados.addRow(new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getRg(),
                    c.getCpf(),
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
