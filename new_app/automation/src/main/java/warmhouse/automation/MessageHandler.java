package warmhouse.automation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import warmhouse.dataholders.CommandMessage;
import warmhouse.dataholders.SensorValueMessage;
import warmhouse.automation.dbo.Action;
import warmhouse.automation.service.ConditionService;

import java.util.HashSet;
import java.util.Set;

@Component
public class MessageHandler {

    @Autowired
    private ConditionService conditionService;

    public Set<CommandMessage> processAutomationSensorMessage(SensorValueMessage message) {

        // обработать условия - получить связанные с датчиком условия и действия
        var cond =  conditionService.findConditionBySensorId(message.getSensorId());

        Set<Action> actionSet = new HashSet<>();
        Set<CommandMessage> messageSet = new HashSet<>();

        cond.ifPresent(value -> {
            actionSet.addAll( value.getAutomationCondition().testAndGetAllTrigerredActions());
        });

        actionSet.forEach(action -> {
            action.getCommandMessageForAction().ifPresent(messageSet::add);
        });

        return messageSet;
    }

}
