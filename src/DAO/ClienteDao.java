package DAO;

import MODEL.ClienteM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo-NOTE
 */
public class ClienteDao {
    
    public void salvar (ClienteM Cliente) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "insert into Cliente values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setString(2, Cliente.getNome());
        pst.setString(3, Cliente.getRg());
        pst.setString(4, Cliente.getCpf());
        pst.setString(5, Cliente.getRua());
        pst.setString(6, Cliente.getNumero());
        pst.setString(7, Cliente.getBairro()); 
        pst.setString(8, Cliente.getCidade());
        pst.setString(9, Cliente.getTelefone());
        pst.setString(10, Cliente.getCelular1());
        pst.setString(11, Cliente.getCelular2());
        pst.setString(12, Cliente.getNascimento());
        pst.setString(13, Cliente.getObservacao());         
        pst.execute();
        pst.close();
    }
    
    public void excluir(ClienteM Cliente) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "delete from Cliente where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, Cliente.getId());
        pst.execute();
        pst.close();
    }
      
    public void alterar(ClienteM Cliente) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Funcionario set "
                        + "nome = ?, "
                        + "cpf = ?, "
                        + "rg = ?, "
                        + "rua = ?, "
                        + "numero = ?, "
                        + "bairro  = ?, "
                        + "cidade  = ?, "
                        + "telefone  = ?, "
                        + "celular1  = ?, "
                        + "celular2  = ?, "
                        + "nascimento  = ?, "
                        + "observacao  = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, Cliente.getNome());
        pst.setString(2, Cliente.getRua());
        pst.setString(3, Cliente.getNumero());
        pst.setString(4, Cliente.getBairro()); 
        pst.setString(5, Cliente.getCidade());
        pst.setString(6, Cliente.getTelefone());
        pst.setString(7, Cliente.getCelular1());
        pst.setString(8, Cliente.getCelular2());
        pst.setString(9, Cliente.getNascimento());
        pst.setString(10, Cliente.getObservacao());
        pst.setInt(11,Cliente.getId());
        pst.execute();
        pst.close();
     }
    
    public List<ClienteM> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;
        List<ClienteM> listaCliente = new ArrayList<>();
        sql = "select * from Cliente order by nome";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            listaCliente.add(new ClienteM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("rua"),
                            rs.getString("numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("telefone"),
                            rs.getString("celular1"),
                            rs.getString("celular2"),
                            rs.getString("nascimento"),
                            rs.getString("observacao")));
        }
        pst.close();
        return listaCliente;
    }
    
    public ClienteM busca(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        ClienteM cliente = null;        
        sql = "select * from Cliente where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            cliente = new ClienteM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("rua"),
                            rs.getString("numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("telefone"),
                            rs.getString("celular1"),
                            rs.getString("celular2"),
                            rs.getString("nascimento"),
                            rs.getString("observacao"));
        }
        pst.close();
        return cliente;
    }
    
    public List<ClienteM> buscaNomeLista(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<ClienteM> listaCliente = new ArrayList<>();
        String name = "%"+Nome+"%";
        sql = "select * from Cliente where nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaCliente.add(new ClienteM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("rua"),
                            rs.getString("numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("telefone"),
                            rs.getString("celular1"),
                            rs.getString("celular2"),
                            rs.getString("nascimento"),
                            rs.getString("observacao")));
        }

        pst.close();
        return listaCliente;
    }
}
