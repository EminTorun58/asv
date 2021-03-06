package com.numero.uno.wedify.wedify.exception;

import com.numero.uno.wedify.wedify.util.MessageResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageResolver messageResolver;

    @Autowired
    public GlobalExceptionHandler(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException e) {

        // Let an AccessDeniedException fall back to the Spring Security Handler.
        if (e instanceof AccessDeniedException) throw e;
        ApiErrorCode apiErrorCode = ApiErrorCode.INTERNAL;
        ErrorResponse responseBody = new ErrorResponse(
                apiErrorCode,
                messageResolver.getMessage("message.internal")
        );

        return new ResponseEntity<>(responseBody, apiErrorCode.getHttpStatus());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentsInvalidException(MethodArgumentNotValidException e) {
        ArrayList<TargetError> errors = new ArrayList<>();
        ApiErrorCode apiErrorCode = ApiErrorCode.INVALID_ARGUMENTS;

        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError) {
                errors.add(new TargetError(((FieldError) error).getField(), error.getDefaultMessage()));
            } else {
                errors.add(new TargetError(error.getCode(), error.getDefaultMessage()));
            }
        }

        ErrorResponse responseBody = new ErrorResponse(
                apiErrorCode,
                messageResolver.getMessage("message.invalid.arguments"),
                errors.toArray(new TargetError[0])
        );

        return new ResponseEntity<>(responseBody, apiErrorCode.getHttpStatus());
    }

    @ExceptionHandler({HttpMediaTypeException.class})
    public ResponseEntity<ErrorResponse> handleHttpMediaTypeException(HttpMediaTypeException e) {
        ApiErrorCode apiErrorCode = ApiErrorCode.UNSUPPORTED_MEDIA_TYPE;
        ErrorResponse responseBody = new ErrorResponse(
                apiErrorCode,
                e.getMessage()
        );

        return new ResponseEntity<>(responseBody, apiErrorCode.getHttpStatus());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ApiErrorCode apiErrorCode = ApiErrorCode.METHOD_NOT_ALLOWED;
        ErrorResponse responseBody = new ErrorResponse(
                apiErrorCode,
                e.getMessage()
        );

        return new ResponseEntity<>(responseBody, apiErrorCode.getHttpStatus());
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundExceptionException(NoHandlerFoundException e) {
        ApiErrorCode apiErrorCode = ApiErrorCode.URI_NOT_FOUND;
        ErrorResponse responseBody = new ErrorResponse(
                ApiErrorCode.URI_NOT_FOUND,
                messageResolver.getMessage("message.uri.not.found", e.getRequestURL())
        );

        return new ResponseEntity<>(responseBody, apiErrorCode.getHttpStatus());
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        ApiErrorCode apiErrorCode = ApiErrorCode.RESOURCE_NOT_FOUND;
        ErrorResponse responseBody = new ErrorResponse(
                apiErrorCode,
                messageResolver.getMessage("message.resource.not.found", e.getResourceType(), e.getIdentifier())
        );

        return new ResponseEntity<>(responseBody, apiErrorCode.getHttpStatus());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException) {
        ApiErrorCode apiErrorCode = ApiErrorCode.MESSAGE_NOT_READABLE;
        ErrorResponse responseBody = new ErrorResponse(
                apiErrorCode,
                messageResolver.getMessage("message.body.not.readable")
        );

        return new ResponseEntity<>(responseBody, apiErrorCode.getHttpStatus());
    }

    @ExceptionHandler({ResourceAlreadyExistsException.class})
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
        ApiErrorCode apiErrorCode = ApiErrorCode.ALREADY_EXISTS;
        ErrorResponse responseBody = new ErrorResponse(
                apiErrorCode,
                messageResolver.getMessage("message.resource.already.exists", e.getResourceType()),
                new TargetError(e.getTarget(), messageResolver.getMessage("message.resource.already.exists.detail", e.getValue()))
        );

        return new ResponseEntity<>(responseBody, apiErrorCode.getHttpStatus());
    }
}
