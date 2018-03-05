package DAO;

import MODEL.DespesasM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo-NOTE
 */
public class DespesasDao {
    
    public void salvar (DespesasM despesas) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "insert into Despesas set id = ?, descricao = ?, valor = ?, Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ), hora = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setString(2, despesas.getDescricao());
        pst.setFloat(3, despesas.getValor());
        pst.setString(4, despesas.getData());
        pst.setString(5, despesas.getHora());
        pst.execute();
        pst.close();
    }
    
    public void excluir(DespesasM despesas) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "delete from Despesas where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, despesas.getId());
        pst.execute();
        pst.close();
    }
      
    public void alterar(DespesasM despesas) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Despesas set "
                        + "descricao = ?, "
                        + "valor = ?, "
                        + "Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ), "
                        + "hora = ?, "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, despesas.getDescricao());
        pst.setFloat(2, despesas.getValor());
        pst.setString(3, despesas.getData());
        pst.setString(4, despesas.getHora());
        pst.setInt(5,despesas.getId());
        pst.execute();
        pst.close();
     }
    
    public List<DespesasM> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;
        List<DespesasM> listaDespesas = new ArrayList<>();
        sql = "select Id, Descricao, Valor,  Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ) AS Data, Hora from Despesas";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaDespesas.add(new DespesasM(
                            rs.getInt("Id"),
                            rs.getString("Descricao"),
                            rs.getFloat("Valor"),
                            rs.getString("Data"),
                            rs.getString("Hora")));
        }
        pst.close();
        return listaDespesas;
    }
    
    public DespesasM busca(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        DespesasM despesas = null;        
        sql = "select Id, Descricao, Valor,  Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ) AS Data, Hora from Despesas where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            despesas = new DespesasM(
                            rs.getInt("Id"),
                            rs.getString("Descricao"),
                            rs.getFloat("Valor"),
                            rs.getString("Data"),
                            rs.getString("Hora"));
        }
        pst.close();
        return despesas;
    }
    
    public DespesasM buscaNome(String nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        DespesasM despesas = null;        
        sql = "select Id, Descricao, Valor,  Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ) AS Data, Hora from Despesas where nome = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, nome);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            despesas = new DespesasM(
                            rs.getInt("Id"),
                            rs.getString("Descricao"),
                            rs.getFloat("Valor"),
                            rs.getString("Data"),
                            rs.getString("Hora"));
        }
        pst.close();
        return despesas;
    }
    
    public List<DespesasM> buscaNomeLista(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<DespesasM> listaDespesas = new ArrayList<>();
        String name = "%"+Nome+"%";
        sql = "select Id, Descricao, Valor,  Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ) AS Data, Hora from Despesas where nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaDespesas.add(new DespesasM(
                            rs.getInt("Id"),
                            rs.getString("Descricao"),
                            rs.getFloat("Valor"),
                            rs.getString("Data"),
                            rs.getString("Hora")));
        }

        pst.close();
        return listaDespesas;
    }
}
