package MODEL;

import java.util.Date;
/**
 *
 * @author Danilo-NOTE
 */
public class VendaM {
    
    private int Id;
    private ClienteM IdCliente;
    private Date Data;
    private Double TotalVendas;
    private String FormaPagamento;

    public VendaM(int Id, ClienteM IdCliente, Date Data, Double TotalVendas, String FormaPagamento) {
        this.Id = Id;
        this.IdCliente = IdCliente;
        this.Data = Data;
        this.TotalVendas = TotalVendas;
        this.FormaPagamento = FormaPagamento;
    }

    public VendaM() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public ClienteM getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(ClienteM IdCliente) {
        this.IdCliente = IdCliente;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

    public Double getTotalVendas() {
        return TotalVendas;
    }

    public void setTotalVendas(Double TotalVendas) {
        this.TotalVendas = TotalVendas;
    }

    public String getFormaPagamento() {
        return FormaPagamento;
    }

    public void setFormaPagamento(String FormaPagamento) {
        this.FormaPagamento = FormaPagamento;
    }
}
