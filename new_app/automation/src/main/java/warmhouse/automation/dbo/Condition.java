package warmhouse.automation.dbo;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "cond_type")
@Table(name = "conditions")
public abstract class Condition {

    @Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "automation_condition_id")
    private AutomationCondition automationCondition;

    @ManyToOne()
    private Sensor sensor;

    public abstract boolean test(Sensor sensor);

    public AutomationCondition getAutomationCondition() {
        return automationCondition;
    }

    public void setAutomationCondition(AutomationCondition automationCondition) {
        this.automationCondition = automationCondition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
