package warmhouse.automation.dbo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("Threshold")
public class ThresholdCondition extends Condition {

    private Float threshold;
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


}
