package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.MatriculaUsuarioGrupoAtividadeFisicaDAO;
import br.ufscar.acumaaf.siaf.model.Grupoatividadefisica;
import br.ufscar.acumaaf.siaf.model.Matriculausuariogrupoatividadefisica;
import br.ufscar.acumaaf.siaf.model.Usuario;


@ManagedBean(name="matriculausuariogrupoatividadefisicaMB")
@ViewScoped
public class MatriculaUsuarioGrupoAtividadeFisicaManagedBean {
	
	private Matriculausuariogrupoatividadefisica matriculaUsuarioGrupoAtividadeFisica = new Matriculausuariogrupoatividadefisica();
	private Matriculausuariogrupoatividadefisica matriculaUsuarioGrupoAtividadeFisicaSelecionada = new Matriculausuariogrupoatividadefisica();
	private MatriculaUsuarioGrupoAtividadeFisicaDAO matriculaUsuarioGrupoAtividadeFisicaDao = new MatriculaUsuarioGrupoAtividadeFisicaDAO();
	private List<Matriculausuariogrupoatividadefisica> listaMatriculaUsuarioGrupoAtividadeFisica;
	
	private Grupoatividadefisica grupoAtividadeFisica = new Grupoatividadefisica();
	private List<Grupoatividadefisica> listaGrupoAtividadeFisica;
	
	private Usuario usuario = new Usuario();
	private List<Usuario> listaUsuarios;
	
		
	// Construtor da Classe ManagedBean
	public MatriculaUsuarioGrupoAtividadeFisicaManagedBean() {
		listaMatriculaUsuarioGrupoAtividadeFisica = new ArrayList<Matriculausuariogrupoatividadefisica>();
		listaGrupoAtividadeFisica = new ArrayList<Grupoatividadefisica>();
		listaUsuarios = new ArrayList<Usuario>();
	}
	
	public Matriculausuariogrupoatividadefisica getMatriculaUsuarioGrupoAtividadeFisica() {
		return matriculaUsuarioGrupoAtividadeFisica;
	}

	public void setMatriculaUsuarioGrupoAtividadeFisica(
			Matriculausuariogrupoatividadefisica matriculaUsuarioGrupoAtividadeFisica) {
		this.matriculaUsuarioGrupoAtividadeFisica = matriculaUsuarioGrupoAtividadeFisica;
	}

	public Matriculausuariogrupoatividadefisica getMatriculaUsuarioGrupoAtividadeFisicaSelecionada() {
		return matriculaUsuarioGrupoAtividadeFisicaSelecionada;
	}

	public void setMatriculaUsuarioGrupoAtividadeFisicaSelecionada(
			Matriculausuariogrupoatividadefisica matriculaUsuarioGrupoAtividadeFisicaSelecionada) {
		this.matriculaUsuarioGrupoAtividadeFisicaSelecionada = matriculaUsuarioGrupoAtividadeFisicaSelecionada;
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

	public List<Matriculausuariogrupoatividadefisica> getListaMatriculaUsuarioGrupoAtividadeFisica() {
		listaMatriculaUsuarioGrupoAtividadeFisica = (List<Matriculausuariogrupoatividadefisica>) matriculaUsuarioGrupoAtividadeFisicaDao.findMatriculaUsuarioGrupoAtividadeFisicaAll(); 
		return listaMatriculaUsuarioGrupoAtividadeFisica;
	}

	public List<Grupoatividadefisica> getListaGrupoAtividadeFisica() {
		listaGrupoAtividadeFisica = (List<Grupoatividadefisica>) new br.ufscar.acumaaf.siaf.dao.GrupoAtividadeFisicaDAO().findGrupoAtividadeFisicaAll(); 
		return listaGrupoAtividadeFisica;
	}

	public List<Usuario> getListaUsuarios() {
		listaUsuarios = (List<Usuario>) new br.ufscar.acumaaf.siaf.dao.UsuarioDAO().findUserAll(); 
		return listaUsuarios;
	}

	//Método chamado pela página cadastraMatriculaUsuarioGrupoAtividadeFisica.xhtml para matricular um usuário em um grupo de atividade física
	public String cadastrarMatriculaUsuarioGrupoAtividadeFisica(){
		
		System.out.println("Codigo: " + usuario.getCodigousuario());
		System.out.println("Nome..: " + usuario.getNome());
		
		System.out.println("Codigo: " + grupoAtividadeFisica.getCodigogrupoatividadefisica());
		System.out.println("Nome..: " + grupoAtividadeFisica.getNome());
		
		//Sempre configura o cadastro e as atualizações para não sincronizado (false). 
		//Desta forma o SMC sincronizará os dados e manterá as tabelas "matriculausuariogrupoatividadefisica" do SIAF e do SMC consistentes
		matriculaUsuarioGrupoAtividadeFisica.setSincronizado(false);
		
		//Instancia com os dados selecionados
		matriculaUsuarioGrupoAtividadeFisica.setUsuario(usuario);
		matriculaUsuarioGrupoAtividadeFisica.setGrupoatividadefisica(grupoAtividadeFisica);
		
		System.out.println("Código.................:" + matriculaUsuarioGrupoAtividadeFisica.getCodigomatriculausuariogrupoatividadefisica());
		System.out.println("nome do Usuário........:" + matriculaUsuarioGrupoAtividadeFisica.getUsuario().getNome());
		System.out.println("Grupo Atividade Fisica.:" + matriculaUsuarioGrupoAtividadeFisica.getGrupoatividadefisica().getNome());
		System.out.println("Sincronizado?..........:" + matriculaUsuarioGrupoAtividadeFisica.getSincronizado());
		
		//Persiste a associação no banco de dados
		matriculaUsuarioGrupoAtividadeFisicaDao.save(matriculaUsuarioGrupoAtividadeFisica);
		
		return "/matriculausuariogrupoatividadefisica/listarMatriculasUsuarioGrupoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public void deletar(){
		
		matriculaUsuarioGrupoAtividadeFisicaDao.delete(matriculaUsuarioGrupoAtividadeFisicaSelecionada);		
	}
	
	public String chamaPaginaGerenciaMatriculaUsuarioGrupoAtividadeFisica(){
		
		return "/matriculausuariogrupoatividadefisica/gerenciaMatriculaUsuarioGrupoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaCadastrarMatriculaUsuarioGrupoAtividadeFisica(){
		
		return "/matriculausuariogrupoatividadefisica/cadastrarMatriculaUsuarioGrupoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaListarMatriculasUsuarioGrupoAtividadeFisica(){
		
		return "/matriculausuariogrupoatividadefisica/listarMatriculasUsuarioGrupoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
}
