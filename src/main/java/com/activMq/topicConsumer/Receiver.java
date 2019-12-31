package com.activMq.topicConsumer;

import java.nio.charset.Charset;

import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;



public class Receiver  {

	/*
	 * @Autowired private ReceiverService receiverService;
	 */
	private static final Logger LOGGER = LogManager.getLogger(Receiver.class);
	@JmsListener(destination = "unlimit/devices",containerFactory ="topicListenerFactory" )
	public void receiveMessage(@Payload final  Message consumerMessage) throws JMSException {
		LOGGER.info("Into receiveMessage Method"+ consumerMessage);
		
		   ActiveMQBytesMessage messageData = (ActiveMQBytesMessage)consumerMessage;
		   String jsonMessage =new String(messageData.getContent().getData(),
		  Charset.forName("UTF-8")); //
		  LOGGER.info("Into receiveMessage Method : "+jsonMessage); //
//		  receiverService.receviceAllData(consumerMessage);
		 
	}

}