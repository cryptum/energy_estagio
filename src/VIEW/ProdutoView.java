package VIEW;

import DAO.CategoriaDao;
import DAO.EntradadeProdutoDao;
import DAO.MarcaDao;
import DAO.ModeloDao;
import DAO.ProdutoDao;
import MODEL.CategoriaM;
import MODEL.EntradadeProdutoM;
import MODEL.MarcaM;
import MODEL.ModeloM;
import MODEL.ProdutoM;
import com.sun.java.swing.plaf.windows.WindowsTableHeaderUI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import util.LimiteDigitos;

/**
 *
 * @author Danilo-NOTE
 */
public class ProdutoView extends javax.swing.JInternalFrame {

    /**
     * Creates new form PRODUTO_DO_CU_QUENTE
     */
    
    ProdutoM produto = new ProdutoM();
    ProdutoDao produtodao = new ProdutoDao();
    List<ProdutoM> listaProduto = new ArrayList<>();
    
    CategoriaM categoria = new CategoriaM();
    CategoriaDao categoriadao = new CategoriaDao();
    List<CategoriaM> listaCategoria = new ArrayList<>();
    
    MarcaM marca = new MarcaM();
    MarcaDao marcadao = new MarcaDao();
    List<MarcaM> listaMarca = new ArrayList<>();
    
    ModeloM modelo = new ModeloM();
    ModeloDao modelodao = new ModeloDao();
    List<ModeloM> listaModelo = new ArrayList<>();
    
    EntradadeProdutoM entradadeProduto = new EntradadeProdutoM();
    EntradadeProdutoDao entradadeProdutodao = new EntradadeProdutoDao();
    List<EntradadeProdutoM> ListaEntradadeProduto = new ArrayList<>();
    
    public ProdutoView() {
        initComponents();
        this.setVisible(true);
        atualizaTabelaProduto();
        atualizaTabelaCategoria();
        atualizaTabelaMarca();
        atualizaTabelaModelo();
        atualizaBoxCategoria();
        atualizaBoxMarca();
        atualizaBoxEditarMarca();
        ProdutoDialog.setSize(535, 500);
        jTabbedPane1.setUI(new BasicTabbedPaneUI());
        tblCategoria.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblCategoria.getTableHeader().setReorderingAllowed(false);
        tblEntradadeProdutos.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblEntradadeProdutos.getTableHeader().setReorderingAllowed(false);
        tblMarca.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblMarca.getTableHeader().setReorderingAllowed(false);
        tblModelo.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblModelo.getTableHeader().setReorderingAllowed(false);
        tblProduto.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblProduto.getTableHeader().setReorderingAllowed(false);
        tblProdutoDialog.getTableHeader().setUI(new WindowsTableHeaderUI());
        tblProdutoDialog.getTableHeader().setReorderingAllowed(false);
        txtId.setVisible(false);
        txtIdMarca.setVisible(false);
        txtIdModelo.setVisible(false);
        txtIdCategoria.setVisible(false);
        txtIdEntradadeProduto.setVisible(false);
        desativarCampos();
        
        txtEditarMarca.setDocument(new LimiteDigitos(45));
        txtEditarModelo.setDocument(new LimiteDigitos(45));
        txtNome.setDocument(new LimiteDigitos(50));
        txtCodigo.setDocument(new LimiteDigitos(45));
        btnAlterar.setUI(new BasicButtonUI());
        btnCancelar.setUI(new BasicButtonUI());
        btnExcluir.setUI(new BasicButtonUI());
        btnLimparCategoria.setUI(new BasicButtonUI());
        btnLimparCategoria1.setUI(new BasicButtonUI());
        btnLimparMarca.setUI(new BasicButtonUI());
        btnLimparModelo.setUI(new BasicButtonUI());
        btnNovo.setUI(new BasicButtonUI());
        btnSalvar.setUI(new BasicButtonUI());
        btnSalvarCategoria.setUI(new BasicButtonUI());
        btnSalvarCategoria1.setUI(new BasicButtonUI());
        btnSalvarMarca.setUI(new BasicButtonUI());
        btnSalvarModelo.setUI(new BasicButtonUI());
        
    }
     
    
    //Atualiza todos os funcionario para a tabela
    public void atualizaTabelaProduto(){
        produto = new ProdutoM();
        try {
            listaProduto = produtodao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaProduto.size()][8];
            int i = 0;
            for (ProdutoM produto : listaProduto) {
                dados[i][0] = String.valueOf(produto.getId());
                dados[i][1] = produto.getNome();
                dados[i][2] = produto.getIdCategoria().getNome();
                dados[i][3] = produto.getIdMarca().getNome();
                dados[i][4] = produto.getIdModelo().getNome();
                dados[i][5] = String.valueOf(produto.getQuantidade());
                dados[i][6] = String.valueOf(produto.getValorMax());
                dados[i][7] = String.valueOf(produto.getValorMini());

                i++;
            }
            String tituloColuna[] = {"ID", "Nome", "Categoria", "Marca","Modelo","Quantidade", "Valor Maximo","Valor Minimo"};
            DefaultTableModel tabelaProduto = new DefaultTableModel();
            tabelaProduto.setDataVector(dados, tituloColuna);
            tblProduto.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblProduto.getColumnModel().getColumn(0).setMaxWidth(0);
            tblProduto.getColumnModel().getColumn(0).setMinWidth(0);
            tblProduto.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProduto.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblProduto.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblProduto.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblProduto.setRowHeight(35);
            tblProduto.updateUI();
    }
    
    //Atualiza Busca
    public void atualizaTabelaProdutoBusca(){
        produto = new ProdutoM();
        
        String dados[][] = new String[listaProduto.size()][8];
            int i = 0;
            for (ProdutoM produto : listaProduto) {
                dados[i][0] = String.valueOf(produto.getId());
                dados[i][1] = produto.getNome();
                dados[i][2] = produto.getIdCategoria().getNome();
                dados[i][3] = produto.getIdMarca().getNome();
                dados[i][4] = produto.getIdModelo().getNome();
                dados[i][5] = String.valueOf(produto.getQuantidade());
                dados[i][6] = String.valueOf(produto.getValorMax());
                dados[i][7] = String.valueOf(produto.getValorMini());

                i++;
            }
            String tituloColuna[] = {"ID", "Nome", "Categoria", "Marca","Modelo","Quantidade", "Valor Maximo","Valor Minimo"};
            DefaultTableModel tabelaProduto = new DefaultTableModel();
            tabelaProduto.setDataVector(dados, tituloColuna);
            tblProduto.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblProduto.getColumnModel().getColumn(0).setMaxWidth(0);
            tblProduto.getColumnModel().getColumn(0).setMinWidth(0);
            tblProduto.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProduto.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblProduto.getColumnModel().getColumn(2).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblProduto.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblProduto.setRowHeight(35);
            tblProduto.updateUI();
    }
    
