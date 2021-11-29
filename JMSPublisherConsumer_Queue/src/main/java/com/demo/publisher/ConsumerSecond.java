package com.demo.publisher;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ConsumerSecond {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin",
                "tcp://localhost:61616");
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        try {
            connection = factory.createConnection();

            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            Destination destination = session.createQueue("demo");

            consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {

                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println(textMessage.getText());
                        textMessage.acknowledge();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            // start the connection
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
