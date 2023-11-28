package br.com.locadora.domain;

import br.com.locadora.enums.Idioma;
import br.com.locadora.enums.converter.IdiomaConverter;

import javax.persistence.*;

@Entity
@Table(name = "smt_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long usuarioID;

    @Column(name = "usr_nome", nullable = false)
    private String nome;

    @Column(name = "usr_senha", nullable = false)
    private String senha;

    @Column(name = "usr_email", unique = true, nullable = false)
    private String email;

    @Convert(converter = IdiomaConverter.class)
    @Column(name = "usr_idioma", nullable = false)
    private Idioma idioma;

    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuarioID=" + usuarioID +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", idioma=" + idioma +
                '}';
    }
}
