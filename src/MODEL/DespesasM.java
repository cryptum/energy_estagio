package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class DespesasM {
    
    private int Id;
    private String Descricao;
    private float Valor;
    private String Data;

    public DespesasM(int Id, String Descricao, float Valor, String Data) {
        this.Id = Id;
        this.Descricao = Descricao;
        this.Valor = Valor;
        this.Data = Data;
    }

    public DespesasM() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public float getValor() {
        return Valor;
    }

    public void setValor(float Valor) {
        this.Valor = Valor;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    
}
