package br.com.cvc.scheduling.transfer.helpers;

import lombok.extern.slf4j.Slf4j;
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
    public static long getBetweenIntervalDates(LocalDate initialDate, LocalDate finalDate){
        return ChronoUnit.DAYS.between( initialDate , finalDate );
    }

}
