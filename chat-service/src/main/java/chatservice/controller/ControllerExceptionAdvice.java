package chatservice.controller;

import chatservice.dto.ExceptionDtoResponse;
import chatservice.exception.MessageException;
import chatservice.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(MessageException.class)
    public ResponseEntity<ExceptionDtoResponse> catchQuestionException(MessageException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ExceptionDtoResponse(exception.getMessage())
                );
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionDtoResponse> catchUserException(UserException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ExceptionDtoResponse(exception.getMessage())
                );
    }
}
