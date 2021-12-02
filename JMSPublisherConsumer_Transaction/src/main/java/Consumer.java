import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin",
                "tcp://localhost:61616");
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;

        try {
            connection = factory.createConnection();
            // Create a session with transaction
            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            Destination destination = session.createQueue("demo-2");

            consumer = session.createConsumer(destination);
            // start the connection
            connection.start();
            // receive the message from the consumer without a listener
            while (true) {
                TextMessage message = (TextMessage) consumer.receive();
                System.out.println(message.getText());
                if (message.getText().contains("Final")) {
                    // acknowledge all
                    session.commit();
                    System.out.println("5 Messages were acknowledged");
                    break;
                }
            }
            // close
            consumer.close();
            session.close();
            connection.close();


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
