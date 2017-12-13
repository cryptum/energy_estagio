package MODEL;

import java.util.Date;
/**
 *
 * @author Danilo-NOTE
 */
public class ClienteM {
    
    private int Id;
    private String Nome;
    private String Rg;
    private String Cpf;
    private String Rua;
    private String Numero;
    private String Bairro;
    private String Cidade;
    private String telefone;
    private String Celular1;
    private String Celular2;
    private String Nascimento;
    private String Observacao;

    public ClienteM(int Id, String Nome, String Rg, String Cpf, String Rua, String Numero, String Bairro, String Cidade, String telefone, String Celular1, String Celular2, String Nascimento, String Observacao) {
        this.Id = Id;
        this.Nome = Nome;
        this.Rg = Rg;
        this.Cpf = Cpf;
        this.Rua = Rua;
        this.Numero = Numero;
        this.Bairro = Bairro;
        this.Cidade = Cidade;
        this.telefone = telefone;
        this.Celular1 = Celular1;
        this.Celular2 = Celular2;
        this.Nascimento = Nascimento;
        this.Observacao = Observacao;
    }

    public ClienteM() {
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

    public String getRg() {
        return Rg;
    }

    public void setRg(String Rg) {
        this.Rg = Rg;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String Rua) {
        this.Rua = Rua;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular1() {
        return Celular1;
    }

    public void setCelular1(String Celular1) {
        this.Celular1 = Celular1;
    }

    public String getCelular2() {
        return Celular2;
    }

    public void setCelular2(String Celular2) {
        this.Celular2 = Celular2;
    }

    public String getNascimento() {
        return Nascimento;
    }

    public void setNascimento(String Nascimento) {
        this.Nascimento = Nascimento;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }

    
}
