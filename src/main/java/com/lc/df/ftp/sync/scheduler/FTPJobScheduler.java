package com.lc.df.ftp.sync.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@EnableScheduling
@Component
public class FTPJobScheduler {
	
	 @Autowired
	    JobLauncher jobLauncher;
	    
	    @Autowired
	    Job job;
	    
	   	@Scheduled(cron = "*/10 * * * * *")
	    public void runJob() throws Exception {
	    	JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
	                .toJobParameters();
	        jobLauncher.run(job, jobParameters);
	    }

}
