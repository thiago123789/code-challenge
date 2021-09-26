package code.challenge.bff.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class SubscriptionInputDto {
    private String firstName;
    private String email;
    private String gender;
    private Date birth;
    private boolean consent;
    private int newsletterId;
}
