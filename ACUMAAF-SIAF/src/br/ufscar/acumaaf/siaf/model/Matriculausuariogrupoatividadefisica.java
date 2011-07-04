package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the matriculausuariogrupoatividadefisica database table.
 * 
 */
@Entity
@Table(name="matriculausuariogrupoatividadefisica")
public class Matriculausuariogrupoatividadefisica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MATRICULAUSUARIOGRUPOATIVIDADEFISICA_CODIGOMATRICULAUSUARIOGRUPOATIVIDADEFISICA_GENERATOR", sequenceName="MATRICULAUSUARIOGRUPOATIVIDADEFISICA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MATRICULAUSUARIOGRUPOATIVIDADEFISICA_CODIGOMATRICULAUSUARIOGRUPOATIVIDADEFISICA_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigomatriculausuariogrupoatividadefisica;

	private Boolean sincronizado;

	//bi-directional many-to-one association to Grupoatividadefisica
    @ManyToOne
	@JoinColumn(name="codigogrupoatividadefisica", nullable=false)
	private Grupoatividadefisica grupoatividadefisica;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="codigousuario", nullable=false)
	private Usuario usuario;

    public Matriculausuariogrupoatividadefisica() {
    }

	public Integer getCodigomatriculausuariogrupoatividadefisica() {
		return this.codigomatriculausuariogrupoatividadefisica;
	}

	public void setCodigomatriculausuariogrupoatividadefisica(Integer codigomatriculausuariogrupoatividadefisica) {
		this.codigomatriculausuariogrupoatividadefisica = codigomatriculausuariogrupoatividadefisica;
	}

	public Boolean getSincronizado() {
		return this.sincronizado;
	}

	public void setSincronizado(Boolean sincronizado) {
		this.sincronizado = sincronizado;
	}

	public Grupoatividadefisica getGrupoatividadefisica() {
		return this.grupoatividadefisica;
	}

	public void setGrupoatividadefisica(Grupoatividadefisica grupoatividadefisica) {
		this.grupoatividadefisica = grupoatividadefisica;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}