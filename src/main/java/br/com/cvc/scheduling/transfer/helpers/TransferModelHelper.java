package br.com.cvc.scheduling.transfer.helpers;

import br.com.cvc.scheduling.transfer.exceptions.TransferBusinessException;
import br.com.cvc.scheduling.transfer.model.Transfer;
import br.com.cvc.scheduling.transfer.model.dto.TransferDTO;
import br.com.cvc.scheduling.transfer.model.enumerator.TransferErrorsEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Helper for transfer model objects.
 *
 * @author Victor Rodrigues de Matos
 */

@Slf4j
@Service
public class TransferModelHelper {

    public void verifyIfTransferValueIsZero(Double transferValue){
        log.info("TransferModelHelper:{} verifyIfTransferValueIsZero...");
        if(transferValue == 0){
            throw new TransferBusinessException(HttpStatus.BAD_REQUEST, TransferErrorsEnum.TRANSFER_VALUE_IS_ZERO);
        }
    }

    public void verifyIfFinalDateIsLessThanInitialDate(LocalDate initialDate, LocalDate finalDate){
        log.info("TransferModelHelper:{} verifyIfFinalDateIsLessThanInitialDate...");
        if(getBetweenIntervalDates(initialDate,finalDate) < 0){
            throw new TransferBusinessException(HttpStatus.BAD_REQUEST, TransferErrorsEnum.INVALID_DATE);
        }
    }

    public void verifyIfAccountsNumbersAreTheSame(String sourceAccount, String targetAccount){
        log.info("TransferModelHelper:{} verifyIfAccountsNumbersAreTheSame...");
        if(sourceAccount.equalsIgnoreCase(targetAccount)){
            throw new TransferBusinessException(HttpStatus.BAD_REQUEST, TransferErrorsEnum.ACCOUNTS_NUMBER_ARE_EQUALS);
        }
    }

    public Transfer converteTransferDTOtoTransfer(TransferDTO transferDTO){
        return Transfer.builder()
                .schedulingDate(LocalDate.now())
                .sourceAccount(transferDTO.getSourceAccount())
                .targetAccount(transferDTO.getTargetAccount())
                .transferDate(transferDTO.getTransferDate())
                .value(transferDTO.getValue())
                .build();
    }

    public long getBetweenIntervalDates(LocalDate initialDate, LocalDate finalDate){
       return ChronoUnit.DAYS.between( initialDate , finalDate );
    }

}
