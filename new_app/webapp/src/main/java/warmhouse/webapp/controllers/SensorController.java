package warmhouse.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warmhouse.webapp.dbo.Sensor;
import warmhouse.webapp.repository.SensorsRepository;
import warmhouse.webapp.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v3/sensor")
class SensorController {

    @Autowired
    public SensorsRepository sensorsRepository;

    @Autowired
    public UserRepository userRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Sensor> getSensor(@PathVariable Long id){

        var useropt =  sensorsRepository.findById(id);
        return useropt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "")
    public void registerSensor(@RequestBody SensorCreateJson sensorJson){
        var sensor = new Sensor(sensorJson.name, sensorJson.type, sensorJson.location, sensorJson.sensorSerialNumber);

        if (sensorJson.userId != null) {
            var user = userRepository.findById(sensorJson.userId);
            user.ifPresent(sensor::setUser);
        }

        sensorsRepository.save(sensor);
    }

    @GetMapping(value = "/byUserId/{id}")
    public List<Sensor> getAllSensorsByUserId(@PathVariable Long id){
        return sensorsRepository.getSensorsByUserId(id);
    }

    public record SensorCreateJson(
            String name,
            String type,
            String location,
            String sensorSerialNumber,
            Long userId
    ){}

}
