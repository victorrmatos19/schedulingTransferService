package br.com.cvc.scheduling.transfer.services;

import br.com.cvc.scheduling.transfer.model.Transfer;
import br.com.cvc.scheduling.transfer.model.dto.TransferDTO;
import br.com.cvc.scheduling.transfer.repository.TransferRepository;
import br.com.cvc.scheduling.transfer.services.validations.TransferValidationdEnum;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


/**
 * Service to scheduling transfer.
 *
 * @author Victor Rodrigues de Matos
 */
@Slf4j
@Setter
@Service
public class SchedulingTransferService implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    @Autowired
    private RateService rateService;

    @Autowired
    private TransferRepository transferRepository;

    public Transfer save(TransferDTO transferDTO){
        Transfer transfer = converteTransferDTOtoTransfer(transferDTO);

        log.info("SchedulingTransferService: Validating transfer...");
        List<TransferValidationdEnum> validations = Arrays.asList(TransferValidationdEnum.values());
        validations.forEach( validation -> validation.validate(transfer));

        log.info("SchedulingTransferService: Calculing transfer rate...");
        Double rate = rateService.calculateRateByTransfer(transfer);
        transfer.setRate(rate);
        log.info("SchedulingTransferService: Rate calculed with success...");

        return transferRepository.save(transfer);
    }

    @CacheEvict(cacheNames = "allTransfers", key ="#root.method.name", allEntries = true)
    public List<Transfer> findAllTransfers(){
        return transferRepository.findAll();
    }

    @CacheEvict(cacheNames = "TransfersByAccount", key ="#root.method.name", allEntries = true)
    public List<Transfer> listTransfersBySourceAccount(String sourceAccount){
        return transferRepository.findAllBySourceAccount(sourceAccount);
    }

    private Transfer converteTransferDTOtoTransfer(TransferDTO transferDTO){
        return Transfer.builder()
                .schedulingDate(LocalDate.now())
                .sourceAccount(transferDTO.getSourceAccount())
                .targetAccount(transferDTO.getTargetAccount())
                .transferDate(transferDTO.getTransferDate())
                .value(transferDTO.getValue())
                .build();
    }
}
