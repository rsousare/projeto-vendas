package dao;

import com.projeto.jdbc.ConnectionFactory;
import com.projeto.model.ItemVenda;
import com.projeto.model.Produtos;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemVendaDao {
    private Connection con;

    public ItemVendaDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastraItem(ItemVenda obj) {
        try {
            String sql = "INSERT INTO tb_itensvendas (venda_id, produto_id, qtd, subtotal) VALUES (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getVendas().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());

            stmt.execute();
            stmt.close();

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    public List<ItemVenda> listaItensPorVenda(int venda_id) {
        try {
            List<ItemVenda> lista = new ArrayList<>();

            String sql = "SELECT p.descricao, i.qtd, p.preco, i.subtotal FROM tb_itensvendas as i " +
                    "INNER JOIN tb_produtos as p on (i.produto_id = p.id) WHERE i.venda_id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, venda_id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItemVenda item = new ItemVenda();
                Produtos prod = new Produtos();

                prod.setDescricao(rs.getString("p.descricao"));
                item.setQtd(rs.getInt("i.qtd"));
                prod.setPreco(rs.getDouble("p.preco"));
                item.setSubtotal(rs.getDouble("i.subtotal"));

                item.setProduto(prod);
                lista.add(item);
            }
            return lista;

        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }
}
