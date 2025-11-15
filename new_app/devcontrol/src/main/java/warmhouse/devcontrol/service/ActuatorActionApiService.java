package warmhouse.devcontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmhouse.dataholders.ActuatorCommandMessage;

import java.util.Date;

@Service
public class ActuatorActionApiService {

    @Autowired
    public ActuatorsService actuatorsService;

    public void doAction(ActuatorCommandMessage commandMessage) {

        var optAct = actuatorsService.findActuatorById(commandMessage.getActuatorId());

        optAct.ifPresent(
                actuator -> {

                    System.out.println("Action fired on actuator ID: " + actuator.getId());

                    // do smth with actuator API

                    actuator.setValue(commandMessage.getValue());
                    actuator.setStatus(commandMessage.getStatus());
                    actuator.setLastUpdated(new Date());

                    actuatorsService.save(actuator);
                }
        );

    }

}
