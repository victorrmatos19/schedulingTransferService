package br.com.cvc.scheduling.transfer.helpers;

import br.com.cvc.scheduling.transfer.model.Transfer;
import br.com.cvc.scheduling.transfer.exceptions.TransferBusinessException;
import br.com.cvc.scheduling.transfer.model.dto.ErrorResponseDTO;
import br.com.cvc.scheduling.transfer.model.dto.TransferDTO;
import br.com.cvc.scheduling.transfer.model.enumerator.TransferErrorsEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class to test the {@link TransferModelHelper} class.
 *
 * @author Victor Rodrigues de Matos
 */

@DisplayName("Test: TransferModelHelper")
public class TransferModelHelperTest {

    @InjectMocks
    private TransferModelHelper transferModelHelper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("1.verifyIfTransferValueIsZero: should valid transfer value with success")
    public void verifyIfTransferValueIsZeroWithSuccess() {
        Double value = 100.00;

        assertDoesNotThrow(() -> transferModelHelper.verifyIfTransferValueIsZero(value));
    }

    @Test
    @DisplayName("2.verifyIfTransferValueIsZero: should valid transfer value with error")
    public void verifyIfTransferValueIsZeroWithError() {
        Double value = 0.00;

        TransferBusinessException transferBusinessException = assertThrows(TransferBusinessException.class, () -> {
            transferModelHelper.verifyIfTransferValueIsZero(value);
        });

        testErrors(HttpStatus.BAD_REQUEST, TransferErrorsEnum.TRANSFER_VALUE_IS_ZERO, transferBusinessException);
    }

    @Test
    @DisplayName("3.verifyIfFinalDateIsLessThanInitialDate: should valid dates with success")
    public void verifyIfFinalDateIsLessThanInitialDateWithSuccess() {
        LocalDate initialDate = LocalDate.parse("2020-06-20");
        LocalDate finalDate = LocalDate.parse("2020-06-22");

        assertDoesNotThrow(() -> transferModelHelper.verifyIfFinalDateIsLessThanInitialDate(initialDate,finalDate));
    }

    @Test
    @DisplayName("4.verifyIfFinalDateIsLessThanInitialDate: should valid dates with error")
    public void verifyIfFinalDateIsLessThanInitialDateWithError() {
        LocalDate initialDate = LocalDate.parse("2020-06-20");
        LocalDate finalDate = LocalDate.parse("2020-06-18");

        TransferBusinessException transferBusinessException = assertThrows(TransferBusinessException.class, () -> {
            transferModelHelper.verifyIfFinalDateIsLessThanInitialDate(initialDate,finalDate);
        });

        testErrors(HttpStatus.BAD_REQUEST, TransferErrorsEnum.INVALID_DATE, transferBusinessException);
    }

    @Test
    @DisplayName("5.verifyIfAccountsNumbersAreTheSame: should valid accounts numbers with success")
    public void verifyIfAccountsNumbersAreTheSameWithSuccess() {
        String sourceAccount = "1234";
        String targetAccount = "4321";

        assertDoesNotThrow(() -> transferModelHelper.verifyIfAccountsNumbersAreTheSame(sourceAccount,targetAccount));
    }

    @Test
    @DisplayName("6.verifyIfAccountsNumbersAreTheSame: should valid accounts numbers with error")
    public void verifyIfAccountsNumbersAreTheSameWithError() {
        String sourceAccount = "1234";
        String targetAccount = "1234";

        TransferBusinessException transferBusinessException = assertThrows(TransferBusinessException.class, () -> {
            transferModelHelper.verifyIfAccountsNumbersAreTheSame(sourceAccount,targetAccount);
        });

        testErrors(HttpStatus.BAD_REQUEST, TransferErrorsEnum.ACCOUNTS_NUMBER_ARE_EQUALS, transferBusinessException);
    }

    @Test
    @DisplayName("7.converteTransferDTOtoTransfer: should converte TransferDTO to Transfer")
    public void converteTransferDTOtoTransfer() {
        TransferDTO transferDTO = createTransferDTO();

        Transfer transfer = transferModelHelper.converteTransferDTOtoTransfer(transferDTO);

        assertEquals(transferDTO.getSourceAccount(), transfer.getSourceAccount());
        assertEquals(transferDTO.getTargetAccount(), transfer.getTargetAccount());
        assertEquals(transferDTO.getValue(), transfer.getValue());
        assertEquals(transferDTO.getTransferDate(), transfer.getTransferDate());
        assertNotNull(transfer.getSchedulingDate());
    }

    private TransferDTO createTransferDTO() {
        return TransferDTO.builder()
                    .sourceAccount("1234")
                    .targetAccount("4321")
                    .transferDate(LocalDate.parse("2000-06-19"))
                    .value(100.00)
                .build();
    }

    public void testErrors(HttpStatus status, TransferErrorsEnum transferErrorsEnum,
                           TransferBusinessException transferBusinessException) {
        ErrorResponseDTO errorResponse = transferBusinessException.getErrorResponse();

        assertEquals(status, errorResponse.getHttpStatus());
        assertEquals(transferErrorsEnum.getCode(), errorResponse.getCode());
    }
}
