package VIEW;

import DAO.*;
import MODEL.*;
import com.sun.java.swing.plaf.windows.WindowsTableHeaderUI;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import util.ColorirVendaML;

/**
 *
 * @author Danilo-NOTE
 */
public class VendaViewML extends javax.swing.JInternalFrame {

    /**
     * Creates new form VendaViewML_DO_CU_QUENTE
     */
    
    ProdutoM produto = new ProdutoM();
    ProdutoDao produtoDao = new ProdutoDao();
    List<ProdutoM> listaProduto = new ArrayList<>();

    FuncionarioM funcionario = new FuncionarioM();
    FuncionarioDao funcionariodao = new FuncionarioDao();
    List<FuncionarioM> listaFuncionario = new ArrayList<>();
    
    VendaMLM vendaml = new VendaMLM();
    VendaMLDao vendamldao = new VendaMLDao();
    List<VendaMLM> listaVendaml = new ArrayList<>();
    List<VendaMLM> ListaTabelaProduto = new ArrayList<>();

    
    public VendaViewML() {
        initComponents();
        this.setVisible(true);
        atualizaTabelaVenda();
        atualizaTabelaItemVenda();
        jTabbedPane1.setUI(new BasicTabbedPaneUI());
        tblFuncionarioDialog.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblFuncionarioDialog.getTableHeader().setReorderingAllowed(false);
        tblItenVenda.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblItenVenda.getTableHeader().setReorderingAllowed(false);
        tblProdutoDialog.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblProdutoDialog.getTableHeader().setReorderingAllowed(false);
        tblVenda.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblVenda.getTableHeader().setReorderingAllowed(false);
        ProdutoDialog.setSize(520, 460);
        FuncionarioDialog.setSize(520, 460);
        atualizaBoxFuncionario();
        txtIdProduto.setVisible(false);
        txtIdVenda.setVisible(false);
        txtIDIten.setVisible(false);
        txtQuantidadeTotal.setVisible(false);
        btnAddItemVendas.setUI(new BasicButtonUI());
        btnNovo.setUI(new BasicButtonUI());
    }
    
