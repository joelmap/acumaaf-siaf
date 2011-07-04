package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuarioresponsavelgrupoatividadefisica database table.
 * 
 */
@Entity
@Table(name="usuarioresponsavelgrupoatividadefisica")
public class Usuarioresponsavelgrupoatividadefisica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIORESPONSAVELGRUPOATIVIDADEFISICA_CODIGOUSUARIORESPONSAVELGRUPOATIVIDADEFISICA_GENERATOR", sequenceName="USUARIORESPONSAVELGRUPOATIVIDADEFISICA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIORESPONSAVELGRUPOATIVIDADEFISICA_CODIGOUSUARIORESPONSAVELGRUPOATIVIDADEFISICA_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigousuarioresponsavelgrupoatividadefisica;

	@Column(length=300)
	private String observacao;

	//bi-directional many-to-one association to Grupoatividadefisica
    @ManyToOne
	@JoinColumn(name="codigogrupoatividadefisica", nullable=false)
	private Grupoatividadefisica grupoatividadefisica;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="codigousuario", nullable=false)
	private Usuario usuario;

    public Usuarioresponsavelgrupoatividadefisica() {
    }

	public Integer getCodigousuarioresponsavelgrupoatividadefisica() {
		return this.codigousuarioresponsavelgrupoatividadefisica;
	}

	public void setCodigousuarioresponsavelgrupoatividadefisica(Integer codigousuarioresponsavelgrupoatividadefisica) {
		this.codigousuarioresponsavelgrupoatividadefisica = codigousuarioresponsavelgrupoatividadefisica;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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