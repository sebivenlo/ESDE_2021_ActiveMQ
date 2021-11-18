package com.demo.publisher;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {

	public static void main(String[] args) {
		// Creates a connection factory of type ActiveMQ using the
		ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", 
				"tcp://localhost:61616");
		
		try {
			Connection connection = factory.createConnection();
			Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			Destination destination = session.createQueue("demo");
			
			String[] messages = {"First Message", "Second Message", "Third Message",
					"Fourth Message"};

			MessageProducer producer = session.createProducer(destination);
			
			for (String message : messages) {
				TextMessage textMessage = session.createTextMessage(message);
				producer.send(textMessage);
			}
			
			System.out.println("Messages were published");
			// close jms resources
			producer.close();
			session.close();
			connection.close();

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
