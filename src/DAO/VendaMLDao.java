package DAO;

import MODEL.VendaMLM;
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
public class VendaMLDao {
    
    PreparedStatement pst;
    String sql;
    ClienteDao clientedao = new ClienteDao();
    FuncionarioDao funcionariodao = new FuncionarioDao();
    ProdutoDao produtodao = new ProdutoDao();
    MarcaDao marcadao = new MarcaDao();
    ModeloDao modelodao = new ModeloDao();
    
    public void salvar (VendaMLM venda) throws SQLException{

        sql = "insert into VendaML set id = ?, idfuncionario = ?, idproduto = ?, Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ), horario = ?, rastreio = ?, detalhes = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setInt(2, venda.getIdFuncionario().getId());
        pst.setInt(3, venda.getIdProduto().getId());
        pst.setString(4, venda.getData());
        pst.setString(5, venda.getHorario());
        pst.setString(6, venda.getRastreio());
        pst.setString(7, venda.getDetalhes());
        pst.execute();
        pst.close();
        buscaquantidade(venda.getIdProduto().getId(), 1);
    }
    
    public void buscaquantidade(int id, int quantidade) throws SQLException{
        ProdutoM produto = null;        
        sql = "select * from Produto where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            produto = new ProdutoM(
                            rs.getInt("id"),
                            marcadao.busca(rs.getInt("idmarca")),
                            modelodao.busca(rs.getInt("idmodelo")),
                            rs.getString("nome"),
                            rs.getFloat("valorcusto"),
                            rs.getFloat("valormax"),
                            rs.getFloat("valormini"),
                            rs.getString("codigo"),
                            rs.getInt("quantidade"));
        }
        pst.close();
        quantidade = produto.getQuantidade() - quantidade;
        atualizaQuantidade(quantidade, produto.getId());
    }

    public void atualizaQuantidade(int quantidade, int id)throws SQLException{
        sql = "update Produto set "
                        + "quantidade  = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, quantidade);
        pst.setInt(2, id);
        pst.execute();
        pst.close();
    }
  
    public List<VendaMLM> listaTodos() throws SQLException{
        List<VendaMLM> listavenda = new ArrayList<>();
        sql = "select id, idfuncionario, idproduto, DATE_FORMAT( data, \"%d/%m/%Y\" ) AS data, horario, rastreio, detalhes from VendaML ";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while(rs.next()){
            listavenda.add(new VendaMLM(
                        rs.getInt("id"),
                        funcionariodao.busca(rs.getInt("idfuncionario")),
                        produtodao.busca(rs.getInt("idproduto")),
                        rs.getString("data"),
                        rs.getString("horario"),
                        rs.getString("rastreio"),
                        rs.getString("detalhes")));
        }
        pst.close();
    return listavenda;
    }
    
    public VendaMLM busca(int id) throws SQLException{
        VendaMLM venda = null;
        sql = "select id, idfuncionario, idproduto, DATE_FORMAT( data, \"%d/%m/%Y\" ) AS data, horario, rastreio, detalhes from VendaML where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            venda = new VendaMLM(
                        rs.getInt("id"),
                        funcionariodao.busca(rs.getInt("idfuncionario")),
                        produtodao.busca(rs.getInt("idproduto")),
                        rs.getString("data"),
                        rs.getString("horario"),
                        rs.getString("rastreio"),
                        rs.getString("detalhes"));
        }
        pst.close();
        return venda;
    }

    
}