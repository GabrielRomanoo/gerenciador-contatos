package br.com.gerenciadorcontatos.model.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.gerenciadorcontatos.model.entity.Contato;
import lombok.EqualsAndHashCode;
import lombok.ToString;

public class ContatoDto {
	
	
	private Long id;

	private String nome;

	private String email;

	private Integer telefone;

	private String dataNascimento;
	
	private String rua;
	
	private Integer numero;
	
	private String cep;
	

	public Contato converter() {
		// TODO Auto-generated method stub
		return null;
	}

	public static ContatoDto converter(Contato contato) {
		return null;
	}

	public static List<ContatoDto> converterLista(List<Contato> contatos) {
		// TODO Auto-generated method stub
		return null;
	}

}
