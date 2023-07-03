package br.com.gerenciadorcontatos.service;

import java.math.BigInteger;
import java.util.List;

public interface ContatoService<T> {
	
	T create(T obj);

	T findById(BigInteger id);

	List<T> findAll();

	boolean update(T obj);
	
	boolean delete(BigInteger id);
}
