package br.com.cvc.scheduling.transfer.services;

import br.com.cvc.scheduling.transfer.helpers.TransferModelHelper;
import br.com.cvc.scheduling.transfer.model.Transfer;
import br.com.cvc.scheduling.transfer.model.dto.TransferDTO;
import br.com.cvc.scheduling.transfer.repository.TransferRepository;
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
    private TransferModelHelper transferModelHelper;

    @InjectMocks
    private SchedulingTransferService schedulingTransferService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        schedulingTransferService.setTransferModelHelper(transferModelHelper);
    }

    @Test
    @DisplayName("1.save: Save transfer on DB with success")
    public void saveWithSuccess(){
        TransferDTO transferDTO = createTransferDTO();

        when(rateService.calculateRateByTransfer(any())).thenReturn(100.00);
        when(transferRepository.save(any())).thenReturn(createTransfer());

        Transfer actualTransfer = schedulingTransferService.save(transferDTO);

        assertNotNull(actualTransfer);
    }

    @Test
    @DisplayName("2.findAllTransfers: List all transfers with success")
    public void findAllTransfersWithSuccess(){
        when(transferRepository.findAll()).thenReturn(Arrays.asList());

        List<Transfer> transfers = schedulingTransferService.findAllTransfers();

        assertNotNull(transfers.isEmpty());
    }

    @Test
    @DisplayName("3.listTransfersBySourceAccount: List transfers by  with success")
    public void listTransfersBySourceAccountWithSuccess(){
        when(transferRepository.findAllBySourceAccount(anyString())).thenReturn(Arrays.asList());

        String sourceAccount = "1234";

        List<Transfer> transfers = schedulingTransferService.listTransfersBySourceAccount(sourceAccount);

        assertNotNull(transfers);
    }


    private Transfer createTransfer() {
        return Transfer.builder().build();
    }

    private TransferDTO createTransferDTO() {
        return TransferDTO.builder()
                .sourceAccount("1234")
                .targetAccount("4321")
                .transferDate(LocalDate.now().plusDays(10))
                .value(100.00)
                .build();
    }
}
