package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vinculousuariounidadedesaude database table.
 * 
 */
@Entity
@Table(name="vinculousuariounidadedesaude")
public class Vinculousuariounidadedesaude implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VINCULOUSUARIOUNIDADEDESAUDE_CODIGOVINCULO_GENERATOR", sequenceName="VINCULOUSUARIOUNIDADEDESAUDE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VINCULOUSUARIOUNIDADEDESAUDE_CODIGOVINCULO_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigovinculo;

	private Boolean sincronizado;

	//bi-directional many-to-one association to Unidadesaude
    @ManyToOne
	@JoinColumn(name="codigounidadesaude", nullable=false)
	private Unidadesaude unidadesaude;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="codigousuario", nullable=false)
	private Usuario usuario;

    public Vinculousuariounidadedesaude() {
    }

	public Integer getCodigovinculo() {
		return this.codigovinculo;
	}

	public void setCodigovinculo(Integer codigovinculo) {
		this.codigovinculo = codigovinculo;
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
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}