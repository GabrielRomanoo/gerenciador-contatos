package br.com.gerenciadorcontatos.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@Entity
@Table(name = "ENDERECO")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ToString.Exclude
	@EqualsAndHashCode.Include
	@JsonIgnore
	@Column(name = "ID_ENDERECO")
	private Long id;
	
	@Deprecated
	public Endereco() {
	}
	
	@Column(name = "NM_RUA")
	private String rua;
	
	@Column(name = "NR_NUMERO")
	private Integer numero;
	
	@Column(name = "CD_CEP")
	private String cep;
	
    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_CONTATO")
	private Contato contato;
	
	@Column(name = "DT_CRIADO")
	private LocalDate criadoEm = LocalDate.now();
	
	@Column(name = "DT_ATUALIZADO")
	private LocalDate atualizadoEm = LocalDate.now();
}
