package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class ItensVendaML {
    
    private int Id;
    private ProdutoM IdProduto;
    private VendaMLM IdVendaMl;

    public ItensVendaML(int Id, ProdutoM IdProduto, VendaMLM IdVendaMl) {
        this.Id = Id;
        this.IdProduto = IdProduto;
        this.IdVendaMl = IdVendaMl;
    }

    public ItensVendaML() {
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

    public VendaMLM getIdVendaMl() {
        return IdVendaMl;
    }

    public void setIdVendaMl(VendaMLM IdVendaMl) {
        this.IdVendaMl = IdVendaMl;
    }
}
