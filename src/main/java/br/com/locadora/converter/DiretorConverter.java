package br.com.locadora.converter;

import br.com.locadora.domain.Diretor;
import br.com.locadora.service.DiretorService;
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
@FacesConverter(value = "diretorConverter")
public class DiretorConverter implements Converter {

    private DiretorService diretorService;

    public DiretorConverter() {
        this.diretorService = DiretorService.getInstance();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isNotBlank(value)) {
            try {
                return diretorService.findById(Long.parseLong(value));
            } catch (NegocioException e) {
                FacesUtil.addMsgErro(SmartLocadoraConstantes.ERRO_INESPERADO);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Diretor entity = (Diretor) value;
        return (entity != null && entity.getDiretorID() != null) ? String.valueOf(entity.getDiretorID()) : null;
    }
}
