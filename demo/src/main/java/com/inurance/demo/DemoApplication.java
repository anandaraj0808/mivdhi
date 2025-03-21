package com.inurance.demo;

import com.inurance.demo.InsurancePolicy;
import com.inurance.demo.InsuranceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(InsuranceRepository repository) {
		return args -> {
			if (repository.count() == 0) { // Prevent duplicate inserts
				repository.saveAll(List.of(
						new InsurancePolicy("Secure Future Term Life", "Term Life", 5000, 1000000),
						new InsurancePolicy("Health Shield Plan", "Health", 3000, 500000),
						new InsurancePolicy("Car Protect Plan", "Vehicle", 2000, 300000)
				));
			}
		};
	}
}


