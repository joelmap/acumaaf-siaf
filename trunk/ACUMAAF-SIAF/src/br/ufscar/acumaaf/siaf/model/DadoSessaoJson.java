package br.ufscar.acumaaf.siaf.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DadoSessaoJson {
	
	private Integer codigoSessao;
	private Integer codigoUsuario;
	private Integer codigoInstrumentoColeta;
	private String valor;
	
	public DadoSessaoJson() {
	}

	public Integer getCodigoSessao() {
		return codigoSessao;
	}

	public void setCodigoSessao(Integer codigoSessao) {
		this.codigoSessao = codigoSessao;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Integer getCodigoInstrumentoColeta() {
		return codigoInstrumentoColeta;
	}

	public void setCodigoInstrumentoColeta(Integer codigoInstrumentoColeta) {
		this.codigoInstrumentoColeta = codigoInstrumentoColeta;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
