package VIEW;

import DAO.*;
import MODEL.*;
import com.sun.java.swing.plaf.windows.WindowsTableHeaderUI;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.PasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import util.ColorirVenda;

/**
 *
 * @author Danilo-NOTE
 */
public class VendaView extends javax.swing.JInternalFrame {

    /**
     * Creates new form VendaView_DO_CU_QUENTE
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
    
    ItensVenda itemVenda = new ItensVenda();
    ItemvendaDao itemvendadao  = new ItemvendaDao();
    private List<ItensVenda> listaItemVenda = new ArrayList<>();

    public List<ItensVenda> getListaItemVenda() {
        return listaItemVenda;
    }

    public void setListaItemVenda(List<ItensVenda> listaItemVenda) {
        this.listaItemVenda = listaItemVenda;
    }
    
    
    public VendaView() {
        initComponents();
        this.setVisible(true);
        atualizaTabelaVenda();
        atualizaTabelaItemVenda();
        jTabbedPane1.setUI(new BasicTabbedPaneUI());
        tblClienteDialog.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblClienteDialog.getTableHeader().setReorderingAllowed(false);
        tblFuncionarioDialog.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblFuncionarioDialog.getTableHeader().setReorderingAllowed(false);
        tblItenVenda.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblItenVenda.getTableHeader().setReorderingAllowed(false);
        tblItensDialog.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblItensDialog.getTableHeader().setReorderingAllowed(false);
        tblProdutoDialog.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblProdutoDialog.getTableHeader().setReorderingAllowed(false);
        tblVenda.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblVenda.getTableHeader().setReorderingAllowed(false);
        txtData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        txtIdVenda.setVisible(false);
        txtIdProduto.setVisible(false);
        txtIDIten.setVisible(false);
        txtQuantidadeTotal.setVisible(false);
        txtIdFuncionario.setVisible(false);
        txtIdCliente.setVisible(false);
        
        ClienteDialog.setSize(525, 490);
        ProdutoDialog.setSize(535, 500);
        FuncionarioDialog.setSize(525, 490);
        ItensDialog.setSize(535, 500);
        FinalizaDialog.setSize(825, 365);
        btnAddItemVendas.setUI(new BasicButtonUI());
        btnCancelar.setUI(new BasicButtonUI());
        btnExcluiItemVenda.setUI(new BasicButtonUI());
        btnExcluir.setUI(new BasicButtonUI());
        btnFinalizar.setUI(new BasicButtonUI());
        btnNovo.setUI(new BasicButtonUI());
        btnSalvar.setUI(new BasicButtonUI());
        btnVoltar.setUI(new BasicButtonUI());
    }

    //Atualiza todos os funcionario para a tabela
    public void atualizaTabelaVenda(){
        venda = new VendaM();
        try {
        listaVenda = vendadao.listaTodos();
        }catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        Object dados[][] = new String[listaVenda.size()][5];
            int i = 0;
            for (VendaM venda2 : listaVenda) {
                dados[i][0] = String.valueOf(venda2.getId());
                dados[i][1] = venda2.getIdCliente().getNome();
                dados[i][2] = venda2.getData();
                dados[i][3] = String.valueOf(venda2.getTotalVendas());
                
                if(venda2.getExcluido() == false){
                    dados[i][4] = "Ativa";
                }else{
                    dados[i][4] = "Cancelada";
                }
                i++;
            }
            String tituloColuna[] = {"ID", "Cliente", "Data","Valor Total","Situa????o"};
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
            
            tblVenda.getColumnModel().getColumn(4).setMaxWidth(0);
            tblVenda.getColumnModel().getColumn(4).setMinWidth(0);
            tblVenda.getColumnModel().getColumn(4).setPreferredWidth(0);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblVenda.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblVenda.setDefaultRenderer(Object.class, new ColorirVenda());
            tblVenda.setRowHeight(35);
            tblVenda.updateUI();
    }
    
    public void atualizaTabelaVendabusca(){
        venda = new VendaM();
        
        String dados[][] = new String[listaVenda.size()][5];
            int i = 0;
            for (VendaM venda2 : listaVenda) {
                dados[i][0] = String.valueOf(venda2.getId());
                dados[i][1] = venda2.getIdCliente().getNome();
                dados[i][2] = venda2.getData();
                dados[i][3] = String.valueOf(venda2.getTotalVendas());
                
                if(venda2.getExcluido() == false){
                    dados[i][4] = "Ativa";
                }else{
                    dados[i][4] = "Cancelada";
                }
                i++;
            }
            String tituloColuna[] = {"ID", "Cliente", "Data","Valor total","situa????o"};
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
            
            tblVenda.getColumnModel().getColumn(4).setMaxWidth(0);
            tblVenda.getColumnModel().getColumn(4).setMinWidth(0);
            tblVenda.getColumnModel().getColumn(4).setPreferredWidth(0);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblVenda.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblVenda.setDefaultRenderer(Object.class, new ColorirVenda());
            tblVenda.setRowHeight(35);
            tblVenda.updateUI();
    }
    
    //Atualiza Busca
    public void atualizaTabelaClienteDialogBusca(){
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

            tblClienteDialog.getColumnModel().getColumn(0).setMaxWidth(0);
            tblClienteDialog.getColumnModel().getColumn(0).setMinWidth(0);
            tblClienteDialog.getColumnModel().getColumn(0).setPreferredWidth(0);
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

            tblClienteDialog.getColumnModel().getColumn(0).setMaxWidth(0);
            tblClienteDialog.getColumnModel().getColumn(0).setMinWidth(0);
            tblClienteDialog.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblClienteDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblClienteDialog.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblClienteDialog.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblClienteDialog.setRowHeight(35);
            tblClienteDialog.updateUI();
    }
    
    public void atualizaTabelaFuncionarioDialogBusca(){
        funcionario = new FuncionarioM();

        
        String dados[][] = new String[listaFuncionario.size()][3];
            int i = 0;
            for (FuncionarioM funcionario : listaFuncionario) {
                dados[i][0] = String.valueOf(funcionario.getId());
                dados[i][1] = funcionario.getNome();
                dados[i][2] = funcionario.getNascimento();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome", "Nascimento"};
            DefaultTableModel tabelaFuncionario = new DefaultTableModel();
            tabelaFuncionario.setDataVector(dados, tituloColuna);
            tblFuncionarioDialog.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblFuncionarioDialog.getColumnModel().getColumn(0).setMaxWidth(0);
            tblFuncionarioDialog.getColumnModel().getColumn(0).setMinWidth(0);
            tblFuncionarioDialog.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblFuncionarioDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblFuncionarioDialog.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblFuncionarioDialog.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblFuncionarioDialog.setRowHeight(35);
            tblFuncionarioDialog.updateUI();
    }
    
    public void atualizaTabelaFuncionarioDialog(){
        funcionario = new FuncionarioM();
        try {
            listaFuncionario = funcionariodao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaFuncionario.size()][3];
            int i = 0;
            for (FuncionarioM funcionario : listaFuncionario) {
                dados[i][0] = String.valueOf(funcionario.getId());
                dados[i][1] = funcionario.getNome();
                dados[i][2] = funcionario.getNascimento();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome","Nascimento"};
            DefaultTableModel tabelaFuncionario = new DefaultTableModel();
            tabelaFuncionario.setDataVector(dados, tituloColuna);
            tblFuncionarioDialog.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblFuncionarioDialog.getColumnModel().getColumn(0).setMaxWidth(0);
            tblFuncionarioDialog.getColumnModel().getColumn(0).setMinWidth(0);
            tblFuncionarioDialog.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblFuncionarioDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblFuncionarioDialog.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblFuncionarioDialog.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblFuncionarioDialog.setRowHeight(35);
            tblFuncionarioDialog.updateUI();
    }
    
    public void atualizaTabelaProdutoDialogBusca(){
        produto = new ProdutoM();

        String dados[][] = new String[listaProduto.size()][4];
            int i = 0;
            for (ProdutoM produto : listaProduto) {
                dados[i][0] = String.valueOf(produto.getId());
                dados[i][1] = produto.getNome();
                dados[i][2] = String.valueOf(produto.getQuantidade());
                dados[i][3] = String.valueOf(produto.getValorMax());

                i++;
            }
            String tituloColuna[] = {"ID", "Nome","Quantidade","Valor M??ximo"};
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
                dados[i][3] = String.valueOf(produto.getValorMax());

                i++;
            }
            String tituloColuna[] = {"ID", "Nome","Quantidade","Valor M??ximo"};
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
        
        String dados[][] = new String[listaItemVenda.size()][4];
        int i = 0;
        for(ItensVenda iv : listaItemVenda){
            dados[i][0] = iv.getIdProduto().getNome();
            dados[i][1] = String.valueOf(iv.getQuantidade());
            dados[i][2] = String.valueOf(iv.getPreco());
            dados[i][3] = String.valueOf(iv.getTotal());
            i++;
        }
        String tituloColuna[] = {"Produto", "Qtde", "Pre??o Unit.", "Pre??o Total"};
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
        
        tblItenVenda.getColumnModel().getColumn(0).setPreferredWidth(500);
        tblItenVenda.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblItenVenda.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblItenVenda.getColumnModel().getColumn(3).setPreferredWidth(100);
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tblItenVenda.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tblItenVenda.setRowHeight(25);
        tblItenVenda.updateUI();
        
    }
    
    private void atualizaTabelaItemVendalimpa() {
        venda = new VendaM();
        String dados[][] = new String[listaItemVenda.size()][4];
        int i = 0;
        for(ItensVenda iv : listaItemVenda){
            dados[i][0] = iv.getIdProduto().getNome();
            dados[i][1] = String.valueOf(iv.getQuantidade());
            dados[i][2] = String.valueOf(iv.getPreco());
            dados[i][3] = String.valueOf(iv.getTotal());
            i++;
        }
        String tituloColuna[] = {"Produto", "Qtde", "Pre??o Unit.", "Pre??o Total"};
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
        tblItenVenda.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblItenVenda.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblItenVenda.getColumnModel().getColumn(3).setPreferredWidth(100);
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        tblItenVenda.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tblItenVenda.setRowHeight(25);
        tblItenVenda.updateUI();
        
    }
    
    public void atualizaTabelaItensDialog(){

        String dados[][] = new String[listaItemVenda.size()][4];
            int i = 0;
            for (ItensVenda itemVendab : listaItemVenda) {
                dados[i][0] = String.valueOf(itemVendab.getId());
                dados[i][1] = itemVendab.getIdProduto().getNome();
                dados[i][2] = String.valueOf(itemVendab.getQuantidade());
                dados[i][3] = String.valueOf(itemVendab.getTotal());

                i++;
            }
            String tituloColuna[] = {"ID", "Produto","Quantidade","Total"};
            DefaultTableModel tabelaitem = new DefaultTableModel();
            tabelaitem.setDataVector(dados, tituloColuna);
            tblItensDialog.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false,false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblItensDialog.getColumnModel().getColumn(0).setMaxWidth(0);
            tblItensDialog.getColumnModel().getColumn(0).setMinWidth(0);
            tblItensDialog.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblItensDialog.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblItensDialog.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblItensDialog.getColumnModel().getColumn(3).setPreferredWidth(70);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblItensDialog.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblItensDialog.setRowHeight(35);
            tblItensDialog.updateUI();
    }
    
    public void CalculaVenda(){
        txtTotal.setText(String.valueOf(Float.valueOf(txtQuantidade.getText()) * Float.valueOf(txtUnidade.getText())));
    }
    
    private float totalVenda(){
        float total = 0;
        for(ItensVenda iv : getListaItemVenda()){
            total += iv.getTotal();
        }
        return total;
    }
    
    public void PreparaVenda(){
        txtQuantidade.setText("1");
        txtIdProduto.setText("");
        txtproduto.setText("");
    }
    

    // DECLARA????O DE M??TODOS DE CONTROLE DE BOT??ES
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
       btnFinalizar.setEnabled(true);
       btnCancelar.setEnabled(true);
       tblVenda.setEnabled(false);
       tblVenda.clearSelection();
    }
   
    public void prepararSalvareCancelar() {
       btnNovo.setEnabled(true);
       btnFinalizar.setEnabled(false);
       btnCancelar.setEnabled(false);
       tblVenda.setEnabled(true);
    }
   
    public void prepararSelecaoTabela(){
       btnNovo.setEnabled(true);
    }
   
    public void prepararAlterar(){
       btnNovo.setEnabled(false);
       btnFinalizar.setEnabled(true);
       btnCancelar.setEnabled(true);
       tblVenda.setEnabled(false);
       tblVenda.clearSelection();
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
        FinalizaDialog = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtFuncionario = new javax.swing.JTextField();
        txtIdFuncionario = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtIdCliente = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbxFormaPagamento = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtData = new javax.swing.JFormattedTextField();
        lblTOTAL1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        FuncionarioDialog = new javax.swing.JDialog();
        jPanel36 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        txtBuscaFuncionarioDialog = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblFuncionarioDialog = new javax.swing.JTable();
        ItensDialog = new javax.swing.JDialog();
        jPanel37 = new javax.swing.JPanel();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblItensDialog = new javax.swing.JTable();
        jSeparator9 = new javax.swing.JSeparator();
        btnExcluir = new javax.swing.JButton();
        txtIdVenda = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVenda = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
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
        txtQuantidadeTotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnFinalizar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        btnSair = new javax.swing.JLabel();

        ClienteDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ClienteDialog.setUndecorated(true);
        ClienteDialog.setResizable(false);

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));

        jLabel68.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(26, 26, 26));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("Buscar Cliente");

        txtBuscaClienteDialog.setBackground(new java.awt.Color(253, 253, 254));
        txtBuscaClienteDialog.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
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
        tblClienteDialog.setFont(new java.awt.Font("Champagne & Limousines", 1, 12)); // NOI18N
        tblClienteDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator5))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscaClienteDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaClienteDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout ClienteDialogLayout = new javax.swing.GroupLayout(ClienteDialog.getContentPane());
        ClienteDialog.getContentPane().setLayout(ClienteDialogLayout);
        ClienteDialogLayout.setHorizontalGroup(
            ClienteDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ClienteDialogLayout.setVerticalGroup(
            ClienteDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ProdutoDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ProdutoDialog.setMinimumSize(new java.awt.Dimension(310, 380));
        ProdutoDialog.setUndecorated(true);
        ProdutoDialog.setResizable(false);

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));

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
                "Nome", "Quantidade", "Valor M??ximo"
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
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBuscaProdutoDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaProdutoDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout ProdutoDialogLayout = new javax.swing.GroupLayout(ProdutoDialog.getContentPane());
        ProdutoDialog.getContentPane().setLayout(ProdutoDialogLayout);
        ProdutoDialogLayout.setHorizontalGroup(
            ProdutoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ProdutoDialogLayout.setVerticalGroup(
            ProdutoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        FinalizaDialog.setBackground(new java.awt.Color(249, 249, 249));
        FinalizaDialog.setUndecorated(true);
        FinalizaDialog.setResizable(false);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Iniciais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel9.setText("Funcionario:");

        txtFuncionario.setBackground(new java.awt.Color(245, 245, 245));
        txtFuncionario.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel1.setText("Cliente");

        txtCliente.setBackground(new java.awt.Color(245, 245, 245));
        txtCliente.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtClienteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCliente)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSalvar.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvar.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnSalvar.setText("Finalizar");
        btnSalvar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnVoltar.setBackground(new java.awt.Color(255, 255, 255));
        btnVoltar.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adicionais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel7.setText("Forma de Pagamento:");

        cbxFormaPagamento.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        cbxFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Dinheiro", "Cart??o Cr??dito", "Cart??o D??bito", "Cheque" }));
        cbxFormaPagamento.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel3.setText("Data:");

        txtData.setBackground(new java.awt.Color(245, 245, 245));
        txtData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        txtData.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        lblTOTAL1.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        lblTOTAL1.setText("..");

        jLabel10.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel10.setText("Total: R$");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cbxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(lblTOTAL1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblTOTAL1))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout FinalizaDialogLayout = new javax.swing.GroupLayout(FinalizaDialog.getContentPane());
        FinalizaDialog.getContentPane().setLayout(FinalizaDialogLayout);
        FinalizaDialogLayout.setHorizontalGroup(
            FinalizaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FinalizaDialogLayout.setVerticalGroup(
            FinalizaDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        FuncionarioDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        FuncionarioDialog.setUndecorated(true);
        FuncionarioDialog.setResizable(false);

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));

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
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator7))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscaFuncionarioDialog)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout FuncionarioDialogLayout = new javax.swing.GroupLayout(FuncionarioDialog.getContentPane());
        FuncionarioDialog.getContentPane().setLayout(FuncionarioDialogLayout);
        FuncionarioDialogLayout.setHorizontalGroup(
            FuncionarioDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FuncionarioDialogLayout.setVerticalGroup(
            FuncionarioDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ItensDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ItensDialog.setMinimumSize(new java.awt.Dimension(310, 380));
        ItensDialog.setUndecorated(true);
        ItensDialog.setResizable(false);

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator8.setBackground(new java.awt.Color(249, 249, 249));
        jSeparator8.setForeground(new java.awt.Color(104, 129, 164));

        tblItensDialog.setFont(new java.awt.Font("Champagne & Limousines", 0, 11)); // NOI18N
        tblItensDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Quantidade", "Valor M??ximo"
            }
        ));
        tblItensDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItensDialogMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tblItensDialog);

        jSeparator9.setBackground(new java.awt.Color(249, 249, 249));
        jSeparator9.setForeground(new java.awt.Color(104, 129, 164));

        btnExcluir.setBackground(new java.awt.Color(255, 255, 255));
        btnExcluir.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnExcluir.setText("Excluir Venda");
        btnExcluir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator8)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtIdVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout ItensDialogLayout = new javax.swing.GroupLayout(ItensDialog.getContentPane());
        ItensDialog.getContentPane().setLayout(ItensDialogLayout);
        ItensDialogLayout.setHorizontalGroup(
            ItensDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ItensDialogLayout.setVerticalGroup(
            ItensDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
        txtBusca.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        txtBusca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtBusca.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaCaretUpdate(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );

        jTabbedPane1.addTab("Consulta", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sele????o de Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel4.setText("Quantidade:");

        jLabel5.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel5.setText("Pre??o Unit??rio:");

        txtQuantidade.setBackground(new java.awt.Color(245, 245, 245));
        txtQuantidade.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtQuantidade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtQuantidade.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtQuantidadeCaretUpdate(evt);
            }
        });

        txtUnidade.setBackground(new java.awt.Color(245, 245, 245));
        txtUnidade.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtUnidade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtUnidade.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtUnidadeCaretUpdate(evt);
            }
        });

        txtTotal.setBackground(new java.awt.Color(245, 245, 245));
        txtTotal.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));

        jLabel6.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel6.setText("Total:");

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

        btnExcluiItemVenda.setBackground(new java.awt.Color(255, 255, 255));
        btnExcluiItemVenda.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnExcluiItemVenda.setText("Remover Produto -");
        btnExcluiItemVenda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnExcluiItemVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluiItemVendaActionPerformed(evt);
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
        jLabel2.setText("Produto:");

        jLabel8.setFont(new java.awt.Font("Champagne & Limousines", 1, 15)); // NOI18N
        jLabel8.setText("Total: R$");

        lblTOTAL.setFont(new java.awt.Font("Champagne & Limousines", 1, 15)); // NOI18N
        lblTOTAL.setText("..");

        jLabel11.setFont(new java.awt.Font("Champagne & Limousines", 1, 15)); // NOI18N
        jLabel11.setText("+");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel12.setText("=");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtIDIten, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txtQuantidadeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtproduto, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel11)
                .addGap(36, 36, 36)
                .addComponent(txtUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel12)
                .addGap(35, 35, 35)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btnAddItemVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(btnExcluiItemVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(235, 235, 235)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDIten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantidadeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtproduto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddItemVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluiItemVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblTOTAL))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnFinalizar.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnFinalizar.setText("Salvar");
        btnFinalizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        btnNovo.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("Venda", jPanel6);

        jLabel15.setFont(new java.awt.Font("Champagne & Limousines", 0, 20)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Venda");

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(500, 500, 500)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 399, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(btnSair))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
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
        try{
            listaItemVenda = itemvendadao.busca(Integer.parseInt(tblVenda.getValueAt(tblVenda.getSelectedRow(),0).toString()));
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        ItensDialog.setVisible(true);
        txtIdVenda.setText(tblVenda.getValueAt(tblVenda.getSelectedRow(),0).toString());
        ItensDialog.setLocationRelativeTo(null);
        tblItensDialog.getTableHeader().setReorderingAllowed(false);
        atualizaTabelaItensDialog();
    }//GEN-LAST:event_tblVendaMouseClicked

    private void txtBuscaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaCaretUpdate
        listaVenda = null;
        listaCliente = null;
        if(txtBusca.getText().equals("")){
            atualizaTabelaVenda();
        }else{
                    
            try {
                cliente = clientedao.buscaNome2(txtBusca.getText());
                listaVenda = vendadao.buscaNomeLista(cliente.getId());

                if(listaVenda == null){
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limparCampos();
        prepararSalvareCancelar();
        desativarCampos();
        atualizaTabelaItemVendalimpa();
        listaItemVenda = new ArrayList<>();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        listaItemVenda = new ArrayList<>();
        limparCampos();
        prepararNovo();
        ativarCampos();
        atualizaTabelaItemVendalimpa();
        txtCliente.requestFocusInWindow();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
            FinalizaDialog.setVisible(true);
            FinalizaDialog.setLocationRelativeTo(null);
            txtData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnExcluiItemVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluiItemVendaActionPerformed
        if(txtIDIten.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Selecione primeiro um produto! ","erro", JOptionPane.WARNING_MESSAGE);
        }else{
        listaItemVenda.remove(tblItenVenda.getSelectedRow());
        atualizaTabelaItemVenda();
        lblTOTAL.setText(String.valueOf(totalVenda()));
        lblTOTAL1.setText(lblTOTAL.getText());
        txtIDIten.setText("");
        }
    }//GEN-LAST:event_btnExcluiItemVendaActionPerformed

    private void btnAddItemVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemVendasActionPerformed
        if(txtproduto.getText().isEmpty() || txtIdProduto.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Selecione primeiro um produto! ","erro", JOptionPane.WARNING_MESSAGE);
        }else if(Integer.valueOf(txtQuantidade.getText()) > Integer.valueOf(txtQuantidadeTotal.getText())){
            JOptionPane.showMessageDialog(null, "Quantidade ultrapassa o estoque! ","erro", JOptionPane.WARNING_MESSAGE);
            txtQuantidade.requestFocusInWindow();
        }else if(listaItemVenda.contains(itemVenda)){
            JOptionPane.showMessageDialog(null, "itens repetidos");
            itemVenda = new ItensVenda();
            txtQuantidade.setText("");
            txtQuantidadeTotal.setText("");
            txtUnidade.setText("");
            txtTotal.setText("");
            txtproduto.setText("");
            txtIdProduto.setText("");
        }else{        
        produto.setId(Integer.valueOf(txtIdProduto.getText()));
            itemVenda = new ItensVenda();
            itemVenda.setIdProduto(produto);
            itemVenda.setPreco(Float.valueOf(txtUnidade.getText()));
            itemVenda.setQuantidade(Integer.valueOf(txtQuantidade.getText()));
            itemVenda.setTotal(Float.valueOf(txtTotal.getText()));
            itemVenda.setExcluido(false);
            listaItemVenda.add(itemVenda);
            atualizaTabelaItemVenda();
            lblTOTAL.setText(String.valueOf(totalVenda()));
            lblTOTAL1.setText(lblTOTAL.getText());
            PreparaVenda();

        txtQuantidade.setText("");
        txtQuantidadeTotal.setText("");
        txtUnidade.setText("");
        txtTotal.setText("");
        txtproduto.setText("");
        txtIdProduto.setText("");
        }
    }//GEN-LAST:event_btnAddItemVendasActionPerformed

    private void txtBuscaClienteDialogCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaClienteDialogCaretUpdate
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
                    atualizaTabelaClienteDialogBusca();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtBuscaClienteDialogCaretUpdate

    private void tblProdutoDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoDialogMouseClicked
        // TODO add your handling code here:
        txtIdProduto.setText(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 0).toString());
        txtproduto.setText(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 1).toString());
        txtQuantidadeTotal.setText(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 2).toString());
        txtUnidade.setText(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 3).toString());
        produto = new ProdutoM();
        produto.setId(Integer.parseInt(txtIdProduto.getText()));
        produto.setNome(txtproduto.getText());
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

    private void tblItenVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItenVendaMouseClicked
        txtIDIten.setText(tblItenVenda.getValueAt(tblItenVenda.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_tblItenVendaMouseClicked

    private void txtFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFuncionarioMouseClicked
       FuncionarioDialog.setVisible(true);
       FuncionarioDialog.setLocationRelativeTo(null);
       atualizaTabelaFuncionarioDialog();
       tblFuncionarioDialog.getTableHeader().setReorderingAllowed(false);
    }//GEN-LAST:event_txtFuncionarioMouseClicked

    private void txtUnidadeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtUnidadeCaretUpdate
        if(txtQuantidade.getText().isEmpty() ){
            //txtQtde.setText("1");
        }else{
            CalculaVenda();
        }
    }//GEN-LAST:event_txtUnidadeCaretUpdate

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
            venda.setIdCliente(cliente);
            venda.setIdFuncionario(funcionario);
            venda.setData(txtData.getText());
            venda.setTotalVendas(Float.valueOf(lblTOTAL.getText()));
            venda.setFormaPagamento(String.valueOf(cbxFormaPagamento.getSelectedItem()));
            venda.setExcluido(false);
            try{
                vendadao.salvar(venda,listaItemVenda);
                JOptionPane.showMessageDialog(null, "Gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
            atualizaTabelaVenda();
            atualizaTabelaItemVendalimpa();
            prepararSalvareCancelar();
            desativarCampos();
            limparCampos();
            FinalizaDialog.dispose();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtBuscaFuncionarioDialogCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaFuncionarioDialogCaretUpdate
        atualizaTabelaClienteDialog();
        listaFuncionario = null;
        if(txtBuscaFuncionarioDialog.getText().equals("")){
            atualizaTabelaClienteDialog();
        }else{

            try {
                listaFuncionario = funcionariodao.buscaNomeLista(txtBuscaFuncionarioDialog.getText());

                if(listaFuncionario == null){
                    JOptionPane.showMessageDialog(null, "Nenhum Funcionario encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaClienteDialog();
                }else{
                    atualizaTabelaClienteDialogBusca();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtBuscaFuncionarioDialogCaretUpdate

    private void tblFuncionarioDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFuncionarioDialogMouseClicked
        txtIdFuncionario.setText(tblFuncionarioDialog.getValueAt(tblFuncionarioDialog.getSelectedRow(), 0).toString());
        txtFuncionario.setText(tblFuncionarioDialog.getValueAt(tblFuncionarioDialog.getSelectedRow(), 1).toString());
        funcionario.setId(Integer.valueOf(txtIdFuncionario.getText()));
        funcionario.setNome(txtFuncionario.getText());
        FuncionarioDialog.dispose();
    }//GEN-LAST:event_tblFuncionarioDialogMouseClicked

    private void tblItensDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItensDialogMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblItensDialogMouseClicked

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

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
    FinalizaDialog.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        FinalizaDialog.dispose();
        ItensDialog.dispose();
    }//GEN-LAST:event_formMouseClicked

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
                    /*JPasswordField password = new JPasswordField(10);
                    JPasswordField login = new JPasswordField(10);
                    password.setEchoChar('*');
                    login.setEchoChar('*');
                    JLabel rotulo1 = new JLabel("Login de Administrador:");
                    JLabel rotulo2 = new JLabel("Senha de Administrador:");
                    JPanel entUsuario1 = new JPanel();
                    JPanel entUsuario2 = new JPanel();
                    entUsuario1.add(rotulo1);
                    entUsuario1.add(password);
                    entUsuario2.add(rotulo2);
                    entUsuario2.add(login);
                    JOptionPane.showMessageDialog(null, entUsuario1, "Acesso restrito",JOptionPane.PLAIN_MESSAGE);
                    JOptionPane.showMessageDialog(null, entUsuario2, "Acesso restrito",JOptionPane.PLAIN_MESSAGE);*/
                
                
                String LoginMaster = JOptionPane.showInputDialog(null,"Digite seu login: ");
                String SenhaMaster = JOptionPane.showInputDialog(null,"Digite sua senha: ");
                
