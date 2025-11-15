package warmhouse.devcontrol;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import warmhouse.dataholders.ActuatorCommandMessage;
import warmhouse.dataholders.CommandMessage;
import warmhouse.dataholders.SensorValueMessage;
import warmhouse.devcontrol.service.ActuatorActionApiService;

@Component
public class MqClient {

    private final String TOPIC_FROM_SENSOR = "sensor_be";
    private final String TOPIC_TO_ACTUATOR = "be_actuator";

    private final JmsTemplate jmsTopicTemplate;

    @Autowired
    public ActuatorActionApiService actuatorActionApiService;

    @JmsListener(destination = TOPIC_TO_ACTUATOR, containerFactory = "jmsTopicContainerFactory")
    public void onMessage(CommandMessage message) throws JsonProcessingException {
        System.out.println("Received message from topic: " + message);

        //todo: switch or smth to different actions API
        actuatorActionApiService.doAction((ActuatorCommandMessage) message);
    }



    public void sendMessage(SensorValueMessage message) {
        System.out.println("Send message to topic: " + message);
        jmsTopicTemplate.convertAndSend(TOPIC_FROM_SENSOR, message);
    }

    @Autowired
    public MqClient(@Qualifier("jmsTopicTemplate") JmsTemplate jmsTemplate
    ) {
        this.jmsTopicTemplate = jmsTemplate;
    }

}
