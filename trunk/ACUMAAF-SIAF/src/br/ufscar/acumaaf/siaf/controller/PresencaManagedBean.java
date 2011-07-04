package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.PresencaDAO;
import br.ufscar.acumaaf.siaf.model.Sessao;
import br.ufscar.acumaaf.siaf.model.Presenca;
import br.ufscar.acumaaf.siaf.model.Usuario;


@ManagedBean(name="presencaMB")
@ViewScoped
public class PresencaManagedBean {
	
	private Presenca presenca = new Presenca();
	private PresencaDAO presencaDao = new PresencaDAO();
	private Presenca presencaSelecionada = new Presenca();
	private List<Presenca> listaPresenca;
	private Sessao sessao = new Sessao();
	private List<Sessao> listaSessoes;
	private Usuario usuario = new Usuario();
	private List<Usuario> listaUsuarios;
	private Usuario[] listaUsuariosSelecionados;
	private boolean mostrar = false;
	
	
	// Construtor da Classe ManagedBean
	public PresencaManagedBean() {
		listaPresenca = new ArrayList<Presenca>();
		listaSessoes = new ArrayList<Sessao>();
		listaUsuarios = new ArrayList<Usuario>();
	}

	public Presenca getPresencaSelecionada() {
		return presencaSelecionada;
	}

	public void setPresencaSelecionada(
			Presenca presencaSelecionada) {
		this.presencaSelecionada = presencaSelecionada;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public List<Presenca> getListaPresenca() {
		listaPresenca =  (List<Presenca>) presencaDao.findPresencaAll();
		return listaPresenca;
	}

	public List<Sessao> getListaSessoes() {
		listaSessoes = (List<Sessao>) new br.ufscar.acumaaf.siaf.dao.SessaoDAO().findSessaoAll();
		return listaSessoes;
	}

	public List<Usuario> getListaUsuarios() {
		listaUsuarios = (List<Usuario>) new br.ufscar.acumaaf.siaf.dao.UsuarioDAO().findListaFrequencia(sessao.getCodigosessao()); 
		return listaUsuarios;
	}
	
	public Usuario[] getListaUsuariosSelecionados() {
		return listaUsuariosSelecionados;
	}

	public void setListaUsuariosSelecionados(Usuario[] listaUsuariosSelecionados) {
		this.listaUsuariosSelecionados = listaUsuariosSelecionados;
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
	
	public Presenca getPresenca() {
		return presenca;
	}

	public void setPresenca(Presenca presenca) {
		this.presenca = presenca;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	//Método chamado pela página cadastrarPresenca.xhtml para cadastrar uma nova presenca de usuários  
	//em um Grupo de Atividade Física
	public String cadastrarPresenca(){
				
		for (int i = 0; i < listaUsuariosSelecionados.length; i++) {
		
			//Instancia com os dados selecionados pelo usuário
			presenca.setUsuario((Usuario) listaUsuariosSelecionados[i]);
			presenca.setSessao(sessao);
			
			System.out.println("Código.........................:" + presenca.getCodigopresenca());
			System.out.println("Nome Usuário...................:" + presenca.getUsuario().getNome());
			System.out.println("Data da Sessão Atividade fisica:" + presenca.getSessao().getData());
			System.out.println("Observação.....................:" + presenca.getObservacao());
			System.out.println("------------------------------------------");
			
			//Persiste a presença no banco de dados
			presencaDao.save(presenca);
			presenca = null;
			presenca = new Presenca();						
		}
		return "/presenca/listarPresencas.xhtml?faces-redirect=true";
	}
	
	public void deletar(){
		
		presencaDao.delete(presencaSelecionada);
	}
	
	public String chamaPaginaGerenciaPresenca(){
		
		return "/presenca/gerenciaPresenca.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaCadastrarPresenca(){
		
		return "/presenca/cadastrarPresenca.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaListarPresencas(){
		
		return "/presenca/listarPresencas.xhtml?faces-redirect=true";
	}	
}