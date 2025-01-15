package com.projeto.view.login;

import dao.FuncionariosDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmLogin extends JFrame{
    private JPanel mainPanelLogin;
    private JPanel panelcabecalho;
    private JTextField txtemailLogin;
    private JLabel lblemailLogin;
    private JLabel lblsenhaLogin;
    private JButton btnentrarLogin;
    private JButton btnsairLogin;
    private JLabel lblcabecalho;
    private JPasswordField txtsenhaLogin;

    public FrmLogin() {
        setTitle("Controle de Vendas - Login");
        setContentPane(mainPanelLogin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        btnentrarLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String email, senha;
                    email = txtemailLogin.getText();
                    senha = txtsenhaLogin.getText();

                    FuncionariosDao dao = new FuncionariosDao();
                    dao.login(email, senha);

                }catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Erro: " + erro);
                }
            }
        });
        btnsairLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


    public JPanel getMainPanel() {
        return mainPanelLogin;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Controle de Vendas - Autenticação");
            com.projeto.view.login.FrmLogin form = new com.projeto.view.login.FrmLogin();
            frame.setContentPane(form.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
