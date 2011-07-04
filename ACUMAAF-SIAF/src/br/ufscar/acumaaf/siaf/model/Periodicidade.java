package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the periodicidade database table.
 * 
 */
@Entity
@Table(name="periodicidade")
public class Periodicidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PERIODICIDADE_CODIGOPERIODICIDADE_GENERATOR", sequenceName="PERIODICIDADE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERIODICIDADE_CODIGOPERIODICIDADE_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigoperiodicidade;

	@Column(length=300)
	private String descricao;

	@Column(length=50)
	private String nome;

	private Boolean sincronizado;

	//bi-directional many-to-one association to Grupoatividadefisica
	@OneToMany(mappedBy="periodicidade")
	private Set<Grupoatividadefisica> grupoatividadefisicas;

    public Periodicidade() {
    }

	public Integer getCodigoperiodicidade() {
		return this.codigoperiodicidade;
	}

	public void setCodigoperiodicidade(Integer codigoperiodicidade) {
		this.codigoperiodicidade = codigoperiodicidade;
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

	public Set<Grupoatividadefisica> getGrupoatividadefisicas() {
		return this.grupoatividadefisicas;
	}

	public void setGrupoatividadefisicas(Set<Grupoatividadefisica> grupoatividadefisicas) {
		this.grupoatividadefisicas = grupoatividadefisicas;
	}
	
}