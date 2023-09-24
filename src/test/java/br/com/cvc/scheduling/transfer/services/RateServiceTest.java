package br.com.cvc.scheduling.transfer.services;

import br.com.cvc.scheduling.transfer.exceptions.TransferBusinessException;
import br.com.cvc.scheduling.transfer.helpers.TransferModelHelper;
import br.com.cvc.scheduling.transfer.model.Rate;
import br.com.cvc.scheduling.transfer.model.Transfer;
import br.com.cvc.scheduling.transfer.model.dto.ErrorResponseDTO;
import br.com.cvc.scheduling.transfer.model.dto.TransferDTO;
import br.com.cvc.scheduling.transfer.model.enumerator.TransferErrorsEnum;
import br.com.cvc.scheduling.transfer.repository.RateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to test the {@link RateService} class.
 *
 * @author Victor Rodrigues de Matos
 */

@DisplayName("Test: RateService")
public class RateServiceTest {

    @Mock
    private RateRepository rateRepository;

    @InjectMocks
    private RateService rateService;

    @InjectMocks
    private TransferModelHelper transferModelHelper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        rateService.setTransferModelHelper(transferModelHelper);
    }

    @Test
    @DisplayName("1.calculateRateByTransfer: calculate rate when rate not found")
    public void calculateRateByTransferWhenRateNotFound(){
        when(rateRepository.findAll()).thenReturn(getAllRates());

        Transfer transfer = createTransfer(100.00, Long.valueOf(41));

        TransferBusinessException transferBusinessException = assertThrows(TransferBusinessException.class, () -> {
            rateService.calculateRateByTransfer(transfer);
        });

        testErrors(HttpStatus.BAD_REQUEST, TransferErrorsEnum.VALUE_NOT_VALID_FOR_RATE, transferBusinessException);
    }

    @Test
    @DisplayName("2.calculateRateByTransfer: calculate rate when transfer is today")
    public void calculateRateByTransferWhenTransferIsToday(){
        Transfer transfer = createTransfer(100.00, Long.valueOf(0));
        Double rateExpected = 6.00;

        when(rateRepository.findAll()).thenReturn(getAllRates());

        Double rate = rateService.calculateRateByTransfer(transfer);

        assertEquals(rateExpected, rate);
    }

    @Test
    @DisplayName("3.calculateRateByTransfer: calculate rate when interval is 4 days")
    public void calculateRateByTransferWhenIntervalIsFourDays(){
        Transfer transfer = createTransfer(100.00, Long.valueOf(4));
        Double rateExpected = 48.0;

        when(rateRepository.findAll()).thenReturn(getAllRates());

        Double rate = rateService.calculateRateByTransfer(transfer);

        assertEquals(rateExpected, rate);
    }

    @Test
    @DisplayName("4.calculateRateByTransfer: calculate rate when interval is 11 days")
    public void calculateRateByTransferWhenIntervalIsElevenDays(){
        Transfer transfer = createTransfer(100.00, Long.valueOf(11));
        Double rateExpected = 8.0;

        when(rateRepository.findAll()).thenReturn(getAllRates());

        Double rate = rateService.calculateRateByTransfer(transfer);

        assertEquals(rateExpected, rate);
    }

    @Test
    @DisplayName("5.calculateRateByTransfer: calculate rate when interval is 21 days")
    public void calculateRateByTransferWhenIntervalIsTwentyOneDays(){
        Transfer transfer = createTransfer(100.00, Long.valueOf(21));
        Double rateExpected = 6.0;

        when(rateRepository.findAll()).thenReturn(getAllRates());

        Double rate = rateService.calculateRateByTransfer(transfer);

        assertEquals(rateExpected, rate);
    }

    @Test
    @DisplayName("6.calculateRateByTransfer: calculate rate when interval is 31 days")
    public void calculateRateByTransferWhenIntervalIsThirtyOneDays(){
        Transfer transfer = createTransfer(100.00, Long.valueOf(31));
        Double rateExpected = 4.0;

        when(rateRepository.findAll()).thenReturn(getAllRates());

        Double rate = rateService.calculateRateByTransfer(transfer);

        assertEquals(rateExpected, rate);
    }

    @Test
    @DisplayName("6.calculateRateByTransfer: calculate rate when interval is 41 days")
    public void calculateRateByTransferWhenIntervalIsFortyOneDays(){
        Transfer transfer = createTransfer(100000.00, Long.valueOf(41));
        Double rateExpected = 2000.0;

        when(rateRepository.findAll()).thenReturn(getAllRates());

        Double rate = rateService.calculateRateByTransfer(transfer);

        assertEquals(rateExpected, rate);
    }

    private Transfer createTransfer(Double value, Long afterDays){
        return new Transfer(
                null,
                null,
                value,
                LocalDate.now().plusDays(afterDays),
                null,
                null,
                LocalDate.now()
        );
    }

    public void testErrors(HttpStatus status, TransferErrorsEnum transferErrorsEnum,
                           TransferBusinessException transferBusinessException) {
        ErrorResponseDTO errorResponse = transferBusinessException.getErrorResponse();

        assertEquals(status, errorResponse.getHttpStatus());
        assertEquals(transferErrorsEnum.getCode(), errorResponse.getCode());
    }

    private List<Rate> getAllRates(){
        List<Rate> rates = new ArrayList<>();

        rates.add(buildRate("A",0L, 0L, 0.03, 3.0, null));
        rates.add(buildRate("B",1L, 10L, null, 12.0,  null));
        rates.add(buildRate("C",11L, 20L, 0.08, null,  null));
        rates.add(buildRate("C",21L, 30L, 0.06, null,  null));
        rates.add(buildRate("C",31L, 40L, 0.04, null,  null));
        rates.add(buildRate("C",41L, null, 0.02, null,  100000.0));

        return rates;
    }

    private Rate buildRate(String type, Long minInterval, Long maxInterval, Double percent, Double value, Double
                            valueLimit){
        return new Rate(1L,type, minInterval, maxInterval, percent, value, valueLimit);
    }

}
