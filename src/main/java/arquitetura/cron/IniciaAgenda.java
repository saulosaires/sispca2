package arquitetura.cron;

 
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.SchedulerException;

import arquitetura.utils.SispcaLogger;

public class IniciaAgenda implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		try {
			Agendador.finaliza();
		} catch (SchedulerException e) {
			SispcaLogger.logError(e.getCause().getMessage());
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		try {

			Agendador.inicia();
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());
		}
	}
 

}
