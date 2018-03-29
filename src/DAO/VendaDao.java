package DAO;

import MODEL.ClienteM;
import MODEL.VendaM;
import MODEL.ItensVenda;
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
public class VendaDao {
    
    PreparedStatement pst;
    String sql;
    ClienteDao clientedao = new ClienteDao();
    FuncionarioDao funcionariodao = new FuncionarioDao();
    CategoriaDao categoriadao = new CategoriaDao();
    MarcaDao marcadao = new MarcaDao();
    ModeloDao modelodao = new ModeloDao();
    
    public void salvar (VendaM venda,List<ItensVenda> item ) throws SQLException{

        int idvenda = 0;
        sql = "insert into Venda set id = ?, idcliente = ?, idfuncionario = ?, Data = STR_TO_DATE( ?, \"%d/%m/%Y\" ), totalvenda = ?, formapagamento = ?, Excluido = ?";
        pst = Conexao.getInstance().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1,0);
        pst.setInt(2, venda.getIdCliente().getId());
        pst.setInt(3, venda.getIdFuncionario().getId());
        pst.setString(4, venda.getData());
        pst.setFloat(5, venda.getTotalVendas());
        pst.setString(6, venda.getFormaPagamento());
        pst.setBoolean(7, venda.getExcluido());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        while (rs.next()) {
            idvenda = rs.getInt(1);
        }
        pst.close();
        salvarItensVenda((List<ItensVenda>) item, idvenda);
        
    }
    
    public void salvarItensVenda (List<ItensVenda> item, int idVenda) throws SQLException{
        for(ItensVenda itens : item){
            
            sql = "insert into ItemVenda values(?,?,?,?,?,?,?)";
            pst = Conexao.getInstance().prepareStatement(sql);
            pst.setInt(1,0);
            pst.setInt(2, idVenda);
            pst.setInt(3, itens.getIdProduto().getId());
            pst.setInt(4, itens.getQuantidade());
            pst.setFloat(5, itens.getPreco());
            pst.setFloat(6, itens.getTotal());
            pst.setBoolean(7, itens.getExcluido());
            pst.execute();
            pst.close();
            buscaquantidade(itens.getIdProduto().getId(), itens.getQuantidade());
        }
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
                            categoriadao.busca(rs.getInt("idcategoria")),
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
      
    
    
    public List<VendaM> listaTodos() throws SQLException{
        List<VendaM> listavenda = new ArrayList<>();
        sql = "select id, idcliente, idfuncionario, DATE_FORMAT( data, \"%d/%m/%Y\" ) AS data, totalvenda, formapagamento, excluido from Venda ORDER BY id DESC";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while(rs.next()){
            listavenda.add(new VendaM(
                        rs.getInt("id"),
                        clientedao.busca(rs.getInt("idcliente")),
                        funcionariodao.busca(rs.getInt("idfuncionario")),
                        rs.getString("data"),
                        rs.getFloat("totalvenda"),
                        rs.getString("formapagamento"),
                        rs.getBoolean("excluido")));
        }
        pst.close();
    return listavenda;
    }
    
    public VendaM busca(int id) throws SQLException{
        VendaM venda = null;
        sql = "select id, idcliente, idfuncionario, DATE_FORMAT( data, \"%d/%m/%Y\" ) AS data, totalvenda, formapagamento, excluido from Venda where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            venda = new VendaM(
            rs.getInt("id"),
            clientedao.busca(rs.getInt("idcliente")),
            funcionariodao.busca(rs.getInt("idfuncionario")),
                        rs.getString("data"),
                        rs.getFloat("totalvenda"),
                        rs.getString("formapagamento"),
                        rs.getBoolean("excluido"));
        }
        pst.close();
        return venda;
    }
    
    public List<VendaM> buscaNomeLista(int Nome) throws SQLException{
        List<VendaM> listavenda = new ArrayList<>();
        //String name = "%"+Nome+"%";
        sql = "select id, idcliente, idfuncionario, DATE_FORMAT( data, \"%d/%m/%Y\" ) AS data, totalvenda, formapagamento, excluido from Venda where idcliente like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, Nome);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listavenda.add(new VendaM(
            rs.getInt("id"),
            clientedao.busca(rs.getInt("idcliente")),
            funcionariodao.busca(rs.getInt("idfuncionario")),
                        rs.getString("data"),
                        rs.getFloat("totalvenda"),
                        rs.getString("formapagamento"),
                        rs.getBoolean("excluido")));
        }

        pst.close();
        return listavenda;
    }
    
    public List<VendaM> buscaDataLista(String de, String ate) throws SQLException{
        List<VendaM> listavenda = new ArrayList<>();
        //String name = "%"+Nome+"%";
        sql = "select id, idcliente, idfuncionario, DATE_FORMAT( data, \"%d/%m/%Y\" ) AS data, totalvenda, formapagamento, excluido from Venda where Data >= STR_TO_DATE( ?, \"%d/%m/%Y\" ) and Data <= STR_TO_DATE( ?, \"%d/%m/%Y\" ) ";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, de);
        pst.setString(2, ate);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listavenda.add(new VendaM(
            rs.getInt("id"),
            clientedao.busca(rs.getInt("idcliente")),
            funcionariodao.busca(rs.getInt("idfuncionario")),
                        rs.getString("data"),
                        rs.getFloat("totalvenda"),
                        rs.getString("formapagamento"),
                        rs.getBoolean("excluido")));
        }

        pst.close();
        return listavenda;
    }
    
    public List<VendaM> buscaClienteLista(String nome) throws SQLException{
        List<VendaM> listavenda = new ArrayList<>();
        String name = "%"+nome+"%";
        sql = "select venda.id, idcliente, idfuncionario, DATE_FORMAT( data, \"%d/%m/%Y\" ) AS data, totalvenda, formapagamento, excluido from Venda, Cliente where Cliente.id = Venda.IdCliente and Cliente.Nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listavenda.add(new VendaM(
            rs.getInt("id"),
            clientedao.busca(rs.getInt("idcliente")),
            funcionariodao.busca(rs.getInt("idfuncionario")),
                        rs.getString("data"),
                        rs.getFloat("totalvenda"),
                        rs.getString("formapagamento"),
                        rs.getBoolean("excluido")));
        }

        pst.close();
        return listavenda;
    }
    
    public void alterarVendaTrue(VendaM venda) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Venda set "
                        + "excluido  = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setBoolean(1, venda.getExcluido());
        pst.setInt(2,venda.getId());
        pst.execute();
        pst.close();
    }
}
