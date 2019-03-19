# SpringScheduler

Software used in this example

•	Spring Boot 1.4.1.RELEASE

•	Java 8

•	Quartz 2.2.1

•	MySQL

•	Maven

•	Eclipse/NetBeans

•	PostMan


# @Scheduled annotation
This annotation is used for task scheduling. The trigger information needs to be provided along with this annotation. You can use the properties fixedDelay/fixedRate/cron to provide the triggering information.

# 1.	fixedRate makes Spring run the task on periodic intervals even if the last invocation may be still running.
# 2.	fixedDelay specifically controls the next execution time when the last execution finishes.
# 3.	cron is a feature originating from Unix cron utility and has various options based on your requirements.

# Example usage can be as below:

@Scheduled(fixedDelay =30000)
public void demoServiceMethod () {... }

@Scheduled(fixedRate=30000)
public void demoServiceMethod () {... }

@Scheduled(cron="0 0 * * * *")
public void demoServiceMethod () {... }



# QUARTZ SCHEDULER WITH DATABSE
Main features of quartz are as below:

Job Scheduling – Schedule a job based on day, date, year, month or cron as well. You can start repeat indefinitely jobs as well.

Job Execution – Executes Java code on trigger.

Job Persistence – Support JobStore to store jobs into database. So, even after server restart trigger will be fired.

Transactions – JTA transaction to rollback scheduling in case of failure of code.

Clustering – Multiple nodes can automatically maintain load balancing and failover

Listeners & Plug-Ins – Control job/trigger behavior

# CREATE A SIMPLE SPRING BOOT APPLICATION WITH MAVEN.

# 1.Config quartz

quartz.properties

# 2.Task scheduling using fixed delay attribute in @Scheduled annotation

Config under package 

com.scheduler.annotation

# 3.Spring Scheduling using XML

Configuration under package 

com.scheduler.xml.config;

# From command line -> mvn spring-boot:run

# from ide project->run
 

# REST API

Method:GET

http://localhost:9090/unscheduled

Method:GET

http://localhost:9090/schedule

 
Method:GET

http://localhost:9090/unscheduled
 

