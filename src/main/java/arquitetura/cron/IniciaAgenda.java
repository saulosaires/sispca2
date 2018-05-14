package arquitetura.cron;

 
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.SchedulerException;

import arquitetura.dao.JobDAO;
import arquitetura.utils.SispcaLogger;

@Named
public class IniciaAgenda implements ServletContextListener {

	private JobDAO jobDAO;
	
	public IniciaAgenda() {}
	
	@Inject
	public IniciaAgenda(JobDAO jobDAO){
		this.jobDAO=jobDAO;
	}
	
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

			Agendador.inicia(jobDAO);
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());
		}
	}
 

}
