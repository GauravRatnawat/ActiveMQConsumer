package com.activMq.topicConsumer;



import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;




@Configuration
public class ReceiverConfig {


	@Value("${activemq.recevier.broker-url}")
	private String brokerUrl;
	@Value("${activemq.recevier.username}")
	private String userName;
	@Value("${activemq.recevier.password}")
	private String passWord;

	  @Bean
	    @SuppressWarnings("SpringJavaAutowiringInspection")
	    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory) {
	        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	        factory.setConnectionFactory(connectionFactory);
	        factory.setPubSubDomain(true);
	        return factory;
	    }
	@Bean
	public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory =
				new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);
//		activeMQConnectionFactory.setUserName(userName);
//		activeMQConnectionFactory.setPassword(passWord);
		activeMQConnectionFactory.setWatchTopicAdvisories(false);
		return activeMQConnectionFactory;
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory =
				new DefaultJmsListenerContainerFactory();
		factory
		.setConnectionFactory(receiverActiveMQConnectionFactory());
		factory.setConcurrency("1-2");
		return factory;
	}


	@Bean
	public Receiver receiver() {
		return new Receiver();
	}

	//  @Bean
	//  public MessageConverter jacksonJmsMessageConverter() {
	//    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	//    converter.setTargetType(MessageType.TEXT);
	//    converter.setTypeIdPropertyName("_type");
	//    return converter;
	//  }
}