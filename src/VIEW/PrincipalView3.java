/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Danilo-NOTE
 */
public class PrincipalView3 extends javax.swing.JFrame {

    /**
     * Creates new form PrincipalView
     */
    public PrincipalView3() {
        initComponents();
        this.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);
        
        URL url = this.getClass().getResource("Icones Icon/icone.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);

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
        btnEstoque = new javax.swing.JLabel();
        btnVendedor = new javax.swing.JLabel();
        btnVenda = new javax.swing.JLabel();
        btnMarcLivre = new javax.swing.JLabel();
        btnRelatorios = new javax.swing.JLabel();
        btnSituacao = new javax.swing.JLabel();
        btnCliente = new javax.swing.JLabel();
        pnlPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnEstoque.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnEstoque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Estoque.png"))); // NOI18N
        btnEstoque.setText("Estoque");
        btnEstoque.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEstoque.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEstoqueMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEstoqueMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEstoqueMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEstoqueMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnEstoqueMouseReleased(evt);
            }
        });

        btnVendedor.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnVendedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Funcionario.png"))); // NOI18N
        btnVendedor.setText("Funcionario");
        btnVendedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVendedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVendedorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVendedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVendedorMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnVendedorMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnVendedorMouseReleased(evt);
            }
        });

        btnVenda.setBackground(new java.awt.Color(204, 0, 255));
        btnVenda.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnVenda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Vendas.png"))); // NOI18N
        btnVenda.setText("Venda");
        btnVenda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVenda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVendaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVendaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVendaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnVendaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnVendaMouseReleased(evt);
            }
        });

        btnMarcLivre.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnMarcLivre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnMarcLivre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Mercado Livre.png"))); // NOI18N
        btnMarcLivre.setText("Mercado"); // NOI18N
        btnMarcLivre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMarcLivre.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMarcLivre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMarcLivreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMarcLivreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMarcLivreMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMarcLivreMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnMarcLivreMouseReleased(evt);
            }
        });

        btnRelatorios.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnRelatorios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Relatorios.png"))); // NOI18N
        btnRelatorios.setText("Relat??rios");
        btnRelatorios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRelatorios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRelatorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRelatoriosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRelatoriosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRelatoriosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRelatoriosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnRelatoriosMouseReleased(evt);
            }
        });

        btnSituacao.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnSituacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSituacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Estatisticas.png"))); // NOI18N
        btnSituacao.setText("Situa????o");
        btnSituacao.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSituacao.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSituacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSituacaoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSituacaoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSituacaoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSituacaoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSituacaoMouseReleased(evt);
            }
        });

        btnCliente.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Cliente.png"))); // NOI18N
        btnCliente.setText("Cliente");
        btnCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClienteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnClienteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnClienteMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCliente)
                .addGap(15, 15, 15)
                .addComponent(btnEstoque)
                .addGap(15, 15, 15)
                .addComponent(btnVendedor)
                .addGap(15, 15, 15)
                .addComponent(btnVenda)
                .addGap(15, 15, 15)
                .addComponent(btnMarcLivre)
                .addGap(15, 15, 15)
                .addComponent(btnRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnSituacao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVendedor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEstoque, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnRelatorios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMarcLivre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVenda, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(btnSituacao, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCliente, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 775, Short.MAX_VALUE)
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVendaMouseClicked
        VendaView venda = new VendaView();
        ((BasicInternalFrameUI)venda.getUI()).setNorthPane(null);
            pnlPrincipal.removeAll();
            pnlPrincipal.add(venda);
            pnlPrincipal.updateUI();
            btnVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Vendas.png")));
            btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Cliente.png")));
            btnEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Estoque.png")));
            btnVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Funcionario.png")));
            btnMarcLivre.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Mercado livre.png")));
            btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Relatorios.png")));
            btnSituacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Estatisticas.png")));
    }//GEN-LAST:event_btnVendaMouseClicked

    private void btnEstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstoqueMouseClicked
        ProdutoView Produto = new ProdutoView();
        ((BasicInternalFrameUI)Produto.getUI()).setNorthPane(null);
            pnlPrincipal.removeAll();
            pnlPrincipal.add(Produto);
            pnlPrincipal.updateUI();
            btnEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Estoque.png")));
            btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Cliente.png")));
            btnVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Funcionario.png")));
            btnVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Vendas.png")));
            btnMarcLivre.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Mercado livre.png")));
            btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Relatorios.png")));
            btnSituacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Estatisticas.png")));
    }//GEN-LAST:event_btnEstoqueMouseClicked

    private void btnMarcLivreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMarcLivreMouseClicked
        VendaViewML VendaMl = new VendaViewML();
        ((BasicInternalFrameUI)VendaMl.getUI()).setNorthPane(null);
            pnlPrincipal.removeAll();
            pnlPrincipal.add(VendaMl);
            pnlPrincipal.updateUI();
            btnMarcLivre.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Mercado livre.png")));
            btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Cliente.png")));
            btnEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Estoque.png")));
            btnVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Funcionario.png")));
            btnVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Vendas.png")));
            btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Relatorios.png")));
            btnSituacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Estatisticas.png")));
    }//GEN-LAST:event_btnMarcLivreMouseClicked

    private void btnRelatoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRelatoriosMouseClicked
        RelatorioView Relatorio = new RelatorioView();
        ((BasicInternalFrameUI)Relatorio.getUI()).setNorthPane(null);
            pnlPrincipal.removeAll();
            pnlPrincipal.add(Relatorio);
            pnlPrincipal.updateUI();
            btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Relatorios.png")));
            btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Cliente.png")));
            btnEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Estoque.png")));
            btnVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Funcionario.png")));
            btnVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Vendas.png")));
            btnMarcLivre.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Mercado livre.png")));
            btnSituacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Estatisticas.png")));
    }//GEN-LAST:event_btnRelatoriosMouseClicked

    private void btnVendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVendedorMouseClicked
        FuncionarioView Funcionario = new FuncionarioView();
        ((BasicInternalFrameUI)Funcionario.getUI()).setNorthPane(null);
            pnlPrincipal.removeAll();
            pnlPrincipal.add(Funcionario);
            pnlPrincipal.updateUI();
            btnVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Funcionario.png")));
            btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Cliente.png")));
            btnEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Estoque.png")));
            btnVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Vendas.png")));
            btnMarcLivre.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Mercado livre.png")));
            btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Relatorios.png")));
            btnSituacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Estatisticas.png")));
    }//GEN-LAST:event_btnVendedorMouseClicked

    private void btnEstoqueMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstoqueMouseEntered
    btnEstoque.setForeground(Color.yellow);
    }//GEN-LAST:event_btnEstoqueMouseEntered

    private void btnEstoqueMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstoqueMouseExited
    btnEstoque.setForeground(Color.black);
    }//GEN-LAST:event_btnEstoqueMouseExited

    private void btnEstoqueMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstoqueMousePressed
    btnEstoque.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnEstoqueMousePressed

    private void btnEstoqueMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstoqueMouseReleased
    btnEstoque.setForeground(Color.yellow);
    }//GEN-LAST:event_btnEstoqueMouseReleased

    private void btnVendedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVendedorMouseEntered
    btnVendedor.setForeground(Color.yellow);
    }//GEN-LAST:event_btnVendedorMouseEntered

    private void btnVendedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVendedorMouseExited
    btnVendedor.setForeground(Color.black);
    }//GEN-LAST:event_btnVendedorMouseExited

    private void btnVendedorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVendedorMousePressed
    btnVendedor.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnVendedorMousePressed

    private void btnVendedorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVendedorMouseReleased
    btnVendedor.setForeground(Color.yellow);
    }//GEN-LAST:event_btnVendedorMouseReleased

    private void btnVendaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVendaMouseEntered
    btnVenda.setForeground(Color.YELLOW);
    }//GEN-LAST:event_btnVendaMouseEntered

    private void btnVendaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVendaMouseExited
    btnVenda.setForeground(Color.black);
    }//GEN-LAST:event_btnVendaMouseExited

    private void btnVendaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVendaMousePressed
    btnVenda.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnVendaMousePressed

    private void btnVendaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVendaMouseReleased
    btnVenda.setForeground(Color.yellow);
    }//GEN-LAST:event_btnVendaMouseReleased

    private void btnMarcLivreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMarcLivreMouseEntered
    btnMarcLivre.setForeground(Color.yellow);
    }//GEN-LAST:event_btnMarcLivreMouseEntered

    private void btnMarcLivreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMarcLivreMouseExited
    btnMarcLivre.setForeground(Color.black);
    }//GEN-LAST:event_btnMarcLivreMouseExited

    private void btnMarcLivreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMarcLivreMousePressed
    btnMarcLivre.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnMarcLivreMousePressed

    private void btnMarcLivreMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMarcLivreMouseReleased
    btnMarcLivre.setForeground(Color.yellow);
    }//GEN-LAST:event_btnMarcLivreMouseReleased

    private void btnRelatoriosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRelatoriosMouseEntered
    btnRelatorios.setForeground(Color.yellow);
    }//GEN-LAST:event_btnRelatoriosMouseEntered

    private void btnRelatoriosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRelatoriosMouseExited
    btnRelatorios.setForeground(Color.black);
    }//GEN-LAST:event_btnRelatoriosMouseExited

    private void btnRelatoriosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRelatoriosMousePressed
    btnRelatorios.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnRelatoriosMousePressed

    private void btnRelatoriosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRelatoriosMouseReleased
    btnRelatorios.setForeground(Color.yellow);
    }//GEN-LAST:event_btnRelatoriosMouseReleased

    private void btnSituacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSituacaoMouseClicked
        SituacaoDaEmpresa situ = new SituacaoDaEmpresa();
        ((BasicInternalFrameUI)situ.getUI()).setNorthPane(null);
        pnlPrincipal.removeAll();
        pnlPrincipal.add(situ);
        pnlPrincipal.updateUI();
        btnSituacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Estatisticas.png")));
        btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Cliente.png")));
        btnVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Funcionario.png")));
        btnEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Estoque.png")));
        btnVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Vendas.png")));
        btnMarcLivre.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Mercado livre.png")));
        btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Relatorios.png")));
    }//GEN-LAST:event_btnSituacaoMouseClicked

    private void btnSituacaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSituacaoMouseEntered
    btnSituacao.setForeground(Color.yellow);
    }//GEN-LAST:event_btnSituacaoMouseEntered

    private void btnSituacaoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSituacaoMouseExited
    btnSituacao.setForeground(Color.black);
    }//GEN-LAST:event_btnSituacaoMouseExited

    private void btnSituacaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSituacaoMousePressed
    btnSituacao.setForeground(Color.white);
    }//GEN-LAST:event_btnSituacaoMousePressed

    private void btnSituacaoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSituacaoMouseReleased
    btnSituacao.setForeground(Color.yellow);
    }//GEN-LAST:event_btnSituacaoMouseReleased

    private void btnClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClienteMouseClicked
        ClienteView cliente = new ClienteView();
        ((BasicInternalFrameUI)cliente.getUI()).setNorthPane(null);
        pnlPrincipal.removeAll();
        pnlPrincipal.add(cliente);
        pnlPrincipal.updateUI();
        btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Cliente.png")));
        btnVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Funcionario.png")));
        btnEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Estoque.png")));
        btnVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Vendas.png")));
        btnMarcLivre.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Mercado livre.png")));
        btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Relatorios.png")));
        btnSituacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Estatisticas.png")));
    }//GEN-LAST:event_btnClienteMouseClicked

    private void btnClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClienteMouseEntered
    btnCliente.setForeground(Color.yellow);
    }//GEN-LAST:event_btnClienteMouseEntered

    private void btnClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClienteMouseExited
    btnCliente.setForeground(Color.black);
    }//GEN-LAST:event_btnClienteMouseExited

    private void btnClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClienteMousePressed
    btnCliente.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnClienteMousePressed

    private void btnClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClienteMouseReleased
    btnCliente.setForeground(Color.yellow);
    }//GEN-LAST:event_btnClienteMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCliente;
    private javax.swing.JLabel btnEstoque;
    private javax.swing.JLabel btnMarcLivre;
    private javax.swing.JLabel btnRelatorios;
    private javax.swing.JLabel btnSituacao;
    private javax.swing.JLabel btnVenda;
    private javax.swing.JLabel btnVendedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables
}
