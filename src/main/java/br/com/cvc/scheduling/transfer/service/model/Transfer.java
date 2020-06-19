package br.com.cvc.scheduling.transfer.service.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Transfer {

    private Account sourceAccount;
    private Account targetAccount;
    private Double value;
    private Double rate;
    private LocalDate transferDate;
    private LocalDate schedulingDate;

}
