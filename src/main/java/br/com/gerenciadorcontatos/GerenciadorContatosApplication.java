package br.com.gerenciadorcontatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "br.com.gerenciadorcontatos.model")
public class GerenciadorContatosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorContatosApplication.class, args);
	}

}
