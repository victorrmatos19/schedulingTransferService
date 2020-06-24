package br.com.cvc.scheduling.transfer.services;

import br.com.cvc.scheduling.transfer.model.Transfer;
import br.com.cvc.scheduling.transfer.helpers.TransferModelHelper;
import br.com.cvc.scheduling.transfer.model.dto.TransferDTO;
import br.com.cvc.scheduling.transfer.repository.TransferRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Service to scheduling transfer.
 *
 * @author Victor Rodrigues de Matos
 */
@Slf4j
@Setter
@Service
public class SchedulingTransferService {

    @Autowired
    private RateService rateService;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private TransferModelHelper transferModelHelper;

    public Transfer save(TransferDTO transferDTO){
        Transfer transfer = transferModelHelper.converteTransferDTOtoTransfer(transferDTO);

        log.info("SchedulingTransferService: {} Validating transfer model...");
        transferModelHelper.verifyIfAccountsNumbersAreTheSame(transfer.getSourceAccount(),transfer.getTargetAccount());
        transferModelHelper.verifyIfFinalDateIsLessThanInitialDate(transfer.getSchedulingDate(),transfer.getTransferDate());
        transferModelHelper.verifyIfTransferValueIsZero(transfer.getValue());
        log.info("SchedulingTransferService: {} Transfer model validated with success...");

        log.info("SchedulingTransferService: {} Calculing transfer rate...");
        Double rate = rateService.calculateRateByTransfer(transfer);
        transfer.setRate(rate);
        log.info("SchedulingTransferService: {} Rate calculed with success...");

        return transferRepository.save(transfer);
    }

    public List<Transfer> findAllTransfers(){
        return transferRepository.findAll();
    }

    public List<Transfer> listTransfersBySourceAccount(String sourceAccount){
        return transferRepository.findAllBySourceAccount(sourceAccount);
    }
}
