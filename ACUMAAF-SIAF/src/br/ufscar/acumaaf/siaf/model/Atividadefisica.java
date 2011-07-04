package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the atividadefisica database table.
 * 
 */
@Entity
@Table(name="atividadefisica")
public class Atividadefisica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ATIVIDADEFISICA_CODIGOATIVIDADEFISICA_GENERATOR", sequenceName="ATIVIDADEFISICA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ATIVIDADEFISICA_CODIGOATIVIDADEFISICA_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigoatividadefisica;

	@Column(length=300)
	private String descricao;

	@Column(nullable=false, length=50)
	private String nome;

	private Boolean sincronizado;

	//bi-directional many-to-one association to Grupoatividadefisica
	@OneToMany(mappedBy="atividadefisica")
	private Set<Grupoatividadefisica> grupoatividadefisicas;

    public Atividadefisica() {
    }

	public Integer getCodigoatividadefisica() {
		return this.codigoatividadefisica;
	}

	public void setCodigoatividadefisica(Integer codigoatividadefisica) {
		this.codigoatividadefisica = codigoatividadefisica;
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