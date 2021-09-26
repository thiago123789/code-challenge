package code.challenge.subscription.models;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tb_subscriptions")
@Data
public class Subscription {
    @Id
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    @Type(type = "uuid-char")
    private UUID Id;

    @Column
    private String firstName;

    @Column(nullable = false)
    private String email;

    @Column
    private String gender;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birth;

    @Column(nullable = false)
    private boolean consent;

    @Column(nullable = false)
    private int newsletterId;

}
