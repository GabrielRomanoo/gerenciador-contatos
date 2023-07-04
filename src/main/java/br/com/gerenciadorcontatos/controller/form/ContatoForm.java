package br.com.gerenciadorcontatos.controller.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.gerenciadorcontatos.model.entity.Contato;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "Classe para inserção de um Contato")
@Data
@AllArgsConstructor
public class ContatoForm {

	@NotNull
	@NotBlank
	@Schema(required = true, nullable = false, description = "Nome do contato")
	private String nome;

	@Email
	@Schema(required = false, example = "username@domain.com", description = "Email do contato")
	private String email;

	@NotNull
	@Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx e nao pode ser nulo.")
	@Schema(required = true, example = "(xx) xxxxx-xxxx", nullable = false, description = "Número de telefone")
	private String telefone;

	@Pattern(regexp = "^(0[1-9]|1\\d|2\\d|3[01])/(0[1-9]|1[0-2])/\\d{4}$", message = "A data de nascimento deve estar no formato dd/MM/yyyy.")
	@Schema(required = false, example = "dd/MM/yyyy", description = "Data de nascimento do Contato")
	private String dataNascimento;

	@Schema(required = false, description = "Lista de endereco do Contato")
	List<EnderecoForm> enderecos;

	public Contato converterToEntity() {
		return Contato.builder()
				.nome(this.nome)
				.email(this.email)
				.telefone(this.telefone)
				.dataNascimento(Objects.isNull(dataNascimento) ? null : LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				.listaEnderecos(Objects.isNull(enderecos) ? Arrays.asList() : EnderecoForm.converterToEntity(this.enderecos))
				.build();
	}
}
