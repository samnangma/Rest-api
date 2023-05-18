package com.demo.dataanalyticrestfulapi.execption;

import com.demo.dataanalyticrestfulapi.utils.Response;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).
                toList();

        return new ResponseEntity<>(
                Response.<Object>BAD_REQUEST().setMessage(errors).setSuccess(false)
                , HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    protected ResponseEntity<Object> handleMaxFileExceeded(MaxUploadSizeExceededException ex) {

        return new ResponseEntity<>(
                Response.<Object>BAD_REQUEST().setMessage(ex.getMessage()).setSuccess(false)
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {

        return new ResponseEntity<>(
                Response.<Object>BAD_REQUEST().setMessage(ex.getMessage()).setSuccess(false)
                , HttpStatus.BAD_REQUEST);
    }
}