    public void atualizaBoxFuncionario(){
       
        cbxFuncionario.removeAllItems();
        cbxFuncionario.addItem("Selecione");
        
        try {
            listaFuncionario = funcionariodao.listaTodos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         String dados[][] = new String[listaFuncionario.size()][2];
        for (FuncionarioM Funcib : listaFuncionario) {
            cbxFuncionario.addItem(Funcib.getNome());
        }
    } 

    //Atualiza todos os funcionario para a tabela
    public void atualizaTabelaVenda(){
        vendaml = new VendaMLM();
        try {
        listaVendaml = vendamldao.listaTodos();
        }catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        Object dados[][] = new String[listaVendaml.size()][6];
            int i = 0;
            for (VendaMLM venda2 : listaVendaml) {
                dados[i][0] = String.valueOf(venda2.getId());
                dados[i][1] = venda2.getIdProduto().getNome();
                dados[i][2] = venda2.getRastreio();
                dados[i][3] = venda2.getData();
                dados[i][4] = venda2.getHorario();
                if(venda2.getExcluido() == false){
                    dados[i][5] = "Ativa";
                }else{
                    dados[i][5] = "Cancelada";
                }
                i++;
            }
            String tituloColuna[] = {"ID", "Produto","Rastreio", "Data","Horario", "Situação"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            tblVenda.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblVenda.getColumnModel().getColumn(0).setMaxWidth(0);
            tblVenda.getColumnModel().getColumn(0).setMinWidth(0);
            tblVenda.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblVenda.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblVenda.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblVenda.getColumnModel().getColumn(5).setMaxWidth(0);
            tblVenda.getColumnModel().getColumn(5).setMinWidth(0);
            tblVenda.getColumnModel().getColumn(5).setPreferredWidth(0);
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblVenda.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblVenda.setDefaultRenderer(Object.class, new ColorirVendaML());
            tblVenda.setRowHeight(35);
            tblVenda.updateUI();
    }
    
    public void atualizaTabelaVendabusca(){
        vendaml = new VendaMLM();

        
        Object dados[][] = new String[listaVendaml.size()][6];
            int i = 0;
            for (VendaMLM venda2 : listaVendaml) {
                dados[i][0] = String.valueOf(venda2.getId());
                dados[i][1] = venda2.getIdProduto().getNome();
                dados[i][2] = venda2.getRastreio();
                dados[i][3] = venda2.getData();
                dados[i][4] = venda2.getHorario();
                if(venda2.getExcluido() == false){
                    dados[i][5] = "Ativa";
                }else{
                    dados[i][5] = "Cancelada";
                }
                i++;
            }
            String tituloColuna[] = {"ID", "Produto","Rastreio", "Data","Horario"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            tblVenda.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblVenda.getColumnModel().getColumn(0).setMaxWidth(0);
            tblVenda.getColumnModel().getColumn(0).setMinWidth(0);
            tblVenda.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblVenda.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblVenda.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblVenda.getColumnModel().getColumn(5).setMaxWidth(0);
            tblVenda.getColumnModel().getColumn(5).setMinWidth(0);
            tblVenda.getColumnModel().getColumn(5).setPreferredWidth(0);
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblVenda.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblVenda.setDefaultRenderer(Object.class, new ColorirVendaML());
            tblVenda.setRowHeight(35);
            tblVenda.updateUI();
    }
  
    public void atualizaTabelaProdutoDialogBusca(){
        produto = new ProdutoM();

        String dados[][] = new String[listaProduto.size()][4];
        int i = 0;
        for (ProdutoM produto : listaProduto) {
            dados[i][0] = String.valueOf(produto.getId());
            dados[i][1] = produto.getNome();
            dados[i][2] = String.valueOf(produto.getQuantidade());
            dados[i][3] = String.valueOf(produto.getValorMini());

        i++;
        }
        String tituloColuna[] = {"ID", "Nome","Quantidade","Valor Mínimo"};
        DefaultTableModel tabelaproduto = new DefaultTableModel();
        tabelaproduto.setDataVector(dados, tituloColuna);
        tblProdutoDialog.setModel(new DefaultTableModel(dados, tituloColuna) {
        boolean[] canEdit = new boolean[]{
        false, false,false,false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
        }
        });

        tblProdutoDialog.getColumnModel().getColumn(0).setMaxWidth(0);
        tblProdutoDialog.getColumnModel().getColumn(0).setMinWidth(0);
        tblProdutoDialog.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblProdutoDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblProdutoDialog.getColumnModel().getColumn(2).setPreferredWidth(70);
        tblProdutoDialog.getColumnModel().getColumn(3).setPreferredWidth(70);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tblProdutoDialog.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tblProdutoDialog.setRowHeight(35);
        tblProdutoDialog.updateUI();
    }
    
    
    public void atualizaTabelaProdutoDialog(){
        produto = new ProdutoM();
        try {
        listaProduto = produtoDao.listaTodos();
        }catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }

        String dados[][] = new String[listaProduto.size()][4];
        int i = 0;
        for (ProdutoM produto : listaProduto) {
            dados[i][0] = String.valueOf(produto.getId());
            dados[i][1] = produto.getNome();
            dados[i][2] = String.valueOf(produto.getQuantidade());
            dados[i][3] = String.valueOf(produto.getValorMini());

        i++;
        }
        String tituloColuna[] = {"ID", "Nome","Quantidade","Valor Mínimo"};
        DefaultTableModel tabelaproduto = new DefaultTableModel();
        tabelaproduto.setDataVector(dados, tituloColuna);
        tblProdutoDialog.setModel(new DefaultTableModel(dados, tituloColuna) {
        boolean[] canEdit = new boolean[]{
            false, false,false,false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
        }
        });

        tblProdutoDialog.getColumnModel().getColumn(0).setMaxWidth(0);
        tblProdutoDialog.getColumnModel().getColumn(0).setMinWidth(0);
        tblProdutoDialog.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblProdutoDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblProdutoDialog.getColumnModel().getColumn(2).setPreferredWidth(70);
        tblProdutoDialog.getColumnModel().getColumn(3).setPreferredWidth(70);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tblProdutoDialog.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tblProdutoDialog.setRowHeight(35);
        tblProdutoDialog.updateUI();
    }
    
    private void atualizaTabelaItemVenda() {
        
        
        String dados[][] = new String[ListaTabelaProduto.size()][5];
        int i = 0;
        for(VendaMLM iv : ListaTabelaProduto){
            dados[i][0] = String.valueOf(iv.getId());
            dados[i][1] = iv.getIdProduto().getNome();
            dados[i][2] = String.valueOf(iv.getIdProduto().getValorMini());
            dados[i][3] = String.valueOf(iv.getData());
            dados[i][4] = String.valueOf(iv.getHorario());
            i++;
        }
        String tituloColuna[] = {"Id","Produto", "Preço Unit.", "Data", "Horario"};
        DefaultTableModel tabelaItens = new DefaultTableModel();
        tabelaItens.setDataVector(dados, tituloColuna);
        tblItenVenda.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
        
        tblItenVenda.getColumnModel().getColumn(0).setMaxWidth(0);
        tblItenVenda.getColumnModel().getColumn(0).setMinWidth(0);
        tblItenVenda.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblItenVenda.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblItenVenda.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblItenVenda.getColumnModel().getColumn(3).setPreferredWidth(100);
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tblItenVenda.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tblItenVenda.setRowHeight(25);
        tblItenVenda.updateUI();
        
    }

    public void PreparaVenda(){
        txtCodigodeBarras.setText("1");
        txtIdProduto.setText("");
        txtproduto.setText("");
    }
    

    // DECLARAÇÃO DE MÉTODOS DE CONTROLE DE BOTÕES
    public void limparCampos(){
        lblTOTAL.setText("");
        txtIDIten.setText("");
        txtIdProduto.setText("");
        txtproduto.setText("");
        txtCodigodeBarras.setText("");
    }
   
    public void prepararNovo() {
       tblVenda.setEnabled(false);
       tblVenda.clearSelection();
    }
   
    public void prepararSalvareCancelar() {
       btnNovo.setEnabled(true);
       tblVenda.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ProdutoDialog = new javax.swing.JDialog();
        jPanel35 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        txtBuscaProdutoDialog = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblProdutoDialog = new javax.swing.JTable();
        FuncionarioDialog = new javax.swing.JDialog();
        jPanel36 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        txtBuscaFuncionarioDialog = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblFuncionarioDialog = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        btnExcluir = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVenda = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtIdVenda = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigodeBarras = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblItenVenda = new javax.swing.JTable();
        btnAddItemVendas = new javax.swing.JButton();
        txtproduto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtIdProduto = new javax.swing.JTextField();
        txtIDIten = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblTOTAL = new javax.swing.JLabel();
        txtQuantidadeTotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbxFuncionario = new javax.swing.JComboBox<>();
        btnNovo = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        btnSair = new javax.swing.JLabel();

        ProdutoDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ProdutoDialog.setMinimumSize(new java.awt.Dimension(310, 380));
        ProdutoDialog.setUndecorated(true);
        ProdutoDialog.setResizable(false);
        ProdutoDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel35.setBackground(new java.awt.Color(249, 249, 249));

        jLabel69.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(26, 26, 26));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("Buscar Produto");

        txtBuscaProdutoDialog.setBackground(new java.awt.Color(253, 253, 254));
        txtBuscaProdutoDialog.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtBuscaProdutoDialog.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscaProdutoDialog.setToolTipText("Limite em Reais para comprar fiado na loja.");
        txtBuscaProdutoDialog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(104, 129, 164)));
        txtBuscaProdutoDialog.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaProdutoDialogCaretUpdate(evt);
            }
        });

        jSeparator6.setBackground(new java.awt.Color(249, 249, 249));
        jSeparator6.setForeground(new java.awt.Color(104, 129, 164));

        tblProdutoDialog.setFont(new java.awt.Font("Champagne & Limousines", 0, 11)); // NOI18N
        tblProdutoDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Quantidade", "Valor Mínimo"
            }
        ));
        tblProdutoDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoDialogMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblProdutoDialog);

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscaProdutoDialog)
                    .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
                .addContainerGap(117, Short.MAX_VALUE))
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6)
                    .addComponent(jScrollPane11))
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txtBuscaProdutoDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );

        ProdutoDialog.getContentPane().add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 460));

        FuncionarioDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        FuncionarioDialog.setUndecorated(true);
        FuncionarioDialog.setResizable(false);
        FuncionarioDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel36.setBackground(new java.awt.Color(249, 249, 249));

        jLabel70.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(26, 26, 26));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("Buscar Funcionario");

        txtBuscaFuncionarioDialog.setBackground(new java.awt.Color(253, 253, 254));
        txtBuscaFuncionarioDialog.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtBuscaFuncionarioDialog.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscaFuncionarioDialog.setToolTipText("Limite em Reais para comprar fiado na loja.");
        txtBuscaFuncionarioDialog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(104, 129, 164)));
        txtBuscaFuncionarioDialog.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaFuncionarioDialogCaretUpdate(evt);
            }
        });

        jSeparator7.setBackground(new java.awt.Color(249, 249, 249));
        jSeparator7.setForeground(new java.awt.Color(104, 129, 164));

        tblFuncionarioDialog.setBackground(new java.awt.Color(248, 248, 248));
        tblFuncionarioDialog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));
        tblFuncionarioDialog.setFont(new java.awt.Font("Champagne & Limousines", 1, 12)); // NOI18N
        tblFuncionarioDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Nascimento"
            }
        ));
        tblFuncionarioDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFuncionarioDialogMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblFuncionarioDialog);

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBuscaFuncionarioDialog)
                            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
                        .addGap(0, 116, Short.MAX_VALUE))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator7))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5)))
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscaFuncionarioDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        FuncionarioDialog.getContentPane().add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 460));

        setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        setClosable(true);
        setMaximumSize(new java.awt.Dimension(1919, 800));
        setMinimumSize(new java.awt.Dimension(1919, 800));
        setPreferredSize(new java.awt.Dimension(1919, 800));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnExcluir.setBackground(new java.awt.Color(255, 255, 255));
        btnExcluir.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnExcluir.setText("Excluir Venda");
        btnExcluir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        tblVenda.setBackground(new java.awt.Color(248, 248, 248));
        tblVenda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));
        tblVenda.setFont(new java.awt.Font("Champagne & Limousines", 0, 12)); // NOI18N
        tblVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Danilo Vieira Bernardes", "(34)3423-5123", "06/06/1997", "Frutal"},
                {"Leandro Matias Baldo", "(34)3421-4123", "05/02/1996", "Frutal"},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Telefone", "Nascimento", "Cidade"
            }
        ));
        tblVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVendaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblVenda);
        if (tblVenda.getColumnModel().getColumnCount() > 0) {
            tblVenda.getColumnModel().getColumn(0).setMinWidth(20);
        }

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        txtBusca.setBackground(new java.awt.Color(245, 245, 245));
        txtBusca.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtBusca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtBusca.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaCaretUpdate(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel13.setText("Produto: ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 163, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtIdVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtIdVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53))
        );

        jTabbedPane1.addTab("Consulta", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleção de Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Código de barras:");

        txtCodigodeBarras.setBackground(new java.awt.Color(245, 245, 245));
        txtCodigodeBarras.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtCodigodeBarras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtCodigodeBarras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCodigodeBarrasMouseClicked(evt);
            }
        });
        txtCodigodeBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigodeBarrasKeyPressed(evt);
            }
        });

        tblItenVenda.setFont(new java.awt.Font("Champagne & Limousines", 0, 11)); // NOI18N
        tblItenVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblItenVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItenVendaMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblItenVenda);

        btnAddItemVendas.setBackground(new java.awt.Color(255, 255, 255));
        btnAddItemVendas.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnAddItemVendas.setText("Adicionar Produto +");
        btnAddItemVendas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnAddItemVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemVendasActionPerformed(evt);
            }
        });

        txtproduto.setBackground(new java.awt.Color(245, 245, 245));
        txtproduto.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtproduto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtproduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtprodutoMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Produto:");

        jLabel8.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel8.setText("Total: R$");

        lblTOTAL.setFont(new java.awt.Font("Champagne & Limousines", 1, 15)); // NOI18N
        lblTOTAL.setText("..");

        jLabel11.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel11.setText("ou");

        jLabel9.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel9.setText("Vendedor: ");

        cbxFuncionario.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        cbxFuncionario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxFuncionario.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(10, 10, 10)
                        .addComponent(cbxFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(409, 409, 409)
                        .addComponent(txtQuantidadeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtIDIten, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(145, 145, 145)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnAddItemVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(454, 454, 454)
                                .addComponent(jLabel8))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtproduto, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel11)
                                .addGap(58, 58, 58)
                                .addComponent(txtCodigodeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane12)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel9))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(cbxFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtQuantidadeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDIten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtproduto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtCodigodeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddItemVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTOTAL)
                        .addComponent(jLabel8)))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnNovo.setBackground(new java.awt.Color(255, 255, 255));
        btnNovo.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnNovo.setText("Limpar");
        btnNovo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Venda", jPanel6);

        jLabel15.setFont(new java.awt.Font("Champagne & Limousines", 0, 20)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Venda Mercado Livre");

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Fechar.png"))); // NOI18N
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSairMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSairMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSair))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(560, 560, 560)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1007, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(348, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 128, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVendaMouseClicked
        txtIdVenda.setText(tblVenda.getValueAt(tblVenda.getSelectedRow(),0).toString());
    }//GEN-LAST:event_tblVendaMouseClicked

    private void txtBuscaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaCaretUpdate
        if(txtBusca.getText().equals("")){
            atualizaTabelaVenda();
            
        }else{

            try {
            listaVendaml = vendamldao.busca(txtBusca.getText());

            if(listaVendaml == null){
            JOptionPane.showMessageDialog(null, "Nenhuma venda encontrado!","", JOptionPane.WARNING_MESSAGE);
            atualizaTabelaVenda();
            
            }else{
                
            atualizaTabelaVendabusca();
            }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtBuscaCaretUpdate

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limparCampos();
        prepararNovo();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAddItemVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemVendasActionPerformed
        try{
            funcionario = funcionariodao.buscaNome(String.valueOf(cbxFuncionario.getSelectedItem()));
        if(txtproduto.getText().isEmpty() || txtIdProduto.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Selecione primeiro um produto! ","erro", JOptionPane.WARNING_MESSAGE);
            txtproduto.setText("");
            txtIdProduto.setText("");
        }else if(1 >= Integer.valueOf(txtQuantidadeTotal.getText())){
            JOptionPane.showMessageDialog(null, "Quantidade ultrapassa o estoque! ","erro", JOptionPane.WARNING_MESSAGE);
        }else{     
            vendaml = new VendaMLM();
            vendaml.setIdFuncionario(funcionario);
            produto.setId(Integer.valueOf(txtIdProduto.getText()));
            vendaml.setIdProduto(produto);
            vendaml.setTotalVenda(produto.getValorMini());
            vendaml.setData(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
            vendaml.setHorario(new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis())));
            vendaml.setRastreio("Sem Código");
            vendaml.setDetalhes("Sem Detalhes");
           
                vendamldao.salvar(vendaml);
                JOptionPane.showMessageDialog(null, "Gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            ListaTabelaProduto.add(vendaml);
            atualizaTabelaItemVenda();
            atualizaTabelaVenda();
            prepararSalvareCancelar();
            limparCampos();
        }
        }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
    }//GEN-LAST:event_btnAddItemVendasActionPerformed

    private void tblProdutoDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoDialogMouseClicked
        // TODO add your handling code here:
        txtIdProduto.setText(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 0).toString());
        txtproduto.setText(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 1).toString());
        txtQuantidadeTotal.setText(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 2).toString());
        produto = new ProdutoM();
        produto.setId(Integer.parseInt(txtIdProduto.getText()));
        produto.setNome(txtproduto.getText());
        produto.setValorMini(Float.valueOf(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 3).toString()));
        ProdutoDialog.dispose();
    }//GEN-LAST:event_tblProdutoDialogMouseClicked

    private void txtprodutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtprodutoMouseClicked
       txtCodigodeBarras.setText("");
       ProdutoDialog.setVisible(true);
       ProdutoDialog.setLocationRelativeTo(null);
       atualizaTabelaProdutoDialog();
       tblProdutoDialog.getTableHeader().setReorderingAllowed(false);
    }//GEN-LAST:event_txtprodutoMouseClicked

    private void tblItenVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItenVendaMouseClicked
        txtIDIten.setText(tblItenVenda.getValueAt(tblItenVenda.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_tblItenVendaMouseClicked

    private void txtBuscaProdutoDialogCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaProdutoDialogCaretUpdate
        if (txtBuscaProdutoDialog.getText().equals("")) {
            atualizaTabelaProdutoDialog();
        }else{

            try {
                listaProduto = produtoDao.buscaNomeLista(txtBuscaProdutoDialog.getText());

                if(listaProduto == null){

                    JOptionPane.showMessageDialog(null, "Nenhum Produto encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaProdutoDialog();
                }else{
                    atualizaTabelaProdutoDialogBusca();
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }

        }
    }//GEN-LAST:event_txtBuscaProdutoDialogCaretUpdate

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void tblFuncionarioDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFuncionarioDialogMouseClicked

    }//GEN-LAST:event_tblFuncionarioDialogMouseClicked

    private void txtBuscaFuncionarioDialogCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaFuncionarioDialogCaretUpdate

    }//GEN-LAST:event_txtBuscaFuncionarioDialogCaretUpdate

    private void txtCodigodeBarrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigodeBarrasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            boolean existe = false;
                try {
                    existe = produtoDao.buscacodigo(txtCodigodeBarras.getText());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
                    Logger.getLogger(VendaViewML.class.getName()).log(Level.SEVERE, null, ex);
                }
        if(existe == false){
            JOptionPane.showMessageDialog(null, "Selecione primeiro um Produto! ","erro", JOptionPane.WARNING_MESSAGE);
            txtproduto.setText("");
            txtIdProduto.setText("");
        }if(cbxFuncionario.getSelectedIndex()== 0){
            JOptionPane.showMessageDialog(null, "Selecione primeiro um Funcionario! ","erro", JOptionPane.WARNING_MESSAGE);
        }else{     
            vendaml = new VendaMLM();
            funcionario = new FuncionarioM();
            produto = new ProdutoM();
                try {
                    produto = produtoDao.buscacodigo2(txtCodigodeBarras.getText());
                    
                } catch (SQLException ex) {
                    Logger.getLogger(VendaViewML.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Produto não encontrado");
                }
                try {
                    funcionario = funcionariodao.buscaNome(String.valueOf(cbxFuncionario.getSelectedItem()));
                } catch (SQLException ex) {
                    Logger.getLogger(VendaViewML.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Funcionario não encontrado");
                }
            vendaml.setIdFuncionario(funcionario);
            vendaml.setIdProduto(produto);
            vendaml.setTotalVenda(produto.getValorMini());
            vendaml.setData(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
            vendaml.setHorario(new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis())));
            vendaml.setRastreio("Sem Código");
            vendaml.setDetalhes("Sem Detalhes");
                try{
                    vendamldao.salvar(vendaml);
                    JOptionPane.showMessageDialog(null, "Gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
                }
            ListaTabelaProduto.add(vendaml);
            atualizaTabelaItemVenda();
            atualizaTabelaVenda();
            prepararSalvareCancelar();
            limparCampos();
        }
    }
    }//GEN-LAST:event_txtCodigodeBarrasKeyPressed

    private void txtCodigodeBarrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodigodeBarrasMouseClicked
        txtproduto.setText("");
    }//GEN-LAST:event_txtCodigodeBarrasMouseClicked

    private void btnSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnSairMouseClicked

    private void btnSairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseEntered
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Fechar.png")));
    }//GEN-LAST:event_btnSairMouseEntered

    private void btnSairMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseExited
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Fechar.png")));
    }//GEN-LAST:event_btnSairMouseExited

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja Excluir ?");
        if (confirma == 0) {
            try {
                String LoginMaster = JOptionPane.showInputDialog(null,"Digite seu login: ");
                String SenhaMaster = JOptionPane.showInputDialog(null,"Digite sua senha: ");

                funcionario = funcionariodao.valida(LoginMaster, SenhaMaster);
                if(funcionario == null){
                    JOptionPane.showMessageDialog(null, "Funcionario não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
                }else{

                    vendaml.setId(Integer.valueOf(txtIdVenda.getText()));
                    vendaml.setExcluido(true);
                    vendamldao.alterarVendaMLTrue(vendaml);
                    atualizaTabelaVenda();

                }
            } catch (SQLException ex) {
                Logger.getLogger(VendaView.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog FuncionarioDialog;
    private javax.swing.JDialog ProdutoDialog;
    private javax.swing.JButton btnAddItemVendas;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JLabel btnSair;
    private javax.swing.JComboBox<String> cbxFuncionario;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTOTAL;
    private javax.swing.JTable tblFuncionarioDialog;
    private javax.swing.JTable tblItenVenda;
    private javax.swing.JTable tblProdutoDialog;
    private javax.swing.JTable tblVenda;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtBuscaFuncionarioDialog;
    private javax.swing.JTextField txtBuscaProdutoDialog;
    private javax.swing.JTextField txtCodigodeBarras;
    private javax.swing.JTextField txtIDIten;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtIdVenda;
    private javax.swing.JTextField txtQuantidadeTotal;
    private javax.swing.JTextField txtproduto;
    // End of variables declaration//GEN-END:variables
}
