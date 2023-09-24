package br.com.cvc.scheduling.transfer.repository

import br.com.cvc.scheduling.transfer.model.Transfer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Transfer Repository
 *
 * @author Victor Rodrigues de Matos
 */
@Repository
interface TransferRepository : JpaRepository<Transfer, Long> {

    fun findAllBySourceAccount(sourceAccount : String) : List<Transfer>;
}