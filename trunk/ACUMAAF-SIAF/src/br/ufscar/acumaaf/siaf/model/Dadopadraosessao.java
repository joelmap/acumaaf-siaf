package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dadopadraosessao database table.
 * 
 */
@Entity
@Table(name="dadopadraosessao")
public class Dadopadraosessao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DADOPADRAOSESSAO_CODIGODADOPADRAOSESSAO_GENERATOR", sequenceName="DADOPADRAOSESSAO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DADOPADRAOSESSAO_CODIGODADOPADRAOSESSAO_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigodadopadraosessao;

	@Column(length=300)
	private String descricao;

	private Boolean sincronizado;

	@Column(nullable=false, length=20)
	private String valormax;

	@Column(nullable=false, length=20)
	private String valormin;

	//bi-directional many-to-one association to Instrumentocoleta
    @ManyToOne
	@JoinColumn(name="codigoinstrumentocoleta", nullable=false)
	private Instrumentocoleta instrumentocoleta;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="codigousuario", nullable=false)
	private Usuario usuario;

    public Dadopadraosessao() {
    }

	public Integer getCodigodadopadraosessao() {
		return this.codigodadopadraosessao;
	}

	public void setCodigodadopadraosessao(Integer codigodadopadraosessao) {
		this.codigodadopadraosessao = codigodadopadraosessao;
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

	public String getValormax() {
		return this.valormax;
	}

	public void setValormax(String valormax) {
		this.valormax = valormax;
	}

	public String getValormin() {
		return this.valormin;
	}

	public void setValormin(String valormin) {
		this.valormin = valormin;
	}

	public Instrumentocoleta getInstrumentocoleta() {
		return this.instrumentocoleta;
	}

	public void setInstrumentocoleta(Instrumentocoleta instrumentocoleta) {
		this.instrumentocoleta = instrumentocoleta;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}