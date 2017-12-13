package MODEL;

import java.util.Date;

/**
 *
 * @author Danilo-NOTE
 */
public class VendaMLM {
    
    private int Id;
    private Date Data;
    private String Rastreio;

    public VendaMLM(int Id, Date Data, String Rastreio) {
        this.Id = Id;
        this.Data = Data;
        this.Rastreio = Rastreio;
    }

    public VendaMLM() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

    public String getRastreio() {
        return Rastreio;
    }

    public void setRastreio(String Rastreio) {
        this.Rastreio = Rastreio;
    }
}
