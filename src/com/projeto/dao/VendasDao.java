package dao;

import com.projeto.jdbc.ConnectionFactory;
import com.projeto.model.Clientes;
import com.projeto.model.Vendas;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendasDao {
    private Connection con;

    public VendasDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarVenda(Vendas obj) {
        try {
            String sql = "INSERT INTO tb_vendas (cliente_id, data_venda, total_venda, observacoes) VALUES (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getClientes().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObs());

            stmt.execute();
            stmt.close();

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    public int retornaUltimaVenda() {
        try {
            int idvenda = 0;
            String sql = "SELECT MAX(id) id FROM tb_vendas";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Vendas p = new Vendas();
                p.setId(rs.getInt("id"));
                idvenda = p.getId();
            }
            return idvenda;

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vendas> listarVendasPorPeriodo(LocalDate data_inicio, LocalDate data_fim) {
        try {
            List<Vendas> lista = new ArrayList<>();
            String sql = "SELECT v.id, date_format(v.data_venda,'%d/%m/%Y') as data_formatada, c.nome, v.total_venda, v.observacoes FROM tb_vendas as v INNER JOIN tb_clientes " +
                    "as c on(v.cliente_id = c.id) WHERE v.data_venda BETWEEN ? AND ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicio.toString());
            stmt.setString(2, data_fim.toString());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vendas obj = new Vendas();
                Clientes c = new Clientes();

                obj.setId(rs.getInt("v.id"));
                obj.setData_venda(rs.getString("data_formatada"));
                c.setNome(rs.getString("c.nome"));
                obj.setTotal_venda(rs.getDouble("v.total_venda"));
                obj.setObs(rs.getString("v.observacoes"));

                obj.setClientes(c);

                lista.add(obj);

            }
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }

    public double retornaTotalVendaPorData(LocalDate data_venda) {
        try {
            double totalVenda = 0;

            String sql = "SELECT SUM(total_venda) as total FROM tb_vendas WHERE data_venda = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_venda.toString());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalVenda = rs.getDouble("total");
            }
            return totalVenda;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
