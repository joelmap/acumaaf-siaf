package br.ufscar.acumaaf.siaf.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GrupoatividadefisicaJson {

	private Integer codigogrupoatividadefisica;
	private String horainicio;
	private String horafim;
	private String nome;
	
	public GrupoatividadefisicaJson() {
	}

	public Integer getCodigogrupoatividadefisica() {
		return codigogrupoatividadefisica;
	}

	public void setCodigogrupoatividadefisica(Integer codigogrupoatividadefisica) {
		this.codigogrupoatividadefisica = codigogrupoatividadefisica;
	}

	public String getHorainicio() {
		return horainicio;
	}

	public void setHorainicio(String horainicio) {
		this.horainicio = horainicio;
	}

	public String getHorafim() {
		return horafim;
	}

	public void setHorafim(String horafim) {
		this.horafim = horafim;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
