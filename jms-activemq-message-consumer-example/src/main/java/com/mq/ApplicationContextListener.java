package com.mq;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mq.consumer.MessageConsumerThread;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Gulam Mustafa
 */
@WebListener
public class ApplicationContextListener implements ServletContextListener {
    
	private static final Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);	
        public void contextInitialized(ServletContextEvent servletContextEvent) {   
    	logger.info("##########StartJMS ActiveMQ Consumer application ##################");
    	  Thread t = new Thread(new MessageConsumerThread());
  	      t.start();
    }
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	logger.info("########## Stop JMS ActiveMQ Consumer application ##################");
    }
}

