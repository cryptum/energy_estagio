package VIEW;

import DAO.ClienteDao;
import DAO.DespesaDao;
import DAO.EntradadeProdutoDao;
import DAO.FuncionarioDao;
import DAO.MarcaDao;
import DAO.ModeloDao;
import DAO.ProdutoDao;
import DAO.SituacaoDaEmpresaDao;
import DAO.VendaDao;
import DAO.VendaMLDao;
import MODEL.ClienteM;
import MODEL.DespesaM;
import MODEL.EntradadeProdutoM;
import MODEL.FuncionarioM;
import MODEL.ProdutoM;
import MODEL.VendaM;
import MODEL.VendaMLM;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Danilo-NOTE
 */
public class SituacaoDaEmpresa extends javax.swing.JInternalFrame {

    /**
     * Creates new form SituacaoDaEmpresa_DO_CU_QUENTE
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
    SituacaoDaEmpresaDao situacaodao = new SituacaoDaEmpresaDao();
    
    DespesaM despesas = new DespesaM();
    DespesaDao despesasdao = new DespesaDao();
    List<DespesaM> ListaDespesas = new ArrayList<>();
    
    EntradadeProdutoM entradadeproduto = new EntradadeProdutoM();
    EntradadeProdutoDao entradadeprodutodao = new EntradadeProdutoDao();
    List<EntradadeProdutoM> ListaEntradadeProduto = new ArrayList<>();

    
    public SituacaoDaEmpresa(){
        initComponents();
        this.setVisible(true);
        String datasistema = new SimpleDateFormat("MM").format(new Date(System.currentTimeMillis()));
        sldMes.setValue(Integer.valueOf(datasistema));
        atualizaBoxAno();
        try {
            AtualizaVIEW();
        } catch (SQLException ex) {
            Logger.getLogger(SituacaoDaEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        txtNumeroMes.setVisible(false);
        txtIdDespesas.setVisible(false);

       
    }
    
    public void AtualizaVIEW() throws SQLException{
        float totalmes = 0, totalano = 0;
        String ano = (String) cbxAno.getSelectedItem();
        String data1, data2, data3,data4;
        data1 = ano+"/"+txtNumeroMes.getText()+"/"+"01";
        data2 = ano+"/"+txtNumeroMes.getText()+"/"+"31";
        data3 = ano+"/01/01";
        data4 = ano+"/12/31";
        
        //Atualiza Venda
        listaVenda = situacaodao.BuscaTotalVendaMes(data1,data2);
        listaVenda.forEach((vendames) -> {
            txtVendaMes.setText(String.valueOf(vendames.getTotalVendas()));
        });
        
        listaVenda = situacaodao.BuscaTotalVendaAno(data3,data4);
        listaVenda.forEach((vendaano) -> {
            txtVendaAno.setText(String.valueOf(vendaano.getTotalVendas()));
        });
        
        //Atualiza Venda Mercado livre
        ListaVendaML = situacaodao.BuscaTotalVendaMLMes(data1,data2);
        ListaVendaML.forEach((vendamlmes) -> {
            txtVendaMLMes.setText(String.valueOf(vendamlmes.getTotalVenda()));
        });
        
        ListaVendaML = situacaodao.BuscaTotalVendaMLMes(data3,data4);
        ListaVendaML.forEach((vendamlano) -> {
            txtVendaMLAno.setText(String.valueOf(vendamlano.getTotalVenda()));
        });
        
        //Atualiza Despesas
        ListaDespesas = situacaodao.BuscaTotalDespesaMes(data1,data2);
        ListaDespesas.forEach((despesa1mes) -> {
            txtDespesasMes.setText(String.valueOf(despesa1mes.getValor()));
        });
        
        ListaDespesas = situacaodao.BuscaTotalDespesaAno(data3,data4);
        ListaDespesas.forEach((despesa1ano) -> {
            txtDespesasAno.setText(String.valueOf(despesa1ano.getValor()));
        });
        
        //Atualiza Entrada de Produto
        ListaEntradadeProduto = situacaodao.BuscaTotalEntradeDeProdutoMes(data1,data2);
        ListaEntradadeProduto.forEach((entradames) -> {
            txtEntradaMes.setText(String.valueOf(entradames.getQuantidade()));
        });
        
        ListaEntradadeProduto = situacaodao.BuscaTotalEntradeDeProdutoAno(data3,data4);
        ListaEntradadeProduto.forEach((entradaano) -> {
            txtEntradaAno.setText(String.valueOf(entradaano.getQuantidade()));
        });
        
    }
    
    
    
    public void atualizaBoxAno(){
        cbxAno.removeAllItems();
        
        try {
            listaVenda = situacaodao.buscaDataSituacao();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         String dados[][] = new String[listaVenda.size()][5];
        for (VendaM venda : listaVenda) {
            cbxAno.addItem(venda.getData());
        }
    }
    
    
    public void atualizaTabelaDespesas(){
        despesas = new DespesaM();
        try {
            ListaDespesas = despesasdao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[ListaDespesas.size()][5];
            int i = 0;
            for (DespesaM despesa : ListaDespesas) {
                dados[i][0] = String.valueOf(despesa.getId());
                dados[i][1] = despesa.getDescricao();
                dados[i][2] = String.valueOf(despesa.getValor());
                dados[i][3] = despesa.getData();
                dados[i][4] = despesa.getHora();

                i++;
            }
            String tituloColuna[] = {"ID", "Descrição", "Valor", "Data","Hora"};
            DefaultTableModel tabeladespesas = new DefaultTableModel();
            tabeladespesas.setDataVector(dados, tituloColuna);
            tblDespesas.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblDespesas.getColumnModel().getColumn(0).setMaxWidth(0);
            tblDespesas.getColumnModel().getColumn(0).setMinWidth(0);
            tblDespesas.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblDespesas.getColumnModel().getColumn(1).setPreferredWidth(300);
            tblDespesas.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblDespesas.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblDespesas.setRowHeight(35);
            tblDespesas.updateUI();
    }
    
    //Atualiza Busca
    public void atualizaTabelaDespesasBusca(){
        despesas = new DespesaM();

        
        String dados[][] = new String[ListaDespesas.size()][5];
            int i = 0;
            for (DespesaM despesa : ListaDespesas) {
                dados[i][0] = String.valueOf(despesa.getId());
                dados[i][1] = despesa.getDescricao();
                dados[i][2] = String.valueOf(despesa.getValor());
                dados[i][3] = despesa.getData();
                dados[i][4] = despesa.getHora();

                i++;
            }
            String tituloColuna[] = {"ID", "Descrição", "Valor", "Data","Hora"};
            DefaultTableModel tabeladespesas = new DefaultTableModel();
            tabeladespesas.setDataVector(dados, tituloColuna);
            tblDespesas.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblDespesas.getColumnModel().getColumn(0).setMaxWidth(0);
            tblDespesas.getColumnModel().getColumn(0).setMinWidth(0);
            tblDespesas.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblDespesas.getColumnModel().getColumn(1).setPreferredWidth(300);
            tblDespesas.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblDespesas.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblDespesas.setRowHeight(35);
            tblDespesas.updateUI();
    }
   
    public void LimparDespesas(){
        txtIdDespesas.setText("");
        txtNomeDespesa.setText("");
        txtDescricaoDespesa.setText("");
        txtDataDespesa.setText("");
        txtHoraDespesa.setText("");
        txtValorDespesa.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Grupo1 = new javax.swing.ButtonGroup();
        Grupo2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        btnAtualizar = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cbxAno = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtVendaMes = new javax.swing.JFormattedTextField();
        txtVendaAno = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        sldMes = new javax.swing.JSlider();
        txtMes = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtVendaMLMes = new javax.swing.JFormattedTextField();
        txtVendaMLAno = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNumeroMes = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtDespesasMes = new javax.swing.JFormattedTextField();
        txtDespesasAno = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtEntradaMes = new javax.swing.JFormattedTextField();
        txtEntradaAno = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDespesas = new javax.swing.JTable();
        txtNomeDespesa = new javax.swing.JFormattedTextField();
        btnSalvarDespesa = new javax.swing.JButton();
        txtIdDespesas = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnLimparDespesa = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        txtBuscaCategoria = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtDataDespesa = new javax.swing.JFormattedTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtHoraDespesa = new javax.swing.JFormattedTextField();
        jLabel25 = new javax.swing.JLabel();
        txtValorDespesa = new javax.swing.JFormattedTextField();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricaoDespesa = new javax.swing.JTextArea();
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

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel21.setText("Ano:");

        jLabel22.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel22.setText("Mês:");

        cbxAno.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        cbxAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018" }));
        cbxAno.setToolTipText("");
        cbxAno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxAnoItemStateChanged(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(248, 248, 248));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Venda ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel2.setText("Mês:");

        txtVendaMes.setBackground(new java.awt.Color(245, 245, 245));
        txtVendaMes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtVendaMes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtVendaMes.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        txtVendaAno.setBackground(new java.awt.Color(245, 245, 245));
        txtVendaAno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtVendaAno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtVendaAno.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel4.setText("Ano:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVendaMes, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVendaAno, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtVendaAno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtVendaMes, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sldMes.setBackground(new java.awt.Color(211, 211, 211));
        sldMes.setMaximum(12);
        sldMes.setMinimum(1);
        sldMes.setToolTipText("Utilize as setas do teclado para maior precisão");
        sldMes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldMesStateChanged(evt);
            }
        });

        txtMes.setBackground(new java.awt.Color(245, 245, 245));
        txtMes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtMes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtMes.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(248, 248, 248));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Venda Mercado Livre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel3.setText("Mês:");

        txtVendaMLMes.setBackground(new java.awt.Color(245, 245, 245));
        txtVendaMLMes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtVendaMLMes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtVendaMLMes.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        txtVendaMLAno.setBackground(new java.awt.Color(245, 245, 245));
        txtVendaMLAno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtVendaMLAno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtVendaMLAno.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel5.setText("Ano:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVendaMLMes, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVendaMLAno, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVendaMLAno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVendaMLMes, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        txtNumeroMes.setBackground(new java.awt.Color(245, 245, 245));
        txtNumeroMes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtNumeroMes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtNumeroMes.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        jPanel6.setBackground(new java.awt.Color(248, 248, 248));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Despesas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel6.setText("Mês:");

        txtDespesasMes.setBackground(new java.awt.Color(245, 245, 245));
        txtDespesasMes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtDespesasMes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtDespesasMes.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        txtDespesasAno.setBackground(new java.awt.Color(245, 245, 245));
        txtDespesasAno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtDespesasAno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtDespesasAno.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel7.setText("Ano:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDespesasMes, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDespesasAno, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDespesasAno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDespesasMes, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(248, 248, 248));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entrada de Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel8.setText("Mês:");

        txtEntradaMes.setBackground(new java.awt.Color(245, 245, 245));
        txtEntradaMes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtEntradaMes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtEntradaMes.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        txtEntradaAno.setBackground(new java.awt.Color(245, 245, 245));
        txtEntradaAno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtEntradaAno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtEntradaAno.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel9.setText("Ano:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEntradaMes, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEntradaAno, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEntradaAno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEntradaMes, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 323, Short.MAX_VALUE)
                        .addComponent(sldMes, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(cbxAno, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtNumeroMes, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(cbxAno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22)
                        .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sldMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumeroMes, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("<html><p align = \"center\"> Visão <br> Geral </p></html> ", jPanel4);

        jPanel9.setBackground(new java.awt.Color(248, 248, 248));

        jPanel14.setBackground(new java.awt.Color(248, 248, 248));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Despesas", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        tblDespesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome"
            }
        ));
        tblDespesas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDespesasMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblDespesas);

        txtNomeDespesa.setBackground(new java.awt.Color(245, 245, 245));
        txtNomeDespesa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtNomeDespesa.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        btnSalvarDespesa.setText("Salvar");
        btnSalvarDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarDespesaActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel19.setText("Nome da Despesa:");

        btnLimparDespesa.setText("Limpar");
        btnLimparDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparDespesaActionPerformed(evt);
            }
        });

        jPanel15.setBackground(new java.awt.Color(248, 248, 248));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        txtBuscaCategoria.setBackground(new java.awt.Color(245, 245, 245));
        txtBuscaCategoria.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtBuscaCategoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtBuscaCategoria.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaCategoriaCaretUpdate(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel20.setText("Nome:");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtBuscaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtDataDespesa.setBackground(new java.awt.Color(245, 245, 245));
        txtDataDespesa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtDataDespesa.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel23.setText("Data:");

        jLabel24.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel24.setText("Hora:");

        txtHoraDespesa.setBackground(new java.awt.Color(245, 245, 245));
        txtHoraDespesa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtHoraDespesa.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel25.setText("Valor:");

        txtValorDespesa.setBackground(new java.awt.Color(245, 245, 245));
        txtValorDespesa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtValorDespesa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtValorDespesa.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Myanmar Text", 0, 15)); // NOI18N
        jLabel26.setText("Descrição da Despesa:");

        txtDescricaoDespesa.setBackground(new java.awt.Color(245, 245, 245));
        txtDescricaoDespesa.setColumns(20);
        txtDescricaoDespesa.setLineWrap(true);
        txtDescricaoDespesa.setRows(5);
        txtDescricaoDespesa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        jScrollPane2.setViewportView(txtDescricaoDespesa);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addComponent(btnSalvarDespesa)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimparDespesa))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdDespesas, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDataDespesa)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtHoraDespesa)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtValorDespesa)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtNomeDespesa))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtIdDespesas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(txtNomeDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addComponent(jLabel23)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtDataDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtHoraDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtValorDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLimparDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalvarDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("<html><p align = \"center\">Inserir <br> Despesas</p></html> ", jPanel9);

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));

        jLabel15.setFont(new java.awt.Font("Shruti", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(227, 227, 227));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Situação da Empresa");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
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
        try {
            AtualizaVIEW();
        } catch (SQLException ex) {
            Logger.getLogger(SituacaoDaEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void cbxAnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxAnoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAnoItemStateChanged

    private void sldMesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldMesStateChanged
        int opt = sldMes.getValue();
        switch(opt){
            case 1 :{txtMes.setText("Janeiro"); txtNumeroMes.setText("01");break;}
            case 2 :{txtMes.setText("Fevereiro");txtNumeroMes.setText("02");break;}
            case 3 :{txtMes.setText("Março");txtNumeroMes.setText("03");break;}
            case 4 :{txtMes.setText("Abril");txtNumeroMes.setText("04");break;}
            case 5 :{txtMes.setText("Maio");txtNumeroMes.setText("05");break;}
            case 6 :{txtMes.setText("Junho");txtNumeroMes.setText("06");break;}
            case 7 :{txtMes.setText("Julho");txtNumeroMes.setText("07");break;}
            case 8 :{txtMes.setText("Agosto");txtNumeroMes.setText("08");break;}
            case 9 :{txtMes.setText("Setembro");txtNumeroMes.setText("09");break;}
            case 10 :{txtMes.setText("Outubro");txtNumeroMes.setText("10");break;}
            case 11 :{txtMes.setText("Novembro");txtNumeroMes.setText("11");break;}
            case 12 :{txtMes.setText("Dezembro");txtNumeroMes.setText("12");break;}
        }
    }//GEN-LAST:event_sldMesStateChanged

    private void tblDespesasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDespesasMouseClicked
        despesas = new DespesaM();
        txtIdDespesas.setText(tblDespesas.getValueAt(tblDespesas.getSelectedRow(),0).toString());

        try{
            despesas = despesasdao.busca(Integer.parseInt(txtIdDespesas.getText()));
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
        }
        
        tblDespesas.getTableHeader().setReorderingAllowed(false);
        txtIdDespesas.setText(Integer.toString(despesas.getId()));
        txtNomeDespesa.setText(despesas.getDescricao());
        txtValorDespesa.setText(String.valueOf(despesas.getValor()));
        txtDataDespesa.setText(despesas.getData());
        txtHoraDespesa.setText(despesas.getHora());
    }//GEN-LAST:event_tblDespesasMouseClicked

    private void btnSalvarDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarDespesaActionPerformed
        despesas = new DespesaM();
        String custo = txtValorDespesa.getText();
        String valor = custo.replaceAll(",", ".");
        
        
        if(txtNomeDespesa.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha todos os obrigatórios !", "erro", JOptionPane.WARNING_MESSAGE);
            txtNomeDespesa.requestFocusInWindow();
        }
        else if(txtIdDespesas.getText().isEmpty()){
        
        //Salva tudo digitado no campo de texto para o objeto e salva no banco de dados
        despesas.setDescricao(txtNomeDespesa.getText());
        despesas.setValor(Float.valueOf(valor));
        despesas.setData(txtDataDespesa.getText());
        despesas.setHora(txtHoraDespesa.getText());
        try{
        produtodao.salvar(produto);
        JOptionPane.showMessageDialog(null, "Gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException ex){
        JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        }
        else{
        //Salva tudo que foi alterado nos campos de texto para o objeto e salva no banco de dados
        despesas.setId(Integer.valueOf(txtIdDespesas.getText()));
        despesas.setDescricao(txtNomeDespesa.getText());
        despesas.setValor(Float.valueOf(valor));
        despesas.setData(txtDataDespesa.getText());
        despesas.setHora(txtHoraDespesa.getText());
        try{
        produtodao.alterar(produto);
        JOptionPane.showMessageDialog(null, "Alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException ex){
        JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        }
        atualizaTabelaDespesas();
        LimparDespesas();
        tblDespesas.clearSelection();
    }//GEN-LAST:event_btnSalvarDespesaActionPerformed

    private void btnLimparDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparDespesaActionPerformed
        txtIdDespesas.setText("");
        txtNomeDespesa.setText("");
        tblDespesas.clearSelection();
        txtNomeDespesa.requestFocusInWindow();
    }//GEN-LAST:event_btnLimparDespesaActionPerformed

    private void txtBuscaCategoriaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaCategoriaCaretUpdate
        ListaDespesas = null;
        if(txtBuscaCategoria.getText().equals("")){
            atualizaTabelaDespesas();
        }else{
            try {
                ListaDespesas = despesasdao.buscaNomeLista(txtBuscaCategoria.getText());

                if(ListaDespesas == null){
                    JOptionPane.showMessageDialog(null, "Nenhum Categoria encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaDespesas();
                }else{
                    atualizaTabelaDespesasBusca();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtBuscaCategoriaCaretUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Grupo1;
    private javax.swing.ButtonGroup Grupo2;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnLimparDespesa;
    private javax.swing.JButton btnSalvarDespesa;
    private javax.swing.JComboBox<String> cbxAno;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JSlider sldMes;
    private javax.swing.JTable tblDespesas;
    private javax.swing.JTextField txtBuscaCategoria;
    private javax.swing.JFormattedTextField txtDataDespesa;
    private javax.swing.JTextArea txtDescricaoDespesa;
    private javax.swing.JFormattedTextField txtDespesasAno;
    private javax.swing.JFormattedTextField txtDespesasMes;
    private javax.swing.JFormattedTextField txtEntradaAno;
    private javax.swing.JFormattedTextField txtEntradaMes;
    private javax.swing.JFormattedTextField txtHoraDespesa;
    private javax.swing.JTextField txtIdDespesas;
    private javax.swing.JFormattedTextField txtMes;
    private javax.swing.JFormattedTextField txtNomeDespesa;
    private javax.swing.JFormattedTextField txtNumeroMes;
    private javax.swing.JFormattedTextField txtValorDespesa;
    private javax.swing.JFormattedTextField txtVendaAno;
    private javax.swing.JFormattedTextField txtVendaMLAno;
    private javax.swing.JFormattedTextField txtVendaMLMes;
    private javax.swing.JFormattedTextField txtVendaMes;
    // End of variables declaration//GEN-END:variables
}
