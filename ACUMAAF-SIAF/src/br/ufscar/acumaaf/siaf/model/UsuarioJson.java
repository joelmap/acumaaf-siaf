package br.ufscar.acumaaf.siaf.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The class is mapped for Objetct Jason
 * 
 */
@XmlRootElement
public class UsuarioJson{
	
	private Integer codigousuario;
	private String nome;
	private String usuario;
	private String senha;
	private Integer tipousuario;

    public UsuarioJson() {
    }

	public Integer getCodigousuario() {
		return codigousuario;
	}

	public void setCodigousuario(Integer codigousuario) {
		this.codigousuario = codigousuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(Integer tipousuario) {
		this.tipousuario = tipousuario;
	} 
}