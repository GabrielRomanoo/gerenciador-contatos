package br.com.gerenciadorcontatos.controller.form;

import java.util.List;
import java.util.stream.Collectors;

import br.com.gerenciadorcontatos.model.entity.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EnderecoForm {

	@Schema(required = false, description = "Nome da Rua")
	private String rua;

	@Schema(required = false, description = "Número do endereço")
	private Integer numero;

	@Schema(required = false, example = "00000-000", description = "Número de CEP")
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
