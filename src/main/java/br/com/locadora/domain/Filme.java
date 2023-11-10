package br.com.locadora.domain;


import br.com.locadora.enums.ClassificacaoIndicativa;
import br.com.locadora.enums.Genero;
import br.com.locadora.enums.Idioma;

import java.math.BigInteger;
import java.util.List;


public class Filme {

    private Long filmeID;

    private String titulo;

    private String descricao;

    private BigInteger anoLancamento;

    private Genero genero;

    private ClassificacaoIndicativa classificacaoIndicativa;

    private Idioma idioma;

    private List<Ator> elenco;

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
