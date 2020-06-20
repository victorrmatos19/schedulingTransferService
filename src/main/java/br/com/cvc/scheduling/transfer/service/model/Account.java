package br.com.cvc.scheduling.transfer.service.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * Account Model
 *
 * @author Victor Rodrigues de Matos
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @NotBlank(message = "{number.not.blank}")
    private String number;
}
