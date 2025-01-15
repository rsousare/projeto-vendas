package com.projeto.jdbc;

import javax.swing.*;

public class TestaConexao {
    public static void main(String[] args) {
        try {
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com Sucesso");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar" + e);
        }
    }
}
