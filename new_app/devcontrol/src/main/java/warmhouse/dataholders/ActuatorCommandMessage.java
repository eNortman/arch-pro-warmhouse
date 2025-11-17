package warmhouse.dataholders;

import java.io.Serializable;
import java.util.Date;

public class ActuatorCommandMessage extends CommandMessage implements Serializable {

    private Long actuatorId;
    private String actuatorName;
    private Float value;
    private Boolean status;
    private Date registeredAt;

    public Long getActuatorId() {
        return actuatorId;
    }

    public void setActuatorId(Long actuatorId) {
        this.actuatorId = actuatorId;
    }

    public String getActuatorName() {
        return actuatorName;
    }

    public void setActuatorName(String actuatorName) {
        this.actuatorName = actuatorName;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }
}
