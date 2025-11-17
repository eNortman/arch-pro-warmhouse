package warmhouse.webapp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warmhouse.webapp.dbo.ActuatorAction;
import warmhouse.webapp.dbo.AutomationCondition;
import warmhouse.webapp.dbo.ThresholdCondition;
import warmhouse.webapp.repository.*;

import java.util.List;

@Tag(name = "Actuators API",
        description = "API для регистрации сценариев автоматизации (реализован частично)")
@RestController
@RequestMapping("/api/v3/automation")
class AutomationScriptsController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ConditionRepository conditionRepository;

    @Autowired
    public ActionRepository actionRepository;

    @Autowired
    public AutomationConditionRepository automationConditionRepository;

    @Autowired
    public SensorsRepository sensorsRepository;

    @Autowired
    public ActuatorsRepository actuatorsRepository;

    @GetMapping("/{id}")
    public ResponseEntity<AutomationCondition> getAutomation(@PathVariable Long id) {
        var optact = automationConditionRepository.findById(id);
        return optact.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/byUserId/{id}")
    public List<AutomationCondition> getAllActuatorsByUserId(@PathVariable Long id) {
        return automationConditionRepository.getActuatorsByUserId(id);
    }

    @PostMapping("")
    public void registerAutomationCondition(@RequestBody AutomationConditionCreateJson automationConditionCreateJson) {
        var user = userRepository.findById(automationConditionCreateJson.userId).orElseThrow();
        var newAutomationCondition = new AutomationCondition(user, automationConditionCreateJson.name);

        automationConditionRepository.save(newAutomationCondition);
    }

    public record AutomationConditionCreateJson(
            String name,
            Long userId
    ){}

    @PostMapping("/{id}/addCondition")
    public void addCondition(@PathVariable Long id, @RequestBody ThresholdConditionCreateJson thresholdJson){
        var authScript = automationConditionRepository.findById(id).orElseThrow();
        var user = userRepository.findById(thresholdJson.userId).orElseThrow();
        var sensor = sensorsRepository.findById(thresholdJson.sensorId).orElseThrow();

        if (authScript.getUser().equals(user) && sensor.getUser().equals(user)) {

            var thCondition = new ThresholdCondition(user, sensor, thresholdJson.threshold, thresholdJson.isAbove);
            thCondition.setAutomationCondition(authScript);
            conditionRepository.save(thCondition);

        }
    }

    public record ThresholdConditionCreateJson(Long userId, Long sensorId, Float threshold, boolean isAbove){}

    @PostMapping("/{id}/addAction")
    public void addAction(@PathVariable Long id, @RequestBody ActionCreateJson actionCreateJson){
        var authScript = automationConditionRepository.findById(id).orElseThrow();
        var user = userRepository.findById(actionCreateJson.userId).orElseThrow();
        var actuator = actuatorsRepository.findById(actionCreateJson.actuatorId).orElseThrow();

        if (authScript.getUser().equals(user) && actuator.getUser().equals(user)) {

            var acAction = new ActuatorAction(actuator, actionCreateJson.targetStatus, actionCreateJson.threshold);
            acAction.setAutomationCondition(authScript);
            actionRepository.save(acAction);

        }
    }

    public  record ActionCreateJson(Long userId, Long actuatorId, Float threshold, boolean targetStatus){}

}
