package br.com.gerenciadorcontatos.model.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONTATO")
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ToString.Exclude
	@EqualsAndHashCode.Include
	@Column(name = "ID_CONTATO")
	private Long id;

	@Column(name = "NM_CONTATO")
	private String nome;

	@Column(name = "NM_EMAIL")
	private String email;

	@Column(name = "NR_TELEFONE")
	private Integer telefone;

	@Column(name = "DT_DATA_NASCIMENTO")
	private String dataNascimento;

	@OneToMany(mappedBy = "contato", cascade = CascadeType.ALL)
	private List<Endereco> listaEnderecos;

	@Column(name = "DT_CRIADO")
	private LocalDate criadoEm = LocalDate.now();

	@Column(name = "DT_ATUALIZADO")
	private LocalDate atualizadoEm = LocalDate.now();
}