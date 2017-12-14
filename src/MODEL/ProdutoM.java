package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class ProdutoM {
    
    private int Id;
    private MarcaM IdMarca;
    private ModeloM IdModelo;
    private String Nome;
    private Double ValorCusto;
    private Double ValorMax;
    private Double ValorMini;
    private String Codigo;

    public ProdutoM(int Id, MarcaM IdMarca, ModeloM IdModelo, String Nome, Double ValorCusto, Double ValorMax, Double ValorMini, String Codigo) {
        this.Id = Id;
        this.IdMarca = IdMarca;
        this.IdModelo = IdModelo;
        this.Nome = Nome;
        this.ValorCusto = ValorCusto;
        this.ValorMax = ValorMax;
        this.ValorMini = ValorMini;
        this.Codigo = Codigo;
    }

    public ProdutoM() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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

    public Double getValorCusto() {
        return ValorCusto;
    }

    public void setValorCusto(Double ValorCusto) {
        this.ValorCusto = ValorCusto;
    }

    public Double getValorMax() {
        return ValorMax;
    }

    public void setValorMax(Double ValorMax) {
        this.ValorMax = ValorMax;
    }

    public Double getValorMini() {
        return ValorMini;
    }

    public void setValorMini(Double ValorMini) {
        this.ValorMini = ValorMini;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    
}
