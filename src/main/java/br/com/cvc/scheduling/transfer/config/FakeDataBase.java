package br.com.cvc.scheduling.transfer.config;

import br.com.cvc.scheduling.transfer.model.Rate;
import br.com.cvc.scheduling.transfer.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FakeDataBase {

    @Autowired
    private RateRepository rateRepository;

    @Bean
    public void injectDataInDatabase(){
        List<Rate> rates = new ArrayList<>();

        rates.add(Rate.builder().type("A").minInterval(Long.valueOf(0)).maxInterval(Long.valueOf(0)).percent(0.03).value(3.0).build());
        rates.add(Rate.builder().type("B").minInterval(Long.valueOf(1)).maxInterval(Long.valueOf(10)).value(12.0).build());
        rates.add(Rate.builder().type("C").minInterval(Long.valueOf(11)).maxInterval(Long.valueOf(20)).percent(0.08).build());
        rates.add(Rate.builder().type("C").minInterval(Long.valueOf(21)).maxInterval(Long.valueOf(30)).percent(0.06).build());
        rates.add(Rate.builder().type("C").minInterval(Long.valueOf(31)).maxInterval(Long.valueOf(40)).percent(0.04).build());
        rates.add(Rate.builder().type("C").minInterval(Long.valueOf(41)).percent(0.02).valueLimit(100000.0).build());

        rateRepository.saveAll(rates);
    }

}
