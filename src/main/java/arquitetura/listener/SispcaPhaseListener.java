package arquitetura.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import administrativo.beans.MenuMBean;
import arquitetura.utils.SessionUtils;

public class SispcaPhaseListener implements PhaseListener {
 
	 
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
     
    	
    	FacesContext facesContext = event.getFacesContext();
    	HttpServletRequest ht=(HttpServletRequest) facesContext.getExternalContext().getRequest();
    	String url=ht.getRequestURI();
    	
    	boolean hasParameters = ht.getParameterMap().isEmpty();
    	
    	MenuMBean menuMBean = (MenuMBean) SessionUtils.getBean("menuMBean");
    	
    	menuMBean.proccessUrl(url,hasParameters);	
    	
    }

    @Override
    public void afterPhase(PhaseEvent event) {
     

    	
    }

 
    
    

}
