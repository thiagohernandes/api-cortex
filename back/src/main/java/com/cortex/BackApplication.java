package com.cortex;

import com.cortex.core.usecase.EleicaoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
@EnableCaching
public class BackApplication {

	@Autowired
	private EleicaoUseCase eleicaoUseCase;

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

	@PostConstruct
	public void init() throws IOException {
		this.eleicaoUseCase.importaCandidatos();
	}
}
