package VIEW;

import DAO.ClienteDao;
import DAO.FuncionarioDao;
import DAO.MarcaDao;
import DAO.ModeloDao;
import DAO.ProdutoDao;
import DAO.VendaDao;
import DAO.VendaMLDao;
import MODEL.ClienteM;
import MODEL.FuncionarioM;
import MODEL.MarcaM;
import MODEL.ModeloM;
import MODEL.ProdutoM;
import MODEL.VendaM;
import MODEL.VendaMLM;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Danilo-NOTE
 */
public class SituacaoDaEmpresa extends javax.swing.JInternalFrame {

    /**
     * Creates new form Relatorio_DO_CU_QUENTE
     */
    
    ClienteM cliente = new ClienteM();
    ClienteDao clientedao = new ClienteDao();
    List<ClienteM> listaCliente = new ArrayList<>();
    
    FuncionarioM funcionario = new FuncionarioM();
    FuncionarioDao fundionariodao = new FuncionarioDao();
    List<FuncionarioM> listaFuncionario = new ArrayList<>();
    
    ProdutoM produto = new ProdutoM();
    ProdutoDao produtodao = new ProdutoDao();
    List<ProdutoM> listaProduto = new ArrayList<>();
    
    VendaM venda = new VendaM();
    VendaDao vendadao = new VendaDao();
    List<VendaM> listaVenda = new ArrayList<>();
    
