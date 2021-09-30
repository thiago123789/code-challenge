package code.challenge.bff.config;

import code.challenge.bff.dtos.ErrorDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Log4j2
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<ErrorDto>> handle(MethodArgumentNotValidException e) throws MethodArgumentNotValidException {
        log.info(e);
        return ResponseEntity.badRequest().body(buildErrorList(e));
    }

    private List<ErrorDto> buildErrorList(MethodArgumentNotValidException e) {
        List<ErrorDto> errorDtoList = new ArrayList();
        for (ObjectError error :  e.getAllErrors()) {
            ErrorDto errorDto = ErrorDto.builder()
                    .code(400)
                    .message(error.getObjectName())
                    .messageDetail(error.getDefaultMessage())
                    .build();
            errorDtoList.add(errorDto);
        }
        return errorDtoList;
    }

}
