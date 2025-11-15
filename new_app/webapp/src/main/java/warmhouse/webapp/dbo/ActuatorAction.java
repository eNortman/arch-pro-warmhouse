package warmhouse.webapp.dbo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("actuator")
public class ActuatorAction extends Action {

    @ManyToOne
    private Actuator actuator;

    private Boolean targetStatus = false;
    private Float targetValue = 0f;

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

    public ActuatorAction() {}

    public ActuatorAction(Actuator actuator, Boolean targetStatus, Float targetValue) {
        this.actuator = actuator;
        this.targetStatus = targetStatus;
        this.targetValue = targetValue;
    }
}
