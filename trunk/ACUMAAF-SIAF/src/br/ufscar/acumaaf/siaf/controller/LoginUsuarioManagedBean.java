package br.ufscar.acumaaf.siaf.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.ufscar.acumaaf.siaf.dao.UsuarioDAO;
import br.ufscar.acumaaf.siaf.model.Usuario;

@ManagedBean (name="loginusuarioMB")
@SessionScoped
public class LoginUsuarioManagedBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private boolean autenticou = false;
	
	// Construtor da Classe ManagedBean
	public LoginUsuarioManagedBean() {
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public String autentica(){
		
		FacesMessage msg = null;
		
		//Consulta no banco de dados se existe algum usuário e senha que confere com os
		//os dados digitados na tela de autenticação
		autenticou = usuarioDAO.checaUsuarioSenha(usuario.getUsuario(), usuario.getSenha()); 
		
		//Se recebeu true da consulta no banco de dados
		if(autenticou){
			usuario = (Usuario) usuarioDAO.findUserByUser(usuario.getUsuario());
			return "/home.xhtml?faces-redirect=true";
		}
		else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha na Autenticação! Usuário e/ou senha incorreto(s).","");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "#";			
		}	
	}
	
	public void logout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(session != null){
			session.invalidate();
		}
		System.out.println("Chamou Logout");
	}
	
}
