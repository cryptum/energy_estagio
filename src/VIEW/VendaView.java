package VIEW;

import DAO.*;
import MODEL.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Danilo-NOTE
 */
public class VendaView extends javax.swing.JInternalFrame {

    /**
     * Creates new form CRIENTE_DO_CU_QUENTE
     */
    
    ProdutoM produto = new ProdutoM();
    ProdutoDao produtoDao = new ProdutoDao();
    List<ProdutoM> listaProduto = new ArrayList<>();
    
    ClienteM cliente = new ClienteM();
    ClienteDao clientedao = new ClienteDao();
    List<ClienteM> listaCliente = new ArrayList<>();
    
    FuncionarioM funcionario = new FuncionarioM();
    FuncionarioDao funcionariodao = new FuncionarioDao();
    List<FuncionarioM> listaFuncionario = new ArrayList<>();
    
    VendaM venda = new VendaM();
    VendaDao vendadao = new VendaDao();
    List<VendaM> listaVenda = new ArrayList<>();
    
    VendaM2 venda2 = new VendaM2();
    List<VendaM2> listaVenda2 = new ArrayList<>();
    ItensVenda itemVenda = new ItensVenda();
    
    public VendaView() {
        initComponents();
        this.setVisible(true);
        atualizaTabelaVenda();
        atualizaTabelaItemVenda();
        tblVenda.getTableHeader().setReorderingAllowed(false);
        tblItenVenda.getTableHeader().setReorderingAllowed(false);
        txtData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        
        ClienteDialog.setSize(520, 460);
        ProdutoDialog.setSize(520, 460);
    }

    //Atualiza todos os funcionario para a tabela
    public void atualizaTabelaVenda(){
        venda2 = new VendaM2();
        try {
        listaVenda2 = vendadao.listaTodos();
        }catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaVenda2.size()][5];
            int i = 0;
            for (VendaM2 venda2 : listaVenda2) {
                dados[i][0] = String.valueOf(venda2.getId());
                dados[i][1] = venda2.getIdCliente().getNome();
                dados[i][2] = venda2.getData();
                dados[i][3] = String.valueOf(venda2.getTotalVendas());

                i++;
            }
            String tituloColuna[] = {"ID", "Cliente", "Data","Valor total"};
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

