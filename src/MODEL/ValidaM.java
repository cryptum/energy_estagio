package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class ValidaM {
    
    private int Id;
    private int Mes;
    private int Ano;

    public ValidaM(int Id, int Mes, int Ano) {
        this.Id = Id;
        this.Mes = Mes;
        this.Ano = Ano;
    }

    public ValidaM() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getMes() {
        return Mes;
    }

    public void setMes(int Mes) {
        this.Mes = Mes;
    }

    public int getAno() {
        return Ano;
    }

    public void setAno(int Ano) {
        this.Ano = Ano;
    }
    
    
}
