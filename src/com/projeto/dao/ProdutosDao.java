package dao;

import com.projeto.jdbc.ConnectionFactory;
import com.projeto.model.Fornecedores;
import com.projeto.model.Produtos;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDao {

    private Connection con;

    public ProdutosDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarProduto(Produtos obj) {
        try {
            String sql = "insert into tb_produtos(descricao, preco, qtd_estoque, for_id) values (?,?,?,?);";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso");
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e);
        }
    }

    public void alterarProduto(Produtos obj) {
        try {
            String sql = "UPDATE tb_produtos set descricao=?, preco=?, qtd_estoque=?, for_id=? WHERE id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());

            stmt.setInt(5, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto Alterado com Sucesso");
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e);
        }
    }

    public void excluirProduto(Produtos obj) {
        try {
            String sql = "DELETE FROM tb_produtos WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto Excluido com Sucesso");
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e);
        }
    }

    public List<Produtos> listarProdutos() {
        try {
            List<Produtos> lista = new ArrayList<>();
            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque, f.nome FROM tb_produtos as p INNER JOIN" +
                    " tb_fornecedores as f on (p.for_id = f.id)";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedor(f);

                lista.add(obj);

            }
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }

    public List<Produtos> buscarProdutoPorNome(String descricao) {
        try {
            List<Produtos> lista = new ArrayList<>();
            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque, f.nome FROM tb_produtos as p INNER JOIN" +
                    " tb_fornecedores as f on (p.for_id = f.id) WHERE p.descricao LIKE ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, descricao);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));

                obj.setFornecedor(f);

                lista.add(obj);

            }
            return lista;

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro: " + e);
            return null;
        }
    }

    public Produtos consultarPorNome(String descricao) {
        try {
            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque, f.nome FROM tb_produtos as p INNER JOIN" +
                    " tb_fornecedores as f on (p.for_id = f.id) WHERE p.descricao LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, descricao);

            ResultSet rs = stmt.executeQuery();

            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f);
            }

            return obj;

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado");
            return null;
        }
    }

    public Produtos consultarPorCodigo(int id) {
        try {
            String sql = "SELECT * FROM tb_produtos WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            Produtos obj = new Produtos();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));
            }

            return obj;

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado" + e);
            return null;
        }
    }

    public void baixarStock(int id, int qtd_nova) {
        try {
            String sql = "UPDATE tb_produtos set qtd_estoque = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    public int retornaStockAtual(int id) {
        try {
            int qtd_estoque = 0;
            String sql = "SELECT qtd_estoque from tb_produtos WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                qtd_estoque = (rs.getInt("qtd_estoque"));
            }
            return qtd_estoque;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
