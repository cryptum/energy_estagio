/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import java.awt.Color;

import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Danilo-NOTE
 */
public class PrincipalView extends javax.swing.JFrame {

    /**
     * Creates new form PrincipalView
     */
    public PrincipalView() {
        initComponents();
        this.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Venda = new javax.swing.JLabel();
        Cliente = new javax.swing.JLabel();
        MarcLivre = new javax.swing.JLabel();
        Relatorios = new javax.swing.JLabel();
        Estoque = new javax.swing.JLabel();
        Vendedor = new javax.swing.JLabel();
        Relatorios1 = new javax.swing.JLabel();
        pnlPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(40, 40, 40));

        Venda.setBackground(new java.awt.Color(204, 0, 255));
        Venda.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        Venda.setForeground(new java.awt.Color(227, 227, 227));
        Venda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Venda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jogo 2 - Para Fundo Cinza Escuro ((#404040)  esse cinza em RGB)/Vendas.png"))); // NOI18N
        Venda.setText("Venda");
        Venda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Venda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Venda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                VendaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                VendaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                VendaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                VendaMouseReleased(evt);
            }
        });

        Cliente.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        Cliente.setForeground(new java.awt.Color(227, 227, 227));
        Cliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jogo 2 - Para Fundo Cinza Escuro ((#404040)  esse cinza em RGB)/Cliente.png"))); // NOI18N
        Cliente.setText("Cliente");
        Cliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Cliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ClienteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ClienteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ClienteMouseReleased(evt);
            }
        });

        MarcLivre.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        MarcLivre.setForeground(new java.awt.Color(227, 227, 227));
        MarcLivre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MarcLivre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jogo 2 - Para Fundo Cinza Escuro ((#404040)  esse cinza em RGB)/Mercado Livre.png"))); // NOI18N
        MarcLivre.setText("<html><p align = \"center\">Mercado<br>Livre</p></html> "); // NOI18N
        MarcLivre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MarcLivre.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MarcLivre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MarcLivreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MarcLivreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MarcLivreMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MarcLivreMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                MarcLivreMouseReleased(evt);
            }
        });

        Relatorios.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        Relatorios.setForeground(new java.awt.Color(227, 227, 227));
        Relatorios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Relatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jogo 2 - Para Fundo Cinza Escuro ((#404040)  esse cinza em RGB)/Relatorios.png"))); // NOI18N
        Relatorios.setText("Relatórios");
        Relatorios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Relatorios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Relatorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RelatoriosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RelatoriosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RelatoriosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                RelatoriosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                RelatoriosMouseReleased(evt);
            }
        });

        Estoque.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        Estoque.setForeground(new java.awt.Color(227, 227, 227));
        Estoque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Estoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jogo 2 - Para Fundo Cinza Escuro ((#404040)  esse cinza em RGB)/Estoque.png"))); // NOI18N
        Estoque.setText("Estoque");
        Estoque.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Estoque.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Estoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EstoqueMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EstoqueMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EstoqueMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EstoqueMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                EstoqueMouseReleased(evt);
            }
        });

        Vendedor.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        Vendedor.setForeground(new java.awt.Color(227, 227, 227));
        Vendedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Vendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jogo 2 - Para Fundo Cinza Escuro ((#404040)  esse cinza em RGB)/Funcionario.png"))); // NOI18N
        Vendedor.setText("Funcionario");
        Vendedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Vendedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Vendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VendedorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                VendedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                VendedorMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                VendedorMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                VendedorMouseReleased(evt);
            }
        });

        Relatorios1.setFont(new java.awt.Font("Myanmar Text", 1, 18)); // NOI18N
        Relatorios1.setForeground(new java.awt.Color(227, 227, 227));
        Relatorios1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Relatorios1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jogo 2 - Para Fundo Cinza Escuro ((#404040)  esse cinza em RGB)/Estatisticas.png"))); // NOI18N
        Relatorios1.setText("Situação");
        Relatorios1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Relatorios1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Relatorios1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Relatorios1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Relatorios1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Relatorios1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Relatorios1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Relatorios1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(Estoque, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Cliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Venda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MarcLivre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addComponent(Vendedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(Relatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Relatorios1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Estoque, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Vendedor)
                .addGap(0, 0, 0)
                .addComponent(Venda)
                .addGap(0, 0, 0)
                .addComponent(MarcLivre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Relatorios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Relatorios1)
                .addContainerGap())
        );

        pnlPrincipal.setBackground(new java.awt.Color(230, 230, 230));

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1111, Short.MAX_VALUE)
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClienteMouseClicked
        ClienteView Cliente = new ClienteView();
            pnlPrincipal.removeAll();
            pnlPrincipal.add(Cliente);
            pnlPrincipal.updateUI();
    }//GEN-LAST:event_ClienteMouseClicked

    private void VendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendaMouseClicked
        VendaView venda = new VendaView();
            pnlPrincipal.removeAll();
            pnlPrincipal.add(venda);
            pnlPrincipal.updateUI();
    }//GEN-LAST:event_VendaMouseClicked

    private void EstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EstoqueMouseClicked
        ProdutoView Produto = new ProdutoView();
            pnlPrincipal.removeAll();
            pnlPrincipal.add(Produto);
            pnlPrincipal.updateUI();
    }//GEN-LAST:event_EstoqueMouseClicked

    private void MarcLivreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MarcLivreMouseClicked
        VendaViewML VendaMl = new VendaViewML();
            pnlPrincipal.removeAll();
            pnlPrincipal.add(VendaMl);
            pnlPrincipal.updateUI();
    }//GEN-LAST:event_MarcLivreMouseClicked

    private void RelatoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RelatoriosMouseClicked
        RelatorioView Relatorio = new RelatorioView();
            pnlPrincipal.removeAll();
            pnlPrincipal.add(Relatorio);
            pnlPrincipal.updateUI();
    }//GEN-LAST:event_RelatoriosMouseClicked

    private void VendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendedorMouseClicked
        FuncionarioView Funcionario = new FuncionarioView();
            pnlPrincipal.removeAll();
            pnlPrincipal.add(Funcionario);
            pnlPrincipal.updateUI();
    }//GEN-LAST:event_VendedorMouseClicked

    private void ClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClienteMouseEntered
        Cliente.setForeground(Color.GRAY);
    }//GEN-LAST:event_ClienteMouseEntered

    private void ClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClienteMouseExited
        Cliente.setForeground(Color.getHSBColor(90 , 20 , 120));
    }//GEN-LAST:event_ClienteMouseExited

    private void ClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClienteMousePressed
        Cliente.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_ClienteMousePressed

    private void ClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClienteMouseReleased
        Cliente.setForeground(Color.getHSBColor(90 , 20 , 120));
    }//GEN-LAST:event_ClienteMouseReleased

    private void EstoqueMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EstoqueMouseEntered
        Estoque.setForeground(Color.GRAY);
    }//GEN-LAST:event_EstoqueMouseEntered

    private void EstoqueMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EstoqueMouseExited
        Estoque.setForeground(Color.getHSBColor(90, 20, 120));
    }//GEN-LAST:event_EstoqueMouseExited

    private void EstoqueMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EstoqueMousePressed
        Estoque.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_EstoqueMousePressed

    private void EstoqueMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EstoqueMouseReleased
        Estoque.setForeground(Color.getHSBColor(90 , 20 , 120));
    }//GEN-LAST:event_EstoqueMouseReleased

    private void VendedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendedorMouseEntered
        Vendedor.setForeground(Color.GRAY);
    }//GEN-LAST:event_VendedorMouseEntered

    private void VendedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendedorMouseExited
        Vendedor.setForeground(Color.getHSBColor(90, 20, 120));
    }//GEN-LAST:event_VendedorMouseExited

    private void VendedorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendedorMousePressed
        Vendedor.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_VendedorMousePressed

    private void VendedorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendedorMouseReleased
        Vendedor.setForeground(Color.getHSBColor(90 , 20 , 120));
    }//GEN-LAST:event_VendedorMouseReleased

    private void VendaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendaMouseEntered
        Venda.setForeground(Color.GRAY);
    }//GEN-LAST:event_VendaMouseEntered

    private void VendaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendaMouseExited
        Venda.setForeground(Color.getHSBColor(90, 20, 120));
    }//GEN-LAST:event_VendaMouseExited

    private void VendaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendaMousePressed
        Venda.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_VendaMousePressed

    private void VendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VendaMouseReleased
        Venda.setForeground(Color.getHSBColor(90 , 20 , 120));
    }//GEN-LAST:event_VendaMouseReleased

    private void MarcLivreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MarcLivreMouseEntered
        MarcLivre.setForeground(Color.GRAY);
    }//GEN-LAST:event_MarcLivreMouseEntered

    private void MarcLivreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MarcLivreMouseExited
        MarcLivre.setForeground(Color.getHSBColor(90, 20, 120));
    }//GEN-LAST:event_MarcLivreMouseExited

    private void MarcLivreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MarcLivreMousePressed
        MarcLivre.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_MarcLivreMousePressed

    private void MarcLivreMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MarcLivreMouseReleased
        MarcLivre.setForeground(Color.getHSBColor(90 , 20 , 120));
    }//GEN-LAST:event_MarcLivreMouseReleased

    private void RelatoriosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RelatoriosMouseEntered
        Relatorios.setForeground(Color.GRAY);
    }//GEN-LAST:event_RelatoriosMouseEntered

    private void RelatoriosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RelatoriosMouseExited
        Relatorios.setForeground(Color.getHSBColor(90, 20, 120));
    }//GEN-LAST:event_RelatoriosMouseExited

    private void RelatoriosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RelatoriosMousePressed
        Relatorios.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_RelatoriosMousePressed

    private void RelatoriosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RelatoriosMouseReleased
        Relatorios.setForeground(Color.getHSBColor(90 , 20 , 120));
    }//GEN-LAST:event_RelatoriosMouseReleased

    private void Relatorios1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Relatorios1MouseClicked
        SituacaoDaEmpresa situ = new SituacaoDaEmpresa();
        pnlPrincipal.removeAll();
        pnlPrincipal.add(situ);
        pnlPrincipal.updateUI();
    }//GEN-LAST:event_Relatorios1MouseClicked

    private void Relatorios1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Relatorios1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Relatorios1MouseEntered

    private void Relatorios1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Relatorios1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Relatorios1MouseExited

    private void Relatorios1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Relatorios1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Relatorios1MousePressed

    private void Relatorios1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Relatorios1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Relatorios1MouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cliente;
    private javax.swing.JLabel Estoque;
    private javax.swing.JLabel MarcLivre;
    private javax.swing.JLabel Relatorios;
    private javax.swing.JLabel Relatorios1;
    private javax.swing.JLabel Venda;
    private javax.swing.JLabel Vendedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables
}
