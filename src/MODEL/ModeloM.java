package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class ModeloM {
    
    private int Id;
    private MarcaM IdMarca;
    private String Nome;

    public ModeloM(int Id, MarcaM IdMarca, String Nome) {
        this.Id = Id;
        this.IdMarca = IdMarca;
        this.Nome = Nome;
    }

    public ModeloM() {
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

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }
}
