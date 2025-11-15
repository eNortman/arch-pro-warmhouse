package warmhouse.webapp.dbo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "actuators")
public class Actuator {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    private String name;
    private String type;
    private String location; // заданное значение (если поддерживается)
    private Boolean status; // включен/выключен
    private Float value;
    private Date lastUpdated;
    private Date createdAt = new Date();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
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

    public Actuator() {
    }

    public Actuator(User user, String name, String type, String location) {
        this.user = user;
        this.name = name;
        this.type = type;
        this.location = location;

        this.status = Boolean.FALSE;
        this.createdAt = new Date();
        this.lastUpdated = new Date();
    }
}