                funcionario = funcionariodao.valida(LoginMaster, SenhaMaster);
                if(funcionario == null){
                    JOptionPane.showMessageDialog(null, "Funcionario n??o encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
                }else{
                
                    venda.setId(Integer.valueOf(txtIdVenda.getText()));
                    venda.setExcluido(true);
                    itemVenda.setIdVenda(venda);
                    itemVenda.setExcluido(true);
                    vendadao.alterarVendaTrue(venda);
                    itemvendadao.alterarItemVendaTrue(itemVenda);
                    ItensDialog.dispose();
                    atualizaTabelaVenda();
                    
                }
                } catch (SQLException ex) {
                    Logger.getLogger(VendaView.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
                }

            }
    }//GEN-LAST:event_btnExcluirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog ClienteDialog;
    private javax.swing.JDialog FinalizaDialog;
    private javax.swing.JDialog FuncionarioDialog;
    private javax.swing.JDialog ItensDialog;
    private javax.swing.JDialog ProdutoDialog;
    private javax.swing.JButton btnAddItemVendas;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluiItemVenda;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JLabel btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbxFormaPagamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTOTAL;
    private javax.swing.JLabel lblTOTAL1;
    private javax.swing.JTable tblClienteDialog;
    private javax.swing.JTable tblFuncionarioDialog;
    private javax.swing.JTable tblItenVenda;
    private javax.swing.JTable tblItensDialog;
    private javax.swing.JTable tblProdutoDialog;
    private javax.swing.JTable tblVenda;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtBuscaClienteDialog;
    private javax.swing.JTextField txtBuscaFuncionarioDialog;
    private javax.swing.JTextField txtBuscaProdutoDialog;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JTextField txtFuncionario;
    private javax.swing.JTextField txtIDIten;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdFuncionario;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtIdVenda;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtQuantidadeTotal;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUnidade;
    private javax.swing.JTextField txtproduto;
    // End of variables declaration//GEN-END:variables
}
