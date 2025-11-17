package warmhouse.automation.dbo;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "automation_conditions")
public class AutomationCondition {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    private Date lastUpdated;
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
}
