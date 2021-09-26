package code.challenge.bff.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    private int code;
    private String message;
    private String messageDetail;
}
