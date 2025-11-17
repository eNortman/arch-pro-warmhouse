package warmhouse.webapp.dbo;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "act_type")
@Table(name = "actions")
public abstract class Action {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "automation_condition_id")
    private AutomationCondition automationCondition;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public AutomationCondition getAutomationCondition() {
        return automationCondition;
    }

    public void setAutomationCondition(AutomationCondition automationCondition) {
        this.automationCondition = automationCondition;
    }
}
