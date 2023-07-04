package br.com.gerenciadorcontatos.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.gerenciadorcontatos.model.entity.Endereco;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoDto {
	
	private String rua;

	private Integer numero;

	private String cep;
	
	public static EnderecoDto converter(Endereco endereco) {
		return EnderecoDto.builder().rua(endereco.getRua()).numero(endereco.getNumero()).cep(endereco.getCep()).build();
	}	

	public static List<EnderecoDto> converterLista(List<Endereco> enderecos) {
		return enderecos.stream().map(endereco -> EnderecoDto.converter(endereco)).collect(Collectors.toList());
	}
}
