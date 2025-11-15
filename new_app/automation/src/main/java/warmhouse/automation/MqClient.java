package warmhouse.automation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import warmhouse.dataholders.CommandMessage;
import warmhouse.dataholders.SensorValueMessage;

@Component
class MqClient {

    private final String TOPIC_FROM_SENSOR = "sensor_be";
    private final String TOPIC_TO_ACTUATOR = "be_actuator";

    private final MessageHandler messageHandler;
    private final JmsTemplate jmsTopicTemplate;

    @JmsListener(destination = TOPIC_FROM_SENSOR, containerFactory = "jmsTopicContainerFactory")
    public void onMessage(SensorValueMessage message) {
        System.out.println("Received message from topic: " + message);
        messageHandler.processAutomationSensorMessage(message).forEach(this::sendMessage);
    }

    public void sendMessage(CommandMessage message) {
        System.out.println("Send message to topic: " + message);
        jmsTopicTemplate.convertAndSend(TOPIC_TO_ACTUATOR, message);
    }

    @Autowired
    public MqClient(
            MessageHandler messageHandler, @Qualifier("jmsTopicTemplate") JmsTemplate jmsTemplate
    ) {
        this.messageHandler = messageHandler;
        this.jmsTopicTemplate = jmsTemplate;
    }

}
