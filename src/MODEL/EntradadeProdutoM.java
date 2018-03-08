package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class EntradadeProdutoM {
    
    private int Id;
    private ProdutoM IdProduto;
    private String Data;
    private String Hora;
    private int Quantidade;

    public EntradadeProdutoM(int Id, ProdutoM IdProduto, String Data, String Hora, int Quantidade) {
        this.Id = Id;
        this.IdProduto = IdProduto;
        this.Data = Data;
        this.Hora = Hora;
        this.Quantidade = Quantidade;
    }

    public EntradadeProdutoM() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public ProdutoM getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(ProdutoM IdProduto) {
        this.IdProduto = IdProduto;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    } 
    
}
