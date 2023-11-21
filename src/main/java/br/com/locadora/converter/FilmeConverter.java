package br.com.locadora.converter;

import br.com.locadora.domain.Filme;
import br.com.locadora.service.FilmeService;
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
@FacesConverter(value = "filmeConverter")
public class FilmeConverter implements Converter {

    private FilmeService filmeService;

    public FilmeConverter() {
        this.filmeService = FilmeService.getInstance();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isNotBlank(value)) {
            try {
                return filmeService.findById(Long.parseLong(value));
            } catch (NegocioException e) {
                FacesUtil.addMsgErro(SmartLocadoraConstantes.ERRO_INESPERADO);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Filme entity = (Filme) value;
        return (entity != null && entity.getFilmeID() != null) ? String.valueOf(entity.getFilmeID()) : null;
    }
}
