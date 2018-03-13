package DAO;

import MODEL.EntradadeProdutoM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo-NOTE
 */
public class EntradadeProdutoDao {
    
    ProdutoDao produtodao = new ProdutoDao();
    
    public void salvar (EntradadeProdutoM entradadeproduto) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "insert into EntradadeProduto set Id = ?, idproduto = ?, Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ), Hora = ?, Quantidade = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setInt(2, entradadeproduto.getIdProduto().getId());
        pst.setString(3, entradadeproduto.getData());
        pst.setString(4, entradadeproduto.getHora());
        pst.setInt(5, entradadeproduto.getQuantidade());
        pst.execute();
        pst.close();
    }
    
    public void excluir(EntradadeProdutoM entradadeproduto) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "delete from EntradadeProduto where Id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, entradadeproduto.getId());
        pst.execute();
        pst.close();
    }
      
    public void alterar(EntradadeProdutoM entradadeproduto) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update EntradadeProduto set "
                        + "IdProduto = ?, "
                        + "Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ), "
                        + "Hora = ?, "
                        + "Quantidade = ?, "

                        + "where Id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, entradadeproduto.getIdProduto().getId());
        pst.setString(2, entradadeproduto.getData());
        pst.setString(3, entradadeproduto.getHora());
        pst.setInt(4, entradadeproduto.getQuantidade());
        pst.setInt(5,entradadeproduto.getId());
        pst.execute();
        pst.close();
     }
    
    public List<EntradadeProdutoM> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;
        List<EntradadeProdutoM> ListaEntradaProduto = new ArrayList<>();
        sql = "select Id, IdProduto, Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ) AS Data, Hora, Quantidade from EntradadeProduto";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            ListaEntradaProduto.add(new EntradadeProdutoM(
                            rs.getInt("Id"),
                            produtodao.busca(rs.getInt("IdProduto")),
                            rs.getString("Data"),
                            rs.getString("Hora"),
                            rs.getInt("Quantidade")));
        }
        pst.close();
        return ListaEntradaProduto;
    }
    
    public EntradadeProdutoM busca(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        EntradadeProdutoM entradadeproduto = null;        
        sql = "select Id, IdProduto, Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ) AS Data, Hora, Quantidade from EntradadeProduto where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            entradadeproduto = new EntradadeProdutoM(
                            rs.getInt("Id"),
                            produtodao.busca(rs.getInt("IdProduto")),
                            rs.getString("Data"),
                            rs.getString("Hora"),
                            rs.getInt("Quantidade"));
        }
        pst.close();
        return entradadeproduto;
    }
    
    public EntradadeProdutoM buscaNome(String nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        EntradadeProdutoM entradadeproduto = null;        
        sql = "select EntradadeProduto.Id, EntradadeProduto.IdProduto, Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ) AS Data, EntradadeProduto.Hora,"
                + " EntradadeProduto.Quantidade from EntradadeProduto, Produto where EntradadeProduto.IdProduto = Produto.ID and Produto.nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, nome);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            entradadeproduto = new EntradadeProdutoM(
                            rs.getInt("Id"),
                            produtodao.busca(rs.getInt("IdProduto")),
                            rs.getString("Data"),
                            rs.getString("Hora"),
                            rs.getInt("Quantidade"));
        }
        pst.close();
        return entradadeproduto;
    }
    
    public List<EntradadeProdutoM> buscaNomeLista(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<EntradadeProdutoM> ListaEntradaProduto = new ArrayList<>();
        String name = "%"+Nome+"%";
        sql = "select EntradadeProduto.Id, EntradadeProduto.IdProduto, Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ) AS Data, EntradadeProduto.Hora,"
                + " EntradadeProduto.Quantidade from EntradadeProduto, Produto where EntradadeProduto.IdProduto = Produto.ID and Produto.nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            ListaEntradaProduto.add(new EntradadeProdutoM(
                            rs.getInt("Id"),
                            produtodao.busca(rs.getInt("IdProduto")),
                            rs.getString("Data"),
                            rs.getString("Hora"),
                            rs.getInt("Quantidade")));
        }

        pst.close();
        return ListaEntradaProduto;
    }
}
