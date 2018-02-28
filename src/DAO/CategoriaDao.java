package DAO;

import MODEL.CategoriaM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo-NOTE
 */
public class CategoriaDao {
    
    public void salvar (CategoriaM categoria) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "insert into Categoria values (?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setString(2, categoria.getNome());         
        pst.execute();
        pst.close();
    }
    
    public void excluir(CategoriaM categoria) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "delete from Categoria where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, categoria.getId());
        pst.execute();
        pst.close();
    }
      
    public void alterar(CategoriaM categoria) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Categoria set "
                        + "nome = ?, "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, categoria.getNome());
        pst.setInt(2,categoria.getId());
        pst.execute();
        pst.close();
     }
    
    public List<CategoriaM> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;
        List<CategoriaM> listaCategoria = new ArrayList<>();
        sql = "select * from Categoria";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaCategoria.add(new CategoriaM(
                            rs.getInt("id"),
                            rs.getString("nome")));
        }
        pst.close();
        return listaCategoria;
    }
    
    public CategoriaM busca(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        CategoriaM categoria = null;        
        sql = "select * from Categoria where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            categoria = new CategoriaM(
                            rs.getInt("id"),
                            rs.getString("nome"));
        }
        pst.close();
        return categoria;
    }
    
    public CategoriaM buscaNome(String nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        CategoriaM categoria = null;        
        sql = "select * from Categoria where nome = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, nome);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            categoria = new CategoriaM(
                            rs.getInt("id"),
                            rs.getString("nome"));
        }
        pst.close();
        return categoria;
    }
    
    public List<CategoriaM> buscaNomeLista(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<CategoriaM> listaCategoria = new ArrayList<>();
        String name = "%"+Nome+"%";
        sql = "select * from Categoria where nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaCategoria.add(new CategoriaM(
                            rs.getInt("id"),
                            rs.getString("nome")));
        }

        pst.close();
        return listaCategoria;
    }
}
