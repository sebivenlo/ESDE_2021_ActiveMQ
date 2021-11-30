import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
public class Producer {

    public static void main(String[] args) throws JMSException {
        // Creates a connection factory of type ActiveMQ using the
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin",
                "tcp://localhost:61616");
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        try {
            // creates a new connection using the factory
            connection = factory.createConnection();
            // creates a new session using the connection with Client Acknowledge mode
            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            // creates a destination with the passed
            Destination destination = session.createQueue("demo-2");
            // array of strings that are used as message payloads
            String[] messages = {"First Message", "Second Message", "Third Message",
                    "Fourth Message", "Final Message"};
            // creates a producer using the previous session
            producer = session.createProducer(destination);

            for (String message : messages) {
                TextMessage textMessage = session.createTextMessage(message);
                // sends a new message
                producer.send(textMessage);
            }
            // commits the producer actions which puts the messages to the queue
            session.commit();
            System.out.println("5 Messages were published");

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
