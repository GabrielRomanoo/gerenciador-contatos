package br.com.gerenciadorcontatos.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gerenciadorcontatos.model.entity.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, BigInteger>{}
