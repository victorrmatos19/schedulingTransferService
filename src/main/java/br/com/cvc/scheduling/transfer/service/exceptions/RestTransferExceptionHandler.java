package br.com.cvc.scheduling.transfer.service.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.cvc.scheduling.transfer.service.model.dto.ErrorResponseDTO;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Rest Exception handler
 *
 * @author Victor Rodrigues de Matos
 */

@RestControllerAdvice
public class RestTransferExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles the exception to return a response to an API
     *
     * @param ex MethodArgumentNotValidException
     * @param headers http headers
     * @param status http status
     * @param request API request to which it must respond
     *
     * @return A {@ErrorResponseDTO} list and HTTP Status
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(getErrorResponseDTOList(ex), status);
    }

    public ErrorResponseDTO converteFieldErrorToErrorResponseDTO(FieldError error){
        return ErrorResponseDTO.builder()
                .code(error.getCode())
                .message(error.getDefaultMessage())
                .field(error.getField())
                .build();
    }

    public List<ErrorResponseDTO> getErrorResponseDTOList(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(this::converteFieldErrorToErrorResponseDTO)
                .collect(Collectors.toList());
    }
}
