package DAO;

import MODEL.FuncionarioM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo-NOTE
 */
public class FuncionarioDao {
    
    public void salvar (FuncionarioM funcionario) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "insert into Funcionario set id = ?, nome = ?, cpf = ?, rg = ?, Nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ),"
                + " telefone = ?, celular1 = ?, celular2 = ?, login = ?, senha = ? admin = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1,0);
        pst.setString(2, funcionario.getNome());
        pst.setString(3, funcionario.getCpf());
        pst.setString(4, funcionario.getRg());
        pst.setString(5, funcionario.getNascimento());
        pst.setString(6, funcionario.getTelefone());
        pst.setString(7, funcionario.getCelular1());
        pst.setString(8, funcionario.getCelular2());
        pst.setString(9, funcionario.getLogin());
        pst.setString(10, funcionario.getSenha());
        pst.setBoolean(11, funcionario.getAdmin());        
        pst.execute();
        pst.close();
    }
    
    public void excluir(FuncionarioM funcionario) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "delete from Funcionario where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, funcionario.getId());
        pst.execute();
        pst.close();
    }
      
    public void alterar(FuncionarioM funcionario) throws SQLException{
        PreparedStatement pst;
        String sql;
        sql = "update Funcionario set "
                        + "nome = ?, "
                        + "cpf = ?, "
                        + "rg = ?, "
                        + "Nascimento = STR_TO_DATE( ?, \"%d/%m/%Y\" ), "
                        + "telefone = ?, "
                        + "celular1 = ?, "
                        + "celular2 = ?, "
                        + "login = ?, "
                        + "senha = ?, "
                        + "admin = ? "

                        + "where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, funcionario.getNome());
        pst.setString(2, funcionario.getCpf());
        pst.setString(3, funcionario.getRg());
        pst.setString(4, funcionario.getNascimento());
        pst.setString(5, funcionario.getTelefone());
        pst.setString(6, funcionario.getCelular1());
        pst.setString(7, funcionario.getCelular2());
        pst.setString(8, funcionario.getLogin());
        pst.setString(9, funcionario.getSenha());
        pst.setBoolean(10, funcionario.getAdmin());
        pst.setInt(11, funcionario.getId());
        pst.execute();
        pst.close();
     }
    
    public List<FuncionarioM> listaTodos() throws SQLException{
        PreparedStatement pst;
        String sql;
        List<FuncionarioM> listaFuncionario = new ArrayList<>();
        sql = "select id, nome, cpf, rg, DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, telefone, celular1, celular2, login, senha, admin from Funcionario order by nome";
        pst = Conexao.getInstance().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaFuncionario.add(new FuncionarioM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("nascimento"),
                            rs.getString("telefone"),
                            rs.getString("celular1"),
                            rs.getString("celular2"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getBoolean("admin")));
        }
        pst.close();
        return listaFuncionario;
    }
    
    public FuncionarioM busca(int id) throws SQLException{
        PreparedStatement pst;
        String sql;
        FuncionarioM funcionario = null;        
        sql = "select id, nome, cpf, rg, DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, telefone, celular1, celular2,"
                + "login, senha, admin from Funcionario where id = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            funcionario = new FuncionarioM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("nascimento"),
                            rs.getString("telefone"),
                            rs.getString("celular1"),
                            rs.getString("celular2"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getBoolean("admin"));
        }
        pst.close();
        return funcionario;
    }
    
    public FuncionarioM buscaNome(String nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        FuncionarioM funcionario = null;        
        sql = "select id, nome, cpf, rg, DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, telefone, celular1, celular2,"
                + "login, senha, admin from Funcionario where nome = ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, nome);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            funcionario = new FuncionarioM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("nascimento"),
                            rs.getString("telefone"),
                            rs.getString("celular1"),
                            rs.getString("celular2"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getBoolean("admin"));
        }
        pst.close();
        return funcionario;
    }
    
    public List<FuncionarioM> buscaNomeLista(String Nome) throws SQLException{
        PreparedStatement pst;
        String sql;
        List<FuncionarioM> listaFuncionario = new ArrayList<>();
        String name = "%"+Nome+"%";
        sql = "select id,nome, cpf, rg, DATE_FORMAT( Nascimento, \"%d/%m/%Y\" ) AS Nascimento, telefone, celular1, celular2,"
                + "login, senha, admin from Funcionario where nome like ?";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setString(1, name);
        pst.execute();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            listaFuncionario.add(new FuncionarioM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("nascimento"),
                            rs.getString("telefone"),
                            rs.getString("celular1"),
                            rs.getString("celular2"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getBoolean("admin")));
        }
        pst.close();
        return listaFuncionario;
    }
    
    public FuncionarioM valida(String user, String senha) throws SQLException{
        PreparedStatement pst;
        String sql;
        FuncionarioM funcionario = null;
           sql = "select * from Funcionario where login = ? and senha = ?";
           pst = Conexao.getInstance().prepareStatement(sql);
           pst.setString(1, user);
           pst.setString(2, senha);
           
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               funcionario = new FuncionarioM(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("rg"),
                            rs.getString("nascimento"),
                            rs.getString("telefone"),
                            rs.getString("celular1"),
                            rs.getString("celular2"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getBoolean("admin"));;
            }
            pst.close();
            return funcionario;
    }
}
