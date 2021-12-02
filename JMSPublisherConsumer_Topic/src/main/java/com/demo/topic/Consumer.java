package com.demo.topic;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Random;

public class Consumer {

    private static String USER = "User";
    private static String TIME = "Time";
    private static String MESSAGE = "Message";


    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin",
                "tcp://localhost:61616");
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        try {
            Random rand = new Random();
            int subNumber = rand.nextInt(1000);

            connection = factory.createConnection();
            connection.setClientID("Consumer-" + subNumber);

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic("Topic-Name");

            consumer = session.createDurableSubscriber(topic, "Consumer-" + subNumber);
            consumer.setMessageListener(message -> {
                try {
                    System.out.println(message.getObjectProperty(USER) + " " + message.getObjectProperty(TIME));
                    System.out.println(message.getObjectProperty(MESSAGE));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });
            // star the connection
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
