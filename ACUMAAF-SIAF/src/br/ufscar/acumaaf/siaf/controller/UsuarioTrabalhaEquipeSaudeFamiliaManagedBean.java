package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.UsuarioTrabalhaEquipeSaudeFamiliaDAO;
import br.ufscar.acumaaf.siaf.model.Equipesaudefamilia;
import br.ufscar.acumaaf.siaf.model.Usuario;
import br.ufscar.acumaaf.siaf.model.Usuariotrabalhaequipesaudefamilia;

@ManagedBean(name="usuariotrabalhaequipesaudefamiliaMB")
@ViewScoped
public class UsuarioTrabalhaEquipeSaudeFamiliaManagedBean {
	
	private Usuariotrabalhaequipesaudefamilia usuarioTrabalhaEquipeSaudeFamilia = new Usuariotrabalhaequipesaudefamilia();
	private UsuarioTrabalhaEquipeSaudeFamiliaDAO usuarioTrabalhaEquipeSaudeDAO = new UsuarioTrabalhaEquipeSaudeFamiliaDAO();
	private List<Usuariotrabalhaequipesaudefamilia> listaUsuarioTrabalhaEquipeSaudeFamilia;
	private Usuario usuario = new Usuario();
	private List<Usuario> listaUsuarios;
	private Equipesaudefamilia equipeSaudeFamilia = new Equipesaudefamilia();
	private List<Equipesaudefamilia> listaEquipesSaudeFamilia;
	private Usuariotrabalhaequipesaudefamilia usuarioTrabalhaEquipeSaudeSelecionado = new Usuariotrabalhaequipesaudefamilia();
	
