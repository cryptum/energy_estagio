package DAO;

import MODEL.VendaM;
import MODEL.VendaM2;
import MODEL.ItensVenda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo-NOTE
 */
public class VendaDao {
    
    PreparedStatement pst;
    String sql;
    ClienteDao clientedao = new ClienteDao();
    FuncionarioDao funcionariodao = new FuncionarioDao();
    
    public void salvar (VendaM venda) throws SQLException{
        
        int idvenda = 0;
        sql = "insert into Venda set id = ?, idcliente = ?, idfuncionario = ?, Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ), totalvenda = ?, formapagamento = ?";
        pst = Conexao.getInstance().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1,0);
        pst.setInt(2, venda.getIdCliente().getId());
        pst.setInt(3, venda.getIdFuncionario().getId());
        pst.setString(4, venda.getData());
        pst.setFloat(5, venda.getTotalVendas());
        pst.setString(6, venda.getFormaPagamento());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        while (rs.next()) {
            idvenda = rs.getInt(1);
        }
        pst.close();
        salvarItensVenda(venda.getItensVenda(), idvenda);
        
    }
    
    public void salvarItensVenda (List<ItensVenda> itensVenda, int idVenda) throws SQLException{
        for(ItensVenda itens : itensVenda){
            
            sql = "insert into ItemVenda values(?,?,?,?,?,?)";
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setInt(1,0);
            pst.setInt(2, idVenda);
            pst.setInt(3, itens.getIdProduto().getId());
            pst.setInt(4, itens.getQuantidade());
            pst.setFloat(5, itens.getPreco());
            pst.setFloat(6, itens.getPrecototalitem());
            pst.execute();
            pst.close();
            
        }
    }
    
    public VendaM2 busca(int id) throws SQLException{
        VendaM2 venda = null;
        sql = "select * from Venda where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            venda = new VendaM2(
            rs.getInt("id"),
            clientedao.busca(rs.getInt("idcliente")),
            funcionariodao.busca(rs.getInt("idcliente")),
            rs.getString("data"),
            rs.getFloat("totalvenda"),
            rs.getString("formapagamento"));
            }
        pst.close();
        return venda;
    }
    
    public void atualizaQuantidade(int quantidade, int id)throws SQLException{
        sql = "update Produto set "
                        + "quantidade  = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, -quantidade);
        pst.setInt(2, id);
        pst.execute();
        pst.close();
    }
    
    public void excluir(VendaM venda) throws SQLException{
        sql = "delete from Venda where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, venda.getId());
        pst.execute();
        excluirItem(venda);
        pst.close();
    }
    public void excluirItem(VendaM venda) throws SQLException{
        sql = "delete from ItemVenda where idvenda = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, venda.getId());
        pst.execute();
        pst.close();
    }
      
    
    
    public List<VendaM2> listaTodos() throws SQLException{
        List<VendaM2> listavenda = new ArrayList<>();
        sql = "select id, idcliente, idfuncionario, DATE_FORMAT( data, \"%d/%m/%Y\" ) AS data, totalvenda, formapagamento from Venda ";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while(rs.next()){
            listavenda.add(new VendaM2(
                        rs.getInt("id"),
                        clientedao.busca(rs.getInt("idcliente")),
                        funcionariodao.busca(rs.getInt("idfuncionario")),
                        rs.getString("data"),
                        rs.getFloat("totalvenda"),
                        rs.getString("formapagamento")));
        }
        pst.close();
    return listavenda;
    }/*
    
    public VendaM busca(int id) throws SQLException{
    PreparedStatement pst;
    String sql;
    VendaM venda = null;
    sql = "select * from Venda where id = ?";
    pst = Conexao.getInstance().prepareStatement(sql);
    pst.setInt(1, id);
    ResultSet rs = pst.executeQuery();
    while(rs.next()){
    venda = new VendaM(
    rs.getInt("id"),
    clientedao.busca(rs.getInt("idcliente")),
    rs.getString("data"),
    rs.getFloat("totalvenda"),
    rs.getString("formapagamento"));
    }
    pst.close();
    return venda;
    }
    
    public List<VendaM> buscaNomeLista(String Nome) throws SQLException{
    PreparedStatement pst;
    String sql;
    List<VendaM> listavenda = new ArrayList<>();
    String name = "%"+Nome+"%";
    sql = "select * from Venda where nome like ?";
    pst = Conexao.getInstance().prepareStatement(sql);
    pst.setString(1, name);
    pst.execute();
    ResultSet rs = pst.executeQuery();
    while(rs.next()){
    listavenda.add(new VendaM(
    rs.getInt("id"),
    clientedao.busca(rs.getInt("idcliente")),
    rs.getString("data"),
    rs.getDouble("totalvenda"),
    rs.getString("formapagamento")));
    }
    
    pst.close();
    return listavenda;
    }*/
}