            tblVenda.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblVenda.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblVenda.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblVenda.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblVenda.setRowHeight(35);
            tblVenda.updateUI();
    }
    
    public void atualizaTabelaVendabusca(){
        venda = new VendaM();
        
        String dados[][] = new String[listaVenda2.size()][5];
            int i = 0;
            for (VendaM2 venda : listaVenda2) {
                dados[i][0] = String.valueOf(venda.getId());
                dados[i][1] = venda.getIdCliente().getNome();
                dados[i][2] = venda.getData();
                dados[i][3] = String.valueOf(venda.getTotalVendas());

                i++;
            }
            String tituloColuna[] = {"ID", "Cliente", "Data","Valor total"};
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

            tblVenda.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblVenda.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblVenda.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblVenda.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblVenda.setRowHeight(35);
            tblVenda.updateUI();
    }
    
    //Atualiza Busca
    public void atualizaTabelaClienteBusca(){
        cliente = new ClienteM();
        
        String dados[][] = new String[listaCliente.size()][3];
            int i = 0;
            for (ClienteM cliente : listaCliente) {
                dados[i][0] = String.valueOf(cliente.getId());
                dados[i][1] = cliente.getNome();
                dados[i][2] = cliente.getCidade();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome", "Cidade"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            tblClienteDialog.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblClienteDialog.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblClienteDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblClienteDialog.getColumnModel().getColumn(2).setPreferredWidth(50);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblClienteDialog.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblClienteDialog.setRowHeight(35);
            tblClienteDialog.updateUI();
    }
    
    public void atualizaTabelaClienteDialog(){
        cliente = new ClienteM();
        try {
            listaCliente = clientedao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaCliente.size()][5];
            int i = 0;
            for (ClienteM cliente : listaCliente) {
                dados[i][0] = String.valueOf(cliente.getId());
                dados[i][1] = cliente.getNome();
                dados[i][2] = cliente.getNascimento();
                dados[i][3] = cliente.getTelefone();
                dados[i][4] = cliente.getCelular1();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome", "Cidade","Telefone", "Celular1"};
            DefaultTableModel tabelaCliente = new DefaultTableModel();
            tabelaCliente.setDataVector(dados, tituloColuna);
            tblClienteDialog.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false,
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblClienteDialog.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblClienteDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblClienteDialog.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblClienteDialog.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblClienteDialog.setRowHeight(35);
            tblClienteDialog.updateUI();
    }
    
    
    public void atualizaTabelaProdutoBusca(){
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
            String tituloColuna[] = {"ID", "Nome","Quantidade","Valor Minimo"};
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

            tblProdutoDialog.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblProdutoDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
            
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
            String tituloColuna[] = {"ID", "Nome","Quantidade","Valor Minimo"};
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

            tblProdutoDialog.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblProdutoDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblProdutoDialog.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblProdutoDialog.setRowHeight(35);
            tblProdutoDialog.updateUI();
    }
    
    private void atualizaTabelaItemVenda() {
        
        String dados[][] = new String[venda.getItensVenda().size()][4];
        int i = 0;
        for(ItensVenda iv : venda.getItensVenda()){
            dados[i][0] = iv.getIdProduto().getNome();
            dados[i][1] = String.valueOf(iv.getQuantidade());
            dados[i][2] = String.valueOf(iv.getPreco());
            dados[i][3] = String.valueOf(iv.getPrecototalitem());
            i++;
        }
        String tituloColuna[] = {"Produto", "Qtde", "Preço Unit.", "Preço Total"};
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
        
        tblItenVenda.getColumnModel().getColumn(0).setPreferredWidth(300);
        tblItenVenda.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblItenVenda.getColumnModel().getColumn(2).setPreferredWidth(300);
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tblItenVenda.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tblItenVenda.setRowHeight(25);
        tblItenVenda.updateUI();
        
    }
    
    private void atualizaTabelaItemVendalimpa() {
        venda = new VendaM();
        String dados[][] = new String[venda.getItensVenda().size()][4];
        int i = 0;
        for(ItensVenda iv : venda.getItensVenda()){
            dados[i][0] = iv.getIdProduto().getNome();
            dados[i][1] = String.valueOf(iv.getQuantidade());
            dados[i][2] = String.valueOf(iv.getPreco());
            dados[i][3] = String.valueOf(iv.getPrecototalitem());
            i++;
        }
        String tituloColuna[] = {"Produto", "Qtde", "Preço Unit.", "Preço Total"};
        DefaultTableModel tabelaItens = new DefaultTableModel();
        tabelaItens.setDataVector(dados, tituloColuna);
        tblItenVenda.setModel(new DefaultTableModel(null, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
        
        tblItenVenda.getColumnModel().getColumn(0).setPreferredWidth(300);
        tblItenVenda.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblItenVenda.getColumnModel().getColumn(2).setPreferredWidth(300);
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tblItenVenda.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tblItenVenda.setRowHeight(25);
        tblItenVenda.updateUI();
        
    }
    
    
    public void CalculaVenda(){
        txtTotal.setText(String.valueOf(Float.valueOf(txtQuantidade.getText()) * Float.valueOf(txtUnidade.getText())));
    }
    
    private float totalVenda(){
        float total = 0;
        for(ItensVenda iv : venda.getItensVenda()){
            total += iv.getPrecototalitem();
        }
        return total;
    }
    
    public void PreparaVenda(){
        txtQuantidade.setText("1");
        txtIdProduto.setText("");
        txtproduto.setText("");
    }
    
    
    
    
    
    
    // DECLARAÇÃO DE MÉTODOS DE CONTROLE DE BOTÕES
    public void limparCampos(){
        lblTOTAL.setText("");
        txtIDIten.setText("");
        txtIdCliente.setText("");
        txtCliente.setText("");
        txtData.setText("");
        txtQuantidade.setText("");
        txtUnidade.setText("");
        txtTotal.setText("");
        cbxFormaPagamento.setSelectedIndex(0);
    }
   
    public void ativarCampos(){
        txtIdCliente.setEnabled(true);
        txtCliente.setEnabled(true);
        txtData.setEnabled(true);
        txtQuantidade.setEnabled(true);
        txtUnidade.setEnabled(true);
        txtTotal.setEnabled(true);
        cbxFormaPagamento.setEnabled(true);
    }

    public void desativarCampos(){
        txtIdCliente.setEnabled(false);
        txtCliente.setEnabled(false);
        txtData.setEnabled(false);
        txtQuantidade.setEnabled(false);
        txtUnidade.setEnabled(false);
        txtTotal.setEnabled(false);
        cbxFormaPagamento.setEnabled(false);

    }
   
    public void prepararNovo() {
       btnNovo.setEnabled(false);
       btnSalvar.setEnabled(true);
       btnCancelar.setEnabled(true);
       tblVenda.setEnabled(false);
       tblVenda.clearSelection();
    }
   
    public void prepararSalvareCancelar() {
       btnNovo.setEnabled(true);
       btnSalvar.setEnabled(false);
       btnCancelar.setEnabled(false);
       tblVenda.setEnabled(true);
    }
   
    public void prepararSelecaoTabela(){
       btnNovo.setEnabled(true);
       btnExcluir.setEnabled(true);
       btnAlterar.setEnabled(true);
    }
   
    public void prepararAlterar(){
       btnNovo.setEnabled(false);
       btnExcluir.setEnabled(false);
       btnAlterar.setEnabled(false);
       btnSalvar.setEnabled(true);
       btnCancelar.setEnabled(true);
       tblVenda.setEnabled(false);
       tblVenda.clearSelection();
    }
   
    public void prepararExcluir(){
       btnExcluir.setEnabled(false);
       btnAlterar.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ClienteDialog = new javax.swing.JDialog();
        jPanel34 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        txtBuscaClienteDialog = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblClienteDialog = new javax.swing.JTable();
        ProdutoDialog = new javax.swing.JDialog();
        jPanel35 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        txtBuscaProdutoDialog = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblProdutoDialog = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVenda = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtIdCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbxFormaPagamento = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtData = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        txtUnidade = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblItenVenda = new javax.swing.JTable();
        btnAddItemVendas = new javax.swing.JButton();
        btnExcluiItemVenda = new javax.swing.JButton();
        txtproduto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtIdProduto = new javax.swing.JTextField();
        txtIDIten = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblTOTAL = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtFuncionario = new javax.swing.JTextField();
        txtIdFuncionario = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();

        ClienteDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ClienteDialog.setResizable(false);
        ClienteDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel34.setBackground(new java.awt.Color(249, 249, 249));

        jLabel68.setFont(new java.awt.Font("Shruti", 0, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(26, 26, 26));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("Buscar Cliente");

        txtBuscaClienteDialog.setBackground(new java.awt.Color(253, 253, 254));
        txtBuscaClienteDialog.setFont(new java.awt.Font("Shruti", 0, 18)); // NOI18N
        txtBuscaClienteDialog.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscaClienteDialog.setToolTipText("Limite em Reais para comprar fiado na loja.");
        txtBuscaClienteDialog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(104, 129, 164)));
        txtBuscaClienteDialog.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaClienteDialogCaretUpdate(evt);
            }
        });

        jSeparator5.setBackground(new java.awt.Color(249, 249, 249));
        jSeparator5.setForeground(new java.awt.Color(104, 129, 164));

        tblClienteDialog.setBackground(new java.awt.Color(248, 248, 248));
        tblClienteDialog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));
        tblClienteDialog.setFont(new java.awt.Font("Myanmar Text", 1, 12)); // NOI18N
        tblClienteDialog.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClienteDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteDialogMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblClienteDialog);

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBuscaClienteDialog)
                            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
                        .addGap(0, 116, Short.MAX_VALUE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator5))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4)))
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscaClienteDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        ClienteDialog.getContentPane().add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 460));

        ProdutoDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ProdutoDialog.setMinimumSize(new java.awt.Dimension(310, 380));
        ProdutoDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel35.setBackground(new java.awt.Color(249, 249, 249));

        jLabel69.setFont(new java.awt.Font("Shruti", 0, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(26, 26, 26));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("Buscar Produto");

        txtBuscaProdutoDialog.setBackground(new java.awt.Color(253, 253, 254));
        txtBuscaProdutoDialog.setFont(new java.awt.Font("Shruti", 0, 18)); // NOI18N
        txtBuscaProdutoDialog.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscaProdutoDialog.setToolTipText("Limite em Reais para comprar fiado na loja.");
        txtBuscaProdutoDialog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(104, 129, 164)));
        txtBuscaProdutoDialog.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaProdutoDialogCaretUpdate(evt);
            }
        });
        txtBuscaProdutoDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscaProdutoDialogActionPerformed(evt);
            }
        });

        jSeparator6.setBackground(new java.awt.Color(249, 249, 249));
        jSeparator6.setForeground(new java.awt.Color(104, 129, 164));

        tblProdutoDialog.setModel(new javax.swing.table.DefaultTableModel(
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
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addContainerGap())
        );

        ProdutoDialog.getContentPane().add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 460));

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(248, 248, 248));

        tblVenda.setBackground(new java.awt.Color(248, 248, 248));
        tblVenda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));
        tblVenda.setFont(new java.awt.Font("Myanmar Text", 1, 12)); // NOI18N
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

        jPanel5.setBackground(new java.awt.Color(248, 248, 248));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 100, 100)), "Busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(30, 30, 30))); // NOI18N

        txtBusca.setBackground(new java.awt.Color(245, 245, 245));
        txtBusca.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtBusca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtBusca.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaCaretUpdate(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel13.setText("Cliente:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusca, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
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

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAlterar)
                .addGap(51, 51, 51)
                .addComponent(btnExcluir)
                .addGap(488, 488, 488))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Consulta", jPanel4);

        jPanel6.setBackground(new java.awt.Color(248, 248, 248));

        jPanel2.setBackground(new java.awt.Color(248, 248, 248));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 100, 100)), "Dados Iniciais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel1.setText("Cliente");

        txtCliente.setBackground(new java.awt.Color(245, 245, 245));
        txtCliente.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtClienteMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel3.setText("Data:");

        cbxFormaPagamento.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        cbxFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Dinheiro", "Cartão Crédito", "Cartão Débito", "Cheque" }));
        cbxFormaPagamento.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel7.setText("Forma de Pagamento:");

        txtData.setBackground(new java.awt.Color(245, 245, 245));
        txtData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtData.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCliente)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)
                            .addComponent(cbxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(248, 248, 248));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 100, 100)), "Endereço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel4.setText("Quantidade:");

        jLabel5.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel5.setText("Preço Unitário:");

        txtQuantidade.setBackground(new java.awt.Color(245, 245, 245));
        txtQuantidade.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtQuantidade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtQuantidade.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtQuantidadeCaretUpdate(evt);
            }
        });

        txtUnidade.setBackground(new java.awt.Color(245, 245, 245));
        txtUnidade.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtUnidade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));

        txtTotal.setBackground(new java.awt.Color(245, 245, 245));
        txtTotal.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));

        jLabel6.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel6.setText("Total:");

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

        btnAddItemVendas.setText("Adicionar Produto +");
        btnAddItemVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemVendasActionPerformed(evt);
            }
        });

        btnExcluiItemVenda.setText("Remover Produto -");
        btnExcluiItemVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluiItemVendaActionPerformed(evt);
            }
        });

        txtproduto.setBackground(new java.awt.Color(245, 245, 245));
        txtproduto.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtproduto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtproduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtprodutoMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel2.setText("Produto:");

        jLabel8.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel8.setText("Total:");

        lblTOTAL.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        lblTOTAL.setText("..");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane12))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtproduto, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(btnAddItemVendas)
                                .addGap(30, 30, 30)
                                .addComponent(btnExcluiItemVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtIDIten, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDIten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTOTAL)
                    .addComponent(jLabel8))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(txtproduto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExcluiItemVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddItemVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtQuantidade)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtUnidade)
                    .addComponent(jLabel6)
                    .addComponent(txtTotal))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(248, 248, 248));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 100, 100)), "Dados Iniciais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel9.setText("Funcionario:");

        txtFuncionario.setBackground(new java.awt.Color(245, 245, 245));
        txtFuncionario.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtFuncionario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFuncionarioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(txtIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(btnCancelar)
                .addGap(102, 102, 102))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))))
        );

        jTabbedPane1.addTab("Cadastro", jPanel6);

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));

        jLabel15.setFont(new java.awt.Font("Shruti", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(227, 227, 227));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Venda");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 775, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1228, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 641, Short.MAX_VALUE)))
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

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
    prepararAlterar();
    ativarCampos();
    jTabbedPane1.setSelectedIndex(1);
    txtCliente.requestFocusInWindow(); 
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if(txtIdCliente.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Selecione um Cliente", "erro", JOptionPane.WARNING_MESSAGE);
        }
        else{
            cliente.setId(Integer.parseInt(txtIdCliente.getText()));
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir: "+ txtCliente.getText());
            if(confirma ==0){
                try{
                    clientedao.excluir(cliente);
                    limparCampos();
                    txtCliente.requestFocusInWindow();
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
                }
                atualizaTabelaVenda();
                prepararExcluir();
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tblVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVendaMouseClicked
        limparCampos();
        cliente = new ClienteM();

        txtIdCliente.setText(tblVenda.getValueAt(tblVenda.getSelectedRow(),0).toString());
        String integer = txtIdCliente.getText();
        int id = Integer.parseInt(integer);
        cliente.setId(id);
        
        try{
            cliente = clientedao.busca(id);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
        }
   
        tblVenda.getTableHeader().setReorderingAllowed(false);
        txtIdCliente.setText(Integer.toString(cliente.getId()));
        txtCliente.setText(cliente.getNome());
        txtCliente.setText(cliente.getNome());
        txtCliente.setText(cliente.getNome());
        txtCliente.setText(cliente.getNome());
        txtCliente.setText(cliente.getNome());
        txtCliente.setText(cliente.getNome());

        btnAlterar.setEnabled(true);
        btnExcluir.setEnabled(true);
    }//GEN-LAST:event_tblVendaMouseClicked

    private void txtBuscaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaCaretUpdate
        listaCliente = null;
        if(txtBusca.getText().equals("")){
            atualizaTabelaVenda();
        }else{
                    
            try {
                listaCliente = clientedao.buscaNomeLista(txtBusca.getText());

                if(listaCliente == null){
                    JOptionPane.showMessageDialog(null, "Nenhum Cliente encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaVenda();
                }else{
                    atualizaTabelaVendabusca();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtBuscaCaretUpdate

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limparCampos();
        prepararSalvareCancelar();
        desativarCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limparCampos();
        prepararNovo();
        ativarCampos();
        atualizaTabelaItemVendalimpa();
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        txtCliente.requestFocusInWindow();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
            funcionario.setId(1);
            //Salva tudo digitado no campo de texto para o objeto e salva no banco de dados
            venda.setIdCliente(cliente);
            venda.setIdFuncionario(funcionario);
            venda.setData(txtData.getText());
            venda.setTotalVendas(Float.valueOf(lblTOTAL.getText()));
            venda.setFormaPagamento(String.valueOf(cbxFormaPagamento.getSelectedItem()));
            try{
                vendadao.salvar(venda);
                JOptionPane.showMessageDialog(null, "Gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
            atualizaTabelaVenda();
            atualizaTabelaItemVendalimpa();
            prepararSalvareCancelar();
            desativarCampos();
            limparCampos();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluiItemVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluiItemVendaActionPerformed
        if(txtIDIten.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Selecione primeiro um produto! ","erro", JOptionPane.WARNING_MESSAGE);
        }else{
        venda.getItensVenda().remove(tblItenVenda.getSelectedRow());
        atualizaTabelaItemVenda();
        lblTOTAL.setText(String.valueOf(totalVenda()));
        txtIDIten.setText("");
        }
    }//GEN-LAST:event_btnExcluiItemVendaActionPerformed

    private void btnAddItemVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemVendasActionPerformed
        if(txtproduto.getText().isEmpty() && txtIdProduto.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Selecione primeiro um produto! ","erro", JOptionPane.WARNING_MESSAGE);
        }else{        
        produto.setId(Integer.valueOf(txtIdProduto.getText()));
            itemVenda = new ItensVenda();
            itemVenda.setIdProduto(produto);
            itemVenda.setPreco(Float.valueOf(txtUnidade.getText()));
            itemVenda.setQuantidade(Integer.valueOf(txtQuantidade.getText()));
            itemVenda.setPrecototalitem(Float.valueOf(txtTotal.getText()));
            venda.getItensVenda().add(itemVenda);
            atualizaTabelaItemVenda();
            lblTOTAL.setText(String.valueOf(totalVenda()));
            PreparaVenda();

        txtQuantidade.setText("");
        txtUnidade.setText("");
        txtTotal.setText("");
        txtproduto.setText("");
        txtIdProduto.setText("");
        }
    }//GEN-LAST:event_btnAddItemVendasActionPerformed

    private void txtBuscaClienteDialogCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaClienteDialogCaretUpdate
        // TODO add your handling code here:
        // Para atualizar a tabela Cliente
        atualizaTabelaClienteDialog();
        listaCliente = null;
        if(txtBuscaClienteDialog.getText().equals("")){
            atualizaTabelaClienteDialog();
        }else{

            try {
                listaCliente = clientedao.buscaNomeLista(txtBuscaClienteDialog.getText());

                if(listaCliente == null){
                    JOptionPane.showMessageDialog(null, "Nenhum Cliente encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaClienteDialog();
                }else{
                    atualizaTabelaClienteBusca();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
        }

    }//GEN-LAST:event_txtBuscaClienteDialogCaretUpdate

    private void txtBuscaProdutoDialogCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaProdutoDialogCaretUpdate
        // TODO add your handling code here:
        if (txtBuscaProdutoDialog.getText().equals("")) {
            atualizaTabelaProdutoDialog();
        }else{

            try {
                listaProduto = produtoDao.buscaNomeLista(txtBuscaProdutoDialog.getText());

                if(listaProduto == null){

                    JOptionPane.showMessageDialog(null, "Nenhum Produto encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaProdutoDialog();
                }else{
                    atualizaTabelaProdutoBusca();
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }

        }

    }//GEN-LAST:event_txtBuscaProdutoDialogCaretUpdate

    private void tblProdutoDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoDialogMouseClicked
        // TODO add your handling code here:
        txtIdProduto.setText(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 0).toString());
        txtproduto.setText(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 1).toString());
        txtUnidade.setText(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 3).toString());
        produto = new ProdutoM();
        produto.setId(Integer.parseInt(txtIdProduto.getText()));
        produto.setNome(txtproduto.getText());
        produto.setQuantidade(Integer.parseInt(txtUnidade.getText()));
        ProdutoDialog.dispose();
        txtQuantidade.setText("1");
    }//GEN-LAST:event_tblProdutoDialogMouseClicked

    private void txtClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtClienteMouseClicked
       ClienteDialog.setVisible(true);
       ClienteDialog.setLocationRelativeTo(null);
       atualizaTabelaClienteDialog();
       tblClienteDialog.getTableHeader().setReorderingAllowed(false);
    }//GEN-LAST:event_txtClienteMouseClicked

    private void txtprodutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtprodutoMouseClicked
       ProdutoDialog.setVisible(true);
       ProdutoDialog.setLocationRelativeTo(null);
       atualizaTabelaProdutoDialog();
       tblProdutoDialog.getTableHeader().setReorderingAllowed(false);
    }//GEN-LAST:event_txtprodutoMouseClicked

    private void txtQuantidadeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtQuantidadeCaretUpdate
        if(txtQuantidade.getText().isEmpty() ){
            //txtQtde.setText("1");
        }else{
            CalculaVenda();
        }
    }//GEN-LAST:event_txtQuantidadeCaretUpdate

    private void tblClienteDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteDialogMouseClicked
        txtIdCliente.setText(tblClienteDialog.getValueAt(tblClienteDialog.getSelectedRow(), 0).toString());
        txtCliente.setText(tblClienteDialog.getValueAt(tblClienteDialog.getSelectedRow(), 1).toString());
        cliente.setId(Integer.valueOf(txtIdCliente.getText()));
        cliente.setNome(txtCliente.getText());
        ClienteDialog.dispose();
    }//GEN-LAST:event_tblClienteDialogMouseClicked

    private void txtBuscaProdutoDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaProdutoDialogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaProdutoDialogActionPerformed

    private void tblItenVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItenVendaMouseClicked
        txtIDIten.setText(tblItenVenda.getValueAt(tblItenVenda.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_tblItenVendaMouseClicked

    private void txtFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFuncionarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuncionarioMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog ClienteDialog;
    private javax.swing.JDialog ProdutoDialog;
    private javax.swing.JButton btnAddItemVendas;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluiItemVenda;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxFormaPagamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTOTAL;
    private javax.swing.JTable tblClienteDialog;
    private javax.swing.JTable tblItenVenda;
    private javax.swing.JTable tblProdutoDialog;
    private javax.swing.JTable tblVenda;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtBuscaClienteDialog;
    private javax.swing.JTextField txtBuscaProdutoDialog;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JTextField txtFuncionario;
    private javax.swing.JTextField txtIDIten;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdFuncionario;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUnidade;
    private javax.swing.JTextField txtproduto;
    // End of variables declaration//GEN-END:variables
}
