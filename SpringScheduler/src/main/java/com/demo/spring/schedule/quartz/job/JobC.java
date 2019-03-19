package com.demo.spring.schedule.quartz.job;

import com.demo.spring.schedule.utils.AppLogger;
import java.util.Date;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class JobC extends QuartzJobBean {
    private final static AppLogger logger = AppLogger.getInstance();
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		        logger.info(new Date() + " Job C is runing");
		
	}

}
