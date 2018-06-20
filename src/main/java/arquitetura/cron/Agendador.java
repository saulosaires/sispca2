package arquitetura.cron;

 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import arquitetura.dao.JobDAO;
import arquitetura.model.SispcaJob;
 
@Named
public class Agendador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 894741163218892105L;
	private static Scheduler scheduler = null;
	private static List<JobDetail> listJobs = new ArrayList<>();

	 
	
	public  static void inicia(JobDAO jobDAO) throws SchedulerException, ClassNotFoundException {

		JobDetail job = null;
 
		List<SispcaJob>  listClasse = jobDAO.findAll();



		if (!listClasse.isEmpty()) {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			
			for (SispcaJob classe : listClasse) {


				Class<? extends Job> castClass = (Class<? extends Job>) Class.forName("arquitetura.cron.jobs." + classe.getNome());
				job = JobBuilder.newJob(castClass)
								.withIdentity(classe.getNome(), "grupo").build();

				Trigger trigger = TriggerBuilder.newTrigger()
											    .withIdentity(classe.getNome(), "grupo")
											    .startNow()
											    .withSchedule(CronScheduleBuilder.cronSchedule(classe.getPeriodo()))
											    //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(40))
											    .build();
				 
				scheduler.scheduleJob(job, trigger);

			}
		}
		
		
	}

	public static void finaliza() throws SchedulerException {

		for (JobDetail job : listJobs) {
			scheduler.deleteJob(job.getKey());
		}
	}

}
