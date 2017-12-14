package DAO;

import MODEL.MarcaM;
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
public class MarcaDao {
    
    public void salvar (MarcaM marca) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "insert into Marca values (?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setString(2, marca.getNome());         
        pst.execute();
        pst.close();
    }
    
    public void excluir(MarcaM marca) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "delete from Marca where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, marca.getId());
        pst.execute();
        pst.close();
    }
      
    public void alterar(MarcaM marca) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Marca set "
                        + "nome = ?, "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, marca.getNome());
        pst.setInt(11,marca.getId());
        pst.execute();
        pst.close();
     }
    
    public List<MarcaM> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;
        List<MarcaM> listaMarca = new ArrayList<>();
        sql = "select * from Marca order by nome";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaMarca.add(new MarcaM(
                            rs.getInt("id"),
                            rs.getString("nome")));
        }
        pst.close();
        return listaMarca;
    }
    
    public MarcaM busca(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        MarcaM marca = null;        
        sql = "select * from Marca where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            marca = new MarcaM(
                            rs.getInt("id"),
                            rs.getString("nome"));
        }
        pst.close();
        return marca;
    }
    
    public MarcaM buscaNome(String nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        MarcaM marca = null;        
        sql = "select * from Marca where nome = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, nome);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            marca = new MarcaM(
                            rs.getInt("id"),
                            rs.getString("nome"));
        }
        pst.close();
        return marca;
    }
    
    public List<MarcaM> buscaNomeLista(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<MarcaM> listaMarca = new ArrayList<>();
        String name = "%"+Nome+"%";
        sql = "select * from Marca where nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaMarca.add(new MarcaM(
                            rs.getInt("id"),
                            rs.getString("nome")));
        }

        pst.close();
        return listaMarca;
    }
}
