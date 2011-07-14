package br.ufscar.acumaaf.siaf.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SessaoJson {
	
	private Integer codigosessao;
	private Date data;
	private Integer codigogrupoatividadefisica;
	
	public SessaoJson() {
	}

	public Integer getCodigosessao() {
		return codigosessao;
	}

	public void setCodigosessao(Integer codigosessao) {
		this.codigosessao = codigosessao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getCodigogrupoatividadefisica() {
		return codigogrupoatividadefisica;
	}

	public void setCodigogrupoatividadefisica(Integer codigogrupoatividadefisica) {
		this.codigogrupoatividadefisica = codigogrupoatividadefisica;
	}
}
