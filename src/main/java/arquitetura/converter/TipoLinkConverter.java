package arquitetura.converter;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import administrativo.model.TipoLink;
import administrativo.service.TipoLinkService;

@FacesConverter(value = "tipoLinkConverter", forClass = TipoLink.class)
public class TipoLinkConverter implements Converter {

	
	TipoLinkService tipoLinkService= CDI.current().select(TipoLinkService.class).get();
  
	
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && !string.isEmpty()) {
            return tipoLinkService.findById(Long.parseLong(string));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null && (o instanceof TipoLink)) {
            return String.valueOf(((TipoLink) o).getId());
        }

        return null;
    }
 
    
    
    

}