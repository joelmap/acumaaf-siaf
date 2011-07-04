package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the dadoindicador database table.
 * 
 */
@Entity
@Table(name="dadoindicador")
public class Dadoindicador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DADOINDICADOR_CODIGODADOINDICADOR_GENERATOR", sequenceName="DADOINDICADOR_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DADOINDICADOR_CODIGODADOINDICADOR_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigodadoindicador;

    @Temporal( TemporalType.DATE)
	@Column(nullable=false)
	private Date data;

	@Column(length=300)
	private String observacao;

	@Column(nullable=false, length=30)
	private String valor;

	//bi-directional many-to-one association to Indicador
    @ManyToOne
	@JoinColumn(name="codigoindicador", nullable=false)
	private Indicador indicador;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="codigousuario", nullable=false)
	private Usuario usuario;

    public Dadoindicador() {
    }

	public Integer getCodigodadoindicador() {
		return this.codigodadoindicador;
	}

	public void setCodigodadoindicador(Integer codigodadoindicador) {
		this.codigodadoindicador = codigodadoindicador;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Indicador getIndicador() {
		return this.indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}