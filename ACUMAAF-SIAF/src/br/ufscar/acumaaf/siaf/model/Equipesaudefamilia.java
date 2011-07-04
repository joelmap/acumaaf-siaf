package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the equipesaudefamilia database table.
 * 
 */
@Entity
@Table(name="equipesaudefamilia")
public class Equipesaudefamilia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EQUIPESAUDEFAMILIA_CODIGOEQUIPESAUDEFAMILIA_GENERATOR", sequenceName="EQUIPESAUDEFAMILIA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EQUIPESAUDEFAMILIA_CODIGOEQUIPESAUDEFAMILIA_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigoequipesaudefamilia;

	@Column(length=300)
	private String descricao;

	@Column(nullable=false, length=50)
	private String nome;

	private Boolean sincronizado;

	//bi-directional many-to-one association to Unidadesaude
    @ManyToOne
	@JoinColumn(name="codigounidadesaude", nullable=false)
	private Unidadesaude unidadesaude;

	//bi-directional many-to-one association to Grupoatividadefisica
	@OneToMany(mappedBy="equipesaudefamilia")
	private Set<Grupoatividadefisica> grupoatividadefisicas;

	//bi-directional many-to-one association to Usuariotrabalhaequipesaudefamilia
	@OneToMany(mappedBy="equipesaudefamilia")
	private Set<Usuariotrabalhaequipesaudefamilia> usuariotrabalhaequipesaudefamilias;

    public Equipesaudefamilia() {
    }

	public Integer getCodigoequipesaudefamilia() {
		return this.codigoequipesaudefamilia;
	}

	public void setCodigoequipesaudefamilia(Integer codigoequipesaudefamilia) {
		this.codigoequipesaudefamilia = codigoequipesaudefamilia;
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

	public Unidadesaude getUnidadesaude() {
		return this.unidadesaude;
	}

	public void setUnidadesaude(Unidadesaude unidadesaude) {
		this.unidadesaude = unidadesaude;
	}
	
	public Set<Grupoatividadefisica> getGrupoatividadefisicas() {
		return this.grupoatividadefisicas;
	}

	public void setGrupoatividadefisicas(Set<Grupoatividadefisica> grupoatividadefisicas) {
		this.grupoatividadefisicas = grupoatividadefisicas;
	}
	
	public Set<Usuariotrabalhaequipesaudefamilia> getUsuariotrabalhaequipesaudefamilias() {
		return this.usuariotrabalhaequipesaudefamilias;
	}

	public void setUsuariotrabalhaequipesaudefamilias(Set<Usuariotrabalhaequipesaudefamilia> usuariotrabalhaequipesaudefamilias) {
		this.usuariotrabalhaequipesaudefamilias = usuariotrabalhaequipesaudefamilias;
	}
	
}