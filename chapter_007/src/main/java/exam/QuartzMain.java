package exam;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;
import java.util.Properties;

public class QuartzMain {
    public static void main(String[] args) {
        String propertiesFile = "app.properties";
        try (InputStream in = QuartzMain.class.getClassLoader().getResourceAsStream(propertiesFile)) {
            Properties pr = new Properties();
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();

            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger")
                    .withSchedule(CronScheduleBuilder.cronSchedule(pr.getProperty("cron.time")))
                    .forJob("Job", "group1")
                    .build();

            JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).build();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
