package br.com.gerenciadorcontatos.service.implementation;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
    @Transactional 
	public Contato create(Contato novoContato) {
		contatoRepository.save(novoContato);
		return novoContato;
	}

	@Override
	public Optional<Contato> findById(BigInteger id) {
		return contatoRepository.findById(id);
	}

	@Override
	public List<Contato> findAll() {
		return contatoRepository.findAll();
	}

	@Override
	public boolean update(BigInteger id, Contato contato) {
		if (!contatoRepository.existsById(id)) {
			return false;
		}
		contato.setAtualizadoEm(LocalDate.now());
		contato.setId(id);
		contatoRepository.save(contato);
		return true;
	}

	@Override
	public boolean delete(BigInteger id) {
		if (!contatoRepository.existsById(id)) {
			return false;
		}
		contatoRepository.deleteById(id);
		return true;
	}

}
