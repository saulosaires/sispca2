package arquitetura.cron;

 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.spi.CDI;

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
 
public class Agendador implements Serializable{

	private static Scheduler scheduler = null;
	private static List<JobDetail> listJobs = new ArrayList<>();

	static JobDAO jobDAO;
	public Agendador(){
		
		jobDAO = CDI.current().select(JobDAO.class).get();
	}
	
	public static void inicia() throws Exception {

		JobDetail job = null;
 
		List<SispcaJob>  listClasse = jobDAO.findAll();



		if (!listClasse.isEmpty()) {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();

			for (SispcaJob classe : listClasse) {


				Class<? extends Job> castClass = (Class<? extends Job>) Class.forName("arquitetura.cron.jobs.." + classe.getNome());
				
				job = JobBuilder.newJob(castClass)
								.withIdentity(classe.getNome(), "grupo").build();

				Trigger trigger = TriggerBuilder.newTrigger()
											    .withIdentity(classe.getNome(), "grupo")
											    .withSchedule(CronScheduleBuilder.cronSchedule(classe.getPeriodo())).build();

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
