package com.test;



import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import com.google.gson.Gson;
/**
 *
 * @author Gulam Mustafa
 */
public class MessageProducerSingletonConn {

    private static MessageProducerSingletonConn singleInstance;
    public ConnectionFactory connectionFactory;
    public Connection connection;

    private MessageProducerSingletonConn() {
        try {
            // Create a ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

//           		 ActiveMQConnection.DEFAULT_BROKER_URL);
            // Create a Connection
            connection = connectionFactory.createConnection();

            connection.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MessageProducerSingletonConn getInstance() {
        if (singleInstance == null) {
            synchronized (MessageProducerSingletonConn.class) {
                if (singleInstance == null) {
                    singleInstance = new MessageProducerSingletonConn();
                }
            }
        }

        return singleInstance;
    }
    // send Message one to one

    public static boolean createTxtMessageQueue(String queueName, SmsMQProducer messageDTO) {
        // Create a Session
        boolean isMsgSendToMQ = true;
        Session session;
        Destination destination;
        MessageProducer producer;
        TextMessage message;
        try {
            session = getInstance().connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination
            destination = session.createQueue(queueName);

            // Create a MessageProducer from the Session to the Queue
            producer = session.createProducer(destination);

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            Gson gson = new Gson();
            String jsonInString = gson.toJson(messageDTO);
            message = session.createTextMessage(jsonInString);
            producer.send(message);
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            isMsgSendToMQ = false;
            return isMsgSendToMQ;

        }

        return isMsgSendToMQ;
    }

}
