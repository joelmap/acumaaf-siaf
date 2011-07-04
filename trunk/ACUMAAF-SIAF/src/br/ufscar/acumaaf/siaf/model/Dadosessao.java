package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the dadosessao database table.
 * 
 */
@Entity
@Table(name="dadosessao")
public class Dadosessao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DADOSESSAO_CODIGODADOSESSAO_GENERATOR", sequenceName="DADOSESSAO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DADOSESSAO_CODIGODADOSESSAO_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigodadosessao;

	@Column(length=300)
	private String descricao;

	@Column(nullable=false, length=20)
	private String valor;

	//bi-directional many-to-one association to Dadocriticosessao
	@OneToMany(mappedBy="dadosessao")
	private Set<Dadocriticosessao> dadocriticosessaos;

	//bi-directional many-to-one association to Instrumentocoleta
    @ManyToOne
	@JoinColumn(name="codigoinstrumentocoleta", nullable=false)
	private Instrumentocoleta instrumentocoleta;

	//bi-directional many-to-one association to Sessao
    @ManyToOne
	@JoinColumn(name="codigosessao", nullable=false)
	private Sessao sessao;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="codigousuario", nullable=false)
	private Usuario usuario;

    public Dadosessao() {
    }

	public Integer getCodigodadosessao() {
		return this.codigodadosessao;
	}

	public void setCodigodadosessao(Integer codigodadosessao) {
		this.codigodadosessao = codigodadosessao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Set<Dadocriticosessao> getDadocriticosessaos() {
		return this.dadocriticosessaos;
	}

	public void setDadocriticosessaos(Set<Dadocriticosessao> dadocriticosessaos) {
		this.dadocriticosessaos = dadocriticosessaos;
	}
	
	public Instrumentocoleta getInstrumentocoleta() {
		return this.instrumentocoleta;
	}

	public void setInstrumentocoleta(Instrumentocoleta instrumentocoleta) {
		this.instrumentocoleta = instrumentocoleta;
	}
	
	public Sessao getSessao() {
		return this.sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}