package com.example.accountvalidate.exception;

import com.example.accountvalidate.modal.BaseAccountAPIResponse;
import com.example.accountvalidate.util.ServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BaseAccountAPIResponse response = new BaseAccountAPIResponse();
        response.setResponseCode(ServiceConstants.INVALID_INPUT);
        response.setResponseMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(response, status);
    }
}