    public void atualizaTabelaMarca(){
        marca = new MarcaM();
        try {
            listaMarca = marcadao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaMarca.size()][3];
            int i = 0;
            for (MarcaM marca : listaMarca) {
                dados[i][0] = String.valueOf(marca.getId());
                dados[i][1] = marca.getNome();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome"};
            DefaultTableModel tabelaMarca = new DefaultTableModel();
            tabelaMarca.setDataVector(dados, tituloColuna);
            tblMarca.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblMarca.getColumnModel().getColumn(0).setMaxWidth(0);
            tblMarca.getColumnModel().getColumn(0).setMinWidth(0);
            tblMarca.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblMarca.getColumnModel().getColumn(1).setPreferredWidth(200);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblMarca.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblMarca.setRowHeight(35);
            tblMarca.updateUI();
    }
    
    public void atualizaTabelaMarcaBusca(){
        marca = new MarcaM();
        
        String dados[][] = new String[listaMarca.size()][3];
            int i = 0;
            for (MarcaM marca : listaMarca) {
                dados[i][0] = String.valueOf(marca.getId());
                dados[i][1] = marca.getNome();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome"};
            DefaultTableModel tabelaMarca = new DefaultTableModel();
            tabelaMarca.setDataVector(dados, tituloColuna);
            tblMarca.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblMarca.getColumnModel().getColumn(0).setMaxWidth(0);
            tblMarca.getColumnModel().getColumn(0).setMinWidth(0);
            tblMarca.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblMarca.getColumnModel().getColumn(1).setPreferredWidth(200);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblMarca.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblMarca.setRowHeight(35);
            tblMarca.updateUI();
    }
    
    public void atualizaTabelaModelo(){
        modelo = new ModeloM();
        try {
            listaModelo = modelodao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaModelo.size()][3];
            int i = 0;
            for (ModeloM modelo : listaModelo) {
                dados[i][0] = String.valueOf(modelo.getId());
                dados[i][1] = modelo.getNome();
                dados[i][2] = modelo.getIdMarca().getNome();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome","Marca"};
            DefaultTableModel tabelaModelo = new DefaultTableModel();
            tabelaModelo.setDataVector(dados, tituloColuna);
            tblModelo.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblModelo.getColumnModel().getColumn(0).setMaxWidth(0);
            tblModelo.getColumnModel().getColumn(0).setMinWidth(0);
            tblModelo.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblModelo.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblModelo.getColumnModel().getColumn(2).setPreferredWidth(200);
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblModelo.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblModelo.setRowHeight(35);
            tblModelo.updateUI();
    }
    
    public void atualizaTabelaModeloBusca(){
        modelo = new ModeloM();
        
        String dados[][] = new String[listaModelo.size()][3];
            int i = 0;
            for (ModeloM modelo : listaModelo) {
                dados[i][0] = String.valueOf(modelo.getId());
                dados[i][1] = modelo.getNome();
                dados[i][2] = modelo.getIdMarca().getNome();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome","Marca"};
            DefaultTableModel tabelaModelo = new DefaultTableModel();
            tabelaModelo.setDataVector(dados, tituloColuna);
            tblModelo.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblModelo.getColumnModel().getColumn(0).setMaxWidth(0);
            tblModelo.getColumnModel().getColumn(0).setMinWidth(0);
            tblModelo.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblModelo.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblModelo.getColumnModel().getColumn(2).setPreferredWidth(200);
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblModelo.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblModelo.setRowHeight(35);
            tblModelo.updateUI();
    }
    
    public void atualizaTabelaCategoria(){
        categoria = new CategoriaM();
        try {
            listaCategoria = categoriadao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        
        String dados[][] = new String[listaCategoria.size()][2];
            int i = 0;
            for (CategoriaM categoria : listaCategoria) {
                dados[i][0] = String.valueOf(categoria.getId());
                dados[i][1] = categoria.getNome();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome"};
            DefaultTableModel tabelaModelo = new DefaultTableModel();
            tabelaModelo.setDataVector(dados, tituloColuna);
            tblCategoria.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblCategoria.getColumnModel().getColumn(0).setMaxWidth(0);
            tblCategoria.getColumnModel().getColumn(0).setMinWidth(0);
            tblCategoria.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblCategoria.getColumnModel().getColumn(1).setPreferredWidth(200);
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblCategoria.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblCategoria.setRowHeight(35);
            tblCategoria.updateUI();
    }
    
    public void atualizaTabelaCategoriaBusca(){
        categoria = new CategoriaM();
        
        String dados[][] = new String[listaCategoria.size()][2];
            int i = 0;
            for (CategoriaM categoria : listaCategoria) {
                dados[i][0] = String.valueOf(categoria.getId());
                dados[i][1] = categoria.getNome();

                i++;
            }
            String tituloColuna[] = {"ID", "Nome"};
            DefaultTableModel tabelaModelo = new DefaultTableModel();
            tabelaModelo.setDataVector(dados, tituloColuna);
            tblCategoria.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblCategoria.getColumnModel().getColumn(0).setMaxWidth(0);
            tblCategoria.getColumnModel().getColumn(0).setMinWidth(0);
            tblCategoria.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblCategoria.getColumnModel().getColumn(1).setPreferredWidth(200);
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblCategoria.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblCategoria.setRowHeight(35);
            tblCategoria.updateUI();
    }
    
    public void atualizaTabelaProdutoDialog(){
        produto = new ProdutoM();
        try {
            listaProduto = produtodao.listaTodos();
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
            String tituloColuna[] = {"ID", "Nome","Quantidade","Valor Máximo"};
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
            String tituloColuna[] = {"ID", "Nome","Quantidade","Valor Máximo"};
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
    
    public void atualizaTabelaEntradadeProduto(){
        entradadeProduto = new EntradadeProdutoM();
        try {
            ListaEntradadeProduto = entradadeProdutodao.listaTodos();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        String dados[][] = new String[ListaEntradadeProduto.size()][5];
            int i = 0;
            for (EntradadeProdutoM entrada : ListaEntradadeProduto) {
                dados[i][0] = String.valueOf(entrada.getId());
                dados[i][1] = entrada.getIdProduto().getNome();
                dados[i][2] = entrada.getData();
                dados[i][3] = entrada.getHora();
                dados[i][4] = String.valueOf(entrada.getQuantidade());

                i++;
            }
            String tituloColuna[] = {"ID", "Produto", "Data", "Hora","Quantidade Adicionada"};
            DefaultTableModel tabelaEntrada = new DefaultTableModel();
            tabelaEntrada.setDataVector(dados, tituloColuna);
            tblEntradadeProdutos.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblEntradadeProdutos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblEntradadeProdutos.getColumnModel().getColumn(0).setMinWidth(0);
            tblEntradadeProdutos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblEntradadeProdutos.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblEntradadeProdutos.getColumnModel().getColumn(4).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblEntradadeProdutos.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblEntradadeProdutos.setRowHeight(35);
            tblEntradadeProdutos.updateUI();
    }
    
    public void atualizaTabelaEntradadeProdutoBusca(){
        entradadeProduto = new EntradadeProdutoM();
        
        String dados[][] = new String[ListaEntradadeProduto.size()][5];
            int i = 0;
            for (EntradadeProdutoM entrada : ListaEntradadeProduto) {
                dados[i][0] = String.valueOf(entrada.getId());
                dados[i][1] = entrada.getIdProduto().getNome();
                dados[i][2] = entrada.getData();
                dados[i][3] = entrada.getHora();
                dados[i][4] = String.valueOf(entrada.getQuantidade());

                i++;
            }
            String tituloColuna[] = {"ID", "Produto", "Data", "Hora","Quantidade Adicionada"};
            DefaultTableModel tabelaEntrada = new DefaultTableModel();
            tabelaEntrada.setDataVector(dados, tituloColuna);
            tblEntradadeProdutos.setModel(new DefaultTableModel(dados, tituloColuna) {
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            tblEntradadeProdutos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblEntradadeProdutos.getColumnModel().getColumn(0).setMinWidth(0);
            tblEntradadeProdutos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblEntradadeProdutos.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblEntradadeProdutos.getColumnModel().getColumn(4).setPreferredWidth(100);
            
            DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
            centralizado.setHorizontalAlignment(SwingConstants.CENTER);
            tblEntradadeProdutos.getColumnModel().getColumn(0).setCellRenderer(centralizado);
            tblEntradadeProdutos.setRowHeight(35);
            tblEntradadeProdutos.updateUI();
    }
    
    public void atualizaBoxCategoria(){
       
        cbxCategoria.removeAllItems();
        cbxCategoria.addItem("Selecione");
        
        try {
            listaCategoria = categoriadao.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         String dados[][] = new String[listaCategoria.size()][5];
        for (CategoriaM categoria : listaCategoria) {
            cbxCategoria.addItem(categoria.getNome());
        }
    } 
    
    public void atualizaBoxEditarMarca(){
       
        cbxEditarMarca.removeAllItems();
        cbxEditarMarca.addItem("Selecione");
        
        try {
            listaMarca = marcadao.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         String dados[][] = new String[listaMarca.size()][5];
        for (MarcaM marcab : listaMarca) {
            cbxEditarMarca.addItem(marcab.getNome());
        }
    } 
    
    public void atualizaBoxMarca(){
       
        cbxMarca.removeAllItems();
        cbxMarca.addItem("Selecione");
        
        try {
            listaMarca = marcadao.listaTodos();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         String dados[][] = new String[listaMarca.size()][5];
        for (MarcaM marcab : listaMarca) {
            cbxMarca.addItem(marcab.getNome());
        }
    } 

    public void atualizaBoxModelo(){
       
        cbxModelo.removeAllItems();
        cbxModelo.addItem("Selecione");
        
        try {
            listaModelo = modelodao.buscaModelo(cbxMarca.getSelectedIndex());
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         String dados[][] = new String[listaModelo.size()][5];
        for (ModeloM modelb : listaModelo) {
            cbxModelo.addItem(modelb.getNome());
        }
    }
    
    public void CalculaVenda(){
        txtTotal.setText(String.valueOf(Integer.valueOf(txtQuantidadeadd.getText()) + Integer.valueOf(txtQuantidadeatual.getText())));
    }
    
    // DECLARAÇÃO DE MÉTODOS DE CONTROLE DE BOTÕES
    public void limparCampos(){
        txtId.setText("");
        txtNome.setText("");
        cbxCategoria.setSelectedIndex(0);
        cbxMarca.setSelectedIndex(0);
        cbxModelo.setSelectedIndex(0);
        txtValorCusto.setText("");
        txtvalorMinimo.setText("");
        txtValorMaximo.setText("");
        txtCodigo.setValue("");
        txtQuantidade.setText("");
    }
   
    public void ativarCampos(){
        txtId.setEnabled(true);
        txtNome.setEnabled(true);
        cbxCategoria.setEnabled(true);
        cbxMarca.setEnabled(true);
        cbxModelo.setEnabled(true);
        txtValorCusto.setEnabled(true);
        txtvalorMinimo.setEnabled(true);
        txtValorMaximo.setEnabled(true);
        txtCodigo.setEnabled(true);
        txtQuantidade.setEnabled(true);
    }

    public void desativarCampos(){
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        txtId.setEnabled(false);
        txtNome.setEnabled(false);
        cbxCategoria.setEnabled(false);
        cbxMarca.setEnabled(false);
        cbxModelo.setEnabled(false);
        txtValorCusto.setEnabled(false);
        txtvalorMinimo.setEnabled(false);
        txtValorMaximo.setEnabled(false);
        txtCodigo.setEnabled(false);
        txtQuantidade.setEnabled(false);
    }
   
    public void prepararNovo() {
       btnNovo.setEnabled(false);
       btnSalvar.setEnabled(true);
       btnCancelar.setEnabled(true);
       tblProduto.setEnabled(false);
       tblProduto.clearSelection();
    }
   
    public void prepararSalvareCancelar() {
       btnNovo.setEnabled(true);
       btnAlterar.setEnabled(true);
       btnExcluir.setEnabled(true);
       btnSalvar.setEnabled(false);
       btnCancelar.setEnabled(false);
       tblProduto.setEnabled(true);
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
       tblProduto.setEnabled(false);
       tblProduto.clearSelection();
    }
   
    public void prepararExcluir(){
       btnExcluir.setEnabled(false);
       btnAlterar.setEnabled(false);
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
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProduto = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        cbxMarca = new javax.swing.JComboBox<>();
        cbxModelo = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JFormattedTextField();
        cbxCategoria = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtvalorMinimo = new javax.swing.JFormattedTextField();
        txtValorMaximo = new javax.swing.JFormattedTextField();
        txtValorCusto = new javax.swing.JFormattedTextField();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCategoria = new javax.swing.JTable();
        txtEditarCategoria = new javax.swing.JFormattedTextField();
        btnSalvarCategoria = new javax.swing.JButton();
        txtIdCategoria = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnLimparCategoria = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        txtBuscaCategoria = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMarca = new javax.swing.JTable();
        txtEditarMarca = new javax.swing.JFormattedTextField();
        btnSalvarMarca = new javax.swing.JButton();
        txtIdMarca = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnLimparMarca = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        txtBuscaMarca = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblModelo = new javax.swing.JTable();
        txtEditarModelo = new javax.swing.JFormattedTextField();
        btnSalvarModelo = new javax.swing.JButton();
        txtIdModelo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnLimparModelo = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        txtBuscaModelo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbxEditarMarca = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblEntradadeProdutos = new javax.swing.JTable();
        txtNomeEntradadeproduto = new javax.swing.JFormattedTextField();
        btnSalvarCategoria1 = new javax.swing.JButton();
        txtIdEntradadeProduto = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        btnLimparCategoria1 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        txtBuscaEntradadeProduto = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtQuantidadeadd = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        txtQuantidadeatual = new javax.swing.JFormattedTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JFormattedTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnSair = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblProduto1 = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        txtBusca1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        btnAlterar1 = new javax.swing.JButton();
        btnExcluir1 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        txtNome1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtId1 = new javax.swing.JTextField();
        txtCodigo1 = new javax.swing.JFormattedTextField();
        jLabel33 = new javax.swing.JLabel();
        cbxMarca1 = new javax.swing.JComboBox<>();
        cbxModelo1 = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        txtQuantidade1 = new javax.swing.JFormattedTextField();
        cbxCategoria1 = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtvalorMinimo1 = new javax.swing.JFormattedTextField();
        txtValorMaximo1 = new javax.swing.JFormattedTextField();
        txtValorCusto1 = new javax.swing.JFormattedTextField();
        btnSalvar1 = new javax.swing.JButton();
        btnNovo1 = new javax.swing.JButton();
        btnCancelar1 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblCategoria1 = new javax.swing.JTable();
        txtEditarCategoria1 = new javax.swing.JFormattedTextField();
        btnSalvarCategoria2 = new javax.swing.JButton();
        txtIdCategoria1 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        btnLimparCategoria2 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        txtBuscaCategoria1 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblMarca1 = new javax.swing.JTable();
        txtEditarMarca1 = new javax.swing.JFormattedTextField();
        btnSalvarMarca1 = new javax.swing.JButton();
        txtIdMarca1 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        btnLimparMarca1 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        txtBuscaMarca1 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblModelo1 = new javax.swing.JTable();
        txtEditarModelo1 = new javax.swing.JFormattedTextField();
        btnSalvarModelo1 = new javax.swing.JButton();
        txtIdModelo1 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        btnLimparModelo1 = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        txtBuscaModelo1 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        cbxEditarMarca1 = new javax.swing.JComboBox<>();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblEntradadeProdutos1 = new javax.swing.JTable();
        txtNomeEntradadeproduto1 = new javax.swing.JFormattedTextField();
        btnSalvarCategoria3 = new javax.swing.JButton();
        txtIdEntradadeProduto1 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        btnLimparCategoria3 = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        txtBuscaEntradadeProduto1 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtQuantidadeadd1 = new javax.swing.JFormattedTextField();
        jLabel51 = new javax.swing.JLabel();
        txtQuantidadeatual1 = new javax.swing.JFormattedTextField();
        jLabel52 = new javax.swing.JLabel();
        txtTotal1 = new javax.swing.JFormattedTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        btnSair1 = new javax.swing.JLabel();

        ProdutoDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ProdutoDialog.setMaximumSize(new java.awt.Dimension(535, 500));
        ProdutoDialog.setMinimumSize(new java.awt.Dimension(535, 500));
        ProdutoDialog.setPreferredSize(new java.awt.Dimension(535, 500));
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

        jSeparator6.setBackground(new java.awt.Color(249, 249, 249));
        jSeparator6.setForeground(new java.awt.Color(104, 129, 164));

        tblProdutoDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Quantidade", "Valor Máximo"
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

        setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setMaximumSize(new java.awt.Dimension(1919, 800));
        setMinimumSize(new java.awt.Dimension(1919, 800));
        setPreferredSize(new java.awt.Dimension(1919, 800));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tblProduto.setBackground(new java.awt.Color(248, 248, 248));
        tblProduto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));
        tblProduto.setFont(new java.awt.Font("Champagne & Limousines", 0, 12)); // NOI18N
        tblProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Behinger", "Pedaleira", "500", "450"},
                {"Landscape", "Amplificador", "400", "300"},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Modelo", "Nome", "Valor Maximo", "Valor Minimo"
            }
        ));
        tblProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblProduto);
        if (tblProduto.getColumnModel().getColumnCount() > 0) {
            tblProduto.getColumnModel().getColumn(0).setMinWidth(20);
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
        jLabel13.setText("Nome:");

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

        btnAlterar.setBackground(new java.awt.Color(255, 255, 255));
        btnAlterar.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setBackground(new java.awt.Color(255, 255, 255));
        btnExcluir.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(488, 488, 488))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
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

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Iniciais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel1.setText("Nome do Produto:");

        txtNome.setBackground(new java.awt.Color(245, 245, 245));
        txtNome.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtNome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));

        jLabel10.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel10.setText("Marca:");

        jLabel11.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel11.setText("Modelo:");

        txtCodigo.setBackground(new java.awt.Color(245, 245, 245));
        txtCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtCodigo.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel6.setText("Codigo de Barras:");

        cbxMarca.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        cbxMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxMarca.setToolTipText("");
        cbxMarca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMarcaItemStateChanged(evt);
            }
        });

        cbxModelo.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        cbxModelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel18.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel18.setText("Quantidade:");

        txtQuantidade.setBackground(new java.awt.Color(245, 245, 245));
        txtQuantidade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtQuantidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        txtQuantidade.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        cbxCategoria.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        cbxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxCategoria.setToolTipText("");
        cbxCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCategoriaItemStateChanged(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel21.setText("Categoria:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(cbxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(cbxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(4, 4, 4)
                        .addComponent(cbxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valores e Custos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel3.setText("Valor Mercado Livre:");

        jLabel2.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel2.setText("Valor Custo:");

        jLabel4.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel4.setText("R$");

        jLabel5.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel5.setText("R$");

        jLabel9.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel9.setText("Valor Loja:");

        jLabel7.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel7.setText("R$");

        txtvalorMinimo.setBackground(new java.awt.Color(245, 245, 245));
        txtvalorMinimo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtvalorMinimo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtvalorMinimo.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        txtValorMaximo.setBackground(new java.awt.Color(245, 245, 245));
        txtValorMaximo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtValorMaximo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtValorMaximo.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        txtValorCusto.setBackground(new java.awt.Color(245, 245, 245));
        txtValorCusto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtValorCusto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtValorCusto.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtValorMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(79, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtvalorMinimo))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtValorCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(80, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(117, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtvalorMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        btnSalvar.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvar.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnNovo.setBackground(new java.awt.Color(255, 255, 255));
        btnNovo.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
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
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("Produtos", jPanel6);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Categoria", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        tblCategoria.setFont(new java.awt.Font("Champagne & Limousines", 0, 11)); // NOI18N
        tblCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome"
            }
        ));
        tblCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblCategoria);

        txtEditarCategoria.setBackground(new java.awt.Color(245, 245, 245));
        txtEditarCategoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtEditarCategoria.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        btnSalvarCategoria.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvarCategoria.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnSalvarCategoria.setText("Salvar");
        btnSalvarCategoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnSalvarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarCategoriaActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel19.setText("Nome da Categoria:");

        btnLimparCategoria.setBackground(new java.awt.Color(255, 255, 255));
        btnLimparCategoria.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnLimparCategoria.setText("Limpar");
        btnLimparCategoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnLimparCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparCategoriaActionPerformed(evt);
            }
        });

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        txtBuscaCategoria.setBackground(new java.awt.Color(245, 245, 245));
        txtBuscaCategoria.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        txtBuscaCategoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtBuscaCategoria.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaCategoriaCaretUpdate(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
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

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(btnSalvarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btnLimparCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtEditarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 31, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEditarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
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

        jTabbedPane1.addTab("Categoria", jPanel9);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Marca", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        tblMarca.setFont(new java.awt.Font("Champagne & Limousines", 0, 11)); // NOI18N
        tblMarca.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMarcaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMarca);

        txtEditarMarca.setBackground(new java.awt.Color(245, 245, 245));
        txtEditarMarca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtEditarMarca.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        btnSalvarMarca.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvarMarca.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnSalvarMarca.setText("Salvar");
        btnSalvarMarca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnSalvarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarMarcaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel8.setText("Nome da Marca:");

        btnLimparMarca.setBackground(new java.awt.Color(255, 255, 255));
        btnLimparMarca.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnLimparMarca.setText("Limpar");
        btnLimparMarca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnLimparMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparMarcaActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        txtBuscaMarca.setBackground(new java.awt.Color(245, 245, 245));
        txtBuscaMarca.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtBuscaMarca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtBuscaMarca.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaMarcaCaretUpdate(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel14.setText("Nome:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaMarca)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel14)
                .addComponent(txtBuscaMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEditarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btnSalvarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimparMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtIdMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEditarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modelo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        tblModelo.setFont(new java.awt.Font("Champagne & Limousines", 0, 11)); // NOI18N
        tblModelo.setModel(new javax.swing.table.DefaultTableModel(
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
        tblModelo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblModeloMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblModelo);

        txtEditarModelo.setBackground(new java.awt.Color(245, 245, 245));
        txtEditarModelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtEditarModelo.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        btnSalvarModelo.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvarModelo.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnSalvarModelo.setText("Salvar");
        btnSalvarModelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnSalvarModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarModeloActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel12.setText("Nome do Modelo:");

        btnLimparModelo.setBackground(new java.awt.Color(255, 255, 255));
        btnLimparModelo.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnLimparModelo.setText("Limpar");
        btnLimparModelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnLimparModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparModeloActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        txtBuscaModelo.setBackground(new java.awt.Color(245, 245, 245));
        txtBuscaModelo.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtBuscaModelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtBuscaModelo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaModeloCaretUpdate(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel16.setText("Nome:");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaModelo)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel16)
                .addComponent(txtBuscaModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel17.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel17.setText("Marca:");

        cbxEditarMarca.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        cbxEditarMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxEditarMarca.setToolTipText("");
        cbxEditarMarca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxEditarMarcaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEditarModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnSalvarModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimparModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel17)
                    .addComponent(cbxEditarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtIdModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEditarModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxEditarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("<html><p align = \"center\">Marca <br> Modelo</p></html> ", jPanel7);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entrada de Produtos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        tblEntradadeProdutos.setFont(new java.awt.Font("Champagne & Limousines", 0, 11)); // NOI18N
        tblEntradadeProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome"
            }
        ));
        tblEntradadeProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEntradadeProdutosMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblEntradadeProdutos);

        txtNomeEntradadeproduto.setBackground(new java.awt.Color(245, 245, 245));
        txtNomeEntradadeproduto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtNomeEntradadeproduto.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtNomeEntradadeproduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNomeEntradadeprodutoMouseClicked(evt);
            }
        });

