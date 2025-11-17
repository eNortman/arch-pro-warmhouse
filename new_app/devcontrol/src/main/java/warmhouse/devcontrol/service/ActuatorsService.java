package warmhouse.devcontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmhouse.devcontrol.dbo.Actuator;
import warmhouse.devcontrol.repository.ActuatorsRepository;

import java.util.Optional;

@Service
public class ActuatorsService {

    @Autowired
    private ActuatorsRepository actuatorsRepository;

    public Optional<Actuator> findActuatorById(Long id) {
        return actuatorsRepository.findById(id);
    }

    public void save(Actuator actuator) {
        actuatorsRepository.save(actuator);
    }
}
