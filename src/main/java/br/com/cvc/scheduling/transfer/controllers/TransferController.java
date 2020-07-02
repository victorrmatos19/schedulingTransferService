package br.com.cvc.scheduling.transfer.controllers;

import br.com.cvc.scheduling.transfer.model.Transfer;
import br.com.cvc.scheduling.transfer.model.dto.TransferDTO;
import br.com.cvc.scheduling.transfer.services.SchedulingTransferService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private SchedulingTransferService transferService;

    @ApiOperation(value = "Scheduling transfer on system")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/scheduling")
    public ResponseEntity<Transfer> schedulingTransfer(@RequestBody @Valid TransferDTO transferDTO){
        return ResponseEntity.ok(transferService.save(transferDTO));
    }

    @ApiOperation(value = "List all transfers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    @GetMapping("/listAllTransfers")
    public ResponseEntity<List<Transfer>> listAllTransfers(){
        return ResponseEntity.ok(transferService.findAllTransfers());
    }

    @ApiOperation(value = "List transfers by source account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    @GetMapping("/listTransfersBySourceAccount")
    public ResponseEntity<List<Transfer>> listTransfersBySourceAccount(@Param("sourceAccount") String sourceAccount){
        return ResponseEntity.ok(transferService.listTransfersBySourceAccount(sourceAccount));
    }
}
