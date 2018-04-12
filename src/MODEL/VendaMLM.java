package MODEL;

import java.util.Date;

/**
 *
 * @author Danilo-NOTE
 */
public class VendaMLM {
    
    private int Id;
    private FuncionarioM IdFuncionario;
    private ProdutoM IdProduto;
    private float TotalVenda;
    private String Data;
    private String Horario;
    private String Rastreio;
    private String Detalhes;
    private Boolean Excluido;

    public VendaMLM() {
    }

    public VendaMLM(int Id, FuncionarioM IdFuncionario, ProdutoM IdProduto, float TotalVenda, String Data, String Horario, String Rastreio, String Detalhes, Boolean Excluido) {
        this.Id = Id;
        this.IdFuncionario = IdFuncionario;
        this.IdProduto = IdProduto;
        this.TotalVenda = TotalVenda;
        this.Data = Data;
        this.Horario = Horario;
        this.Rastreio = Rastreio;
        this.Detalhes = Detalhes;
        this.Excluido = Excluido;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public FuncionarioM getIdFuncionario() {
        return IdFuncionario;
    }

    public void setIdFuncionario(FuncionarioM IdFuncionario) {
        this.IdFuncionario = IdFuncionario;
    }

    public ProdutoM getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(ProdutoM IdProduto) {
        this.IdProduto = IdProduto;
    }

    public float getTotalVenda() {
        return TotalVenda;
    }

    public void setTotalVenda(float TotalVenda) {
        this.TotalVenda = TotalVenda;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String Horario) {
        this.Horario = Horario;
    }

    public String getRastreio() {
        return Rastreio;
    }

    public void setRastreio(String Rastreio) {
        this.Rastreio = Rastreio;
    }

    public String getDetalhes() {
        return Detalhes;
    }

    public void setDetalhes(String Detalhes) {
        this.Detalhes = Detalhes;
    }

    public Boolean getExcluido() {
        return Excluido;
    }

    public void setExcluido(Boolean Excluido) {
        this.Excluido = Excluido;
    }
    
    
    
}
