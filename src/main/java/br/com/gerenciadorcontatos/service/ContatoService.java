package br.com.gerenciadorcontatos.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import br.com.gerenciadorcontatos.model.entity.Contato;

public interface ContatoService<T> {
	
	T create(T obj);

	Optional<Contato> findById(BigInteger id);

	List<T> findAll();

	boolean delete(BigInteger id);

	boolean update(BigInteger id, T contato);
}
