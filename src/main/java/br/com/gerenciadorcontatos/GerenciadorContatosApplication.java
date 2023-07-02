package br.com.gerenciadorcontatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
@EntityScan(basePackages = "br.com.gerenciadorcontatos.model")
public class GerenciadorContatosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorContatosApplication.class, args);
	}

}