    VendaMLM vendaML = new VendaMLM();
    VendaMLDao vendaMLdao = new VendaMLDao();
    List<VendaMLM> ListaVendaML = new ArrayList<>();

    
    public SituacaoDaEmpresa() {
        initComponents();
        this.setVisible(true);
        Tabela.getTableHeader().setReorderingAllowed(false);

    }

    
    //Atualiza todos os funcionario para a tabela
    public void atualizaTabelaCliente(){
        cliente = new ClienteM();
        
        String dados[][] = new String[listaCliente.size()][6];
            int i = 0;
            for (ClienteM cliente : listaCliente) {
                dados[i][0] = String.valueOf(cliente.getId());
                dados[i][1] = cliente.getNome();
                dados[i][2] = cliente.getCidade();
                dados[i][3] = cliente.getNascimento();
                dados[i][4] = cliente.getTelefone();
                dados[i][5] = cliente.getCelular1();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome", "Cidade", "Nascimento","Telefone", "Celular1"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            Tabela.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            Tabela.getColumnModel().getColumn(0).setMaxWidth(0);
            Tabela.getColumnModel().getColumn(0).setMinWidth(0);
            Tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
            Tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
            Tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            Tabela.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            Tabela.setRowHeight(35);
            Tabela.updateUI();
    }
    
    public void atualizaTabelaFuncionario(){
        funcionario = new FuncionarioM();
        
        String dados[][] = new String[listaFuncionario.size()][5];
            int i = 0;
            for (FuncionarioM funcionario1 : listaFuncionario) {
                dados[i][0] = String.valueOf(funcionario1.getId());
                dados[i][1] = funcionario1.getNome();
                dados[i][2] = funcionario1.getNascimento();
                dados[i][3] = funcionario1.getTelefone();
                dados[i][4] = funcionario1.getCelular1();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome", "Nascimento","Telefone", "Celular1"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            Tabela.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false,
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            Tabela.getColumnModel().getColumn(0).setMaxWidth(0);
            Tabela.getColumnModel().getColumn(0).setMinWidth(0);
            Tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
            Tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
            Tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            Tabela.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            Tabela.setRowHeight(35);
            Tabela.updateUI();
    }
    
    public void atualizaTabelaProduto(){
        produto = new ProdutoM();
        
        String dados[][] = new String[listaProduto.size()][7];
            int i = 0;
            for (ProdutoM produto : listaProduto) {
                dados[i][0] = String.valueOf(produto.getId());
                dados[i][1] = produto.getNome();
                dados[i][2] = produto.getIdMarca().getNome();
                dados[i][3] = produto.getIdModelo().getNome();
                dados[i][4] = String.valueOf(produto.getQuantidade());
                dados[i][5] = String.valueOf(produto.getValorMax());
                dados[i][6] = String.valueOf(produto.getValorMini());

                i++;
            }
            String tituloColuna[] = {"ID", "Nome", "Marca","Modelo","Quantidade", "Valor Maximo","Valor Minimo"};
            DefaultTableModel tabelaProduto = new DefaultTableModel();
            tabelaProduto.setDataVector(dados, tituloColuna);
            Tabela.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            Tabela.getColumnModel().getColumn(0).setMaxWidth(0);
            Tabela.getColumnModel().getColumn(0).setMinWidth(0);
            Tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
            Tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
            Tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            Tabela.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            Tabela.setRowHeight(35);
            Tabela.updateUI();
    }
    
    public void atualizaTabelaVenda(){
        venda = new VendaM();
        
        String dados[][] = new String[listaVenda.size()][5];
            int i = 0;
            for (VendaM venda : listaVenda) {
                dados[i][0] = String.valueOf(venda.getId());
                dados[i][1] = venda.getIdCliente().getNome();
                dados[i][2] = venda.getData();
                dados[i][3] = String.valueOf(venda.getTotalVendas());

                i++;
            }
            String tituloColuna[] = {"ID", "Cliente", "Data","Valor total"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            Tabela.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            Tabela.getColumnModel().getColumn(0).setMaxWidth(0);
            Tabela.getColumnModel().getColumn(0).setMinWidth(0);
            Tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
            Tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
            Tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            Tabela.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            Tabela.setRowHeight(35);
            Tabela.updateUI();
    }
    
    public void atualizaTabelaVendaML(){
        vendaML = new VendaMLM();

        
        String dados[][] = new String[ListaVendaML.size()][5];
            int i = 0;
            for (VendaMLM venda2 : ListaVendaML) {
                dados[i][0] = String.valueOf(venda2.getId());
                dados[i][1] = venda2.getIdProduto().getNome();
                dados[i][2] = venda2.getRastreio();
                dados[i][3] = venda2.getData();
                dados[i][4] = venda2.getHorario();

                i++;
            }
            String tituloColuna[] = {"ID", "Produto","Rastreio", "Data","Horario"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            Tabela.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            Tabela.getColumnModel().getColumn(0).setMaxWidth(0);
            Tabela.getColumnModel().getColumn(0).setMinWidth(0);
            Tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
            Tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
            Tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            Tabela.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            Tabela.setRowHeight(35);
            Tabela.updateUI();
    }
    
    
    
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Grupo1 = new javax.swing.ButtonGroup();
        Grupo2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        btnAtualizar = new javax.swing.JButton();
        cbxCategoria = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cbxCategoria1 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtValorCusto = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(248, 248, 248));

        Tabela.setBackground(new java.awt.Color(248, 248, 248));
        Tabela.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));
        Tabela.setFont(new java.awt.Font("Myanmar Text", 1, 12)); // NOI18N
        Tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(Tabela);

        btnAtualizar.setText("Atualizar");
        btnAtualizar.setEnabled(false);
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        cbxCategoria.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        cbxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        cbxCategoria.setToolTipText("");
        cbxCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCategoriaItemStateChanged(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel21.setText("Mes:");

        jLabel22.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel22.setText("Ano:");

        cbxCategoria1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        cbxCategoria1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018" }));
        cbxCategoria1.setToolTipText("");
        cbxCategoria1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCategoria1ItemStateChanged(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(248, 248, 248));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valores e Custos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel2.setText("Valor Custo:");

        txtValorCusto.setBackground(new java.awt.Color(245, 245, 245));
        txtValorCusto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtValorCusto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtValorCusto.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(txtValorCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(137, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(txtValorCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(cbxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(cbxCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(1, 1, 1)
                        .addComponent(cbxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(1, 1, 1)
                        .addComponent(cbxCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Opções", jPanel4);

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));

        jLabel15.setFont(new java.awt.Font("Shruti", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(227, 227, 227));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Relatórios");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 761, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 629, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed

    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void cbxCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCategoriaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCategoriaItemStateChanged

    private void cbxCategoria1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCategoria1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCategoria1ItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Grupo1;
    private javax.swing.ButtonGroup Grupo2;
    private javax.swing.JTable Tabela;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JComboBox<String> cbxCategoria;
    private javax.swing.JComboBox<String> cbxCategoria1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JFormattedTextField txtValorCusto;
    // End of variables declaration//GEN-END:variables
}
