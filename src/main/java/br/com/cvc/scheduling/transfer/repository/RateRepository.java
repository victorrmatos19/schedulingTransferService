package br.com.cvc.scheduling.transfer.repository;

import br.com.cvc.scheduling.transfer.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Rate Repository
 *
 * @author Victor Rodrigues de Matos
 */
@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

}
