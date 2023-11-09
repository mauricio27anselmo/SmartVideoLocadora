package br.com.locadora.converter;

import br.com.locadora.domain.Cliente;
import br.com.locadora.service.ClienteService;
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
@FacesConverter(value = "clienteConverter")
public class ClienteConverter implements Converter {

    private ClienteService clienteService;

    public ClienteConverter() {
        this.clienteService = ClienteService.getInstance();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isNotBlank(value)) {
            try {
                return clienteService.findById(Long.parseLong(value));
            } catch (NegocioException e) {
                FacesUtil.addMsgErro(SmartLocadoraConstantes.ERRO_INESPERADO);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Cliente entity = (Cliente) value;
        return (entity != null && entity.getClienteID() != null) ? String.valueOf(entity.getClienteID()) : null;
    }
}
