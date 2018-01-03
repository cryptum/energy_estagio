package DAO;

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
public class ItemvendaDao {

    ClienteDao clientedao = new ClienteDao();
    ProdutoDao produtodao = new ProdutoDao();
    VendaDao vendadao = new VendaDao();
    
    public List<ItensVenda> busca(int idvenda) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<ItensVenda> listaitens = new ArrayList<>();
        sql = "select id, idvenda, idproduto, quantidade, preco, total from itemvenda where idvenda = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, idvenda);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaitens.add(new ItensVenda(
            rs.getInt("id"),
            vendadao.busca(rs.getInt("IdVenda")),
            produtodao.busca(rs.getInt("IdProduto")),
            rs.getInt("quantidade"),
            rs.getFloat("preco"),
            rs.getFloat("total")));
        }
        pst.close();
        return listaitens;
    }
}
