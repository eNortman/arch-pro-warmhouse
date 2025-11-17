package warmhouse.webapp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warmhouse.webapp.dbo.Actuator;
import warmhouse.webapp.repository.ActuatorsRepository;
import warmhouse.webapp.repository.UserRepository;

import java.util.List;

@Tag(name = "Actuators API",
        description = "API для управления исполнительными у-вами")
@RestController
@RequestMapping("/api/v3/actuator")
class ActuatorController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ActuatorsRepository actuatorsRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Actuator> getActuator(@PathVariable Long id) {
        var optact = actuatorsRepository.findById(id);
        return optact.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/byUserId/{id}")
    public List<Actuator> getAllActuatorsByUserId(@PathVariable Long id){
        return actuatorsRepository.getActuatorsByUserId(id);
    }

    @PostMapping("")
    public void registerActuator(@RequestBody ActuatorCreateJson actuatorJson){

        var user = userRepository.findById(actuatorJson.userId).orElseThrow();

        var newActuator = new Actuator(
                user,
                actuatorJson.type,
                actuatorJson.name,
                actuatorJson.location
        );

        actuatorsRepository.save(newActuator);

    }

    public record ActuatorCreateJson(
            String name,
            String type,
            String location,
            Long userId
    ){}

}
