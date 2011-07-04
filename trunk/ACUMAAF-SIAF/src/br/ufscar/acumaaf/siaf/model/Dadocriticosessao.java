package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dadocriticosessao database table.
 * 
 */
@Entity
@Table(name="dadocriticosessao")
public class Dadocriticosessao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DADOCRITICOSESSAO_CODIGODADOCRITICOSESSAO_GENERATOR", sequenceName="DADOCRITICOSESSAO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DADOCRITICOSESSAO_CODIGODADOCRITICOSESSAO_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigodadocriticosessao;

	//bi-directional many-to-one association to Dadosessao
	//@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="codigodadosessao", nullable=false)
	private Dadosessao dadosessao;

    public Dadocriticosessao() {
    }

	public Integer getCodigodadocriticosessao() {
		return this.codigodadocriticosessao;
	}

	public void setCodigodadocriticosessao(Integer codigodadocriticosessao) {
		this.codigodadocriticosessao = codigodadocriticosessao;
	}

	public Dadosessao getDadosessao() {
		return this.dadosessao;
	}

	public void setDadosessao(Dadosessao dadosessao) {
		this.dadosessao = dadosessao;
	}
	
}