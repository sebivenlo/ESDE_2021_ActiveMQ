package com.demo.topic;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Publisher {

    // Message property keys
    private static String USER = "User";
    private static String TIME = "Time";
    private static String MESSAGE = "Message";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin",
                "tcp://localhost:61616");
        Connection connection = null;
        Session session = null;
        MessageProducer producer= null;
        try {
            connection = factory.createConnection();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic("Topic-Name");

            producer = session.createProducer(destination);

            Message message = session.createMessage();
            message.setObjectProperty(TIME, returnCurrentLocalDateTimeAsString());
            message.setObjectProperty(USER, "Test-User");
            message.setObjectProperty(MESSAGE, "Hello");

            producer.send(message);

            System.out.println("Message published to topic");

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            // close resources
            producer.close();
            session.close();
            connection.close();
        }
    }

    private static String returnCurrentLocalDateTimeAsString () {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String timeSent = dateTime.format(format);
        return timeSent;
    }

}
