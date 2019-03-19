package com.test;

/**
 *
 * @author Gulam Mustafa
 */
public class SendTestSms {

    public static void main(String args[]) {
        SmsMQProducer message = new SmsMQProducer();
        message.setSmsReceiverNumber("0173186");
        message.setMessageText("this is test message");
        MessageProducerSingletonConn.createTxtMessageQueue("sms", message);
        System.out.println("######Message Send to this number ::#####" + message.getSmsReceiverNumber());
        System.exit(0);
    }

}
