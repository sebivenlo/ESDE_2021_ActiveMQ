package com.demo.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Random;

public class Consumer {

	private static String USER = "User";
	private static String TIME = "Time";
	private static String MESSAGE = "Message";


	public static void main(String[] args) {
		ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", 
				"tcp://localhost:61616");
		
		try {
			Random rand = new Random();
			int subNumber = rand.nextInt(1000);
			Connection connection = factory.createConnection();
			connection.setClientID("Consumer-" + subNumber);

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Topic topic = session.createTopic("Topic-Name");

			MessageConsumer consumer = session.createDurableSubscriber(topic, "Consumer-" + subNumber);
			consumer.setMessageListener(message -> {
				try {
					System.out.println(message.getObjectProperty(USER) + " " + message.getObjectProperty(TIME));
					System.out.println(message.getObjectProperty(MESSAGE));
				} catch (JMSException e) {
					e.printStackTrace();
				}
			});
			connection.start();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
