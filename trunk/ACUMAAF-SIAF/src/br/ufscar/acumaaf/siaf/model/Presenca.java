package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the presenca database table.
 * 
 */
@Entity
@Table(name="presenca")
public class Presenca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRESENCA_CODIGOPRESENCA_GENERATOR", sequenceName="PRESENCA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRESENCA_CODIGOPRESENCA_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigopresenca;

	@Column(length=300)
	private String observacao;

	//bi-directional many-to-one association to Sessao
    @ManyToOne
	@JoinColumn(name="codigosessao", nullable=false)
	private Sessao sessao;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="codigousuario", nullable=false)
	private Usuario usuario;

    public Presenca() {
    }

	public Integer getCodigopresenca() {
		return this.codigopresenca;
	}

	public void setCodigopresenca(Integer codigopresenca) {
		this.codigopresenca = codigopresenca;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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