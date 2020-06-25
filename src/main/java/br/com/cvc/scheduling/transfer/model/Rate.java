package br.com.cvc.scheduling.transfer.model;

import lombok.*;

import javax.persistence.*;

/**
 * Rate Model
 *
 * @author Victor Rodrigues de Matos
 */

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "minInterval")
    private Long minInterval;

    @Column(name = "maxInterval")
    private Long maxInterval;

    @Column(name = "value")
    private Double value;

    @Column(name = "percent")
    private Double percent;

    @Column(name = "valueLimit")
    private Double valueLimit;
}
