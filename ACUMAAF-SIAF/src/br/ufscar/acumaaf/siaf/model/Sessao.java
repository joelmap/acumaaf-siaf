package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the sessao database table.
 * 
 */
@Entity
@Table(name="sessao")
public class Sessao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SESSAO_CODIGOSESSAO_GENERATOR", sequenceName="SESSAO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SESSAO_CODIGOSESSAO_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigosessao;

    @Temporal( TemporalType.DATE)
	@Column(nullable=false)
	private Date data;

	@Column(length=300)
	private String descricao;

	private Boolean sincronizado;

	//bi-directional many-to-one association to Dadosessao
	@OneToMany(mappedBy="sessao")
	private Set<Dadosessao> dadosessaos;

	//bi-directional many-to-one association to Presenca
	@OneToMany(mappedBy="sessao")
	private Set<Presenca> presencas;

	//bi-directional many-to-one association to Grupoatividadefisica
    @ManyToOne
	@JoinColumn(name="codigogrupoatividadefisica", nullable=false)
	private Grupoatividadefisica grupoatividadefisica;

    public Sessao() {
    }

	public Integer getCodigosessao() {
		return this.codigosessao;
	}

	public void setCodigosessao(Integer codigosessao) {
		this.codigosessao = codigosessao;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getSincronizado() {
		return this.sincronizado;
	}

	public void setSincronizado(Boolean sincronizado) {
		this.sincronizado = sincronizado;
	}

	public Set<Dadosessao> getDadosessaos() {
		return this.dadosessaos;
	}

	public void setDadosessaos(Set<Dadosessao> dadosessaos) {
		this.dadosessaos = dadosessaos;
	}
	
	public Set<Presenca> getPresencas() {
		return this.presencas;
	}

	public void setPresencas(Set<Presenca> presencas) {
		this.presencas = presencas;
	}
	
	public Grupoatividadefisica getGrupoatividadefisica() {
		return this.grupoatividadefisica;
	}

	public void setGrupoatividadefisica(Grupoatividadefisica grupoatividadefisica) {
		this.grupoatividadefisica = grupoatividadefisica;
	}
	
}