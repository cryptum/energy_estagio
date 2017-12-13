package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class ItensVenda {
    
    private int Id;
    private ProdutoM IdProduto;
    private VendaM IdVenda;

    public ItensVenda(int Id, ProdutoM IdProduto, VendaM IdVenda) {
        this.Id = Id;
        this.IdProduto = IdProduto;
        this.IdVenda = IdVenda;
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
}
