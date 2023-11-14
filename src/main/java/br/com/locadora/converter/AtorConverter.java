package br.com.locadora.converter;

import br.com.locadora.domain.Ator;
import br.com.locadora.service.AtorService;
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
@FacesConverter(value = "atorConverter")
public class AtorConverter implements Converter {

    private AtorService atorService;

    public AtorConverter() {
        this.atorService = AtorService.getInstance();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isNotBlank(value)) {
            try {
                return atorService.findById(Long.parseLong(value));
            } catch (NegocioException e) {
                FacesUtil.addMsgErro(SmartLocadoraConstantes.ERRO_INESPERADO);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Ator entity = (Ator) value;
        return (entity != null && entity.getAtorID() != null) ? String.valueOf(entity.getAtorID()) : null;
    }
}
