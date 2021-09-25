package code.challenge.bff.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
public class SubscriptionDto implements Serializable {
    private UUID id;
    private String firstName;
    private String email;
    private String gender;
    private Date birth;
    private boolean consent;
    private int newsletterId;

    public SubscriptionDto() {
        id = UUID.randomUUID();
    }
}
