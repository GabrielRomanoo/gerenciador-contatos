package br.com.gerenciadorcontatos.controller.dto;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.gerenciadorcontatos.model.entity.Contato;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContatoDto {

	@Schema(nullable = false, description = "ID do contato")
	private BigInteger id;

	private String nome;

	private String email;

	private String telefone;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	private List<EnderecoDto> enderecos;

	public static ContatoDto converter(Contato contato) {
		return ContatoDto.builder().id(contato.getId()).nome(contato.getNome()).email(contato.getEmail())
				.telefone(contato.getTelefone()).dataNascimento(contato.getDataNascimento())
				.enderecos(EnderecoDto.converterLista(contato.getListaEnderecos())).build();
	}	

	public static List<ContatoDto> converterLista(List<Contato> contatos) {
		return contatos.stream().map(contato -> ContatoDto.converter(contato)).collect(Collectors.toList());
	}

}
