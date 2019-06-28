package exam;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job {
    private String properties;

    public QuartzJob(String properties) {
        this.properties = properties;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        new SqlRuParser().init(this.properties);
    }
}
