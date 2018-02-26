package DAO;

import MODEL.VendaMLM;
import MODEL.ProdutoM;
import MODEL.VendaM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo-NOTE
 */
public class SituacaoDaEmpresaDao {
    
    PreparedStatement pst;
    String sql;
    ClienteDao clientedao = new ClienteDao();
    FuncionarioDao funcionariodao = new FuncionarioDao();
    ProdutoDao produtodao = new ProdutoDao();
    CategoriaDao categoriadao = new CategoriaDao();
    MarcaDao marcadao = new MarcaDao();
    ModeloDao modelodao = new ModeloDao();
    
    public List<VendaM> buscaDataSituacao() throws SQLException{
        List<VendaM> listavenda = new ArrayList<>();
        sql = "select id, idcliente, idfuncionario, DATE_FORMAT( data, \"%Y\" ) AS data, totalvenda, formapagamento from Venda group by data ";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listavenda.add(new VendaM(
            rs.getInt("id"),
            clientedao.busca(rs.getInt("idcliente")),
            funcionariodao.busca(rs.getInt("idfuncionario")),
            rs.getString("data"),
            rs.getFloat("totalvenda"),
            rs.getString("formapagamento")));
        }

        pst.close();
        return listavenda;
    }
    
    public VendaM BuscaTotalVendaMes(String data1, String data2) throws SQLException{
        VendaM venda = new VendaM();
        sql = "SELECT id, idcliente, idfuncionario, DATE_FORMAT( data,\"%d/%m/%Y\") AS data, SUM(totalvenda), formapagamento FROM venda WHERE data >= (?) and data <= (?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, data1);
        pst.setString(2, data2);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            venda = new VendaM(
            rs.getInt("id"),
            clientedao.busca(rs.getInt("idcliente")),
            funcionariodao.busca(rs.getInt("idfuncionario")),
            rs.getString("data"),
            rs.getFloat("totalvenda"),
            rs.getString("formapagamento"));
        }
        pst.close();
        return venda;
    }
    
    public VendaM BuscaTotalVendaAno(int mes, int ano) throws SQLException{
        VendaM venda = new VendaM();
        sql = "SELECT SUM(TotalVenda) FROM venda WHERE Data >= (\"?/01/01\") and Data <= (\"?/12/31\")";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, ano);
        pst.setInt(2, ano);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            venda = new VendaM(
            rs.getInt("id"),
            clientedao.busca(rs.getInt("idcliente")),
            funcionariodao.busca(rs.getInt("idfuncionario")),
            rs.getString("data"),
            rs.getFloat("totalvenda"),
            rs.getString("formapagamento"));
        }

        pst.close();
        return venda;
    }
    
}
