package warmhouse.webapp.dbo;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "automation_conditions")
public class AutomationCondition {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private User user;

    @Column(columnDefinition = "timestamp(6) default now()")
    private Date lastUpdated;

    @Column(columnDefinition = "timestamp(6) default now()")
    private Date createdAt;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Action> action;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Condition> conditions;

    public Set<Action> testAndGetAllTrigerredActions(){

        // если все условия совпали
        if (conditions.stream().allMatch( condition  -> condition.test(condition.getSensor())))
            return action;

        return new HashSet<>();
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

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(Set<Condition> conditions) {
        this.conditions = conditions;
    }

    public Set<Action> getAction() {
        return action;
    }

    public void setAction(Set<Action> action) {
        this.action = action;
    }

    public AutomationCondition() {}

    public AutomationCondition(User user, String name) {
        this.name = name;
        this.user = user;
        this.lastUpdated = new Date();
        this.createdAt = new Date();
    }
}
