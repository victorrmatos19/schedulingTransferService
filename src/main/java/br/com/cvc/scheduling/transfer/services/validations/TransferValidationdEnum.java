package br.com.cvc.scheduling.transfer.services.validations;

import br.com.cvc.scheduling.transfer.exceptions.TransferBusinessException;
import br.com.cvc.scheduling.transfer.model.Transfer;
import br.com.cvc.scheduling.transfer.model.enumerator.TransferErrorsEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import static br.com.cvc.scheduling.transfer.helpers.TransferModelHelper.getBetweenIntervalDates;

@Slf4j
@AllArgsConstructor
public enum TransferValidationdEnum {
    ACCOUNT {
        @Override
        public void validate(Transfer transfer) {
            log.info("TransferModelHelper: VerifyIfAccountsNumbersAreTheSame...");
            if(transfer.getSourceAccount().equalsIgnoreCase(transfer.getTargetAccount())){
                throw new TransferBusinessException(HttpStatus.BAD_REQUEST, TransferErrorsEnum.ACCOUNTS_NUMBER_ARE_EQUALS);
            }
        }
    },
    DATE {
        @Override
        public void validate(Transfer transfer) {
            log.info("TransferModelHelper: VerifyIfFinalDateIsLessThanInitialDate...");
            if(getBetweenIntervalDates(transfer.getSchedulingDate(),transfer.getTransferDate()) < 0){
                throw new TransferBusinessException(HttpStatus.BAD_REQUEST, TransferErrorsEnum.INVALID_DATE);
            }
        }
    },
    VALUE {
        @Override
        public void validate(Transfer transfer) {
            log.info("TransferModelHelper: VerifyIfTransferValueIsZero...");
            if(transfer.getValue() == 0){
                throw new TransferBusinessException(HttpStatus.BAD_REQUEST, TransferErrorsEnum.TRANSFER_VALUE_IS_ZERO);
            }
        }
    };

    public abstract void validate(Transfer transfer);

}
