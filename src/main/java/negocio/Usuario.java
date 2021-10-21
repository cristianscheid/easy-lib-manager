package negocio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.envers.Audited;
import org.hibernate.envers.AuditTable;
        
@Audited
@Entity
@Table(name = "usuario")

public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "nome")
    String nome;
    @Column(name = "sobrenome")
    String sobrenome;
    @Column(name = "login")
    String login;
    @Column(name = "senha")
    String senha;
    @Column(name = "cpf")
    String cpf;
    @Column(name = "admin")
    Boolean admin;

    public Usuario(String login, String senha) {
        this.admin = true;
        this.login = login;
        this.senha = senha;
    }

    public Usuario(int id, String nome, String sobrenome, String login, String senha, String cpf, Boolean admin) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.login = login;
        this.senha = senha;
        this.cpf = cpf;
        this.admin = admin;
    }

    public Usuario(String nome, String sobrenome, String login, String senha, String cpf, Boolean admin) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.login = login;
        this.senha = senha;
        this.cpf = cpf;
        this.admin = admin;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

}
