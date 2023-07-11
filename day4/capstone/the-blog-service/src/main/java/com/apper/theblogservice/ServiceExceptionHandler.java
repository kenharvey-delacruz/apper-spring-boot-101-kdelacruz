package com.apper.theblogservice;

import com.apper.theblogservice.exceptions.EmailAlreadyRegisteredException;
import com.apper.theblogservice.payload.ServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ServiceError handleInvalidInputFields(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(objectError -> new ServiceError(objectError.getDefaultMessage()))
                .orElse(new ServiceError("Unknown invalid argument encountered"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    @ResponseBody
    public ServiceError handleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException ex) {
        return new ServiceError(ex.getMessage());
    }


}