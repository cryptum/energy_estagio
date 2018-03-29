package MODEL;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Danilo-NOTE
 */
public class VendaM {
    
    private int Id;
    private ClienteM IdCliente;
    private FuncionarioM idFuncionario;
    private String Data;
    private float TotalVendas;
    private String FormaPagamento;
    private Boolean Excluido;

    public VendaM() {
    }

    public VendaM(int Id, ClienteM IdCliente, FuncionarioM idFuncionario, String Data, float TotalVendas, String FormaPagamento, Boolean Excluido) {
        this.Id = Id;
        this.IdCliente = IdCliente;
        this.idFuncionario = idFuncionario;
        this.Data = Data;
        this.TotalVendas = TotalVendas;
        this.FormaPagamento = FormaPagamento;
        this.Excluido = Excluido;
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

    public FuncionarioM getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(FuncionarioM idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public float getTotalVendas() {
        return TotalVendas;
    }

    public void setTotalVendas(float TotalVendas) {
        this.TotalVendas = TotalVendas;
    }

    public String getFormaPagamento() {
        return FormaPagamento;
    }

    public void setFormaPagamento(String FormaPagamento) {
        this.FormaPagamento = FormaPagamento;
    }

    public Boolean getExcluido() {
        return Excluido;
    }

    public void setExcluido(Boolean Excluido) {
        this.Excluido = Excluido;
    }

    
}
