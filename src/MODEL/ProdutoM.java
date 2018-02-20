package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class ProdutoM {
    
    private int Id;
    private CategoriaM idCategoria;
    private MarcaM IdMarca;
    private ModeloM IdModelo;
    private String Nome;
    private float ValorCusto;
    private float ValorMax;
    private float ValorMini;
    private String Codigo;
    private int Quantidade;

    public ProdutoM(int Id, CategoriaM idCategoria, MarcaM IdMarca, ModeloM IdModelo, String Nome, float ValorCusto, float ValorMax, float ValorMini, String Codigo, int Quantidade) {
        this.Id = Id;
        this.idCategoria = idCategoria;
        this.IdMarca = IdMarca;
        this.IdModelo = IdModelo;
        this.Nome = Nome;
        this.ValorCusto = ValorCusto;
        this.ValorMax = ValorMax;
        this.ValorMini = ValorMini;
        this.Codigo = Codigo;
        this.Quantidade = Quantidade;
    }

    public ProdutoM() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public CategoriaM getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CategoriaM idCategoria) {
        this.idCategoria = idCategoria;
    }

    public MarcaM getIdMarca() {
        return IdMarca;
    }

    public void setIdMarca(MarcaM IdMarca) {
        this.IdMarca = IdMarca;
    }

    public ModeloM getIdModelo() {
        return IdModelo;
    }

    public void setIdModelo(ModeloM IdModelo) {
        this.IdModelo = IdModelo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public float getValorCusto() {
        return ValorCusto;
    }

    public void setValorCusto(float ValorCusto) {
        this.ValorCusto = ValorCusto;
    }

    public float getValorMax() {
        return ValorMax;
    }

    public void setValorMax(float ValorMax) {
        this.ValorMax = ValorMax;
    }

    public float getValorMini() {
        return ValorMini;
    }

    public void setValorMini(float ValorMini) {
        this.ValorMini = ValorMini;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    
}
