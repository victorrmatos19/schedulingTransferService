package br.com.cvc.scheduling.transfer.services;

import br.com.cvc.scheduling.transfer.exceptions.TransferBusinessException;
import br.com.cvc.scheduling.transfer.helpers.TransferModelHelper;
import br.com.cvc.scheduling.transfer.model.Rate;
import br.com.cvc.scheduling.transfer.model.Transfer;
import br.com.cvc.scheduling.transfer.model.enumerator.TransferErrorsEnum;
import br.com.cvc.scheduling.transfer.repository.RateRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service to calculate rate
 *
 * @author Victor Rodrigues de Matos
 */
@Slf4j
@Setter
@Service
public class RateService {

    private static final String TYPE_B = "B";

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private TransferModelHelper transferModelHelper;

    public Double calculateRateByTransfer(Transfer transfer){
        log.info("RateService: {} calculateRateByTransfer...");
        Long interval = transferModelHelper.getBetweenIntervalDates(transfer.getSchedulingDate(), transfer.getTransferDate());

        List<Rate> rates = getRatesFromDataBase();

        Rate rate = getRateByInterval(rates, interval);

        Double rateWithValue = calculateValue(rate);
        Double rateWithPercent = calculatePercent(rate, transfer.getValue(), rateWithValue);
        Double finalRate = calculateIntervalRate(rate, interval, rateWithPercent);

        verifyLimitValue(rate,transfer.getValue());

        return finalRate;
    }

    private List<Rate> getRatesFromDataBase(){
        List<Rate> rates = rateRepository.findAll();
        if(!rates.isEmpty()){
            return rates;
        }
        log.info("RateService: {} Rates not found...");
        throw new TransferBusinessException(HttpStatus.INTERNAL_SERVER_ERROR, TransferErrorsEnum.RATES_NOT_FOUND);
    }

    private Rate getRateByInterval(List<Rate> rates, Long interval){
        Optional<Rate> optionalRate = rates.stream().filter(r -> filterRateByInterval(r,interval)).findFirst();
        if(optionalRate.isPresent()){
            return optionalRate.get();
        }
        throw new TransferBusinessException(HttpStatus.BAD_REQUEST, TransferErrorsEnum.RATE_NOT_FOUND);
    }

    private boolean filterRateByInterval(Rate rate, Long interval) {
        return (Objects.isNull(rate.getMaxInterval())) ?
                filterRateWithouMaxInterval(rate,interval) :
                filterRateWithMinAndMaxInterval(rate,interval);
    }

    private boolean filterRateWithouMaxInterval(Rate rate, Long interval){
        return interval >= rate.getMinInterval();
    }

    private boolean filterRateWithMinAndMaxInterval(Rate rate, Long interval){
        return interval >= rate.getMinInterval() && interval <= rate.getMaxInterval();
    }

    private Double calculateValue(Rate rate){
        return Objects.nonNull(rate.getValue()) ? rate.getValue() : 0.0;
    }

    private Double calculatePercent(Rate rate,Double transferValue, Double actualValue){
        return Objects.nonNull(rate.getPercent()) ? actualValue + (transferValue * rate.getPercent()) : actualValue;
    }

    private Double calculateIntervalRate(Rate rate,Long interval, Double actualValue){
        return (rate.getType().equalsIgnoreCase(TYPE_B)) ? actualValue * interval : actualValue;
    }

    private void verifyLimitValue(Rate rate,Double transferValue){
        if(Objects.nonNull(rate.getValueLimit())){
            validLimitValue(rate.getValueLimit(),transferValue);
        }
    }

    public void validLimitValue(Double valueLimit, Double actualValue){
        if(valueLimit > actualValue){
            throw new TransferBusinessException(HttpStatus.BAD_REQUEST, TransferErrorsEnum.VALUE_NOT_VALID_FOR_RATE);
        }
    }

}
