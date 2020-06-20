package br.com.cvc.scheduling.transfer.service.repository;

import br.com.cvc.scheduling.transfer.service.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransferRepository extends JpaRepository<Transfer,Long> {
}
