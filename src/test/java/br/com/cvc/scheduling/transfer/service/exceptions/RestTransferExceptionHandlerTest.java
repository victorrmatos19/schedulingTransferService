package br.com.cvc.scheduling.transfer.service.exceptions;

import br.com.cvc.scheduling.transfer.service.model.dto.ErrorResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class to test the {@link RestTransferExceptionHandler} class.
 */

@DisplayName("Test: RestTransferExceptionHandler")
public class RestTransferExceptionHandlerTest {

    private static final String DEFAULT_MESSAGE = "input don't be null or empty";
    private static final String FIELD = "input_test";
    private static final String OBJECT_NAME = "object_name_test";
    private RestTransferExceptionHandler restTransferExceptionHandler;

    private BindingResult bindingResult = mock(BindingResult.class);

    private MethodArgumentNotValidException exception;

    @BeforeEach
    public void beforeEach() {
        restTransferExceptionHandler = new RestTransferExceptionHandler();
        exception = new MethodArgumentNotValidException(null,bindingResult);
    }

    @Test
    @DisplayName("1.handleMethodArgumentNotValid: Should return 400 status")
    public void handleMethodArgumentNotValid(){
        List<FieldError> fieldErrors = createFieldErrors();

        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        ResponseEntity<Object> response = restTransferExceptionHandler.handleMethodArgumentNotValid(exception,null,HttpStatus.BAD_REQUEST,null);

        assertEquals(response.getStatusCodeValue(),400);
        assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("2.converteFieldErrorToErrorResponseDTO: Should converte FieldError to ErrorResponseDTO")
    public void converteFieldErrorToErrorResponseDTO(){
        FieldError fieldError = createFieldError();

        ErrorResponseDTO errorResponseDTO = restTransferExceptionHandler.converteFieldErrorToErrorResponseDTO(fieldError);

        assertEquals(errorResponseDTO.getMessage(),DEFAULT_MESSAGE);
        assertEquals(errorResponseDTO.getField(),FIELD);
    }

    @Test
    @DisplayName("3.getErrorResponseDTOList: Should return ErrorResponseDTO list")
    public void getErrorResponseDTOList(){
        List<FieldError> fieldErrors = createFieldErrors();

        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        List<ErrorResponseDTO> errorResponseDTOList = restTransferExceptionHandler.getErrorResponseDTOList(exception);

        assertFalse(errorResponseDTOList.isEmpty());
        assertEquals(3,errorResponseDTOList.size());
    }

    private List<FieldError> createFieldErrors(){
        List<FieldError> fieldErrors = new ArrayList<>();

        fieldErrors.add(createFieldError());
        fieldErrors.add(createFieldError());
        fieldErrors.add(createFieldError());

        return  fieldErrors;
    }

    private FieldError createFieldError(){
        return new FieldError(OBJECT_NAME, FIELD, DEFAULT_MESSAGE);
    }
}
