package arquitetura.converter;

import java.util.Optional;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import administrativo.model.Perfil;
import administrativo.model.TipoLink;
import administrativo.service.PerfilService;

@FacesConverter(value = "perfilConverter", forClass = Perfil.class)
public class PerfilConverter implements Converter {

	
	 PerfilService perfilService= CDI.current().select(PerfilService.class).get();
  
	
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
       
        	 Optional<Perfil> perfil= perfilService.findByDescription(string);
        	 
        	 if(perfil.isPresent())
        		 return perfil.get();
        
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