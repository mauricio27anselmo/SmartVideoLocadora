package br.com.locadora.service;

import br.com.locadora.dao.LocacaoDAO;
import br.com.locadora.domain.*;
import br.com.locadora.enums.ClassificacaoIndicativa;
import br.com.locadora.enums.StatusItem;
import br.com.locadora.interfaces.service.ILocacaoService;
import br.com.locadora.util.DAOException;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraConstantes;
import br.com.locadora.util.SmartLocadoraUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class LocacaoService extends SmartLocadoraService<Locacao> implements ILocacaoService {

    private static final Logger logger = LogManager.getLogger(LocacaoService.class);

    private static LocacaoService instance;

    private ItemService itemService;

    private LocacaoDAO filmeDAO;

    private LocacaoService() {
        itemService = ItemService.getInstance();
        filmeDAO = LocacaoDAO.getInstance();
        super.setDao(filmeDAO);
    }

    public static LocacaoService getInstance() {
        if (instance == null) {
            instance = new LocacaoService();
        }
        return instance;
    }

    @Override
    public void save(Locacao entity) throws NegocioException {
        try {
            if (!Optional.ofNullable(entity).isPresent()) {
                throw new NegocioException(SmartLocadoraConstantes.PARAMETROS_INVALIDOS);
            }
            validateRental(entity);
            entity.setDataLocacao(LocalDateTime.now());
            filmeDAO.saveNew(entity);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new NegocioException("br.com.locadora.acao.salvarfalha", ex);
        }
    }

    private void validateRental(Locacao entity) throws NegocioException {
        validateItems(entity);
        if (!Optional.ofNullable(entity.getCliente()).isPresent()) {
            throw new NegocioException("br.com.locadora.acao.locacaopessoanaoinformada");
        }
        validateAgeRange(entity);
        if (entity.getValorTotal() == null || entity.getValorTotal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegocioException("br.com.locadora.valortotalacimazeroerro");
        }
    }

    private void validateItems(Locacao entity) throws NegocioException {
        if (entity.getItens() == null || entity.getItens().isEmpty()) {
            throw new NegocioException("br.com.locadora.acao.locacaoitensvazios");
        }
    }

    private void validateAgeRange(Locacao entity) throws NegocioException {
        List<Integer> limitAges = entity.getItens().stream()
                .map(Item::getFilme)
                .filter(Objects::nonNull)
                .map(Filme::getClassificacaoIndicativa)
                .filter(Objects::nonNull)
                .map(ClassificacaoIndicativa::getLimitAge)
                .collect(Collectors.toList());
        Cliente client = entity.getCliente();
        Dependente dependent = entity.getDependente();
        Period period = Period.between(client.getDataNascimento(), LocalDate.now());
        if (Optional.ofNullable(dependent).isPresent()) {
            period = Period.between(dependent.getDataNascimento(), LocalDate.now());
        }
        int age = period.getYears();
        boolean isForbidden = limitAges.stream().anyMatch(ageItemList -> age < ageItemList);
        if (isForbidden) {
            throw new NegocioException("br.com.locadora.acao.locacaoidadenaopermitida");
        }
    }
}
