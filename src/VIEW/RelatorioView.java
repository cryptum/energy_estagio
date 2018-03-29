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
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Danilo-NOTE
 */
public class RelatorioView extends javax.swing.JInternalFrame {

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
    
    Document doc;
    String caminho;
    
    public RelatorioView() {
        initComponents();
        this.setVisible(true);
        Tabela.getTableHeader().setReorderingAllowed(false);
        txtNome.setEnabled(false);
        txtDe.setEnabled(false);
        txtAte.setEnabled(false);
        btnGerarRelatorio.setUI(new BasicButtonUI());
        btnPuxarDados.setUI(new BasicButtonUI());
        
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
    
    // DECLARAÇÃO DE MÉTODOS DE CONTROLE DE BOTÕES
    public void limparCampos(){

    }
   
    public void ativarCampos(){

    }

    public void desativarCampos(){

    }
   
    public void prepararNovo() {

    }
   
    public void prepararSalvareCancelar() {

    }
   
    public void prepararSelecaoTabela(){

    }
   
    public void prepararAlterar(){

    }
   
    public void prepararExcluir(){

    }
    
    public void gerarDocumentoCliente() throws IOException, DocumentException{
        
        File pdf = null;
        JFileChooser chooser = null;
        doc = new com.itextpdf.text.Document(PageSize.A4.rotate());

        String data = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        String hora = new SimpleDateFormat("hh:mm").format(new Date(System.currentTimeMillis()));
        
 	try {
            pdf = File.createTempFile("Clientes ", "");            
        } catch (IOException e1) {            
            e1.printStackTrace();
        }

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo PDF", "pdf");

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(pdf);
        chooser.setSelectedFile(pdf);
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setMultiSelectionEnabled(false);


        int retorno = chooser.showSaveDialog(null);
        if (retorno==JFileChooser.APPROVE_OPTION){
            caminho = chooser.getSelectedFile().getAbsolutePath();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!\n\nLocal: "+chooser.getSelectedFile().getAbsolutePath()+"\n ");
        }

        PdfWriter.getInstance(doc, new FileOutputStream(caminho+".pdf"));
        doc.open();
        Font f11 = new Font(Font.FontFamily.TIMES_ROMAN, 11);
        Font f10 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font f12 = new Font(Font.FontFamily.HELVETICA, 17, Font.BOLD);
        Font fnormal = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            
        Paragraph nomeUniversidade = new Paragraph("Energy Som",f12);
            nomeUniversidade.setAlignment(Element.ALIGN_CENTER);
            nomeUniversidade.setSpacingAfter(10);
            
            Paragraph nomeRelatorio = new Paragraph("Relatório de Clientes" ,f12);
            nomeRelatorio.setAlignment(Element.ALIGN_CENTER);
            nomeRelatorio.setSpacingAfter(10);
            
            Paragraph DataeHora = new Paragraph(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()))+" - "+new SimpleDateFormat("hh:mm").format(new Date(System.currentTimeMillis())) ,f12);
            DataeHora.setAlignment(Element.ALIGN_LEFT);
            DataeHora.setSpacingAfter(10);
            
            doc.add(nomeUniversidade);
            doc.add(nomeRelatorio);
            doc.add(DataeHora);
            
