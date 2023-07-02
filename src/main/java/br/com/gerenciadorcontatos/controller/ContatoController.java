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
import br.com.gerenciadorcontatos.service.implementation.ContatoServiceImplementation;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
	
	private ContatoServiceImplementation contatoService;
	
	@Autowired
	public ContatoController(ContatoServiceImplementation contatoService) {
		this.contatoService = contatoService;
	}
	
	@PostMapping
	@Transactional 
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ContatoDto form) {
		Contato aluno = form.converter();
		contatoService.create(aluno);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping //traz uma lista, se tiver o codigo da turma, traz apenas um
	public ResponseEntity<List<ContatoDto>> lista(@RequestParam(required = false) Long id) { 
		if (id != null) {
			Contato contato = contatoService.findById(id);
			return ResponseEntity.ok(Arrays.asList(ContatoDto.converter(contato)));
		}
		List<Contato> contatos = contatoService.findAll();
		return ResponseEntity.ok(ContatoDto.converterLista(contatos)); // O Spring faz a conversão do objeto para JSON automaticamente, com o uso da biblioteca Jackson.
	}
	
	@DeleteMapping("/{id}")
	@Transactional 
	public ResponseEntity<?> remover(@PathVariable Long id) {
		if (contatoService.findById(id) != null) {
			contatoService.delete(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional //avisa pro spring que é pra commitar a transacao, Métodos anotados com @Transactional serão executados dentro de um contexto transacional, Ao finalizar o método, o Spring efetuará o commit automático da transação, caso nenhuma exception tenha sido lançada.
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid ContatoDto form) { //preciso colocar no pom a dependencia de validacao
		Contato contatoAtual = contatoService.findById(id);
		if (contatoAtual != null) {
			Contato contatoAtualizado = form.converter();
			contatoService.update(contatoAtualizado);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PatchMapping("/{id}")
	@Transactional //avisa pro spring que é pra commitar a transacao, Métodos anotados com @Transactional serão executados dentro de um contexto transacional, Ao finalizar o método, o Spring efetuará o commit automático da transação, caso nenhuma exception tenha sido lançada.
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody @Valid ContatoDto form) { //preciso colocar no pom a dependencia de validacao
		Contato contatoAtual = contatoService.findById(id);
		if (contatoAtual != null) {
			Contato contatoAtualizado = form.converter();
			contatoService.update(contatoAtualizado);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
