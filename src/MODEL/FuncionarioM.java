package MODEL;

/**
 *
 * @author Danilo-NOTE
 */
public class FuncionarioM {

    private int Id;
    private String Nome;
    private String Cpf;
    private String Rg;
    private String Nascimento;
    private String Telefone;
    private String Celular1;
    private String Celular2;
    private String Login;
    private String Senha;

    public FuncionarioM(int Id, String Nome, String Cpf, String Rg, String Nascimento, String Telefone, String Celular1, String Celular2, String Login, String Senha) {
        this.Id = Id;
        this.Nome = Nome;
        this.Cpf = Cpf;
        this.Rg = Rg;
        this.Nascimento = Nascimento;
        this.Telefone = Telefone;
        this.Celular1 = Celular1;
        this.Celular2 = Celular2;
        this.Login = Login;
        this.Senha = Senha;
    }

    public FuncionarioM() {
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

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public String getRg() {
        return Rg;
    }

    public void setRg(String Rg) {
        this.Rg = Rg;
    }

    public String getNascimento() {
        return Nascimento;
    }

    public void setNascimento(String Nascimento) {
        this.Nascimento = Nascimento;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
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

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    
}
