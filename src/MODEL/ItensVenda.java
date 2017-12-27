package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class ItensVenda {
    
    private int Id;
    private ProdutoM IdProduto;
    private VendaM IdVenda;
    private int quantidade;
    private float preco;
    private float total;

    public ItensVenda(int Id, ProdutoM IdProduto, VendaM IdVenda, int quantidade, float preco, float total) {
        this.Id = Id;
        this.IdProduto = IdProduto;
        this.IdVenda = IdVenda;
        this.quantidade = quantidade;
        this.preco = preco;
        this.total = total;
    }

    public ItensVenda() {
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

    public VendaM getIdVenda() {
        return IdVenda;
    }

    public void setIdVenda(VendaM IdVenda) {
        this.IdVenda = IdVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getPrecototalitem() {
        return total;
    }

    public void setPrecototalitem(float total) {
        this.total = total;
    }

    
}
