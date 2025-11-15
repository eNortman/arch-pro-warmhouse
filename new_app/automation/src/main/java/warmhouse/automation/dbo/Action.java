package warmhouse.automation.dbo;

import jakarta.persistence.*;
import warmhouse.dataholders.CommandMessage;

import java.util.Optional;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "act_type")
@Table(name = "actions")
public abstract class Action {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public abstract Optional<CommandMessage> getCommandMessageForAction();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
