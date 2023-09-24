package br.com.cvc.scheduling.transfer.model

import javax.persistence.*

@Entity
@Table(name = "Rate")
class Rate(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Long? = null,
        @Column
        var type : String? = null,
        @Column
        var minInterval : Long? = null,
        @Column
        var maxInterval : Long? = null,
        @Column
        var value : Double? = null,
        @Column
        var percent : Double? = null,
        @Column
        var valueLimit : Double? = null
)
