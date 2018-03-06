/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.ValidaM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Danilo-NOTE
 */
public class ValidaDao {
    
    public ValidaM valida() throws SQLException{
        PreparedStatement pst;
        String sql;
        ValidaM valida = null;
           sql = "select * from DataDoDia where id = 1";
           pst = Conexao.getInstance().prepareStatement(sql);
           
           ResultSet rs = pst.executeQuery();
           while(rs.next()){
               valida = new ValidaM(
                            rs.getInt("id"),
                            rs.getInt("mes"),
                            rs.getInt("ano"));
            }
            pst.close();
            return valida;
    }
}
