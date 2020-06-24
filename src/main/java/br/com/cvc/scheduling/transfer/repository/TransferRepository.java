package br.com.cvc.scheduling.transfer.repository;

import br.com.cvc.scheduling.transfer.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Transfer Repository
 *
 * @author Victor Rodrigues de Matos
 */
@Repository
public interface TransferRepository extends JpaRepository<Transfer,Long> {

    List<Transfer> findAllBySourceAccount(String sourceAccount);
}
