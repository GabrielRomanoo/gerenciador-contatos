package br.com.gerenciadorcontatos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ENDERECO")
	private Long id;
	
	@Column(name = "NM_RUA")
	private String rua;
	
	@Column(name = "NR_NUMERO")
	private Integer numero;
	
	@Column(name = "CD_CEP")
	private String cep;
	
    @ManyToOne(fetch = FetchType.LAZY)
	private Contato contato;
}
