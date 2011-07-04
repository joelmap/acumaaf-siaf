package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the diasemana database table.
 * 
 */
@Entity
@Table(name="diasemana")
public class Diasemana implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DIASEMANA_CODIGODIASEMANA_GENERATOR", sequenceName="DIASEMANA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DIASEMANA_CODIGODIASEMANA_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigodiasemana;

	private Boolean domingo;

	private Boolean quarta;

	private Boolean quinta;

	private Boolean sabado;

	private Boolean segunda;

	private Boolean sexta;

	private Boolean sincronizado;

	private Boolean terca;

	//bi-directional many-to-one association to Grupoatividadefisica
	@OneToMany(mappedBy="diasemana")
	private Set<Grupoatividadefisica> grupoatividadefisicas;

    public Diasemana() {
    }

	public Integer getCodigodiasemana() {
		return this.codigodiasemana;
	}

	public void setCodigodiasemana(Integer codigodiasemana) {
		this.codigodiasemana = codigodiasemana;
	}

	public Boolean getDomingo() {
		return this.domingo;
	}

	public void setDomingo(Boolean domingo) {
		this.domingo = domingo;
	}

	public Boolean getQuarta() {
		return this.quarta;
	}

	public void setQuarta(Boolean quarta) {
		this.quarta = quarta;
	}

	public Boolean getQuinta() {
		return this.quinta;
	}

	public void setQuinta(Boolean quinta) {
		this.quinta = quinta;
	}

	public Boolean getSabado() {
		return this.sabado;
	}

	public void setSabado(Boolean sabado) {
		this.sabado = sabado;
	}

	public Boolean getSegunda() {
		return this.segunda;
	}

	public void setSegunda(Boolean segunda) {
		this.segunda = segunda;
	}

	public Boolean getSexta() {
		return this.sexta;
	}

	public void setSexta(Boolean sexta) {
		this.sexta = sexta;
	}

	public Boolean getSincronizado() {
		return this.sincronizado;
	}

	public void setSincronizado(Boolean sincronizado) {
		this.sincronizado = sincronizado;
	}

	public Boolean getTerca() {
		return this.terca;
	}

	public void setTerca(Boolean terca) {
		this.terca = terca;
	}

	public Set<Grupoatividadefisica> getGrupoatividadefisicas() {
		return this.grupoatividadefisicas;
	}

	public void setGrupoatividadefisicas(Set<Grupoatividadefisica> grupoatividadefisicas) {
		this.grupoatividadefisicas = grupoatividadefisicas;
	}
	
}