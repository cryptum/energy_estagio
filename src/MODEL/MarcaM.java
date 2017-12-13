package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class MarcaM {
    
    private int Id;
    private String Nome;

    public MarcaM(int Id, String Nome) {
        this.Id = Id;
        this.Nome = Nome;
    }
    
    public MarcaM() {
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }
}
