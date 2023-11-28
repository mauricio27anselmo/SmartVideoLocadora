package br.com.locadora.interfaces.service;

import br.com.locadora.domain.Locacao;
import br.com.locadora.filter.PageableFilter;
import br.com.locadora.util.NegocioException;

import java.util.List;

public interface ILocacaoService {

    Locacao findById(Long id) throws NegocioException;

    void insert(Locacao entity) throws NegocioException;

    void update(Locacao entity) throws NegocioException;

    void delete(Locacao entity) throws NegocioException;

    List<Locacao> load(PageableFilter filter) throws NegocioException;

    int count(PageableFilter filter) throws NegocioException;

    void processReturn(Locacao entity) throws NegocioException;
}
