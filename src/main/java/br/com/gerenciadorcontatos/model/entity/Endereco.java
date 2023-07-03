package br.com.gerenciadorcontatos.model.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENDERECO")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_ENDERECO")
	private BigInteger id;

	@Column(name = "NM_RUA")
	private String rua;

	@Column(name = "NR_NUMERO")
	private Integer numero;

	@Column(name = "CD_CEP")
	private String cep;
}
