package service.broker_info;

import java.util.List;

/**
 * Interface which is used to retrieve information from the ActiveMQ Broker (or any other JMS compatible MQ broker)
 */
public interface MQBrokerInfoRetriever {

    List<String> getTopics();
}
