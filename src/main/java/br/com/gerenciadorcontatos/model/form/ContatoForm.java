package br.com.gerenciadorcontatos.model.form;

import java.time.LocalDate;
import java.util.List;

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
	@Schema(required = true, example = "username@domain.com", description = "Email do contato")
	private String email;

	@NotNull
	@Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$")
	@Schema(required = true, example = "(xx) xxxxx-xxxx", nullable = false, description = "Número de telefone")
	private Integer telefone;

	@NotNull
	@NotBlank
	@Schema(required = true, example = "dd/MM/yyyy", description = "Data de nascimento do Contato")
	private String dataNascimento;

	@NotNull
	List<EnderecoForm> enderecos;

	public Contato converterToEntity() {
		return Contato.builder()
				.nome(this.nome)
				.email(this.email)
				.telefone(this.telefone)
				.dataNascimento(LocalDate.parse(dataNascimento))
				.listaEnderecos(EnderecoForm.converterToEntity(this.enderecos))
				.build();
	}
}
