package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class ItensVendaML {
    
    private int Id;
    private VendaMLM IdVendaMl;
    private ProdutoM IdProduto;

    public ItensVendaML(int Id, VendaMLM IdVendaMl, ProdutoM IdProduto) {
        this.Id = Id;
        this.IdVendaMl = IdVendaMl;
        this.IdProduto = IdProduto;
    }

    public ItensVendaML() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public VendaMLM getIdVendaMl() {
        return IdVendaMl;
    }

    public void setIdVendaMl(VendaMLM IdVendaMl) {
        this.IdVendaMl = IdVendaMl;
    }

    public ProdutoM getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(ProdutoM IdProduto) {
        this.IdProduto = IdProduto;
    }
}
