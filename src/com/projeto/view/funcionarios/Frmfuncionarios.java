package com.projeto.view.funcionarios;

import dao.ClientesDao;
import dao.FuncionariosDao;
import com.projeto.model.Clientes;
import com.projeto.model.Funcionarios;
import com.projeto.model.Utilitarios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class Frmfuncionarios extends JFrame{
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
    private JLabel lblcpf;
    private JLabel lblnumero;
    private JLabel lblcomplemento;
    private JTabbedPane tabbedpaneclientes;
    private JLabel lblnomecli;
    private JTable tblfun;
    private JLabel lblcadastro;
    private JPanel panelcabecalho;
    private JPanel gridcli;
    private JPanel gridpessoal;
    private JLabel lbltelefone;
    private JLabel lbluf;
    private JPanel mainPanelFun;
    private JButton btnNovoCli;
    private JButton btnbuscar;
    private JPasswordField txtsenha;
    private JLabel lblsenha;
    private JTextField txtcargo;
    private JComboBox combonivel;
    private JLabel lblacesso;



    public Frmfuncionarios() {
        setTitle("Controle de Vendas - Funcionários");
        setContentPane(mainPanelFun);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        btnSalvarCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Funcionarios obj = new Funcionarios();
                obj.setNome(txtNome.getText());
                obj.setRg(txtRg.getText());
                obj.setCpf(txtCpf.getText());
                obj.setEmail(txtEmail.getText());
                obj.setSenha(txtsenha.getText());
                obj.setCargo(txtcargo.getText());
                obj.setNivel_acesso(combonivel.getSelectedItem().toString());
                obj.setTelefone(txtTelefone.getText());
                obj.setCelular(txtCelular.getText());
                obj.setCep(txtCep.getText());
                obj.setEndereco(txtMorada.getText());
                obj.setNumero(Integer.parseInt(txtNumero.getText()));
                obj.setComplemento(txtComplemento.getText());
                obj.setBairro(txtBairro.getText());
                obj.setCidade(txtCidade.getText());
                obj.setUf(combouf.getSelectedItem().toString());

                FuncionariosDao dao = new FuncionariosDao();
                dao.cadastrarFuncionarios(obj);

                listar();

                new Utilitarios().limpaTela(gridpessoal);
            }
        });

        createTable();
        listar();
        tblfun.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedpaneclientes.setSelectedIndex(0);
                txtCodigo.setText(tblfun.getValueAt(tblfun.getSelectedRow(), 0).toString());
                txtNome.setText(tblfun.getValueAt(tblfun.getSelectedRow(), 1).toString());
                txtRg.setText(tblfun.getValueAt(tblfun.getSelectedRow(), 2).toString());
                txtCpf.setText(tblfun.getValueAt(tblfun.getSelectedRow(), 3).toString());
                txtEmail.setText(tblfun.getValueAt(tblfun.getSelectedRow(), 4).toString());
                txtsenha.setText(tblfun.getValueAt(tblfun.getSelectedRow(), 5).toString());
                txtcargo.setText(tblfun.getValueAt(tblfun.getSelectedRow(), 6).toString());
                combonivel.setSelectedItem(tblfun.getValueAt(tblfun.getSelectedRow(), 7).toString());
                txtTelefone.setText(tblfun.getValueAt(tblfun.getSelectedRow(), 8).toString());
                txtCelular.setText(tblfun.getValueAt(tblfun.getSelectedRow(), 9).toString());
                txtCep.setText(tblfun.getValueAt(tblfun.getSelectedRow(), 10).toString());
                txtMorada.setText(tblfun.getValueAt(tblfun.getSelectedRow(), 11).toString());
                txtNumero.setText(tblfun.getValueAt(tblfun.getSelectedRow(), 12).toString());
                txtComplemento.setText(tblfun.getValueAt(tblfun.getSelectedRow(), 13).toString());
                txtBairro.setText(tblfun.getValueAt(tblfun.getSelectedRow(), 14).toString());
                txtCidade.setText(tblfun.getValueAt(tblfun.getSelectedRow(), 15).toString());
                combouf.setSelectedItem(tblfun.getValueAt(tblfun.getSelectedRow(), 16).toString());
            }
        });

        btnEditarCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Funcionarios obj = new Funcionarios();
                obj.setNome(txtNome.getText());
                obj.setRg(txtRg.getText());
                obj.setCpf(txtCpf.getText());
                obj.setEmail(txtEmail.getText());
                obj.setSenha(txtsenha.getText());
                obj.setCargo(txtcargo.getText());
                obj.setNivel_acesso(combonivel.getSelectedItem().toString());
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

                FuncionariosDao dao = new FuncionariosDao();
                dao.alterarFuncionario(obj);

                new Utilitarios().limpaTela(gridpessoal);

                listar();
            }
        });


        btnExcluirCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Funcionarios obj = new Funcionarios();
                obj.setId(Integer.parseInt(txtCodigo.getText()));

                FuncionariosDao dao = new FuncionariosDao();
                dao.excluirFuncionario(obj);

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
                Funcionarios obj = new Funcionarios();
                FuncionariosDao dao = new FuncionariosDao();

                obj = dao.consultarPorNome(nome);

                if (obj.getNome() != null) {
                    txtCodigo.setText(String.valueOf(obj.getId()));
                    txtNome.setText(obj.getNome());
                    txtRg.setText(obj.getRg());
                    txtCpf.setText(obj.getCpf());
                    txtEmail.setText(obj.getEmail());
                    txtsenha.setText(obj.getSenha());
                    txtcargo.setText(obj.getCargo());
                    combonivel.setSelectedItem(obj.getNivel_acesso());
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
                    JOptionPane.showMessageDialog(null, "Funcionario não encontrado");
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
        FuncionariosDao dao = new FuncionariosDao();
        List<Funcionarios> lista = dao.listarFuncionarios();
        DefaultTableModel dados = (DefaultTableModel) tblfun.getModel();
        dados.setNumRows(0);

        for (Funcionarios c : lista) {
            dados.addRow(new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getRg(),
                    c.getCpf(),
                    c.getEmail(),
                    c.getSenha(),
                    c.getCargo(),
                    c.getNivel_acesso(),
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
        tblfun.setModel(new DefaultTableModel(
                null,
                new String[]{"Codigo", "Nome", "RG", "CPF", "E-mail", "Senha", "Cargo", "Acesso", "Telefone", "Celular",
                        "CEP", "Morada", "Nº", "Complemento", "Bairro", "Cidade", "Estado"}
        ));
    }

    public JPanel getMainPanel() {
        return mainPanelFun;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Controle de Vendas");
            Frmfuncionarios form = new Frmfuncionarios();
            frame.setContentPane(form.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public void pesquisar() {
        String nome = "%" + txtNomeCli.getText() + "%";

        FuncionariosDao dao = new FuncionariosDao();
        List<Funcionarios> lista = dao.buscarFuncionarioPorNome(nome);
        DefaultTableModel dados = (DefaultTableModel) tblfun.getModel();
        dados.setNumRows(0);

        for (Funcionarios c : lista) {
            dados.addRow(new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getRg(),
                    c.getCpf(),
                    c.getEmail(),
                    c.getSenha(),
                    c.getCargo(),
                    c.getNivel_acesso(),
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
