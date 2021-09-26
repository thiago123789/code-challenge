package code.challenge.bff.dtos;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class SubscriptionInputDto {
    private String firstName;

    @Valid
    @Email
    @NotBlank
    private String email;

    private String gender;

    @Valid
    @NotNull
    private Date birth;

    @Valid
    @NotNull
    private boolean consent;

    @Valid
    @NotNull
    private int newsletterId;
}
