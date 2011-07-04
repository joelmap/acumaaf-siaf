package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.UsuarioResponsavelGrupoAtividadeFisicaDAO;
import br.ufscar.acumaaf.siaf.model.Grupoatividadefisica;
import br.ufscar.acumaaf.siaf.model.Usuario;
import br.ufscar.acumaaf.siaf.model.Usuarioresponsavelgrupoatividadefisica;


@ManagedBean(name="usuarioresponsavelgrupoatividadefisicaMB")
@ViewScoped
public class UsuarioResponsavelGrupoAtividadeFisicaManagedBean {
	
	private Usuarioresponsavelgrupoatividadefisica usuarioResponsavelGrupoAtividadeFisica = new Usuarioresponsavelgrupoatividadefisica();
	private UsuarioResponsavelGrupoAtividadeFisicaDAO usuarioResponsavelGrupoAtividadeFisicaDao = new UsuarioResponsavelGrupoAtividadeFisicaDAO();
	private Usuarioresponsavelgrupoatividadefisica usuarioResponsavelGrupoAtividadeFisicaSelecionado = new Usuarioresponsavelgrupoatividadefisica();
	private List<Usuarioresponsavelgrupoatividadefisica> listaUsuarioResponsavelGrupoAtividadeFisica;
	private Grupoatividadefisica grupoAtividadeFisica = new Grupoatividadefisica();
	private List<Grupoatividadefisica> listaGrupoAtividadeFisica;
	private Usuario usuario = new Usuario();
	private List<Usuario> listaUsuarios;
	
	
	// Construtor da Classe ManagedBean
	public UsuarioResponsavelGrupoAtividadeFisicaManagedBean() {
		listaUsuarioResponsavelGrupoAtividadeFisica = new ArrayList<Usuarioresponsavelgrupoatividadefisica>();
		listaGrupoAtividadeFisica = new ArrayList<Grupoatividadefisica>();
		listaUsuarios = new ArrayList<Usuario>();
	}
	
	public Usuarioresponsavelgrupoatividadefisica getUsuarioResponsavelGrupoAtividadeFisica() {
		return usuarioResponsavelGrupoAtividadeFisica;
	}

	public void setUsuarioResponsavelGrupoAtividadeFisica(
			Usuarioresponsavelgrupoatividadefisica usuarioResponsavelGrupoAtividadeFisica) {
		this.usuarioResponsavelGrupoAtividadeFisica = usuarioResponsavelGrupoAtividadeFisica;
	}

	public Usuarioresponsavelgrupoatividadefisica getUsuarioResponsavelGrupoAtividadeFisicaSelecionado() {
		return usuarioResponsavelGrupoAtividadeFisicaSelecionado;
	}

	public void setUsuarioResponsavelGrupoAtividadeFisicaSelecionado(
			Usuarioresponsavelgrupoatividadefisica usuarioResponsavelGrupoAtividadeFisicaSelecionado) {
		this.usuarioResponsavelGrupoAtividadeFisicaSelecionado = usuarioResponsavelGrupoAtividadeFisicaSelecionado;
	}

	public Grupoatividadefisica getGrupoAtividadeFisica() {
		return grupoAtividadeFisica;
	}

	public void setGrupoAtividadeFisica(Grupoatividadefisica grupoAtividadeFisica) {
		this.grupoAtividadeFisica = grupoAtividadeFisica;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuarioresponsavelgrupoatividadefisica> getListaUsuarioResponsavelGrupoAtividadeFisica() {
		listaUsuarioResponsavelGrupoAtividadeFisica =  (List<Usuarioresponsavelgrupoatividadefisica>) usuarioResponsavelGrupoAtividadeFisicaDao.findUsuarioResponsavelGrupoAtividadeFisicaAll();
		return listaUsuarioResponsavelGrupoAtividadeFisica;
	}

	public List<Grupoatividadefisica> getListaGrupoAtividadeFisica() {
		listaGrupoAtividadeFisica = (List<Grupoatividadefisica>) new br.ufscar.acumaaf.siaf.dao.GrupoAtividadeFisicaDAO().findGrupoAtividadeFisicaAll();
		return listaGrupoAtividadeFisica;
	}

	public List<Usuario> getListaUsuarios() {
		listaUsuarios = (List<Usuario>) new br.ufscar.acumaaf.siaf.dao.UsuarioDAO().findUserAll(); 
		return listaUsuarios;
	}

	//Método chamado pela página cadastrarUsuarioResponsavelGrupoAtividadeFisica.xhtml para cadastrar um novo vínculo entre um Usuário responsável 
	//e um Grupo de Atividade Física
	public String cadastrarUsuarioResponsavelGrupoAtividadeFisica(){
		
		//O usuário que foi selecionado no banco de dados 
		System.out.println("Codigo: " + usuario.getCodigousuario());
		System.out.println("Nome..: " + usuario.getNome());
		
		//Grupo de Atividade Física que foi selecionado no banco de dados
		System.out.println("Codigo: " + grupoAtividadeFisica.getCodigogrupoatividadefisica());
		System.out.println("Nome..: " + grupoAtividadeFisica.getNome());
		
		//Instancia com os dados selecionados pelo usuário
		usuarioResponsavelGrupoAtividadeFisica.setUsuario(usuario);
		usuarioResponsavelGrupoAtividadeFisica.setGrupoatividadefisica(grupoAtividadeFisica);
				
		System.out.println("Código...........................:" + usuarioResponsavelGrupoAtividadeFisica.getCodigousuarioresponsavelgrupoatividadefisica());
		System.out.println("Código Usuário...................:" + usuarioResponsavelGrupoAtividadeFisica.getUsuario().getNome());
		System.out.println("Código Grupo de atividade fisica.:" + usuarioResponsavelGrupoAtividadeFisica.getGrupoatividadefisica().getNome());
		System.out.println("Observação.......................:" + usuarioResponsavelGrupoAtividadeFisica.getObservacao());
		
		//Persiste a associação no banco de dados
		usuarioResponsavelGrupoAtividadeFisicaDao.save(usuarioResponsavelGrupoAtividadeFisica);
		
		return "/usuarioresponsavelgrupoatividadefisica/listarVinculosUsuarioResponsavelGrupoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public void deletar(){
		
		usuarioResponsavelGrupoAtividadeFisicaDao.delete(usuarioResponsavelGrupoAtividadeFisicaSelecionado);
	}
	
	public String chamaPaginaGerenciaVinculoUsuarioResponsavelGrupoAtividadeFisica(){
		
		return "/usuarioresponsavelgrupoatividadefisica/gerenciaVinculoUsuarioResponsavelGrupoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaCadastrarVinculoUsuarioResponsavelGrupoAtividadeFisica(){
		
		return "/usuarioresponsavelgrupoatividadefisica/cadastrarVinculoUsuarioResponsavelGrupoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaListarVinculosUsuarioResponsavelGrupoAtividadeFisica(){
		
		return "/usuarioresponsavelgrupoatividadefisica/listarVinculosUsuarioResponsavelGrupoAtividadeFisica.xhtml?faces-redirect=true";
	}	
}