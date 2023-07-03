package br.com.gerenciadorcontatos.controller;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gerenciadorcontatos.model.dto.ContatoDto;
import br.com.gerenciadorcontatos.model.entity.Contato;
import br.com.gerenciadorcontatos.model.form.ContatoForm;
import br.com.gerenciadorcontatos.repository.ContatoRepository;
import br.com.gerenciadorcontatos.service.implementation.ContatoServiceImplementation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/contato")
@Tag(name = "Contato", description = "Endpoints para manipuação de um Contato")
public class ContatoController {

	private ContatoServiceImplementation contatoService;
	
	@Autowired
	private ContatoRepository repository;

	@Autowired
	public ContatoController(ContatoServiceImplementation contatoService) {
		this.contatoService = contatoService;
	}

	@PostMapping
	@Operation(
			description = "Grava um Contato", 
			tags = {"Contato"}, 
			responses = {
					@ApiResponse(
							description = "Success", 
							responseCode = "200", 
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))),
					@ApiResponse(description = "Internal error", responseCode = "500", content = @Content)}
			)
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ContatoForm form) {
		Contato contato = form.converterToEntity();
		//contatoService.create(contato);
		repository.save(contato);
		return ResponseEntity.ok().build();
	}

	@GetMapping // traz uma lista, se tiver o codigo da turma, traz apenas um
	@Operation(
			description = "Lista um ou todos os contatos", 
			tags = {"Contato"}, 
			responses = {
					@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContatoDto.class))),
					@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal error", responseCode = "500", content = @Content)}
			)
	public ResponseEntity<List<ContatoDto>> lista(@RequestParam(required = false) Long id) {
		if (id != null) {
			Contato contato = contatoService.findById(id);
			return ResponseEntity.ok(Arrays.asList(ContatoDto.converter(contato)));
		}
		List<Contato> contatos = contatoService.findAll();
		return ResponseEntity.ok(ContatoDto.converterLista(contatos)); 
	}

	@DeleteMapping("/{id}")
	@Operation(
			description = "Deleta um contato a partir do Id.", 
			tags = {"Contato"}
			)
	@Transactional
	public ResponseEntity<?> remover(@PathVariable @Parameter(description = "O Id do contato a ser deletado.") Long id ) {
		if (contatoService.findById(id) != null) {
			contatoService.delete(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Operation(
			description = "Atualiza um os dados de um contato", 
			tags = {"Contato"}
			)
	@Transactional 
	public ResponseEntity<?> atualizar(@PathVariable @Parameter(description = "O Id do contato a ser atualizado.") Long id,
			@RequestBody @Valid ContatoForm form) { 
		Contato contatoAtual = contatoService.findById(id);
		if (contatoAtual != null) {
			Contato contatoAtualizado = form.converterToEntity();
			contatoService.update(contatoAtualizado);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PatchMapping("/{id}")
	@Operation(
			description = "Atualiza parcialmente os dados de um contato", 
			tags = {"Contato"}
			)
	@Transactional // avisa pro spring que é pra commitar a transacao, Métodos anotados com @Transactional serão executados dentro de um contexto transacional, Ao finalizar o método, o Spring efetuará o commit automático da transação, caso nenhuma exception tenha sido lançada.
	public ResponseEntity<?> atualizarParcial(@PathVariable @Parameter(description = "O Id do contato a ser atualizado parcialmente.") Long id,
			@RequestBody ContatoForm form) { 
		Contato contatoAtual = contatoService.findById(id);
		if (contatoAtual != null) {
			Contato contatoAtualizado = form.converterToEntity();
			//TO DO FAZER LOGICA ESPECIFICA DO PATH
			contatoService.update(contatoAtualizado);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
