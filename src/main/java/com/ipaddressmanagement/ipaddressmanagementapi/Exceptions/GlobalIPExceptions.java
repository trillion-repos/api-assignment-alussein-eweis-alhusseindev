package com.ipaddressmanagement.ipaddressmanagementapi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.logging.Logger;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalIPExceptions extends ResponseEntityExceptionHandler {

    Logger myLogger = Logger.getLogger(String.valueOf(IPException.class));

    @ExceptionHandler(IPException.class)
    public ResponseEntity<Object> globalIPManagementException(IPException myIPException, WebRequest myWebRequest){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp: ", LocalDateTime.now());
        body.put("message: ", myIPException.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    }
}
