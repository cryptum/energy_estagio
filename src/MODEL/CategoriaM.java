package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class CategoriaM {
    
    private int Id;
    private String Nome;

    public CategoriaM(int Id, String Nome) {
        this.Id = Id;
        this.Nome = Nome;
    }
    
    public CategoriaM() {
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
