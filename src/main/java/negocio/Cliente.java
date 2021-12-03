package negocio;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Audited
@Entity
@Table(name = "cliente")

public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "nome")
    String nome;
    @Column(name = "sobrenome")
    String sobrenome;
    @Column(name = "cpf")
    String cpf;
    @Column(name = "email")
    String email;
    @Column(name = "telefone")
    String telefone;
    @Column(name = "celular")
    String celular;
    @Column(name = "excluido")
    Boolean Excluido;

    public Cliente(int id, String nome, String sobrenome, String cpf, String email, String telefone, String celular, Boolean Excluido) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.Excluido = Excluido;
    }

    public Cliente(String nome, String sobrenome, String cpf, String email, String telefone, String celular, Boolean Excluido) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.Excluido = Excluido;
    }

    public Cliente() {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Boolean getExcluido() {
        return Excluido;
    }

    public void setExcluido(Boolean Excluido) {
        this.Excluido = Excluido;
    }

}
