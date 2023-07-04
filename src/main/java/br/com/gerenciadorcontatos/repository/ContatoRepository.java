package br.com.gerenciadorcontatos.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gerenciadorcontatos.model.entity.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, BigInteger>{
	
	public Optional<List<Contato>> findByListaEnderecos_Cep(String cep);
	
	public Optional<List<Contato>> findByListaEnderecos_Rua(String rua);
	
	public Optional<List<Contato>> findByListaEnderecos_Numero(Integer rua);
}
