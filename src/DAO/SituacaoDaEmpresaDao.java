package DAO;

import MODEL.DespesaM;
import MODEL.EntradadeProdutoM;
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
    DespesaDao despesasdao = new DespesaDao();
    EntradadeProdutoDao entradadeprodutodao = new EntradadeProdutoDao();
    
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
    
    public List<VendaM> BuscaTotalVendaMes(String de, String ate) throws SQLException{
        List<VendaM> listavenda = new ArrayList<>();
        sql = "SELECT id, idcliente, idfuncionario, DATE_FORMAT( data,\"%d/%m/%Y\") AS data, SUM(totalvenda) As totalvenda, formapagamento FROM venda WHERE data >= (?) and data <= (?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, de);
        pst.setString(2, ate);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listavenda.add( new VendaM(
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
    
    public List<VendaM> BuscaTotalVendaAno(String ano1, String ano2) throws SQLException{
        List<VendaM> listavenda = new ArrayList<>();
        sql = "SELECT id, idcliente, idfuncionario, DATE_FORMAT( data,\"%d/%m/%Y\") AS data, SUM(totalvenda) As totalvenda, formapagamento FROM venda WHERE Data >= (?) and Data <= (?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, ano1);
        pst.setString(2, ano2);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listavenda.add( new VendaM(
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
    
    public List<VendaMLM> BuscaTotalVendaMLMes(String de, String ate) throws SQLException{
        List<VendaMLM> listavenda = new ArrayList<>();
        sql = "select id, idfuncionario, idproduto, SUM(TotalVenda) AS TotalVenda, DATE_FORMAT( data, \"%d/%m/%Y\" ) AS data, horario, rastreio, detalhes from VendaML WHERE data >= (?) and data <= (?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, de);
        pst.setString(2, ate);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listavenda.add(new VendaMLM(
                        rs.getInt("id"),
                        funcionariodao.busca(rs.getInt("idfuncionario")),
                        produtodao.busca(rs.getInt("idproduto")),
                        rs.getFloat("TotalVenda"),
                        rs.getString("data"),
                        rs.getString("horario"),
                        rs.getString("rastreio"),
                        rs.getString("detalhes")));
        }
        pst.close();
    return listavenda;
    }
    
    public List<VendaMLM> BuscaTotalVendaMLAno(String ano1, String ano2) throws SQLException{
        List<VendaMLM> listavenda = new ArrayList<>();
        sql = "select id, idfuncionario, idproduto, SUM(TotalVenda) AS TotalVenda, DATE_FORMAT( data, \"%d/%m/%Y\" ) AS data, horario, rastreio, detalhes from VendaML WHERE data >= (?) and data <= (?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, ano1);
        pst.setString(2, ano2);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listavenda.add(new VendaMLM(
                        rs.getInt("id"),
                        funcionariodao.busca(rs.getInt("idfuncionario")),
                        produtodao.busca(rs.getInt("idproduto")),
                        rs.getFloat("TotalVenda"),
                        rs.getString("data"),
                        rs.getString("horario"),
                        rs.getString("rastreio"),
                        rs.getString("detalhes")));
        }
        pst.close();
    return listavenda;
    }
    
    
    public List<DespesaM> BuscaTotalDespesaMes(String de, String ate) throws SQLException{
        List<DespesaM> listaDespesas = new ArrayList<>();
        sql = "select id, Descricao, SUM(Valor) AS Valor, DATE_FORMAT( data, \"%d/%m/%Y\" ) AS data, horario from Despesas WHERE data >= (?) and data <= (?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, de);
        pst.setString(2, ate);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaDespesas.add(new DespesaM(
                        rs.getInt("id"),
                        rs.getString("Descricao"),
                        rs.getFloat("Valor"),
                        rs.getString("Data"),
                        rs.getString("Hora")));
        }
        pst.close();
    return listaDespesas;
    }
    
    public List<DespesaM> BuscaTotalDespesaAno(String ano1, String ano2) throws SQLException{
        List<DespesaM> listaDespesas = new ArrayList<>();
        sql = "select id, Descricao, SUM(Valor) AS Valor, DATE_FORMAT( data, \"%d/%m/%Y\" ) AS data, horario from Despesas WHERE data >= (?) and data <= (?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, ano1);
        pst.setString(2, ano2);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaDespesas.add(new DespesaM(
                        rs.getInt("id"),
                        rs.getString("Descricao"),
                        rs.getFloat("Valor"),
                        rs.getString("Data"),
                        rs.getString("Hora")));
        }
        pst.close();
    return listaDespesas;
    }
    
    public List<EntradadeProdutoM> BuscaTotalEntradeDeProdutoMes(String de, String ate) throws SQLException{
        List<EntradadeProdutoM> listaEntrada = new ArrayList<>();
        sql = "select Id, IdProduto, Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ) AS Data, Hora, Count(Quantidade) AS Quantidade from EntradadeProduto WHERE data >= (?) and data <= (?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, de);
        pst.setString(2, ate);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaEntrada.add(new EntradadeProdutoM(
                        rs.getInt("Id"),
                            produtodao.busca(rs.getInt("IdProduto")),
                            rs.getString("Data"),
                            rs.getString("Hora"),
                            rs.getInt("Quantidade")));
        }
        pst.close();
    return listaEntrada;
    }
    
    public List<EntradadeProdutoM> BuscaTotalEntradeDeProdutoAno(String ano1, String ano2) throws SQLException{
        List<EntradadeProdutoM> listaEntrada = new ArrayList<>();
        sql = "select Id, IdProduto, Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ) AS Data, Hora, Count(Quantidade) AS Quantidade from EntradadeProduto WHERE data >= (?) and data <= (?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, ano1);
        pst.setString(2, ano2);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaEntrada.add(new EntradadeProdutoM(
                        rs.getInt("Id"),
                            produtodao.busca(rs.getInt("IdProduto")),
                            rs.getString("Data"),
                            rs.getString("Hora"),
                            rs.getInt("Quantidade")));
        }
        pst.close();
    return listaEntrada;
    }

}