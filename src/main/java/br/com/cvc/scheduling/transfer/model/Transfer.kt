package br.com.cvc.scheduling.transfer.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "Transfer")
class Transfer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column
    var rate: Double? = null,
    @Column
    var value: Double? = null,
    @Column
    var transferDate: LocalDate? = null,
    @Column
    var targetAccount: String? = null,
    @Column
    var sourceAccount: String? = null,
    @Column
    var schedulingDate: LocalDate? = null
)