package br.com.cvc.scheduling.transfer.service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


/**
 * TransferDTO Model
 *
 * @author Victor Rodrigues de Matos
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferDTO {

    @NotNull(message = "{value.not.null}")
    private Double value;

    @NotNull(message = "{transferDate.not.null}")
    private LocalDate transferDate;

    @NotBlank(message = "{targetAccount.not.null}")
    private String targetAccount;

    @NotBlank(message = "{sourceAccount.not.null}")
    private String sourceAccount;
}