        btnSalvarCategoria1.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvarCategoria1.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnSalvarCategoria1.setText("Salvar");
        btnSalvarCategoria1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnSalvarCategoria1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarCategoria1ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel22.setText("Nome da Produto:");

        btnLimparCategoria1.setBackground(new java.awt.Color(255, 255, 255));
        btnLimparCategoria1.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnLimparCategoria1.setText("Limpar");
        btnLimparCategoria1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnLimparCategoria1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparCategoria1ActionPerformed(evt);
            }
        });

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        txtBuscaEntradadeProduto.setBackground(new java.awt.Color(245, 245, 245));
        txtBuscaEntradadeProduto.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        txtBuscaEntradadeProduto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtBuscaEntradadeProduto.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaEntradadeProdutoCaretUpdate(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel23.setText("Produto:");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaEntradadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtBuscaEntradadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtQuantidadeadd.setBackground(new java.awt.Color(245, 245, 245));
        txtQuantidadeadd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtQuantidadeadd.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtQuantidadeadd.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtQuantidadeaddCaretUpdate(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel24.setText("À Adicionar");

        txtQuantidadeatual.setBackground(new java.awt.Color(245, 245, 245));
        txtQuantidadeatual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtQuantidadeatual.setEnabled(false);
        txtQuantidadeatual.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel25.setText("Quantidade Atual:");

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(245, 245, 245));
        txtTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtTotal.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel26.setText("Quantidade Total:");

        jLabel27.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel27.setText("+");

        jLabel28.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel28.setText("=");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNomeEntradadeproduto, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtIdEntradadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(btnSalvarCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(btnLimparCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtQuantidadeadd))
                                .addGap(13, 13, 13)
                                .addComponent(jLabel27)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtQuantidadeatual, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel28)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(0, 9, Short.MAX_VALUE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtIdEntradadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomeEntradadeproduto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuantidadeadd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel25)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtQuantidadeatual, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("<html><p align = \"center\">Entrada<br>De <br> Produtos</p></html> ", jPanel16);

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Champagne & Limousines", 0, 20)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Produto");

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(560, 560, 560)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(534, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane2.setFocusable(false);
        jTabbedPane2.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        tblProduto1.setBackground(new java.awt.Color(248, 248, 248));
        tblProduto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));
        tblProduto1.setFont(new java.awt.Font("Champagne & Limousines", 0, 12)); // NOI18N
        tblProduto1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Behinger", "Pedaleira", "500", "450"},
                {"Landscape", "Amplificador", "400", "300"},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Modelo", "Nome", "Valor Maximo", "Valor Minimo"
            }
        ));
        tblProduto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProduto1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblProduto1);
        if (tblProduto1.getColumnModel().getColumnCount() > 0) {
            tblProduto1.getColumnModel().getColumn(0).setMinWidth(20);
        }

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        txtBusca1.setBackground(new java.awt.Color(245, 245, 245));
        txtBusca1.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        txtBusca1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtBusca1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBusca1CaretUpdate(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel29.setText("Nome:");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusca1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtBusca1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnAlterar1.setBackground(new java.awt.Color(255, 255, 255));
        btnAlterar1.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnAlterar1.setText("Alterar");
        btnAlterar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnAlterar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterar1ActionPerformed(evt);
            }
        });

        btnExcluir1.setBackground(new java.awt.Color(255, 255, 255));
        btnExcluir1.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnExcluir1.setText("Excluir");
        btnExcluir1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAlterar1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(488, 488, 488))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlterar1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Consulta", jPanel19);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Iniciais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel30.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel30.setText("Nome do Produto:");

        txtNome1.setBackground(new java.awt.Color(245, 245, 245));
        txtNome1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtNome1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));

        jLabel31.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel31.setText("Marca:");

        jLabel32.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel32.setText("Modelo:");

        txtCodigo1.setBackground(new java.awt.Color(245, 245, 245));
        txtCodigo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtCodigo1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel33.setText("Codigo de Barras:");

        cbxMarca1.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        cbxMarca1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxMarca1.setToolTipText("");
        cbxMarca1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMarca1ItemStateChanged(evt);
            }
        });

        cbxModelo1.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        cbxModelo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel34.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel34.setText("Quantidade:");

        txtQuantidade1.setBackground(new java.awt.Color(245, 245, 245));
        txtQuantidade1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtQuantidade1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        txtQuantidade1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        cbxCategoria1.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        cbxCategoria1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxCategoria1.setToolTipText("");
        cbxCategoria1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCategoria1ItemStateChanged(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel35.setText("Categoria:");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQuantidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(cbxCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(cbxMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(txtNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(4, 4, 4)
                        .addComponent(cbxCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valores e Custos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        jLabel36.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel36.setText("Valor Mercado Livre:");

        jLabel37.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel37.setText("Valor Custo:");

        jLabel38.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel38.setText("R$");

        jLabel39.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel39.setText("R$");

        jLabel40.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel40.setText("Valor Loja:");

        jLabel41.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel41.setText("R$");

        txtvalorMinimo1.setBackground(new java.awt.Color(245, 245, 245));
        txtvalorMinimo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtvalorMinimo1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtvalorMinimo1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        txtValorMaximo1.setBackground(new java.awt.Color(245, 245, 245));
        txtValorMaximo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtValorMaximo1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtValorMaximo1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        txtValorCusto1.setBackground(new java.awt.Color(245, 245, 245));
        txtValorCusto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtValorCusto1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0.00"))));
        txtValorCusto1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtValorMaximo1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(79, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtvalorMinimo1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtValorCusto1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(80, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(117, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addGap(10, 10, 10)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorCusto1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtvalorMinimo1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorMaximo1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        btnSalvar1.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvar1.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnSalvar1.setText("Salvar");
        btnSalvar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnSalvar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvar1ActionPerformed(evt);
            }
        });

        btnNovo1.setBackground(new java.awt.Color(255, 255, 255));
        btnNovo1.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnNovo1.setText("Novo");
        btnNovo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnNovo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovo1ActionPerformed(evt);
            }
        });

        btnCancelar1.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar1.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnCancelar1.setText("Cancelar");
        btnCancelar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnNovo1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(btnSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jTabbedPane2.addTab("Produtos", jPanel21);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Categoria", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        tblCategoria1.setFont(new java.awt.Font("Champagne & Limousines", 0, 11)); // NOI18N
        tblCategoria1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome"
            }
        ));
        tblCategoria1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoria1MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblCategoria1);

        txtEditarCategoria1.setBackground(new java.awt.Color(245, 245, 245));
        txtEditarCategoria1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtEditarCategoria1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        btnSalvarCategoria2.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvarCategoria2.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnSalvarCategoria2.setText("Salvar");
        btnSalvarCategoria2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnSalvarCategoria2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarCategoria2ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel42.setText("Nome da Categoria:");

        btnLimparCategoria2.setBackground(new java.awt.Color(255, 255, 255));
        btnLimparCategoria2.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnLimparCategoria2.setText("Limpar");
        btnLimparCategoria2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnLimparCategoria2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparCategoria2ActionPerformed(evt);
            }
        });

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        txtBuscaCategoria1.setBackground(new java.awt.Color(245, 245, 245));
        txtBuscaCategoria1.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        txtBuscaCategoria1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtBuscaCategoria1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaCategoria1CaretUpdate(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel43.setText("Nome:");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaCategoria1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(txtBuscaCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(btnSalvarCategoria2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btnLimparCategoria2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtIdCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtEditarCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 31, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(txtIdCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEditarCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarCategoria2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparCategoria2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Categoria", jPanel24);

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Marca", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        tblMarca1.setFont(new java.awt.Font("Champagne & Limousines", 0, 11)); // NOI18N
        tblMarca1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMarca1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMarca1MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblMarca1);

        txtEditarMarca1.setBackground(new java.awt.Color(245, 245, 245));
        txtEditarMarca1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtEditarMarca1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        btnSalvarMarca1.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvarMarca1.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnSalvarMarca1.setText("Salvar");
        btnSalvarMarca1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnSalvarMarca1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarMarca1ActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel44.setText("Nome da Marca:");

        btnLimparMarca1.setBackground(new java.awt.Color(255, 255, 255));
        btnLimparMarca1.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnLimparMarca1.setText("Limpar");
        btnLimparMarca1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnLimparMarca1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparMarca1ActionPerformed(evt);
            }
        });

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        txtBuscaMarca1.setBackground(new java.awt.Color(245, 245, 245));
        txtBuscaMarca1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtBuscaMarca1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtBuscaMarca1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaMarca1CaretUpdate(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel45.setText("Nome:");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaMarca1)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel45)
                .addComponent(txtBuscaMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEditarMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btnSalvarMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimparMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtIdMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEditarMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modelo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        tblModelo1.setFont(new java.awt.Font("Champagne & Limousines", 0, 11)); // NOI18N
        tblModelo1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblModelo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblModelo1MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblModelo1);

        txtEditarModelo1.setBackground(new java.awt.Color(245, 245, 245));
        txtEditarModelo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtEditarModelo1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        btnSalvarModelo1.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvarModelo1.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnSalvarModelo1.setText("Salvar");
        btnSalvarModelo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnSalvarModelo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarModelo1ActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel46.setText("Nome do Modelo:");

        btnLimparModelo1.setBackground(new java.awt.Color(255, 255, 255));
        btnLimparModelo1.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnLimparModelo1.setText("Limpar");
        btnLimparModelo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnLimparModelo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparModelo1ActionPerformed(evt);
            }
        });

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        txtBuscaModelo1.setBackground(new java.awt.Color(245, 245, 245));
        txtBuscaModelo1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtBuscaModelo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtBuscaModelo1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaModelo1CaretUpdate(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel47.setText("Nome:");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaModelo1)
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel47)
                .addComponent(txtBuscaModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel48.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel48.setText("Marca:");

        cbxEditarMarca1.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        cbxEditarMarca1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxEditarMarca1.setToolTipText("");
        cbxEditarMarca1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxEditarMarca1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEditarModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnSalvarModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimparModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel48)
                    .addComponent(cbxEditarMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txtIdModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEditarModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxEditarMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("<html><p align = \"center\">Marca <br> Modelo</p></html> ", jPanel27);

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entrada de Produtos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        tblEntradadeProdutos1.setFont(new java.awt.Font("Champagne & Limousines", 0, 11)); // NOI18N
        tblEntradadeProdutos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome"
            }
        ));
        tblEntradadeProdutos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEntradadeProdutos1MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblEntradadeProdutos1);

        txtNomeEntradadeproduto1.setBackground(new java.awt.Color(245, 245, 245));
        txtNomeEntradadeproduto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtNomeEntradadeproduto1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtNomeEntradadeproduto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNomeEntradadeproduto1MouseClicked(evt);
            }
        });

        btnSalvarCategoria3.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvarCategoria3.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnSalvarCategoria3.setText("Salvar");
        btnSalvarCategoria3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnSalvarCategoria3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarCategoria3ActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel49.setText("Nome da Produto:");

        btnLimparCategoria3.setBackground(new java.awt.Color(255, 255, 255));
        btnLimparCategoria3.setFont(new java.awt.Font("Champagne & Limousines", 1, 18)); // NOI18N
        btnLimparCategoria3.setText("Limpar");
        btnLimparCategoria3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnLimparCategoria3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparCategoria3ActionPerformed(evt);
            }
        });

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        txtBuscaEntradadeProduto1.setBackground(new java.awt.Color(245, 245, 245));
        txtBuscaEntradadeProduto1.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        txtBuscaEntradadeProduto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtBuscaEntradadeProduto1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscaEntradadeProduto1CaretUpdate(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel50.setText("Produto:");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscaEntradadeProduto1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(txtBuscaEntradadeProduto1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtQuantidadeadd1.setBackground(new java.awt.Color(245, 245, 245));
        txtQuantidadeadd1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtQuantidadeadd1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        txtQuantidadeadd1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtQuantidadeadd1CaretUpdate(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel51.setText("À Adicionar");

        txtQuantidadeatual1.setBackground(new java.awt.Color(245, 245, 245));
        txtQuantidadeatual1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtQuantidadeatual1.setEnabled(false);
        txtQuantidadeatual1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        jLabel52.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel52.setText("Quantidade Atual:");

        txtTotal1.setEditable(false);
        txtTotal1.setBackground(new java.awt.Color(245, 245, 245));
        txtTotal1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        txtTotal1.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N

        jLabel53.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel53.setText("Quantidade Total:");

        jLabel54.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel54.setText("+");

        jLabel55.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel55.setText("=");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel33Layout.createSequentialGroup()
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNomeEntradadeproduto1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel33Layout.createSequentialGroup()
                                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtIdEntradadeProduto1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel33Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(btnSalvarCategoria3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(btnLimparCategoria3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtQuantidadeadd1))
                                .addGap(13, 13, 13)
                                .addComponent(jLabel54)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtQuantidadeatual1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel55)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addGap(0, 9, Short.MAX_VALUE)
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addComponent(txtTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(txtIdEntradadeProduto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomeEntradadeproduto1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuantidadeadd1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel52)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtQuantidadeatual1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarCategoria3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparCategoria3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("<html><p align = \"center\">Entrada<br>De <br> Produtos</p></html> ", jPanel32);

        jLabel56.setBackground(new java.awt.Color(255, 255, 255));
        jLabel56.setFont(new java.awt.Font("Champagne & Limousines", 0, 20)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("Produto");

        btnSair1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/Icones Inativos/Fechar.png"))); // NOI18N
        btnSair1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSair1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSair1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSair1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSair1))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(560, 560, 560)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(534, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair1))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        produto = new ProdutoM();
		String custo = txtValorCusto.getText();
                String Custo = custo.replaceAll(",", ".");
                
                String minimo = txtvalorMinimo.getText();
                String Minimo = minimo.replaceAll(",", ".");
                
                String maximo = txtValorMaximo.getText();
                String Maximo = maximo.replaceAll(",", ".");
		
		
        try {
                categoria = categoriadao.buscaNome(cbxCategoria.getSelectedItem().toString());
                marca = marcadao.buscaNome(cbxMarca.getSelectedItem().toString());
                modelo = modelodao.buscaNome(cbxModelo.getSelectedItem().toString());
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
            }
        if(txtNome.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha todos os obrigatórios !", "erro", JOptionPane.WARNING_MESSAGE);
            txtNome.requestFocusInWindow();       
        }
        else if(txtId.getText().isEmpty()){
            
                //Salva tudo digitado no campo de texto para o objeto e salva no banco de dados
            produto.setIdCategoria(categoria);
            produto.setIdMarca(marca);
            produto.setIdModelo(modelo);
            produto.setNome(txtNome.getText());
            produto.setValorCusto(Float.valueOf(Custo));
            produto.setValorMax(Float.valueOf(Maximo));
            produto.setValorMini(Float.valueOf(Minimo));
            produto.setCodigo(txtCodigo.getText());
            produto.setQuantidade(Integer.valueOf(txtQuantidade.getText()));
            try{
                produtodao.salvar(produto);
                JOptionPane.showMessageDialog(null, "Gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
            atualizaTabelaProduto();
            prepararSalvareCancelar();
            desativarCampos();
            limparCampos();
        }
        else{
            //Salva tudo que foi alterado nos campos de texto para o objeto e salva no banco de dados
            produto.setId(Integer.valueOf(txtId.getText()));
            produto.setIdCategoria(categoria);
            produto.setIdMarca(marca);
            produto.setIdModelo(modelo);
            produto.setNome(txtNome.getText());
            produto.setValorCusto(Float.valueOf(Custo));
            produto.setValorMax(Float.valueOf(Maximo));
            produto.setValorMini(Float.valueOf(Minimo));
            produto.setCodigo(txtCodigo.getText());
            produto.setQuantidade(Integer.valueOf(txtQuantidade.getText()));
        try{
            produtodao.alterar(produto);
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);       
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        atualizaTabelaProduto();
        prepararSalvareCancelar();
        desativarCampos();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    limparCampos();
    prepararSalvareCancelar();
    desativarCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
    prepararAlterar();
    ativarCampos();
    jTabbedPane1.setSelectedIndex(1);
    txtNome.requestFocusInWindow(); 
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if(txtId.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Selecione um Cliente", "erro", JOptionPane.WARNING_MESSAGE);
        }
        else{
            produto.setId(Integer.parseInt(txtId.getText()));
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir: "+ txtNome.getText());
            if(confirma ==0){
                try{
                    produtodao.excluir(produto);
                    limparCampos();
                    txtNome.requestFocusInWindow();
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
                }
                atualizaTabelaProduto();
                prepararExcluir();
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tblProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoMouseClicked
        limparCampos();
        produto = new ProdutoM();

        txtId.setText(tblProduto.getValueAt(tblProduto.getSelectedRow(),0).toString());
        cbxCategoria.setSelectedItem(tblProduto.getValueAt(tblProduto.getSelectedRow(),2).toString());
        cbxMarca.setSelectedItem(tblProduto.getValueAt(tblProduto.getSelectedRow(),3).toString());
        cbxModelo.setSelectedItem(tblProduto.getValueAt(tblProduto.getSelectedRow(),4).toString());
        
        try{
            produto = produtodao.busca(Integer.parseInt(txtId.getText()));
            categoria = categoriadao.busca(produto.getIdCategoria().getId());
            marca = marcadao.busca(produto.getIdMarca().getId());
            modelo = modelodao.busca(produto.getIdModelo().getId());
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
        }
   
        tblProduto.getTableHeader().setReorderingAllowed(false);
        txtId.setText(Integer.toString(produto.getId()));
        cbxCategoria.setSelectedItem(categoria.getNome());
        cbxMarca.setSelectedItem(marca.getNome());
        cbxModelo.setSelectedItem(modelo.getNome());
        txtNome.setText(produto.getNome());
        txtValorCusto.setText(String.valueOf(produto.getValorCusto()));
        txtvalorMinimo.setText(String.valueOf(produto.getValorMax()));
        txtValorMaximo.setText(String.valueOf(produto.getValorMini()));
        txtCodigo.setText(produto.getCodigo());
        txtQuantidade.setText(String.valueOf(produto.getQuantidade()));

        btnAlterar.setEnabled(true);
        btnExcluir.setEnabled(true);
    }//GEN-LAST:event_tblProdutoMouseClicked

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limparCampos();
        prepararNovo();
        ativarCampos();
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void txtBuscaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaCaretUpdate
        listaProduto = null;
        if(txtBusca.getText().equals("")){
            atualizaTabelaProduto();
        }else{
                    
            try {
                listaProduto = produtodao.buscaNomeLista(txtBusca.getText());

                if(listaProduto == null){
                    JOptionPane.showMessageDialog(null, "Nenhum Cliente encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaProduto();
                }else{
                    atualizaTabelaProdutoBusca();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
        } 
    }//GEN-LAST:event_txtBuscaCaretUpdate

    private void cbxMarcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMarcaItemStateChanged
        atualizaBoxModelo();
    }//GEN-LAST:event_cbxMarcaItemStateChanged

    private void btnSalvarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarMarcaActionPerformed
    marca = new MarcaM();
        
        if(txtEditarMarca.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha todos os obrigatórios !", "erro", JOptionPane.WARNING_MESSAGE);
            txtEditarMarca.requestFocusInWindow();       
        }
        else if(txtIdMarca.getText().isEmpty()){
            marca.setNome(txtEditarMarca.getText());
            try{
                marcadao.salvar(marca);
                JOptionPane.showMessageDialog(null, "Gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
            atualizaTabelaMarca();
            atualizaBoxEditarMarca();
            atualizaBoxMarca();
            txtIdMarca.setText("");
            txtEditarMarca.setText("");
            tblMarca.clearSelection();
        }
        else{
            marca.setId(Integer.valueOf(txtIdMarca.getText()));
            marca.setNome(txtEditarMarca.getText());
        try{
            marcadao.alterar(marca);
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);       
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        atualizaTabelaMarca();
        atualizaBoxEditarMarca();
        atualizaBoxMarca();
        txtIdMarca.setText("");
        txtEditarMarca.setText("");
        tblMarca.clearSelection();
        }
    }//GEN-LAST:event_btnSalvarMarcaActionPerformed

    private void btnLimparMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparMarcaActionPerformed
        txtIdMarca.setText("");
        txtEditarMarca.setText("");
        tblMarca.clearSelection();
        txtEditarMarca.requestFocusInWindow();
    }//GEN-LAST:event_btnLimparMarcaActionPerformed

    private void txtBuscaMarcaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaMarcaCaretUpdate
        listaMarca = null;
        if(txtBuscaMarca.getText().equals("")){
            atualizaTabelaMarca();
        }else{
            try {
                listaMarca = marcadao.buscaNomeLista(txtBuscaMarca.getText());

                if(listaProduto == null){
                    JOptionPane.showMessageDialog(null, "Nenhuma Marca encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaMarca();
                }else{
                    atualizaTabelaMarcaBusca();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
        }  
    }//GEN-LAST:event_txtBuscaMarcaCaretUpdate

    private void tblMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMarcaMouseClicked
        marca = new MarcaM();
        txtIdMarca.setText(tblMarca.getValueAt(tblMarca.getSelectedRow(),0).toString());
        
        try{
            marca = marcadao.busca(Integer.parseInt(txtIdMarca.getText()));
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
        }
        tblMarca.getTableHeader().setReorderingAllowed(false);
        txtIdMarca.setText(Integer.toString(marca.getId()));
        txtEditarMarca.setText(marca.getNome());
    }//GEN-LAST:event_tblMarcaMouseClicked

    private void tblModeloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblModeloMouseClicked
        modelo = new ModeloM();
        txtIdModelo.setText(tblModelo.getValueAt(tblModelo.getSelectedRow(),0).toString());
        
        try{
            modelo = modelodao.busca(Integer.parseInt(txtIdModelo.getText()));
            marca = marcadao.busca(Integer.valueOf(modelo.getIdMarca().getId()));
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
        }
        tblModelo.getTableHeader().setReorderingAllowed(false);
        txtIdModelo.setText(Integer.toString(modelo.getId()));
        cbxEditarMarca.setSelectedItem(marca.getNome());
        txtEditarModelo.setText(modelo.getNome());
    }//GEN-LAST:event_tblModeloMouseClicked

    private void btnSalvarModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarModeloActionPerformed
    modelo = new ModeloM();
    
        try {
            marca = marcadao.buscaNome(cbxEditarMarca.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(txtEditarModelo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha todos os obrigatórios !", "erro", JOptionPane.WARNING_MESSAGE);
            txtEditarModelo.requestFocusInWindow();       
        }else if (cbxEditarMarca.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Selecione a Marca!", "erro", JOptionPane.WARNING_MESSAGE);
        }else if(txtIdModelo.getText().isEmpty()){
            modelo.setNome(txtEditarModelo.getText());
            modelo.setIdMarca(marca);
            try{
                modelodao.salvar(modelo);
                JOptionPane.showMessageDialog(null, "Gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
            atualizaTabelaModelo();
            atualizaBoxModelo();
            txtIdModelo.setText("");
            txtEditarModelo.setText("");
            tblModelo.clearSelection();
        }
        else{
            modelo.setId(Integer.valueOf(txtIdModelo.getText()));
            modelo.setNome(txtEditarModelo.getText());
            modelo.setIdMarca(marca);
            try{
                modelodao.alterar(modelo);
                JOptionPane.showMessageDialog(null, "Alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);       
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
            atualizaTabelaModelo();
            atualizaBoxModelo();
            txtIdModelo.setText("");
            txtEditarModelo.setText("");
            tblModelo.clearSelection();
    }
    }//GEN-LAST:event_btnSalvarModeloActionPerformed

    private void btnLimparModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparModeloActionPerformed
        txtIdModelo.setText("");
        cbxEditarMarca.setSelectedIndex(0);
        txtEditarModelo.setText("");
        tblModelo.clearSelection();
        txtEditarModelo.requestFocusInWindow();
    }//GEN-LAST:event_btnLimparModeloActionPerformed

    private void txtBuscaModeloCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaModeloCaretUpdate
        listaModelo = null;
        if(txtBuscaModelo.getText().equals("")){
            atualizaTabelaModelo();
        }else{
            try {
                listaModelo = modelodao.buscaNomeLista(txtBuscaModelo.getText());

                if(listaModelo == null){
                    JOptionPane.showMessageDialog(null, "Nenhum Cliente encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaModelo();
                }else{
                    atualizaTabelaModeloBusca();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
        }  
    }//GEN-LAST:event_txtBuscaModeloCaretUpdate

    private void cbxEditarMarcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxEditarMarcaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxEditarMarcaItemStateChanged

    private void tblCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriaMouseClicked
        categoria = new CategoriaM();
        txtIdCategoria.setText(tblCategoria.getValueAt(tblCategoria.getSelectedRow(),0).toString());
        
        try{
            categoria = categoriadao.busca(Integer.parseInt(txtIdCategoria.getText()));
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage());
        }
        tblCategoria.getTableHeader().setReorderingAllowed(false);
        txtIdCategoria.setText(Integer.toString(categoria.getId()));
        txtEditarCategoria.setText(categoria.getNome());
    }//GEN-LAST:event_tblCategoriaMouseClicked

    private void btnSalvarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCategoriaActionPerformed
    categoria = new CategoriaM();
        
        if(txtEditarCategoria.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha todos os obrigatórios !", "erro", JOptionPane.WARNING_MESSAGE);
            txtEditarMarca.requestFocusInWindow();       
        }
        else if(txtIdCategoria.getText().isEmpty()){
            categoria.setNome(txtEditarCategoria.getText());
            try{
                categoriadao.salvar(categoria);
                JOptionPane.showMessageDialog(null, "Gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
            atualizaTabelaCategoria();
            atualizaBoxCategoria();
            txtIdCategoria.setText("");
            txtEditarCategoria.setText("");
            tblCategoria.clearSelection();
        }
        else{
            categoria.setId(Integer.valueOf(txtIdCategoria.getText()));
            categoria.setNome(txtEditarCategoria.getText());
        try{
            categoriadao.alterar(categoria);
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);       
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
        }
        atualizaTabelaCategoria();
        atualizaBoxCategoria();
        txtIdCategoria.setText("");
        txtEditarCategoria.setText("");
        tblCategoria.clearSelection();
        }
    }//GEN-LAST:event_btnSalvarCategoriaActionPerformed

    private void btnLimparCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparCategoriaActionPerformed
        txtIdCategoria.setText("");
        txtEditarCategoria.setText("");
        tblCategoria.clearSelection();
        txtEditarCategoria.requestFocusInWindow();
    }//GEN-LAST:event_btnLimparCategoriaActionPerformed

    private void txtBuscaCategoriaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaCategoriaCaretUpdate
        listaCategoria = null;
        if(txtBuscaCategoria.getText().equals("")){
            atualizaTabelaMarca();
        }else{
            try {
                listaCategoria = categoriadao.buscaNomeLista(txtBuscaCategoria.getText());

                if(listaCategoria == null){
                    JOptionPane.showMessageDialog(null, "Nenhum Categoria encontrado!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaMarca();
                }else{
                    atualizaTabelaCategoriaBusca();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
        } 
    }//GEN-LAST:event_txtBuscaCategoriaCaretUpdate

    private void cbxCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCategoriaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCategoriaItemStateChanged

    private void tblEntradadeProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEntradadeProdutosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblEntradadeProdutosMouseClicked

    private void btnSalvarCategoria1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCategoria1ActionPerformed
        produto = new ProdutoM();
        entradadeProduto = new EntradadeProdutoM();
        if(txtNomeEntradadeproduto.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Preencha todos os obrigatórios !", "erro", JOptionPane.WARNING_MESSAGE);
            txtNomeEntradadeproduto.requestFocusInWindow();       
        }
        else{
            
                //Salva tudo digitado no campo de texto para o objeto e salva no banco de dados
            produto.setId(Integer.valueOf(txtIdEntradadeProduto.getText()));
            produto.setQuantidade(Integer.valueOf(txtTotal.getText()));
            
            entradadeProduto.setId(Integer.valueOf(txtIdEntradadeProduto.getText()));
            entradadeProduto.setIdProduto(produto);
            entradadeProduto.setData(title);
            entradadeProduto.setHora(title);
            entradadeProduto.setQuantidade(Integer.valueOf(txtQuantidadeadd.getText()));
            try{
                produtodao.alterarQuantidade(produto);
                entradadeProdutodao.salvar(entradadeProduto);
                JOptionPane.showMessageDialog(null, "Gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
            txtIdEntradadeProduto.setText("");
            txtNomeEntradadeproduto.setText("");
            txtQuantidadeadd.setText("");
            txtQuantidadeatual.setText("");
            txtTotal.setText("");
            atualizaTabelaEntradadeProduto();
            atualizaTabelaProduto();
        }
    }//GEN-LAST:event_btnSalvarCategoria1ActionPerformed

    private void btnLimparCategoria1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparCategoria1ActionPerformed
        txtIdEntradadeProduto.setText("");
        txtNomeEntradadeproduto.setText("");
        txtQuantidadeadd.setText("");
        txtQuantidadeatual.setText("");
        txtTotal.setText("");
        produto = null;
    }//GEN-LAST:event_btnLimparCategoria1ActionPerformed

    private void txtBuscaEntradadeProdutoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaEntradadeProdutoCaretUpdate
        ListaEntradadeProduto = null;
        if(txtBusca.getText().equals("")){
            atualizaTabelaProduto();
        }else{
                    
            try {
                ListaEntradadeProduto = entradadeProdutodao.buscaNomeLista(txtBuscaEntradadeProduto.getText());

                if(listaProduto == null){
                    JOptionPane.showMessageDialog(null, "Nenhuma Entrada encontrada!","", JOptionPane.WARNING_MESSAGE);
                    atualizaTabelaEntradadeProduto();
                }else{
                    atualizaTabelaEntradadeProdutoBusca();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: "+ex.getMessage(), "erro", JOptionPane.WARNING_MESSAGE);
            }
        } 
    }//GEN-LAST:event_txtBuscaEntradadeProdutoCaretUpdate

    private void txtQuantidadeaddCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtQuantidadeaddCaretUpdate
        if(txtQuantidadeadd.getText().isEmpty() ){
            //txtQtde.setText("1");
        }else{
            CalculaVenda();
        }
    }//GEN-LAST:event_txtQuantidadeaddCaretUpdate

    private void txtBuscaProdutoDialogCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaProdutoDialogCaretUpdate
        if (txtBuscaProdutoDialog.getText().equals("")) {
            atualizaTabelaProdutoDialog();
        }else{

            try {
                listaProduto = produtodao.buscaNomeLista(txtBuscaProdutoDialog.getText());

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

    private void tblProdutoDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoDialogMouseClicked
        // TODO add your handling code here:
        txtIdEntradadeProduto.setText(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 0).toString());
        txtNomeEntradadeproduto.setText(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 1).toString());
        txtQuantidadeatual.setText(tblProdutoDialog.getValueAt(tblProdutoDialog.getSelectedRow(), 2).toString());
        produto = new ProdutoM();
        produto.setId(Integer.parseInt(txtIdEntradadeProduto.getText()));
        produto.setNome(txtQuantidadeatual.getText());
        ProdutoDialog.dispose();
    }//GEN-LAST:event_tblProdutoDialogMouseClicked

    private void txtNomeEntradadeprodutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNomeEntradadeprodutoMouseClicked
       ProdutoDialog.setVisible(true);
       ProdutoDialog.setLocationRelativeTo(null);
       atualizaTabelaProdutoDialog();
       tblProdutoDialog.getTableHeader().setReorderingAllowed(false);
    }//GEN-LAST:event_txtNomeEntradadeprodutoMouseClicked

    private void btnSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnSairMouseClicked

    private void btnSairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseEntered
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Fechar.png")));
    }//GEN-LAST:event_btnSairMouseEntered

    private void btnSairMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseExited
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Fechar.png")));
    }//GEN-LAST:event_btnSairMouseExited

    private void tblProduto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProduto1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProduto1MouseClicked

    private void txtBusca1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBusca1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusca1CaretUpdate

    private void btnAlterar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterar1ActionPerformed

    private void btnExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcluir1ActionPerformed

    private void cbxMarca1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMarca1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxMarca1ItemStateChanged

    private void cbxCategoria1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCategoria1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCategoria1ItemStateChanged

    private void btnSalvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvar1ActionPerformed

    private void btnNovo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNovo1ActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void tblCategoria1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoria1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCategoria1MouseClicked

    private void btnSalvarCategoria2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCategoria2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarCategoria2ActionPerformed

    private void btnLimparCategoria2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparCategoria2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparCategoria2ActionPerformed

    private void txtBuscaCategoria1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaCategoria1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaCategoria1CaretUpdate

    private void tblMarca1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMarca1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblMarca1MouseClicked

    private void btnSalvarMarca1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarMarca1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarMarca1ActionPerformed

    private void btnLimparMarca1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparMarca1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparMarca1ActionPerformed

    private void txtBuscaMarca1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaMarca1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaMarca1CaretUpdate

    private void tblModelo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblModelo1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblModelo1MouseClicked

    private void btnSalvarModelo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarModelo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarModelo1ActionPerformed

    private void btnLimparModelo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparModelo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparModelo1ActionPerformed

    private void txtBuscaModelo1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaModelo1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaModelo1CaretUpdate

    private void cbxEditarMarca1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxEditarMarca1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxEditarMarca1ItemStateChanged

    private void tblEntradadeProdutos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEntradadeProdutos1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblEntradadeProdutos1MouseClicked

    private void txtNomeEntradadeproduto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNomeEntradadeproduto1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeEntradadeproduto1MouseClicked

    private void btnSalvarCategoria3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCategoria3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarCategoria3ActionPerformed

    private void btnLimparCategoria3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparCategoria3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparCategoria3ActionPerformed

    private void txtBuscaEntradadeProduto1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscaEntradadeProduto1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaEntradadeProduto1CaretUpdate

    private void txtQuantidadeadd1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtQuantidadeadd1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantidadeadd1CaretUpdate

    private void btnSair1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSair1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSair1MouseClicked

    private void btnSair1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSair1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSair1MouseEntered

    private void btnSair1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSair1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSair1MouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog ProdutoDialog;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAlterar1;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExcluir1;
    private javax.swing.JButton btnLimparCategoria;
    private javax.swing.JButton btnLimparCategoria1;
    private javax.swing.JButton btnLimparCategoria2;
    private javax.swing.JButton btnLimparCategoria3;
    private javax.swing.JButton btnLimparMarca;
    private javax.swing.JButton btnLimparMarca1;
    private javax.swing.JButton btnLimparModelo;
    private javax.swing.JButton btnLimparModelo1;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnNovo1;
    private javax.swing.JLabel btnSair;
    private javax.swing.JLabel btnSair1;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSalvar1;
    private javax.swing.JButton btnSalvarCategoria;
    private javax.swing.JButton btnSalvarCategoria1;
    private javax.swing.JButton btnSalvarCategoria2;
    private javax.swing.JButton btnSalvarCategoria3;
    private javax.swing.JButton btnSalvarMarca;
    private javax.swing.JButton btnSalvarMarca1;
    private javax.swing.JButton btnSalvarModelo;
    private javax.swing.JButton btnSalvarModelo1;
    private javax.swing.JComboBox<String> cbxCategoria;
    private javax.swing.JComboBox<String> cbxCategoria1;
    private javax.swing.JComboBox<String> cbxEditarMarca;
    private javax.swing.JComboBox<String> cbxEditarMarca1;
    private javax.swing.JComboBox<String> cbxMarca;
    private javax.swing.JComboBox<String> cbxMarca1;
    private javax.swing.JComboBox<String> cbxModelo;
    private javax.swing.JComboBox<String> cbxModelo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tblCategoria;
    private javax.swing.JTable tblCategoria1;
    private javax.swing.JTable tblEntradadeProdutos;
    private javax.swing.JTable tblEntradadeProdutos1;
    private javax.swing.JTable tblMarca;
    private javax.swing.JTable tblMarca1;
    private javax.swing.JTable tblModelo;
    private javax.swing.JTable tblModelo1;
    private javax.swing.JTable tblProduto;
    private javax.swing.JTable tblProduto1;
    private javax.swing.JTable tblProdutoDialog;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtBusca1;
    private javax.swing.JTextField txtBuscaCategoria;
    private javax.swing.JTextField txtBuscaCategoria1;
    private javax.swing.JTextField txtBuscaEntradadeProduto;
    private javax.swing.JTextField txtBuscaEntradadeProduto1;
    private javax.swing.JTextField txtBuscaMarca;
    private javax.swing.JTextField txtBuscaMarca1;
    private javax.swing.JTextField txtBuscaModelo;
    private javax.swing.JTextField txtBuscaModelo1;
    private javax.swing.JTextField txtBuscaProdutoDialog;
    private javax.swing.JFormattedTextField txtCodigo;
    private javax.swing.JFormattedTextField txtCodigo1;
    private javax.swing.JFormattedTextField txtEditarCategoria;
    private javax.swing.JFormattedTextField txtEditarCategoria1;
    private javax.swing.JFormattedTextField txtEditarMarca;
    private javax.swing.JFormattedTextField txtEditarMarca1;
    private javax.swing.JFormattedTextField txtEditarModelo;
    private javax.swing.JFormattedTextField txtEditarModelo1;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtId1;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtIdCategoria1;
    private javax.swing.JTextField txtIdEntradadeProduto;
    private javax.swing.JTextField txtIdEntradadeProduto1;
    private javax.swing.JTextField txtIdMarca;
    private javax.swing.JTextField txtIdMarca1;
    private javax.swing.JTextField txtIdModelo;
    private javax.swing.JTextField txtIdModelo1;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNome1;
    private javax.swing.JFormattedTextField txtNomeEntradadeproduto;
    private javax.swing.JFormattedTextField txtNomeEntradadeproduto1;
    private javax.swing.JFormattedTextField txtQuantidade;
    private javax.swing.JFormattedTextField txtQuantidade1;
    private javax.swing.JFormattedTextField txtQuantidadeadd;
    private javax.swing.JFormattedTextField txtQuantidadeadd1;
    private javax.swing.JFormattedTextField txtQuantidadeatual;
    private javax.swing.JFormattedTextField txtQuantidadeatual1;
    private javax.swing.JFormattedTextField txtTotal;
    private javax.swing.JFormattedTextField txtTotal1;
    private javax.swing.JFormattedTextField txtValorCusto;
    private javax.swing.JFormattedTextField txtValorCusto1;
    private javax.swing.JFormattedTextField txtValorMaximo;
    private javax.swing.JFormattedTextField txtValorMaximo1;
    private javax.swing.JFormattedTextField txtvalorMinimo;
    private javax.swing.JFormattedTextField txtvalorMinimo1;
    // End of variables declaration//GEN-END:variables
}
