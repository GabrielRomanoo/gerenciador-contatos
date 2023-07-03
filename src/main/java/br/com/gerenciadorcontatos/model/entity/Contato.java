package br.com.gerenciadorcontatos.model.entity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "CONTATO")
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CONTATO")
	private BigInteger id;

	@Column(name = "NM_CONTATO", nullable = false)
	private String nome;

	@Column(name = "NM_EMAIL")
	private String email;

	@Column(name = "NR_TELEFONE", nullable = false)
	private String telefone;

	@Column(name = "DT_DATA_NASCIMENTO")
	private LocalDate dataNascimento;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "contato")
	private List<Endereco> listaEnderecos;

	@Column(name = "DT_CRIADO", nullable = false, updatable = false)
	private final LocalDate criadoEm = LocalDate.now();

	@Builder.Default()
	@Column(name = "DT_ATUALIZADO", nullable = false)
	private LocalDate atualizadoEm = LocalDate.now();
}