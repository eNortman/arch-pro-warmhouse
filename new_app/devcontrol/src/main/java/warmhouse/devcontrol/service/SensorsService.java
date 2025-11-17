package warmhouse.devcontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmhouse.devcontrol.dbo.Sensor;
import warmhouse.devcontrol.repository.SensorsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SensorsService {

    @Autowired
    private SensorsRepository sensorsRepository;

    public Optional<Sensor> findSensorById(Long sensorId) {
        return sensorsRepository.findById(sensorId);
    }

    public List<Sensor> findAllSensorsByType(String type) {
        return sensorsRepository.findAllByType(type);
    }

    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
    }
}
