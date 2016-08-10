package sp.rafael.keycloak.operadormodel.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by rafael on 5/5/16.
 */
@Entity
@Table(name="SF_OPERADOR")
@XmlRootElement
public class Operador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_OPERADOR")
    private Long id;


    @Column(name="NOME")
    private String nome;

    @Column(name="SENHA2")
    private String senha2;


    @Column(name="ID_PESSOAS")
    private Long idPessoas;

    @Column(name="ATIVO")
    private String ativo;

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public Operador(){

    }

    public Operador(String nome, String senha2, Long idPessoas, String ativo) {
        this.nome = nome;
        this.senha2 = senha2;
        this.idPessoas = idPessoas;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }

    public Long getIdPessoas() {
        return idPessoas;
    }

    public void setIdPessoas(Long idPessoas) {
        this.idPessoas = idPessoas;
    }
}
