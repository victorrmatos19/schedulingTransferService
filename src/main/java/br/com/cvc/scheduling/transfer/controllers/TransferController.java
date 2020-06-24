package br.com.cvc.scheduling.transfer.controllers;

import br.com.cvc.scheduling.transfer.model.Transfer;
import br.com.cvc.scheduling.transfer.model.dto.TransferDTO;
import br.com.cvc.scheduling.transfer.services.SchedulingTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private SchedulingTransferService transferService;

    @PostMapping("/scheduling")
    public ResponseEntity<Transfer> schedulingTransfer(@RequestBody @Valid TransferDTO transferDTO){
        return ResponseEntity.ok(transferService.save(transferDTO));
    }

    @GetMapping("/listAllTransfers")
    public ResponseEntity<List<Transfer>> listAllTransfers(){
        return ResponseEntity.ok(transferService.findAll());
    }
}
