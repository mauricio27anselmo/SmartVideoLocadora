package br.com.locadora.controllers.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.locadora.business.cliente.ClienteDAO;
import br.com.locadora.business.cliente.Cliente;
import br.com.locadora.business.dependente.Dependente;
import br.com.locadora.business.dependente.DependenteBO;
import br.com.locadora.business.dependente.DependenteDAO;
import br.com.locadora.util.FacesUtil;

@ManagedBean
@ViewScoped
public class DependenteBean {
    private DependenteBO bo = new DependenteBO();

    private Dependente novoDependente = new Dependente();

    public Dependente getNovoDependente(){
        return novoDependente;
    }

    public void cadastrar(){
        bo.cadastrar(novoDependente);
    }

    public List<Dependente> listar(){
        return bo.listar();
    }
}
