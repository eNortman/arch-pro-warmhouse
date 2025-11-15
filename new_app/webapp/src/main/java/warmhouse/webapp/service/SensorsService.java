package warmhouse.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmhouse.webapp.dbo.Sensor;
import warmhouse.webapp.repository.SensorsRepository;

import java.util.Optional;

@Service
public class SensorsService {

    @Autowired
    private SensorsRepository sensorsRepository;

    public Optional<Sensor> findSensorById(Long sensorId) {
        return sensorsRepository.findById(sensorId);
    }

    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
    }
}
