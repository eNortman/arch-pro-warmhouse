package warmhouse.automation.dbo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import warmhouse.dataholders.ActuatorCommandMessage;
import warmhouse.dataholders.CommandMessage;

import java.util.Date;
import java.util.Optional;

@Entity
@DiscriminatorValue("actuator")
public class ActuatorAction extends Action {

    @ManyToOne
    private Actuator actuator;

    private Boolean targetStatus;
    private Float targetValue;

    public Actuator getActuator() {
        return actuator;
    }

    public void setActuator(Actuator actuator) {
        this.actuator = actuator;
    }

    public Boolean getTargetStatus() {
        return targetStatus;
    }

    public void setTargetStatus(Boolean targetStatus) {
        this.targetStatus = targetStatus;
    }

    public Float getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(Float targetValue) {
        this.targetValue = targetValue;
    }

    @Override
    public Optional<CommandMessage> getCommandMessageForAction() {
        var msg = new ActuatorCommandMessage();
        var act = this.getActuator();

        if (act != null) {
            msg.setActuatorId(act.getId());
            msg.setActuatorName(act.getName());
            msg.setStatus(this.targetStatus);
            msg.setValue(this.targetValue);
            msg.setRegisteredAt(new Date());

            return Optional.of(msg);
        }

        return Optional.empty();
    }
}