	// Construtor da Classe ManagedBean
	public UsuarioTrabalhaEquipeSaudeFamiliaManagedBean() {
		listaUsuarioTrabalhaEquipeSaudeFamilia = new ArrayList<Usuariotrabalhaequipesaudefamilia>();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public Usuariotrabalhaequipesaudefamilia getUsuarioTrabalhaEquipeSaudeFamilia() {
		return usuarioTrabalhaEquipeSaudeFamilia;
	}

	public void setUsuarioTrabalhaEquipeSaudeFamilia(Usuariotrabalhaequipesaudefamilia usuarioTrabalhaEquipeSaudeFamilia) {
		this.usuarioTrabalhaEquipeSaudeFamilia = usuarioTrabalhaEquipeSaudeFamilia;
	}

	public List<Usuariotrabalhaequipesaudefamilia> getListaUsuarioTrabalhaEquipeSaudeFamilia() {
		listaUsuarioTrabalhaEquipeSaudeFamilia = (List<Usuariotrabalhaequipesaudefamilia>)usuarioTrabalhaEquipeSaudeDAO.findUsuarioTrabalhaEquipeSaudeFamiliaAll();
		return listaUsuarioTrabalhaEquipeSaudeFamilia;
	}

	public void setListaUsuarioTrabalhaEquipeSaudeFamilia(List<Usuariotrabalhaequipesaudefamilia> listaUsuarioTrabalhaEquipeSaudeFamilia) {
		this.listaUsuarioTrabalhaEquipeSaudeFamilia = listaUsuarioTrabalhaEquipeSaudeFamilia;
	}

	public Equipesaudefamilia getEquipeSaudeFamilia() {
		return equipeSaudeFamilia;
	}

	public void setEquipeSaudeFamilia(Equipesaudefamilia equipeSaudeFamilia) {
		this.equipeSaudeFamilia = equipeSaudeFamilia;
	}

	public List<Equipesaudefamilia> getListaEquipesSaudeFamilia() {
		listaEquipesSaudeFamilia = (List<Equipesaudefamilia>) new br.ufscar.acumaaf.siaf.dao.EquipeSaudeFamiliaDAO().findEquipeSaudeFamiliaAll(); 
		return listaEquipesSaudeFamilia;
	}

	public void setListaEquipesSaudeFamilia(List<Equipesaudefamilia> listaEquipesSaudeFamilia) {
		this.listaEquipesSaudeFamilia = listaEquipesSaudeFamilia;
	}

	public Usuariotrabalhaequipesaudefamilia getUsuarioTrabalhaEquipeSaudeSelecionado() {
		return usuarioTrabalhaEquipeSaudeSelecionado;
	}

	public void setUsuarioTrabalhaEquipeSaudeSelecionado(Usuariotrabalhaequipesaudefamilia usuarioTrabalhaEquipeSaudeSelecionado) {
		this.usuarioTrabalhaEquipeSaudeSelecionado = usuarioTrabalhaEquipeSaudeSelecionado;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Usuario> getListaUsuarios() {
		listaUsuarios = (List<Usuario>) new br.ufscar.acumaaf.siaf.dao.UsuarioDAO().findUserAll();
		return listaUsuarios;
	}


	//Método chamado pela página cadastrarVinculoUsuarioEquipeSaudeFamilia.xhtml para cadastrar um novo vínculo entre um Usuário 
	//e a Equipe de Saúde da Família
	public String cadastrarVinculoProfissionalEquipeSaudeFamilia(){
		
		//O usuário que foi selecionado no banco de dados 
		System.out.println("Codigo: " + usuario.getCodigousuario());
		System.out.println("Nome..: " + usuario.getNome());
		
		//A Equipe de Saúde da Família que foi selecionado no banco de dados
		System.out.println("Codigo: " + equipeSaudeFamilia.getCodigoequipesaudefamilia());
		System.out.println("Nome..: " + equipeSaudeFamilia.getNome());
		
		//Instancia com os dados selecionados pelo usuário
		usuarioTrabalhaEquipeSaudeFamilia.setUsuario(usuario);
		usuarioTrabalhaEquipeSaudeFamilia.setEquipesaudefamilia(equipeSaudeFamilia);
				
		System.out.println("Código...........................:" + usuarioTrabalhaEquipeSaudeFamilia.getCodigousuariotrabalhaequipesaudefamilia());
		System.out.println("Código Usuário...................:" + usuarioTrabalhaEquipeSaudeFamilia.getUsuario().getNome());
		System.out.println("Código Equipe de Saúde da Família:" + usuarioTrabalhaEquipeSaudeFamilia.getEquipesaudefamilia().getNome());
		System.out.println("Nome do cargo....................:" + usuarioTrabalhaEquipeSaudeFamilia.getNomecargo());
		System.out.println("Observação.......................:" + usuarioTrabalhaEquipeSaudeFamilia.getObservacao());
		
		//Persiste a associação no banco de dados
		usuarioTrabalhaEquipeSaudeDAO.save(usuarioTrabalhaEquipeSaudeFamilia);
		
		return "/usuariotrabalhaunidadesaude/listarVinculosUsuarioEquipeSaudeFamilia.xhtml?faces-redirect=true";
	}
	
	public void deletar(){
		
		System.out.println("DEL - Código...........................:" + usuarioTrabalhaEquipeSaudeSelecionado .getCodigousuariotrabalhaequipesaudefamilia());
		System.out.println("DEL - Código Usuário...................:" + usuarioTrabalhaEquipeSaudeSelecionado.getUsuario());
		System.out.println("DEL - Código Equipe de Saúde da Família:" + usuarioTrabalhaEquipeSaudeSelecionado.getEquipesaudefamilia());
		System.out.println("DEL - Nome do cargo....................:" + usuarioTrabalhaEquipeSaudeSelecionado.getNomecargo());
		System.out.println("DEL - Observação.......................:" + usuarioTrabalhaEquipeSaudeSelecionado.getObservacao());
		
		usuarioTrabalhaEquipeSaudeDAO.delete(usuarioTrabalhaEquipeSaudeSelecionado);
	}
	
	public String chamaPaginaGerenciaVinculoUsuarioEquipeSaudeFamilia(){
		
		return "/usuariotrabalhaunidadesaude/gerenciaVinculoUsuarioEquipeSaudeFamilia.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaCadastrarVinculoUsuarioEquipeSaudeFamilia(){
		
		return "/usuariotrabalhaunidadesaude/cadastrarVinculoUsuarioEquipeSaudeFamilia.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaListarVinculosUsuarioEquipeSaudeFamilia(){
		
		return "/usuariotrabalhaunidadesaude/listarVinculosUsuarioEquipeSaudeFamilia.xhtml?faces-redirect=true";
	}	
}