            PdfPTable tabela = new PdfPTable(7);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            PdfPCell cabecalhoNome = new PdfPCell(new Paragraph("Nome", f10));
            cabecalhoNome.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoNome);

            PdfPCell cabecalhoEnd = new PdfPCell(new Paragraph("CPF",f10));
            cabecalhoEnd.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoEnd);
            
            PdfPCell cabecalhoEmail = new PdfPCell(new Paragraph("Cidade",f10));
            cabecalhoEmail.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoEmail);
            
            PdfPCell cabecalhoCidade = new PdfPCell(new Paragraph("Telefone",f10));
            cabecalhoCidade.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoCidade);
            
            PdfPCell cabecalhoTelefone = new PdfPCell(new Paragraph("Celular",f10));
            cabecalhoTelefone.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoTelefone);
            
            PdfPCell cabecalhoCel = new PdfPCell(new Paragraph("Nascimento",f10));
            cabecalhoCel.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoCel);
            
            PdfPCell cabecalhoSetor = new PdfPCell(new Paragraph("observação",f10));
            cabecalhoSetor.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoSetor);
            
            tabela.setHeaderRows(1); // linha que sera repetida em todas as paginas.
            
            for (ClienteM cliente : listaCliente){
                Paragraph pNome = new Paragraph(cliente.getNome(), fnormal);
                pNome.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colNome = new PdfPCell(pNome);
                
                Paragraph pEnd = new Paragraph(cliente.getCpf(), fnormal);
                pEnd.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colEnd = new PdfPCell(pEnd);
                
                Paragraph pEmail = new Paragraph(cliente.getCidade(), fnormal);
                pEmail.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colEmail = new PdfPCell(pEmail);
                
                Paragraph pCidade = new Paragraph(cliente.getTelefone(), fnormal);
                pCidade.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colCidade = new PdfPCell(pCidade);
                colCidade.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                Paragraph pTel = new Paragraph(cliente.getCelular1(), fnormal);
                pTel.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colTel = new PdfPCell(pTel);
                colTel.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                Paragraph pCel = new Paragraph(cliente.getNascimento(), fnormal);
                pTel.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colCel = new PdfPCell(pCel);
                colCel.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                Paragraph pSetor = new Paragraph(cliente.getObservacao(), fnormal);
                pSetor.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colSetor = new PdfPCell(pSetor);
                colSetor.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                tabela.addCell(colNome);
                tabela.addCell(colEnd);
                tabela.addCell(colEmail);
                tabela.addCell(colCidade);
                tabela.addCell(colTel);
                tabela.addCell(colCel);
                tabela.addCell(colSetor);
            }
            doc.add(tabela);

        doc.close();
    }
    
    public void gerarDocumentoFuncionario() throws IOException, DocumentException{
        
        File pdf = null;
        JFileChooser chooser = null;
        doc = new com.itextpdf.text.Document(PageSize.A4.rotate());

        String data = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        String hora = new SimpleDateFormat("hh:mm").format(new Date(System.currentTimeMillis()));
        
 	try {
            pdf = File.createTempFile("Funcionario ", "");            
        } catch (IOException e1) {            
            e1.printStackTrace();
        }

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo PDF", "pdf");

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(pdf);
        chooser.setSelectedFile(pdf);
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setMultiSelectionEnabled(false);


        int retorno = chooser.showSaveDialog(null);
        if (retorno==JFileChooser.APPROVE_OPTION){
            caminho = chooser.getSelectedFile().getAbsolutePath();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!\n\nLocal: "+chooser.getSelectedFile().getAbsolutePath()+"\n ");
        }

        PdfWriter.getInstance(doc, new FileOutputStream(caminho+".pdf"));
        doc.open();
        Font f11 = new Font(Font.FontFamily.TIMES_ROMAN, 11);
        Font f10 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font f12 = new Font(Font.FontFamily.HELVETICA, 17, Font.BOLD);
        Font fnormal = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            
        Paragraph nomeUniversidade = new Paragraph("Energy Som",f12);
            nomeUniversidade.setAlignment(Element.ALIGN_CENTER);
            nomeUniversidade.setSpacingAfter(10);
            
            Paragraph nomeRelatorio = new Paragraph("Relatório de Funcionários" ,f12);
            nomeRelatorio.setAlignment(Element.ALIGN_CENTER);
            nomeRelatorio.setSpacingAfter(10);
            
            Paragraph DataeHora = new Paragraph(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()))+" - "+new SimpleDateFormat("hh:mm").format(new Date(System.currentTimeMillis())) ,f12);
            DataeHora.setAlignment(Element.ALIGN_LEFT);
            DataeHora.setSpacingAfter(10);
            
            doc.add(nomeUniversidade);
            doc.add(nomeRelatorio);
            doc.add(DataeHora);
            
            PdfPTable tabela = new PdfPTable(7);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            PdfPCell cabecalhoNome = new PdfPCell(new Paragraph("Nome", f10));
            cabecalhoNome.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoNome);

            PdfPCell cabecalhoEnd = new PdfPCell(new Paragraph("CPF",f10));
            cabecalhoEnd.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoEnd);
            
            PdfPCell cabecalhoEmail = new PdfPCell(new Paragraph("RG",f10));
            cabecalhoEmail.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoEmail);
            
            PdfPCell cabecalhoCidade = new PdfPCell(new Paragraph("Telefone",f10));
            cabecalhoCidade.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoCidade);
            
            PdfPCell cabecalhoTelefone = new PdfPCell(new Paragraph("Celular 1",f10));
            cabecalhoTelefone.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoTelefone);
            
            PdfPCell cabecalhoCel = new PdfPCell(new Paragraph("Celular 2",f10));
            cabecalhoCel.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoCel);
            
            PdfPCell cabecalhoNasc = new PdfPCell(new Paragraph("Nascimento",f10));
            cabecalhoNasc.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoNasc);
            
            tabela.setHeaderRows(1); // linha que sera repetida em todas as paginas.
            
            for (FuncionarioM funcionario : listaFuncionario){
                Paragraph pNome = new Paragraph(funcionario.getNome(), fnormal);
                pNome.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colNome = new PdfPCell(pNome);
                
                Paragraph pEnd = new Paragraph(funcionario.getCpf(), fnormal);
                pEnd.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colEnd = new PdfPCell(pEnd);
                
                Paragraph pEmail = new Paragraph(funcionario.getRg(), fnormal);
                pEmail.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colEmail = new PdfPCell(pEmail);
                
                Paragraph pCidade = new Paragraph(funcionario.getTelefone(), fnormal);
                pCidade.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colCidade = new PdfPCell(pCidade);
                colCidade.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                Paragraph pTel = new Paragraph(funcionario.getCelular1(), fnormal);
                pTel.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colTel = new PdfPCell(pTel);
                colTel.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                Paragraph pCel = new Paragraph(funcionario.getCelular2(), fnormal);
                pTel.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colCel = new PdfPCell(pCel);
                colCel.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                Paragraph pnasc = new Paragraph(funcionario.getNascimento(), fnormal);
                pnasc.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colnasc = new PdfPCell(pnasc);
                colnasc.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                tabela.addCell(colNome);
                tabela.addCell(colEnd);
                tabela.addCell(colEmail);
                tabela.addCell(colCidade);
                tabela.addCell(colTel);
                tabela.addCell(colCel);
                tabela.addCell(colnasc);
            }
            doc.add(tabela);

        doc.close();
    }
    
    public void gerarDocumentoProduto() throws IOException, DocumentException{
        
        File pdf = null;
        JFileChooser chooser = null;
        doc = new com.itextpdf.text.Document(PageSize.A4.rotate());

        String data = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        String hora = new SimpleDateFormat("hh:mm").format(new Date(System.currentTimeMillis()));
        
 	try {
            pdf = File.createTempFile("Produto ", "");            
        } catch (IOException e1) {            
            e1.printStackTrace();
        }

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo PDF", "pdf");

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(pdf);
        chooser.setSelectedFile(pdf);
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setMultiSelectionEnabled(false);


        int retorno = chooser.showSaveDialog(null);
        if (retorno==JFileChooser.APPROVE_OPTION){
            caminho = chooser.getSelectedFile().getAbsolutePath();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!\n\nLocal: "+chooser.getSelectedFile().getAbsolutePath()+"\n ");
        }

        PdfWriter.getInstance(doc, new FileOutputStream(caminho+".pdf"));
        doc.open();
        Font f11 = new Font(Font.FontFamily.TIMES_ROMAN, 11);
        Font f10 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font f12 = new Font(Font.FontFamily.HELVETICA, 17, Font.BOLD);
        Font fnormal = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            
        Paragraph nomeUniversidade = new Paragraph("Energy Som",f12);
            nomeUniversidade.setAlignment(Element.ALIGN_CENTER);
            nomeUniversidade.setSpacingAfter(10);
            
            Paragraph nomeRelatorio = new Paragraph("Relatório de Produto" ,f12);
            nomeRelatorio.setAlignment(Element.ALIGN_CENTER);
            nomeRelatorio.setSpacingAfter(10);
            
            Paragraph DataeHora = new Paragraph(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()))+" - "+new SimpleDateFormat("hh:mm").format(new Date(System.currentTimeMillis())) ,f12);
            DataeHora.setAlignment(Element.ALIGN_LEFT);
            DataeHora.setSpacingAfter(10);
            
            doc.add(nomeUniversidade);
            doc.add(nomeRelatorio);
            doc.add(DataeHora);
            
            PdfPTable tabela = new PdfPTable(7);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            PdfPCell cabecalhoNome = new PdfPCell(new Paragraph("Nome", f10));
            cabecalhoNome.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoNome);

            PdfPCell cabecalhoEnd = new PdfPCell(new Paragraph("Marca",f10));
            cabecalhoEnd.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoEnd);
            
            PdfPCell cabecalhoEmail = new PdfPCell(new Paragraph("Modelo",f10));
            cabecalhoEmail.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoEmail);
            
            PdfPCell cabecalhoCidade = new PdfPCell(new Paragraph("Valor Custo",f10));
            cabecalhoCidade.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoCidade);
            
            PdfPCell cabecalhoTelefone = new PdfPCell(new Paragraph("Valor Maximo",f10));
            cabecalhoTelefone.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoTelefone);
            
            PdfPCell cabecalhoCel = new PdfPCell(new Paragraph("Valor Minimo",f10));
            cabecalhoCel.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoCel);
            
            PdfPCell cabecalhoNasc = new PdfPCell(new Paragraph("Código",f10));
            cabecalhoNasc.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoNasc);
            
            PdfPCell cabecaquantidade = new PdfPCell(new Paragraph("Quantidade",f10));
            cabecaquantidade.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecaquantidade);
            
            tabela.setHeaderRows(1); // linha que sera repetida em todas as paginas.
            
            for (ProdutoM produto : listaProduto){
                Paragraph pNome = new Paragraph(produto.getNome(), fnormal);
                pNome.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colNome = new PdfPCell(pNome);
                
                Paragraph pEnd = new Paragraph(produto.getIdMarca().getNome(), fnormal);
                pEnd.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colEnd = new PdfPCell(pEnd);
                
                Paragraph pEmail = new Paragraph(produto.getIdModelo().getNome(), fnormal);
                pEmail.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colEmail = new PdfPCell(pEmail);
                
                Paragraph pCidade = new Paragraph(String.valueOf(produto.getValorCusto()), fnormal);
                pCidade.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colCidade = new PdfPCell(pCidade);
                colCidade.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                Paragraph pTel = new Paragraph(String.valueOf(produto.getValorMax()), fnormal);
                pTel.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colTel = new PdfPCell(pTel);
                colTel.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                Paragraph pCel = new Paragraph(String.valueOf(produto.getValorMini()), fnormal);
                pTel.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colCel = new PdfPCell(pCel);
                colCel.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                Paragraph pnasc = new Paragraph(produto.getCodigo(), fnormal);
                pnasc.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colnasc = new PdfPCell(pnasc);
                colnasc.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                Paragraph pquantidade = new Paragraph(produto.getCodigo(), fnormal);
                pquantidade.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colquantidade = new PdfPCell(pquantidade);
                colnasc.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                tabela.addCell(colNome);
                tabela.addCell(colEnd);
                tabela.addCell(colEmail);
                tabela.addCell(colCidade);
                tabela.addCell(colTel);
                tabela.addCell(colCel);
                tabela.addCell(colnasc);
                tabela.addCell(colquantidade);
            }
            doc.add(tabela);

        doc.close();
    }
    
    public void gerarDocumentoVenda() throws IOException, DocumentException{
        
        File pdf = null;
        JFileChooser chooser = null;
        doc = new com.itextpdf.text.Document(PageSize.A4.rotate());

        String data = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        String hora = new SimpleDateFormat("hh:mm").format(new Date(System.currentTimeMillis()));
        
 	try {
            pdf = File.createTempFile("Venda ", "");            
        } catch (IOException e1) {            
            e1.printStackTrace();
        }

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo PDF", "pdf");

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(pdf);
        chooser.setSelectedFile(pdf);
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setMultiSelectionEnabled(false);


        int retorno = chooser.showSaveDialog(null);
        if (retorno==JFileChooser.APPROVE_OPTION){
            caminho = chooser.getSelectedFile().getAbsolutePath();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!\n\nLocal: "+chooser.getSelectedFile().getAbsolutePath()+"\n ");
        }

        PdfWriter.getInstance(doc, new FileOutputStream(caminho+".pdf"));
        doc.open();
        Font f11 = new Font(Font.FontFamily.TIMES_ROMAN, 11);
        Font f10 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font f12 = new Font(Font.FontFamily.HELVETICA, 17, Font.BOLD);
        Font fnormal = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            
        Paragraph nomeUniversidade = new Paragraph("Energy Som",f12);
            nomeUniversidade.setAlignment(Element.ALIGN_CENTER);
            nomeUniversidade.setSpacingAfter(10);
            
            Paragraph nomeRelatorio = new Paragraph("Relatório de Venda" ,f12);
            nomeRelatorio.setAlignment(Element.ALIGN_CENTER);
            nomeRelatorio.setSpacingAfter(10);
            
            Paragraph DataInicioDataFim = new Paragraph("De:"+txtDe.getText()+" - Até:"+txtAte.getText() ,f11);
            DataInicioDataFim.setAlignment(Element.ALIGN_CENTER);
            DataInicioDataFim.setSpacingAfter(10);
            
            Paragraph DataeHora = new Paragraph(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()))+" - "+new SimpleDateFormat("hh:mm").format(new Date(System.currentTimeMillis())) ,f12);
            DataeHora.setAlignment(Element.ALIGN_LEFT);
            DataeHora.setSpacingAfter(10);
            
            doc.add(nomeUniversidade);
            doc.add(nomeRelatorio);
            doc.add(DataInicioDataFim);
            doc.add(DataeHora);
            
            PdfPTable tabela = new PdfPTable(7);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            PdfPCell cabecalhoNome = new PdfPCell(new Paragraph("Cliente", f10));
            cabecalhoNome.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoNome);

            PdfPCell cabecalhoEnd = new PdfPCell(new Paragraph("Vendedor",f10));
            cabecalhoEnd.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoEnd);
            
            PdfPCell cabecalhoEmail = new PdfPCell(new Paragraph("Data",f10));
            cabecalhoEmail.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoEmail);
            
            PdfPCell cabecalhoCidade = new PdfPCell(new Paragraph("Total da Venda",f10));
            cabecalhoCidade.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoCidade);
            
            PdfPCell cabecalhoTelefone = new PdfPCell(new Paragraph("Forma de Pagamento",f10));
            cabecalhoTelefone.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoTelefone);

            tabela.setHeaderRows(1); // linha que sera repetida em todas as paginas.
            
            for (VendaM venda : listaVenda){
                Paragraph pNome = new Paragraph(venda.getIdCliente().getNome(), fnormal);
                pNome.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colNome = new PdfPCell(pNome);
                
                Paragraph pEnd = new Paragraph(venda.getIdFuncionario().getNome(), fnormal);
                pEnd.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colEnd = new PdfPCell(pEnd);
                
                Paragraph pEmail = new Paragraph(venda.getData(), fnormal);
                pEmail.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colEmail = new PdfPCell(pEmail);
                
                Paragraph pCidade = new Paragraph(String.valueOf(venda.getTotalVendas()), fnormal);
                pCidade.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colCidade = new PdfPCell(pCidade);
                colCidade.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                Paragraph pTel = new Paragraph(venda.getFormaPagamento(), fnormal);
                pTel.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colTel = new PdfPCell(pTel);
                colTel.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                tabela.addCell(colNome);
                tabela.addCell(colEnd);
                tabela.addCell(colEmail);
                tabela.addCell(colCidade);
                tabela.addCell(colTel);
            }
            doc.add(tabela);

        doc.close();
    }
    
    public void gerarDocumentoVendaML() throws IOException, DocumentException{
        
        File pdf = null;
        JFileChooser chooser = null;
        doc = new com.itextpdf.text.Document(PageSize.A4.rotate());

        String data = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        String hora = new SimpleDateFormat("hh:mm").format(new Date(System.currentTimeMillis()));
        
 	try {
            pdf = File.createTempFile("Venda Mercado Livre ", "");            
        } catch (IOException e1) {            
            e1.printStackTrace();
        }

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo PDF", "pdf");

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(pdf);
        chooser.setSelectedFile(pdf);
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setMultiSelectionEnabled(false);


        int retorno = chooser.showSaveDialog(null);
        if (retorno==JFileChooser.APPROVE_OPTION){
            caminho = chooser.getSelectedFile().getAbsolutePath();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!\n\nLocal: "+chooser.getSelectedFile().getAbsolutePath()+"\n ");
        }

        PdfWriter.getInstance(doc, new FileOutputStream(caminho+".pdf"));
        doc.open();
        Font f11 = new Font(Font.FontFamily.TIMES_ROMAN, 11);
        Font f10 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font f12 = new Font(Font.FontFamily.HELVETICA, 17, Font.BOLD);
        Font fnormal = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
            
        Paragraph nomeUniversidade = new Paragraph("Energy Som",f12);
            nomeUniversidade.setAlignment(Element.ALIGN_CENTER);
            nomeUniversidade.setSpacingAfter(10);
            doc.add(nomeUniversidade);
            
            Paragraph nomeRelatorio = new Paragraph("Relatório de Vendas Mercado Livre" ,f12);
            nomeRelatorio.setAlignment(Element.ALIGN_CENTER);
            nomeRelatorio.setSpacingAfter(10);
            doc.add(nomeRelatorio);
            
            Paragraph DataInicioDataFim = new Paragraph("De:"+txtDe.getText()+" - Até:"+txtAte.getText() ,f11);
            DataInicioDataFim.setAlignment(Element.ALIGN_CENTER);
            DataInicioDataFim.setSpacingAfter(10);
            doc.add(DataInicioDataFim);
            
            Paragraph DataeHora = new Paragraph(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()))+" - "+new SimpleDateFormat("hh:mm").format(new Date(System.currentTimeMillis())) ,f12);
            DataeHora.setAlignment(Element.ALIGN_LEFT);
            DataeHora.setSpacingAfter(10);
            doc.add(DataeHora);
            
            PdfPTable tabela = new PdfPTable(7);
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            PdfPCell cabecalhoNome = new PdfPCell(new Paragraph("Vendedor", f10));
            cabecalhoNome.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoNome);

            PdfPCell cabecalhoEnd = new PdfPCell(new Paragraph("Produto",f10));
            cabecalhoEnd.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoEnd);
            
            PdfPCell cabecalhoEmail = new PdfPCell(new Paragraph("Data",f10));
            cabecalhoEmail.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoEmail);
            
            PdfPCell cabecalhoCidade = new PdfPCell(new Paragraph("Horario",f10));
            cabecalhoCidade.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoCidade);
            
            PdfPCell cabecalhoTelefone = new PdfPCell(new Paragraph("Rastreio",f10));
            cabecalhoTelefone.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoTelefone);
            
            PdfPCell cabecalhoDetalhes = new PdfPCell(new Paragraph("Detalhes",f10));
            cabecalhoDetalhes.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cabecalhoDetalhes);

            tabela.setHeaderRows(1); // linha que sera repetida em todas as paginas.
            
            for (VendaMLM vendaMLM : ListaVendaML){
                Paragraph pNome = new Paragraph(vendaMLM.getIdFuncionario().getNome(), fnormal);
                pNome.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colNome = new PdfPCell(pNome);
                
                Paragraph pEnd = new Paragraph(vendaMLM.getIdProduto().getNome(), fnormal);
                pEnd.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colEnd = new PdfPCell(pEnd);
                
                Paragraph pEmail = new Paragraph(vendaMLM.getData(), fnormal);
                pEmail.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell colEmail = new PdfPCell(pEmail);
                
                Paragraph pCidade = new Paragraph(vendaMLM.getHorario(), fnormal);
                pCidade.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colCidade = new PdfPCell(pCidade);
                colCidade.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                Paragraph pTel = new Paragraph(vendaMLM.getRastreio(), fnormal);
                pTel.setAlignment(Element.ALIGN_CENTER);
                PdfPCell colTel = new PdfPCell(pTel);
                colTel.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                Paragraph pdetalhes = new Paragraph(vendaMLM.getDetalhes(), fnormal);
                pdetalhes.setAlignment(Element.ALIGN_CENTER);
                PdfPCell coldetalhes = new PdfPCell(pdetalhes);
                coldetalhes.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                tabela.addCell(colNome);
                tabela.addCell(colEnd);
                tabela.addCell(colEmail);
                tabela.addCell(colCidade);
                tabela.addCell(colTel);
                tabela.addCell(coldetalhes);
            }
            doc.add(tabela);

        doc.close();
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
        PainelNome = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtAte = new javax.swing.JFormattedTextField();
        txtDe = new javax.swing.JFormattedTextField();
        btnPuxarDados = new javax.swing.JButton();
        PainelGeral = new javax.swing.JPanel();
        RadioClientes = new javax.swing.JRadioButton();
        RadioFuncionarios = new javax.swing.JRadioButton();
        RadioProdutos = new javax.swing.JRadioButton();
        RadioVendas = new javax.swing.JRadioButton();
        RadioVendasML = new javax.swing.JRadioButton();
        PainelOpcao = new javax.swing.JPanel();
        RadioTodos = new javax.swing.JRadioButton();
        RadioApenas1 = new javax.swing.JRadioButton();
        RadioData = new javax.swing.JRadioButton();
        RadioCliente2 = new javax.swing.JRadioButton();
        btnGerarRelatorio = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        btnSair = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        Tabela.setBackground(new java.awt.Color(248, 248, 248));
        Tabela.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 210, 210)));
        Tabela.setFont(new java.awt.Font("Champagne & Limousines", 0, 12)); // NOI18N
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

        PainelNome.setBackground(new java.awt.Color(255, 255, 255));
        PainelNome.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        txtNome.setBackground(new java.awt.Color(245, 245, 245));
        txtNome.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N
        txtNome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));

        jLabel13.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel13.setText("Nome:");

        jLabel14.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel14.setText("De:");

        jLabel16.setFont(new java.awt.Font("Champagne & Limousines", 0, 15)); // NOI18N
        jLabel16.setText("Até:");

        txtAte.setBackground(new java.awt.Color(245, 245, 245));
        txtAte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        try {
            txtAte.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtAte.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N

        txtDe.setBackground(new java.awt.Color(245, 245, 245));
        txtDe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 225, 225)));
        try {
            txtDe.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDe.setFont(new java.awt.Font("Champagne & Limousines", 0, 14)); // NOI18N

        javax.swing.GroupLayout PainelNomeLayout = new javax.swing.GroupLayout(PainelNome);
        PainelNome.setLayout(PainelNomeLayout);
        PainelNomeLayout.setHorizontalGroup(
            PainelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelNomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelNomeLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelNomeLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDe, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAte, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        PainelNomeLayout.setVerticalGroup(
            PainelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelNomeLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PainelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtAte, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtDe, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnPuxarDados.setBackground(new java.awt.Color(255, 255, 255));
        btnPuxarDados.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        btnPuxarDados.setText("Puxar Dados");
        btnPuxarDados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnPuxarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPuxarDadosActionPerformed(evt);
            }
        });

        PainelGeral.setBackground(new java.awt.Color(255, 255, 255));
        PainelGeral.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Categoria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        Grupo1.add(RadioClientes);
        RadioClientes.setFont(new java.awt.Font("Champagne & Limousines", 0, 12)); // NOI18N
        RadioClientes.setText("Clientes");
        RadioClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioClientesMouseClicked(evt);
            }
        });

        Grupo1.add(RadioFuncionarios);
        RadioFuncionarios.setFont(new java.awt.Font("Champagne & Limousines", 0, 12)); // NOI18N
        RadioFuncionarios.setText("Funcionarios");
        RadioFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioFuncionariosMouseClicked(evt);
            }
        });

        Grupo1.add(RadioProdutos);
        RadioProdutos.setFont(new java.awt.Font("Champagne & Limousines", 0, 12)); // NOI18N
        RadioProdutos.setText("Produtos");
        RadioProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioProdutosMouseClicked(evt);
            }
        });

        Grupo1.add(RadioVendas);
        RadioVendas.setFont(new java.awt.Font("Champagne & Limousines", 0, 12)); // NOI18N
        RadioVendas.setText("Vendas");
        RadioVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioVendasMouseClicked(evt);
            }
        });

        Grupo1.add(RadioVendasML);
        RadioVendasML.setFont(new java.awt.Font("Champagne & Limousines", 0, 12)); // NOI18N
        RadioVendasML.setText("Vendas ML");
        RadioVendasML.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioVendasMLMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PainelGeralLayout = new javax.swing.GroupLayout(PainelGeral);
        PainelGeral.setLayout(PainelGeralLayout);
        PainelGeralLayout.setHorizontalGroup(
            PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RadioClientes)
                    .addComponent(RadioFuncionarios)
                    .addComponent(RadioProdutos)
                    .addComponent(RadioVendas)
                    .addComponent(RadioVendasML))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        PainelGeralLayout.setVerticalGroup(
            PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelGeralLayout.createSequentialGroup()
                .addComponent(RadioClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioFuncionarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioProdutos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioVendas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioVendasML)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PainelOpcao.setBackground(new java.awt.Color(255, 255, 255));
        PainelOpcao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Champagne & Limousines", 0, 14), new java.awt.Color(30, 30, 30))); // NOI18N

        Grupo2.add(RadioTodos);
        RadioTodos.setFont(new java.awt.Font("Champagne & Limousines", 0, 12)); // NOI18N
        RadioTodos.setText("Todos");
        RadioTodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioTodosMouseClicked(evt);
            }
        });

        Grupo2.add(RadioApenas1);
        RadioApenas1.setFont(new java.awt.Font("Champagne & Limousines", 0, 12)); // NOI18N
        RadioApenas1.setText("Apenas 1");
        RadioApenas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioApenas1MouseClicked(evt);
            }
        });

        Grupo2.add(RadioData);
        RadioData.setFont(new java.awt.Font("Champagne & Limousines", 0, 12)); // NOI18N
        RadioData.setText("Data");
        RadioData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioDataMouseClicked(evt);
            }
        });

        Grupo2.add(RadioCliente2);
        RadioCliente2.setFont(new java.awt.Font("Champagne & Limousines", 0, 12)); // NOI18N
        RadioCliente2.setText("Cliente");
        RadioCliente2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RadioCliente2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PainelOpcaoLayout = new javax.swing.GroupLayout(PainelOpcao);
        PainelOpcao.setLayout(PainelOpcaoLayout);
        PainelOpcaoLayout.setHorizontalGroup(
            PainelOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelOpcaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RadioTodos)
                    .addComponent(RadioApenas1)
                    .addComponent(RadioData)
                    .addComponent(RadioCliente2))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        PainelOpcaoLayout.setVerticalGroup(
            PainelOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelOpcaoLayout.createSequentialGroup()
                .addComponent(RadioTodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioApenas1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioCliente2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnGerarRelatorio.setBackground(new java.awt.Color(255, 255, 255));
        btnGerarRelatorio.setFont(new java.awt.Font("Champagne & Limousines", 0, 18)); // NOI18N
        btnGerarRelatorio.setText("Gerar Relatórios");
        btnGerarRelatorio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        btnGerarRelatorio.setEnabled(false);
        btnGerarRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarRelatorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(PainelGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PainelOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PainelNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addComponent(btnPuxarDados, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 92, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PainelGeral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PainelOpcao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PainelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPuxarDados, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Opções", jPanel4);

        jLabel15.setFont(new java.awt.Font("Champagne & Limousines", 0, 20)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Relatórios");

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
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(509, 509, 509)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 538, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSair)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
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

    private void btnPuxarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPuxarDadosActionPerformed
        btnGerarRelatorio.setEnabled(true);
        try{
        if(RadioClientes.isSelected()){
            if(RadioTodos.isSelected()){
                listaCliente = clientedao.listaTodos();
                atualizaTabelaCliente();
            }else if(RadioApenas1.isSelected()){
                listaCliente = clientedao.buscaNomeLista(txtNome.getText());
                atualizaTabelaCliente();
            }
        }
        if(RadioFuncionarios.isSelected()){
            if(RadioTodos.isSelected()){
                listaFuncionario = fundionariodao.listaTodos();
                atualizaTabelaFuncionario();
            }else if(RadioApenas1.isSelected()){
                listaFuncionario = fundionariodao.buscaNomeLista(txtNome.getText());
                atualizaTabelaFuncionario();
            }
        }
        if(RadioProdutos.isSelected()){
            if(RadioTodos.isSelected()){
                listaProduto = produtodao.listaTodos();
                atualizaTabelaProduto();
            }else if(RadioApenas1.isSelected()){
                listaProduto = produtodao.buscaNomeLista(txtNome.getText());
                atualizaTabelaProduto();
            }
        }
        if(RadioVendas.isSelected()){
            if(RadioTodos.isSelected()){
                listaVenda = vendadao.listaTodos();
                atualizaTabelaVenda();
            }else if(RadioData.isSelected()){
                listaVenda = vendadao.buscaDataLista(txtDe.getText(),txtAte.getText());
                atualizaTabelaVenda();
            }else if(RadioCliente2.isSelected()){
                listaVenda = vendadao.buscaClienteLista(txtNome.getText());
                atualizaTabelaVenda();
            }
        }
        // ainta nao fiz siahf hidsi aiodsf hsdihf 
        if(RadioVendasML.isSelected()){
            if(RadioTodos.isSelected()){
                ListaVendaML = vendaMLdao.listaTodos();
                atualizaTabelaVendaML();
            }else if(RadioData.isSelected()){
                ListaVendaML = vendaMLdao.buscaDataLista(txtDe.getText(),txtAte.getText());
                atualizaTabelaVendaML();
            }
        }
        
        }catch(Exception ex){
        }
    }//GEN-LAST:event_btnPuxarDadosActionPerformed

    private void btnGerarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarRelatorioActionPerformed

        if (RadioCliente2.isSelected() == true){
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        }
        else
        {
            String nomediretorio = null;
            String nomepasta = "Relatórios"; // Informa o nome da pasta que armazenará o relatório
            String separador = java.io.File.separator;
            try 
            {
                nomediretorio = caminho + separador + nomepasta;
                
                
                if (!new File(nomediretorio).exists()) 
                {
                    (new File(nomediretorio)).mkdir();
                }
                
                if(RadioClientes.isSelected()){
                    gerarDocumentoCliente();
                }else if(RadioFuncionarios.isSelected()){
                    gerarDocumentoFuncionario();
                }else if(RadioProdutos.isSelected()){
                    gerarDocumentoProduto();
                }else if(RadioVendas.isSelected()){
                    gerarDocumentoVenda();
                }else if (RadioVendasML.isSelected()){
                    gerarDocumentoVendaML();
                }

            } catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
      
    }//GEN-LAST:event_btnGerarRelatorioActionPerformed

    private void RadioClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioClientesMouseClicked
        RadioTodos.setEnabled(true);
        RadioApenas1.setEnabled(true);
        RadioData.setEnabled(false);
        RadioCliente2.setEnabled(false);
        Grupo2.clearSelection();
    }//GEN-LAST:event_RadioClientesMouseClicked

    private void RadioFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioFuncionariosMouseClicked
        RadioTodos.setEnabled(true);
        RadioApenas1.setEnabled(true);
        RadioData.setEnabled(false);
        RadioCliente2.setEnabled(false);
        Grupo2.clearSelection();
    }//GEN-LAST:event_RadioFuncionariosMouseClicked

    private void RadioProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioProdutosMouseClicked
        RadioTodos.setEnabled(true);
        RadioApenas1.setEnabled(true);
        RadioData.setEnabled(false);
        RadioCliente2.setEnabled(false);
        Grupo2.clearSelection();
    }//GEN-LAST:event_RadioProdutosMouseClicked

    private void RadioVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioVendasMouseClicked
        RadioTodos.setEnabled(true);
        RadioApenas1.setEnabled(false);
        RadioData.setEnabled(true);
        RadioCliente2.setEnabled(true);
        Grupo2.clearSelection();
    }//GEN-LAST:event_RadioVendasMouseClicked

    private void RadioVendasMLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioVendasMLMouseClicked
        RadioTodos.setEnabled(true);
        RadioApenas1.setEnabled(false);
        RadioData.setEnabled(true);
        RadioCliente2.setEnabled(false);
        Grupo2.clearSelection();
    }//GEN-LAST:event_RadioVendasMLMouseClicked

    private void RadioApenas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioApenas1MouseClicked
        txtNome.setEnabled(true);
        txtDe.setEnabled(false);
        txtAte.setEnabled(false);
    }//GEN-LAST:event_RadioApenas1MouseClicked

    private void RadioTodosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioTodosMouseClicked
        txtNome.setEnabled(false);
        txtDe.setEnabled(false);
        txtAte.setEnabled(false);
    }//GEN-LAST:event_RadioTodosMouseClicked

    private void RadioDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioDataMouseClicked
        txtNome.setEnabled(false);
        txtDe.setEnabled(true);
        txtAte.setEnabled(true);
    }//GEN-LAST:event_RadioDataMouseClicked

    private void RadioCliente2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RadioCliente2MouseClicked
        txtNome.setEnabled(true);
        txtDe.setEnabled(false);
        txtAte.setEnabled(false);
    }//GEN-LAST:event_RadioCliente2MouseClicked

    private void btnSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnSairMouseClicked

    private void btnSairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseEntered
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Ativos/Fechar.png")));
    }//GEN-LAST:event_btnSairMouseEntered

    private void btnSairMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseExited
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icones Inativos/Fechar.png")));
    }//GEN-LAST:event_btnSairMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Grupo1;
    private javax.swing.ButtonGroup Grupo2;
    private javax.swing.JPanel PainelGeral;
    private javax.swing.JPanel PainelNome;
    private javax.swing.JPanel PainelOpcao;
    private javax.swing.JRadioButton RadioApenas1;
    private javax.swing.JRadioButton RadioCliente2;
    private javax.swing.JRadioButton RadioClientes;
    private javax.swing.JRadioButton RadioData;
    private javax.swing.JRadioButton RadioFuncionarios;
    private javax.swing.JRadioButton RadioProdutos;
    private javax.swing.JRadioButton RadioTodos;
    private javax.swing.JRadioButton RadioVendas;
    private javax.swing.JRadioButton RadioVendasML;
    private javax.swing.JTable Tabela;
    private javax.swing.JButton btnGerarRelatorio;
    private javax.swing.JButton btnPuxarDados;
    private javax.swing.JLabel btnSair;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JFormattedTextField txtAte;
    private javax.swing.JFormattedTextField txtDe;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
