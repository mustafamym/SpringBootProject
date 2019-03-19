package com.demo.spring.schedule.xml.configuration;

import com.demo.spring.schedule.utils.AppLogger;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * @author : md gulam mustafa
 *         Date     : 2/9/15
 *         Time     : 8:03 PM
 */
@Component("xmlScheduledTasks")
public class ScheduleEnabledViaXML {

    private final static AppLogger logger = AppLogger.getInstance();

    public void xmlFixedDelayTask() {
        logger.info(new Date() + " This task runs in fixed delay by xml configuration");
    }

    public void xmlFixedRateTask() {
        logger.info(new Date() + " This task runs in fixed rate by xml configuration");
    }

    public void xmlCronTask() {
        logger.info(new Date() + " This task runs in a cron schedule by xml configuration");
    }
}
