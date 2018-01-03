package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class ItensVenda {
    
    private int Id;
    private VendaM IdVenda;
    private ProdutoM IdProduto;
    private int quantidade;
    private float preco;
    private float total;

    public ItensVenda(int Id, VendaM IdVenda, ProdutoM IdProduto, int quantidade, float preco, float total) {
        this.Id = Id;
        this.IdVenda = IdVenda;
        this.IdProduto = IdProduto;
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

    public VendaM getIdVenda() {
        return IdVenda;
    }

    public void setIdVenda(VendaM IdVenda) {
        this.IdVenda = IdVenda;
    }

    public ProdutoM getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(ProdutoM IdProduto) {
        this.IdProduto = IdProduto;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }    
}
