package br.com.cvc.scheduling.transfer.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Transfer Model
 *
 * @author Victor Rodrigues de Matos
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Transfer")
public class Transfer implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rate", nullable = false)
    private Double rate;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "transfer_date", nullable = false)
    private LocalDate transferDate;

    @Column(name = "target_account", nullable = false)
    private String targetAccount;

    @Column(name = "source_account", nullable = false)
    private String sourceAccount;

    @Column(name = "scheduling_date", nullable = false)
    private LocalDate schedulingDate;

}
