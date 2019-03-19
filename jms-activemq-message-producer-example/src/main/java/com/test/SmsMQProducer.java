package com.test;

import java.io.Serializable;

/**
 *
 * @author Gulam Mustafa
 */
public class SmsMQProducer implements Serializable {

    private static final long serialVersionUID = 1L;

   private String smsReceiverNumber;
  private String messageText;

    public SmsMQProducer() {

    }

    public String getSmsReceiverNumber() {
        return smsReceiverNumber;
    }

    public void setSmsReceiverNumber(String smsReceiverNumber) {
        this.smsReceiverNumber = smsReceiverNumber;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }


}
