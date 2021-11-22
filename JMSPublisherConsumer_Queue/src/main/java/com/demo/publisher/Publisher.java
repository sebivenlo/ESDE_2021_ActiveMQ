package com.demo.publisher;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {

    public static void main(String[] args) throws JMSException {
        // Creates a connection factory of type ActiveMQ using the
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin",
                "tcp://localhost:61616");
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        try {
            connection = factory.createConnection();
            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            Destination destination = session.createQueue("demo");

            String[] messages = {"First Message", "Second Message", "Third Message",
                    "Fourth Message"};

            producer = session.createProducer(destination);

            for (String message : messages) {
                TextMessage textMessage = session.createTextMessage(message);
                producer.send(textMessage);
            }

            System.out.println("Messages were published");

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            // close resources
            producer.close();
            session.close();
            connection.close();
        }
    }

}
