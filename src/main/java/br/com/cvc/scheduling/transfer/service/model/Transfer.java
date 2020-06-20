package br.com.cvc.scheduling.transfer.service.model;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Transfer Model
 *
 * @author Victor Rodrigues de Matos
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {

    private Double rate;

    private LocalDate schedulingDate;

    @NotNull(message = "{value.not.null}")
    private Double value;

    @NotNull(message = "{transferDate.not.null}")
    private LocalDate transferDate;

    @Valid
    @NotNull(message = "{targetAccount.not.null}")
    private Account targetAccount;

    @Valid
    @NotNull(message = "{sourceAccount.not.null}")
    private Account sourceAccount;

}
