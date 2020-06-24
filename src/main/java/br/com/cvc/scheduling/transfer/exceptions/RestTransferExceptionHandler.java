package br.com.cvc.scheduling.transfer.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import br.com.cvc.scheduling.transfer.model.dto.ErrorResponseDTO;
import br.com.cvc.scheduling.transfer.model.enumerator.TransferErrorsEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Rest Exception handler
 *
 * @author Victor Rodrigues de Matos
 */

@RestControllerAdvice
public class RestTransferExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String GENERIC_FIELD = "GENERIC_FIELD";

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

    /**
     * Handles the exception to return a response to an API
     *
     * @param ex TransferBusinessException
     *
     * @return A {@ErrorResponseDTO} and HTTP Status
     */
    @ExceptionHandler({TransferBusinessException.class})
    protected ResponseEntity<Object> handleTransferBusinessException(TransferBusinessException ex) {
        ErrorResponseDTO errorResponse = ex.getErrorResponse();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    /**
     * Handles the exception to return a response to an API
     *
     * @param ex HttpMessageNotReadableException
     * @param headers HTTP headers
     * @param status HTTP status
     * @param request API request to which it must respond
     *
     * @return A {@ErrorResponseDTO} and HTTP Status
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(status,GENERIC_FIELD, TransferErrorsEnum.GENERIC_ERROR.getCode(),ex.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, status);
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
