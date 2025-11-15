package warmhouse.automation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmhouse.automation.dbo.Actuator;
import warmhouse.automation.dbo.Sensor;
import warmhouse.automation.repository.ActuatorsRepository;
import warmhouse.automation.repository.SensorsRepository;

import java.util.Optional;

@Service
public class ActuatorsService {

    @Autowired
    private ActuatorsRepository actuatorsRepository;

    public Optional<Actuator> findActuatorById(Long id) {
        return actuatorsRepository.findById(id);
    }

    public void  save(Actuator actuator) {
        actuatorsRepository.save(actuator);
    }
}
