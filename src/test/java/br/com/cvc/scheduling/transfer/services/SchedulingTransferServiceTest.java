package br.com.cvc.scheduling.transfer.services;

import br.com.cvc.scheduling.transfer.exceptions.TransferBusinessException;
import br.com.cvc.scheduling.transfer.model.Transfer;
import br.com.cvc.scheduling.transfer.model.dto.TransferDTO;
import br.com.cvc.scheduling.transfer.repository.TransferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Class to test the {@link SchedulingTransferService} class.
 *
 * @author Victor Rodrigues de Matos
 */

@DisplayName("Test: SchedulingTransferService")
public class SchedulingTransferServiceTest {

    @Mock
    private RateService rateService;

    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private SchedulingTransferService schedulingTransferService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("1.save: Save transfer on DB with success")
    public void saveWithSuccess(){
        TransferDTO transferDTO = buildTransferDTO();

        when(rateService.calculateRateByTransfer(any())).thenReturn(100.00);
        when(transferRepository.save(any())).thenReturn(createTransfer());

        Transfer actualTransfer = schedulingTransferService.save(transferDTO);

        assertNotNull(actualTransfer);
    }

    @Test
    @DisplayName("2.save: Save transfer on DB with error by account")
    public void saveWithErrorByAccount(){
        TransferDTO transferDTO = buildInvalidAccountTransferDTO();

        when(rateService.calculateRateByTransfer(any())).thenReturn(100.00);

        Assertions.assertThrows(TransferBusinessException.class, () -> {
            schedulingTransferService.save(transferDTO);
        });
    }

    @Test
    @DisplayName("3.findAllTransfers: List all transfers with success")
    public void findAllTransfersWithSuccess(){
        when(transferRepository.findAll()).thenReturn(Arrays.asList());

        List<Transfer> transfers = schedulingTransferService.findAllTransfers();

        assertNotNull(transfers.isEmpty());
    }

    @Test
    @DisplayName("4.save: Save transfer on DB with error by value")
    public void saveWithErrorByValue(){
        TransferDTO transferDTO = buildInvalidValueransferDTO();

        when(rateService.calculateRateByTransfer(any())).thenReturn(100.00);

        Assertions.assertThrows(TransferBusinessException.class, () -> {
            schedulingTransferService.save(transferDTO);
        });
    }

    @Test
    @DisplayName("5.listTransfersBySourceAccount: List transfers by  with success")
    public void listTransfersBySourceAccountWithSuccess(){
        when(transferRepository.findAllBySourceAccount(anyString())).thenReturn(Arrays.asList());

        String sourceAccount = "1234";

        List<Transfer> transfers = schedulingTransferService.listTransfersBySourceAccount(sourceAccount);

        assertNotNull(transfers);
    }

    @Test
    @DisplayName("6.save: Save transfer on DB with error by date")
    public void saveWithErrorByDate(){
        TransferDTO transferDTO = buildInvalidDateTransferDTO();

        when(rateService.calculateRateByTransfer(any())).thenReturn(100.00);

        Assertions.assertThrows(TransferBusinessException.class, () -> {
            schedulingTransferService.save(transferDTO);
        });
    }


    private Transfer createTransfer() {
        return new Transfer();
    }

    private TransferDTO buildInvalidValueransferDTO() {
        return TransferDTO.builder()
                .sourceAccount("1234")
                .targetAccount("4321")
                .transferDate(LocalDate.now().plusDays(10))
                .value(0.0)
                .build();
    }

    private TransferDTO buildInvalidDateTransferDTO() {
        return TransferDTO.builder()
                .sourceAccount("1234")
                .targetAccount("4321")
                .transferDate(LocalDate.now().minusDays(19))
                .value(100.00)
                .build();
    }


    private TransferDTO buildInvalidAccountTransferDTO() {
        return TransferDTO.builder()
                .sourceAccount("1234")
                .targetAccount("1234")
                .transferDate(LocalDate.now().plusDays(10))
                .value(100.00)
                .build();
    }

    private TransferDTO buildTransferDTO() {
        return TransferDTO.builder()
                .sourceAccount("1234")
                .targetAccount("4321")
                .transferDate(LocalDate.now().plusDays(10))
                .value(100.00)
                .build();
    }
}
