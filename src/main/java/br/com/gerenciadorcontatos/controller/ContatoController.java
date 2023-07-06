package br.com.gerenciadorcontatos.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gerenciadorcontatos.controller.dto.ContatoDto;
import br.com.gerenciadorcontatos.controller.form.ContatoForm;
import br.com.gerenciadorcontatos.model.entity.Contato;
import br.com.gerenciadorcontatos.service.implementation.ContatoServiceImplementation;
import br.com.gerenciadorcontatos.util.NullAwareBeanUtilsBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contatos")
@Tag(name = "Contato", description = "Endpoints para manipuação de um Contato")
@RequiredArgsConstructor
public class ContatoController {

	private final ContatoServiceImplementation contatoService;

	private final NullAwareBeanUtilsBean utilsBean;

	@PostMapping
	@Operation(description = "Grava um Contato", tags = { "Contato" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))) })
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ContatoForm form, UriComponentsBuilder uriBuilder) {
		Contato contato = contatoService.create(form.converterToEntity());
		URI uri = uriBuilder.path("/contato/{id}").buildAndExpand(contato.getId()).toUri();
		return ResponseEntity.created(uri).body(ContatoDto.converter(contato));
	}

	@GetMapping
	@Operation(description = "Lista um ou todos os contatos", tags = { "Contato" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContatoDto.class))) })
	public ResponseEntity<List<ContatoDto>> lista(@RequestParam(required = false) BigInteger id) {
		if (id != null) {
			Optional<Contato> contato = contatoService.findById(id);
			if (contato.isPresent())
				return ResponseEntity.ok(Arrays.asList(ContatoDto.converter(contato.get())));
		}
		List<Contato> contatos = contatoService.findAll();
		return ResponseEntity.ok(ContatoDto.converterLista(contatos));
	}

	@DeleteMapping("/{id}")
	@Operation(description = "Deleta um contato a partir do Id.", tags = { "Contato" })
	@Transactional
	public ResponseEntity<?> remover(@PathVariable @Parameter(description = "O Id do contato a ser deletado.") BigInteger id) {
		if (contatoService.findById(id).isPresent()) {
			contatoService.delete(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Operation(description = "Atualiza um os dados de um contato", tags = { "Contato" })
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable BigInteger id, @RequestBody @Valid ContatoForm form) {
		Optional<Contato> contatoAtual = contatoService.findById(id);
		if (!contatoAtual.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Contato contatoAtualizado = form.converterToEntity();
		contatoService.update(id, contatoAtualizado);
		return ResponseEntity.ok(ContatoDto.converter(contatoAtualizado));
	}

	@PatchMapping("/{id}")
	@Operation(description = "Atualiza parcialmente os dados de um contato", tags = { "Contato" })
	@Transactional
	public ResponseEntity<?> atualizarParcial(
			@PathVariable @Parameter(description = "O Id do contato a ser atualizado parcialmente.") BigInteger id,
			@RequestBody ContatoForm form) throws IllegalAccessException, InvocationTargetException {
		Optional<Contato> contatoAtual = contatoService.findById(id);
		if (!contatoAtual.isPresent()) {
			return ResponseEntity.notFound().build(); 
		}
		Contato contatoAtualizado = form.converterToEntity();
		utilsBean.copyProperties(contatoAtual.get(), contatoAtualizado);
		contatoService.update(id, contatoAtual.get());
		return ResponseEntity.ok(ContatoDto.converter(contatoAtual.get()));
	}
	
	@GetMapping("/endereco/cep")
	@Operation(description = "Lista de contatos a partir de um cep", tags = { "Contato" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContatoDto.class))) })
	public ResponseEntity<List<ContatoDto>> listaContatosPorCep(@RequestParam(required = true) String cep) {
		Optional<List<Contato>> contatos = contatoService.findByCep(cep);
		if (contatos.isPresent() && !contatos.get().isEmpty())
			return ResponseEntity.ok(ContatoDto.converterLista(contatos.get()));
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/endereco/rua")
	@Operation(description = "Lista de contatos a partir de uma rua", tags = { "Contato" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContatoDto.class))) })
	public ResponseEntity<List<ContatoDto>> listaContatosPorRua(@RequestParam(required = true) String rua) {
		Optional<List<Contato>> contatos = contatoService.findByRua(rua);
		if (contatos.isPresent() && !contatos.get().isEmpty())
			return ResponseEntity.ok(ContatoDto.converterLista(contatos.get()));
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/endereco/numero")
	@Operation(description = "Lista de contatos a partir de uma rua", tags = { "Contato" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContatoDto.class))) })
	public ResponseEntity<List<ContatoDto>> listaContatosByCep(@RequestParam(required = true) Integer numero) {
		Optional<List<Contato>> contatos = contatoService.findByNumero(numero);
		if (contatos.isPresent() && !contatos.get().isEmpty())
			return ResponseEntity.ok(ContatoDto.converterLista(contatos.get()));
		return ResponseEntity.notFound().build();
	}

}
