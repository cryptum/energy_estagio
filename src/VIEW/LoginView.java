package VIEW;

import DAO.FuncionarioDao;
import DAO.ValidaDao;
import MODEL.FuncionarioM;
import MODEL.ValidaM;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicButtonUI;
import util.LimiteDigitos;

/**
 *
 * @author Danilo-NOTE
 */
public class LoginView extends javax.swing.JFrame {

    FuncionarioM funcionario = new FuncionarioM();
    FuncionarioDao funcionariodao = new FuncionarioDao();
    
    ValidaM valida = new ValidaM();
    ValidaDao validadao = new ValidaDao();
    
    public LoginView() {
        initComponents();
        this.setLocationRelativeTo(null);
        lblErro.setVisible(false);
        txtUser.setDocument(new LimiteDigitos(45));
        txtSenha.setDocument(new LimiteDigitos(45));
        txtUser.setText("login");
        txtSenha.setText("123");
        lblDesenvolvedor.setVisible(false);
        
        btnLogin.setUI(new BasicButtonUI());
        
        String datasistema = new SimpleDateFormat("MM").format(new Date(System.currentTimeMillis()));
        String datasistema2 = new SimpleDateFormat("YYYY").format(new Date(System.currentTimeMillis()));
        int datames= Integer.parseInt(datasistema);
        int dataano= Integer.parseInt(datasistema2);
        
        try {
            valida = validadao.valida();
        } catch (SQLException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "A data expirou!");
            JOptionPane.showMessageDialog(null, "Sem conexão com o Banco de Dados!\nSolicite seu Programador!");
            System.exit(0);
        }
            if((datames <= valida.getMes())&&(dataano<= valida.getAno())){
                
            }else{
                this.setVisible(true);
                lblErro.setVisible(true);
                lblErro.setText("O sistema Contém um problema");
                txtUser.setVisible(false);
                txtSenha.setVisible(false);
                btnLogin.setEnabled(false);
                
        }
        URL url = this.getClass().getResource("Icones Icon/icone.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnLogin = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lblDesenvolvedor = new javax.swing.JLabel();
        lblversão = new javax.swing.JLabel();
        lblErro = new javax.swing.JLabel();
        BackGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Senha.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 550, -1, -1));

        txtSenha.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtSenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSenha.setBorder(null);
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });
        getContentPane().add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 550, 137, 32));

        jSeparator3.setForeground(new java.awt.Color(102, 102, 102));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 590, 202, 10));

        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 590, 202, 10));

        btnLogin.setBackground(new java.awt.Color(255, 255, 255));
        btnLogin.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnLogin.setText("Entrar");
        btnLogin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 1, true));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 650, 93, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Login.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 550, -1, -1));

        txtUser.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUser.setBorder(null);
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserKeyPressed(evt);
            }
        });
        getContentPane().add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 550, 137, 30));

        lblDesenvolvedor.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        lblDesenvolvedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDesenvolvedor.setText("<html><p align = \"center\">Desenvolvedor: Danilo Vieira Bernardes - Contato: danilovbvip@hotmail.com <br> Desing: Bruno Pinheiro Vieira - Contato: bruno.artevisual@gmail.com<br>Todos os direitos reservados da Energy Auto Som </p></html>");
        getContentPane().add(lblDesenvolvedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 660, -1));

        lblversão.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        lblversão.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblversão.setText("EnergyWare Versão 3.9 BETA");
        lblversão.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblversãoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblversãoMouseExited(evt);
            }
        });
        getContentPane().add(lblversão, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 246, -1));

        lblErro.setFont(new java.awt.Font("Champagne & Limousines", 1, 24)); // NOI18N
        lblErro.setForeground(new java.awt.Color(192, 39, 39));
        lblErro.setText("Senha ou usuário incorretos!");
        getContentPane().add(lblErro, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 730, -1, -1));

        BackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/tela iniciar login.png"))); // NOI18N
        getContentPane().add(BackGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        funcionario = null;
        try {
            if (txtUser.getText().isEmpty()) {
                //erro.setText("O nome do usuario deve ser preechido");
                JOptionPane.showMessageDialog(null, "O usuário deve ser preenchido", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                txtUser.requestFocus();

            } else if (txtSenha.getText().isEmpty()) {
                //erro.setText("A senha deve ser preechido");
                //erro.setVisible(true);
                JOptionPane.showMessageDialog(null, "A senha deve ser preenchida", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                txtSenha.requestFocus();
            } else {
                funcionario = funcionariodao.valida(txtUser.getText(), txtSenha.getText());
                if(funcionario == null){
                    lblErro.setVisible(true);
                    txtSenha.setText("");
                    txtSenha.requestFocus();
                }else{
       
                    PrincipalView3 principal = new PrincipalView3();
                    this.dispose();
                    
                }
                
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
            txtUser.setText("");
            txtSenha.setText("");
            txtUser.requestFocus();
            ex.printStackTrace();
            
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        
        funcionario = null;
        try {
            if (txtUser.getText().isEmpty()) {
                //erro.setText("O nome do usuario deve ser preechido");
                JOptionPane.showMessageDialog(null, "O usuário deve ser preenchido", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                txtUser.requestFocus();

            } else if (txtSenha.getText().isEmpty()) {
                //erro.setText("A senha deve ser preechido");
                //erro.setVisible(true);
                JOptionPane.showMessageDialog(null, "A senha deve ser preenchida", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                txtSenha.requestFocus();
            } else {
                funcionario = funcionariodao.valida(txtUser.getText(), txtSenha.getText());
                if(funcionario == null){
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
                    txtSenha.setText("");
                    txtSenha.requestFocus();
                }else{
       
                    PrincipalView3 principal = new PrincipalView3();
                    this.dispose();
                    
                }
                
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
            txtUser.setText("");
            txtSenha.setText("");
            txtUser.requestFocus();
            ex.printStackTrace();
            
        } 
      }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void txtUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSenha.requestFocusInWindow();
        }
    }//GEN-LAST:event_txtUserKeyPressed

    private void lblversãoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblversãoMouseEntered
        lblDesenvolvedor.setVisible(true);
    }//GEN-LAST:event_lblversãoMouseEntered

    private void lblversãoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblversãoMouseExited
        lblDesenvolvedor.setVisible(false);
    }//GEN-LAST:event_lblversãoMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackGround;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblDesenvolvedor;
    private javax.swing.JLabel lblErro;
    private javax.swing.JLabel lblversão;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
