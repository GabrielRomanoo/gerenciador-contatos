package br.com.gerenciadorcontatos.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gerenciadorcontatos.model.entity.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, BigInteger>{}
