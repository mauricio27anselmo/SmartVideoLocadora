package br.com.locadora.domain;


import br.com.locadora.enums.ClassificacaoIndicativa;
import br.com.locadora.enums.Genero;
import br.com.locadora.enums.Idioma;
import br.com.locadora.enums.converter.ClassificacaoIndicativaConverter;
import br.com.locadora.enums.converter.GeneroConverter;
import br.com.locadora.enums.converter.IdiomaConverter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "smt_filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flm_id")
    private Long filmeID;

    @Column(name = "flm_titulo", length = 50, nullable = false)
    private String titulo;

    @Column(name = "flm_descricao", length = 500)
    private String descricao;

    @Column(name = "flm_ano_lancamento", nullable = false)
    private BigInteger anoLancamento;

    @Convert(converter = GeneroConverter.class)
    @Column(name = "flm_genero", nullable = false)
    private Genero genero;

    @Convert(converter = ClassificacaoIndicativaConverter.class)
    @Column(name = "flm_classificacao_indicativa", nullable = false)
    private ClassificacaoIndicativa classificacaoIndicativa;

    @Convert(converter = IdiomaConverter.class)
    @Column(name = "flm_idioma", nullable = false)
    private Idioma idioma;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "smt_filme_ator",
            joinColumns = { @JoinColumn(name = "flm_id") },
            inverseJoinColumns = { @JoinColumn(name = "atr_id") }
    )
    private List<Ator> elenco;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "smt_filme_diretor",
            joinColumns = { @JoinColumn(name = "flm_id") },
            inverseJoinColumns = { @JoinColumn(name = "drt_id") }
    )
    private List<Diretor> direcao;

    public Long getFilmeID() {
        return filmeID;
    }

    public void setFilmeID(Long filmeID) {
        this.filmeID = filmeID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigInteger getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(BigInteger anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public ClassificacaoIndicativa getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(ClassificacaoIndicativa classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public List<Ator> getElenco() {
        return elenco;
    }

    public void setElenco(List<Ator> elenco) {
        this.elenco = elenco;
    }

    public List<Diretor> getDirecao() {
        return direcao;
    }

    public void setDirecao(List<Diretor> direcao) {
        this.direcao = direcao;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "filmeID=" + filmeID +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", anoLancamento=" + anoLancamento +
                ", genero=" + genero +
                ", faixaEtaria=" + classificacaoIndicativa +
                ", idioma=" + idioma +
                ", elenco=" + elenco +
                ", direcao=" + direcao +
                '}';
    }
}
