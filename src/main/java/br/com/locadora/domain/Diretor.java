package br.com.locadora.domain;

import javax.persistence.*;

@Entity
@Table(name = "smt_diretor")
public class Diretor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drt_id")
    private Long diretorID;

    @Column(name = "drt_nome", length = 50, unique = true, nullable = false)
    private String nome;

    public Long getDiretorID() {
        return diretorID;
    }

    public void setDiretorID(Long diretorID) {
        this.diretorID = diretorID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Diretor{" +
                "diretorID=" + diretorID +
                ", nome='" + nome + '\'' +
                '}';
    }
}
