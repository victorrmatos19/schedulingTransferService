package br.com.cvc.scheduling.transfer.exceptions;

import br.com.cvc.scheduling.transfer.model.dto.ErrorResponseDTO;
import br.com.cvc.scheduling.transfer.model.enumerator.TransferErrorsEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

/**
 * Class to test the {@link TransferBusinessException} class.
 */
@DisplayName("Test: TransferBusinessException")
public class TransferBusinessExceptionTest {

    private TransferBusinessException ex;

    @Test
    @DisplayName("1.TransferBusinessException: basic constructor")
    public void basicConstructorTest() {
        ex = new TransferBusinessException(HttpStatus.BAD_REQUEST,
                TransferErrorsEnum.GENERIC_ERROR);

        ErrorResponseDTO error = ex.getErrorResponse();

        Assertions.assertEquals(error.getHttpStatus(), HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(error.getCode(), TransferErrorsEnum.GENERIC_ERROR.getCode());
        Assertions.assertEquals(error.getMessage(), TransferErrorsEnum.GENERIC_ERROR.getDescription());
    }

    @Test
    @DisplayName("2.TransferBusinessException: get message")
    public void getMessageTest() {
        ex = new TransferBusinessException(
                HttpStatus.BAD_REQUEST,
                TransferErrorsEnum.GENERIC_ERROR);

        Assertions.assertEquals("Generic error in application.", ex.getMessage());
    }
}
