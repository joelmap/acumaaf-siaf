package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the instrumentocoleta database table.
 * 
 */
@Entity
@Table(name="instrumentocoleta")
public class Instrumentocoleta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INSTRUMENTOCOLETA_CODIGOINSTRUMENTOCOLETA_GENERATOR", sequenceName="INSTRUMENTOCOLETA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INSTRUMENTOCOLETA_CODIGOINSTRUMENTOCOLETA_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigoinstrumentocoleta;

	@Column(length=300)
	private String descricao;

	@Column(nullable=false, length=50)
	private String nome;

	private Boolean sincronizado;

	@Column(nullable=false, length=20)
	private String unidademedida;

	//bi-directional many-to-one association to Dadopadraosessao
	@OneToMany(mappedBy="instrumentocoleta")
	private Set<Dadopadraosessao> dadopadraosessaos;

	//bi-directional many-to-one association to Dadosessao
	@OneToMany(mappedBy="instrumentocoleta")
	private Set<Dadosessao> dadosessaos;

    public Instrumentocoleta() {
    }

	public Integer getCodigoinstrumentocoleta() {
		return this.codigoinstrumentocoleta;
	}

	public void setCodigoinstrumentocoleta(Integer codigoinstrumentocoleta) {
		this.codigoinstrumentocoleta = codigoinstrumentocoleta;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getSincronizado() {
		return this.sincronizado;
	}

	public void setSincronizado(Boolean sincronizado) {
		this.sincronizado = sincronizado;
	}

	public String getUnidademedida() {
		return this.unidademedida;
	}

	public void setUnidademedida(String unidademedida) {
		this.unidademedida = unidademedida;
	}

	public Set<Dadopadraosessao> getDadopadraosessaos() {
		return this.dadopadraosessaos;
	}

	public void setDadopadraosessaos(Set<Dadopadraosessao> dadopadraosessaos) {
		this.dadopadraosessaos = dadopadraosessaos;
	}
	
	public Set<Dadosessao> getDadosessaos() {
		return this.dadosessaos;
	}

	public void setDadosessaos(Set<Dadosessao> dadosessaos) {
		this.dadosessaos = dadosessaos;
	}
	
}