package br.com.gerenciadorcontatos.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gerenciadorcontatos.model.entity.Contato;
import br.com.gerenciadorcontatos.repository.ContatoRepository;
import br.com.gerenciadorcontatos.service.ContatoService;

@Service
public class ContatoServiceImplementation implements ContatoService<Contato>{
	
	private ContatoRepository contatoRepository;

	@Autowired
	public ContatoServiceImplementation(ContatoRepository contatoRepository) {
		this.contatoRepository = contatoRepository;
	}

	@Override
	public Contato create(Contato novoContato) {
		contatoRepository.save(novoContato);
		return novoContato;
	}

	@Override
	public Contato findById(Long id) {
		Optional<Contato> _aluno = contatoRepository.findById(id);
		return _aluno.orElse(null);
	}

	@Override
	public List<Contato> findAll() {
		return contatoRepository.findAll();
	}

	@Override
	public boolean update(Contato contato) {
		if (!contatoRepository.existsById(contato.getId())) {
			return false;
		}
		contato.setAtualizadoEm(LocalDate.now());
		contatoRepository.save(contato);
		return true;
	}

	@Override
	public boolean delete(Long id) {
		if (!contatoRepository.existsById(id)) {
			return false;
		}
		contatoRepository.deleteById(id);
		return true;
	}

}
