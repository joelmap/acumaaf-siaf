package br.ufscar.acumaaf.siaf.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MatriculausuariogrupoatividadefisicaJson {
	
	private Integer codigomatriculausuariogrupoatividadefisica;
	private Integer codigoGrupoAtividadeFisica;
	private Integer codigoUsuario;

    public MatriculausuariogrupoatividadefisicaJson() {
    }

	public Integer getCodigomatriculausuariogrupoatividadefisica() {
		return codigomatriculausuariogrupoatividadefisica;
	}

	public void setCodigomatriculausuariogrupoatividadefisica(
			Integer codigomatriculausuariogrupoatividadefisica) {
		this.codigomatriculausuariogrupoatividadefisica = codigomatriculausuariogrupoatividadefisica;
	}

	public Integer getCodigoGrupoAtividadeFisica() {
		return codigoGrupoAtividadeFisica;
	}

	public void setCodigoGrupoAtividadeFisica(Integer codigoGrupoAtividadeFisica) {
		this.codigoGrupoAtividadeFisica = codigoGrupoAtividadeFisica;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}    
}