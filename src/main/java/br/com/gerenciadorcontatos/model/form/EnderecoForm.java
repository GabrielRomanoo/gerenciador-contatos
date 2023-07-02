package br.com.gerenciadorcontatos.model.form;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.gerenciadorcontatos.model.entity.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EnderecoForm {

	@NotNull
	@NotBlank
	@Schema(required = true, description = "Nome da Rua")
	private String rua;

	@NotNull
	@Schema(required = true, description = "Número do endereço")
	private Integer numero;

	@NotNull
	@NotBlank
	@Pattern(regexp = "\\d{5}-\\d{3}")
	@Schema(required = true, description = "Número de CEP")
	private String cep;

	public static List<Endereco> converterToEntity(List<EnderecoForm> enderecos) {
		return enderecos.stream()
				.map(endereco -> EnderecoForm.converter(endereco))
				.collect(Collectors.toList());
	}

	public static Endereco converter(EnderecoForm endereco) {
		return Endereco.builder()
				.rua(endereco.getRua())
				.numero(endereco.getNumero())
				.cep(endereco.getCep())
				.build();
	}
}
