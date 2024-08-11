package com.lcwd.electronic.store.exceptions;

import com.lcwd.electronic.store.payloads.ApiResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    //handle resource not found exception in global exception class
    private Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){

        logger.info("Exception handler invoked");

        ApiResponseMessage message = ApiResponseMessage.builder()
                                                    .message(ex.getMessage())
                                                    .status(HttpStatus.NOT_FOUND)
                                                    .success(true)
                                                    .build();
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }

    // handle methodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<Map<String,Object>> HandleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.info("Validation exception invoked");
        Map<String, Object> response = new HashMap<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        allErrors.stream().forEach(objectError -> {
            String message = objectError.getDefaultMessage();
            String field = ((FieldError)objectError).getField();
            response.put(field, message);
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
