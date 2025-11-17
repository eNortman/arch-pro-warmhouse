package warmhouse.webapp.dbo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("Threshold")
public class ThresholdCondition extends Condition {

    private Float threshold;

    @NotNull
    private boolean expectedAbove;

    @Override
    public boolean test(Sensor sensor) {

        var val = sensor.getValue();

        if (expectedAbove) {
            return val > threshold;
        }

        return false;
    }

    public Float getThreshold() {
        return threshold;
    }

    public void setThreshold(Float threshold) {
        this.threshold = threshold;
    }

    public boolean isExpectedAbove() {
        return expectedAbove;
    }

    public void setExpectedAbove(boolean expectedAbove) {
        this.expectedAbove = expectedAbove;
    }

    public ThresholdCondition() {
    }

    public ThresholdCondition(User user, Sensor sensor, Float threshold, boolean expectedAbove) {
        this.setUser(user);
        this.setSensor(sensor);
        this.threshold = threshold;
        this.expectedAbove = expectedAbove;

    }
}
