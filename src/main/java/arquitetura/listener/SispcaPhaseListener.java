package arquitetura.listener;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import administrativo.beans.MenuMBean;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import arquitetura.utils.ViewMap;

@Named
public class SispcaPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7835397915767314767L;

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
		
		try {
			
			ExternalContext context = event.getFacesContext().getExternalContext();

			HttpServletResponse res = (HttpServletResponse) context.getResponse();

			String uri = ((HttpServletRequest) (context.getRequest())).getRequestURI();

			if(ViewMap.isPublic(uri))return;
			

			boolean userLoggedIn = SessionUtils.get("user") != null ? true : false;

			if (!userLoggedIn) {

				res.sendRedirect(ViewMap.LOGIN_PAGE);

			} else if (!uri.equals(ViewMap.ERROR_PAGE) ) {

				String nameView = ViewMap.get(uri);

				String permissao = (String) SessionUtils.get(nameView);

				if (Utils.emptyParam(permissao)) {

					SessionUtils.put("code", nameView);
					SessionUtils.put("type", "Você não tem permissão para essa url: " + uri);
					SessionUtils.put("message", "");

					res.sendRedirect(ViewMap.ERROR_PAGE);

				}

			}
			
			
			MenuMBean menuMBean = (MenuMBean) SessionUtils.getBean("menuMBean");
			menuMBean.proccessUrl(uri);

		} catch (IOException e) {
			SispcaLogger.logError(e.getLocalizedMessage());
			
		}

	}

	@Override
	public void afterPhase(PhaseEvent event) {

		// afterPhase

	}

}
