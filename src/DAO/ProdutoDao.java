package DAO;

import MODEL.ProdutoM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo-NOTE
 */
public class ProdutoDao {
    ModeloDao modeloDao = new ModeloDao();
    MarcaDao marcaDao = new MarcaDao();
    public void salvar (ProdutoM produto) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "insert into Produto values (?,?,?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setInt(2, produto.getIdMarca().getId());
        pst.setInt(3, produto.getIdModelo().getId());
        pst.setString(4, produto.getNome());
        pst.setFloat(5, produto.getValorCusto());
        pst.setFloat(6, produto.getValorMax());
        pst.setFloat(7, produto.getValorMini());
        pst.setString(8, produto.getCodigo());
        pst.setInt(9, produto.getQuantidade()); 
        pst.execute();
        pst.close();
    }
    
    public void excluir(ProdutoM produto) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "delete from Produto where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, produto.getId());
        pst.execute();
        pst.close();
    }
      
    public void alterar(ProdutoM produto) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Produto set "
                        + "idmarca = ?, "
                        + "idmodelo = ?, "
                        + "nome = ?, "
                        + "valorcusto = ?, "
                        + "valormax = ?, "
                        + "valormini = ?, "
                        + "codigo  = ?, "
                        + "quantidade  = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, produto.getIdMarca().getId());
        pst.setInt(2, produto.getIdModelo().getId());
        pst.setString(3, produto.getNome());
        pst.setDouble(4, produto.getValorCusto());
        pst.setDouble(5, produto.getValorMax()); 
        pst.setDouble(6, produto.getValorMini());
        pst.setString(7, produto.getCodigo());
        pst.setInt(8, produto.getQuantidade());
        pst.setInt(9,produto.getId());
        pst.execute();
        pst.close();
     }
    
    public List<ProdutoM> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;
        List<ProdutoM> listaProduto = new ArrayList<>();
        sql = "select * from Produto order by nome";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaProduto.add(new ProdutoM(
                            rs.getInt("id"),
                            marcaDao.busca(rs.getInt("idmarca")),
                            modeloDao.busca(rs.getInt("idmodelo")),
                            rs.getString("nome"),
                            rs.getFloat("valorcusto"),
                            rs.getFloat("valormax"),
                            rs.getFloat("valormini"),
                            rs.getString("codigo"),
                            rs.getInt("quantidade")));
        }
        pst.close();
        return listaProduto;
    }
    
    public ProdutoM busca(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        ProdutoM produto = null;        
        sql = "select * from Produto where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            produto = new ProdutoM(
                            rs.getInt("id"),
                            marcaDao.busca(rs.getInt("idmarca")),
                            modeloDao.busca(rs.getInt("idmodelo")),
                            rs.getString("nome"),
                            rs.getFloat("valorcusto"),
                            rs.getFloat("valormax"),
                            rs.getFloat("valormini"),
                            rs.getString("codigo"),
                            rs.getInt("quantidade"));
        }
        pst.close();
        return produto;
    }
    
    public List<ProdutoM> buscaNomeLista(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<ProdutoM> listaProduto = new ArrayList<>();
        String name = "%"+Nome+"%";
        sql = "select * from Produto where nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaProduto.add(new ProdutoM(
                            rs.getInt("id"),
                            marcaDao.busca(rs.getInt("idmarca")),
                            modeloDao.busca(rs.getInt("idmodelo")),
                            rs.getString("nome"),
                            rs.getFloat("valorcusto"),
                            rs.getFloat("valormax"),
                            rs.getFloat("valormini"),
                            rs.getString("codigo"),
                            rs.getInt("quantidade")));
        }

        pst.close();
        return listaProduto;
    }
}
