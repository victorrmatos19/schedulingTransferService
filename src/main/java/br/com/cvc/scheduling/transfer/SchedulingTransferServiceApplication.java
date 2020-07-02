package br.com.cvc.scheduling.transfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SchedulingTransferServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulingTransferServiceApplication.class, args);
	}

}
