package warmhouse.webapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warmhouse.webapp.dbo.Sensor;
import warmhouse.webapp.repository.SensorsRepository;
import warmhouse.webapp.repository.UserRepository;

import java.util.List;

@Tag(name = "Sensor API",
        description = "API для управления данными о датчиках")
@RestController
@RequestMapping("/api/v3/sensor")
class SensorController {

    @Autowired
    public SensorsRepository sensorsRepository;

    @Autowired
    public UserRepository userRepository;

    @Operation(
            summary = "Получить информацию о датчике по его ID"
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<Sensor> getSensor(@PathVariable Long id){

        var useropt =  sensorsRepository.findById(id);
        return useropt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Регистрация нового датчика"
    )
    @PostMapping(value = "")
    public void registerSensor(@RequestBody SensorCreateJson sensorJson){
        var sensor = new Sensor(sensorJson.name, sensorJson.type, sensorJson.location, sensorJson.sensorSerialNumber);

        if (sensorJson.userId != null) {
            var user = userRepository.findById(sensorJson.userId);
            user.ifPresent(sensor::setUser);
        }

        sensorsRepository.save(sensor);
    }

    @Operation(
            summary = "Получть список всех датчиков пользователя с указанным userID"
    )
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
