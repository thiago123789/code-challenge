package code.challenge.bff.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class SubscriptionInputDto {
    private String firstName;

    @Email
    @NotBlank
    private String email;

    private String gender;

    @NotBlank
    private Date birth;

    @NotBlank
    private boolean consent;

    @NotBlank
    private int newsletterId;
}
