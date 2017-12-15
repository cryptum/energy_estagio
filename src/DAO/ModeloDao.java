package DAO;

import MODEL.ModeloM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo-NOTE
 */
public class ModeloDao {
    MarcaDao marcaDao = new MarcaDao();
    
    public void salvar (ModeloM modelo) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "insert into Modelo values (?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setInt(2, modelo.getIdMarca().getId());
        pst.setString(3, modelo.getNome());        
        pst.execute();
        pst.close();
    }
    
    public void excluir(ModeloM modelo) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "delete from Modelo where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, modelo.getId());
        pst.execute();
        pst.close();
    }
      
    public void alterar(ModeloM modelo) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Modelo set "
                        + "idmarca = ?, "
                        + "nome = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, modelo.getIdMarca().getId());
        pst.setString(2, modelo.getNome());
        pst.setInt(3,modelo.getId());
        pst.execute();
        pst.close();
     }
    
    public List<ModeloM> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;
        List<ModeloM> listaModelo = new ArrayList<>();
        sql = "select * from Modelo";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaModelo.add(new ModeloM(
                            rs.getInt("id"),
                            marcaDao.busca(rs.getInt("idmarca")),
                            rs.getString("nome")));
        }
        pst.close();
        return listaModelo;
    }
    
    public ModeloM busca(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        ModeloM modelo = null;        
        sql = "select * from Modelo where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            modelo = new ModeloM(
                            rs.getInt("id"),
                            marcaDao.busca(rs.getInt("idmarca")),
                            rs.getString("nome"));
        }
        pst.close();
        return modelo;
    }
    
    public List<ModeloM> buscaModelo(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<ModeloM> listaModelo = new ArrayList<>();
        sql = "select * from Modelo where IdMarca = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaModelo.add(new ModeloM(
                            rs.getInt("id"),
                            marcaDao.busca(rs.getInt("idmarca")),
                            rs.getString("nome")));

        }
        pst.close();
        return listaModelo;
    }
    
    public ModeloM buscaNome(String nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        ModeloM modelo = null;        
        sql = "select * from Modelo where nome = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, nome);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            modelo = new ModeloM(
                            rs.getInt("id"),
                            marcaDao.busca(rs.getInt("idmarca")),
                            rs.getString("nome"));
        }
        pst.close();
        return modelo;
    }
    
    public List<ModeloM> buscaNomeLista(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<ModeloM> listaModelo = new ArrayList<>();
        String name = "%"+Nome+"%";
        sql = "select * from Modelo where nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaModelo.add(new ModeloM(
                            rs.getInt("id"),
                            marcaDao.busca(rs.getInt("idmarca")),
                            rs.getString("nome")));
        }

        pst.close();
        return listaModelo;
    }
}
