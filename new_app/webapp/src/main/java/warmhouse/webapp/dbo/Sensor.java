package warmhouse.webapp.dbo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue
    private Long id;

    private String sensorSerialId;

    private String name;
    private String type;
    private String location;
    private Float value = 0f;

    @ColumnDefault("false")
    @NotNull
    private Boolean active = false;

    @ManyToOne
    @NotNull
    private User user;

    @Column(columnDefinition = "timestamp(6) default now()")
    @NotNull
    private Date lastUpdated = new Date();

    @Column(columnDefinition = "timestamp(6) default now()")
    @NotNull
    private Date createdAt = new Date();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public String getSensorSerialId() {
        return sensorSerialId;
    }

    public void setSensorSerialId(String sensorSerialId) {
        this.sensorSerialId = sensorSerialId;
    }

    public Sensor() {
    }

    public Sensor(String name, String type, String location, String sensorSerialId) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.sensorSerialId = sensorSerialId;
        this.active = false;
        this.lastUpdated = new Date();
        this.createdAt = new Date();
    }
}
