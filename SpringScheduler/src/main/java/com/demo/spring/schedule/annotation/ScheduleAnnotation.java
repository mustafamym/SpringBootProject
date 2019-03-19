package com.demo.spring.schedule.annotation;

import com.demo.spring.schedule.utils.AppLogger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author :  Gulam Mustafa
 */
@Service
@EnableScheduling
public class ScheduleAnnotation {

    private final static AppLogger logger = AppLogger.getInstance();

    @Scheduled(fixedDelay = 5000)
    public void fixedDelayTask() throws InterruptedException {
         logger.info(new Date() + " This runs in a fixed delay run by The @Scheduled Annotation.");
    }

    @Scheduled(fixedRate = 6000)
    public void fixedRateTask() {
         logger.info(new Date() + " This runs in a fixed rate run by The @Scheduled Annotation");
    }

    @Scheduled(fixedRate = 7000, initialDelay = 2000)
    public void fixedRateWithInitialDelayTask() {
        logger.info(new Date() + " The @Scheduled Annotation in Spring .This runs in a fixed delay with a initial delay run by The @Scheduled Annotation");
    }

    @Scheduled(cron = "10 * * * * *")
    public void cronTask() {
         logger.info(new Date() + " This runs in a cron schedule run by The @Scheduled Annotation");
    }
}
