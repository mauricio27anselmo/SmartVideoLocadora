package br.com.locadora.converter;

import br.com.locadora.domain.Dependente;
import br.com.locadora.service.DependenteService;
import br.com.locadora.util.FacesUtil;
import br.com.locadora.util.NegocioException;
import br.com.locadora.util.SmartLocadoraConstantes;
import org.apache.commons.lang3.StringUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean
@FacesConverter(value = "dependenteConverter")
public class DependenteConverter implements Converter {

    private DependenteService dependenteService;

    public DependenteConverter() {
        this.dependenteService = DependenteService.getInstance();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isNotBlank(value)) {
            try {
                return dependenteService.findById(Long.parseLong(value));
            } catch (NegocioException e) {
                FacesUtil.addMsgErro(SmartLocadoraConstantes.ERRO_INESPERADO);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Dependente entity = (Dependente) value;
        return (entity != null && entity.getDependenteID() != null) ? String.valueOf(entity.getDependenteID()) : null;
    }
}
